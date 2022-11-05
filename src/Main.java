import DataStructure.ListsUser.SimpleUserList;
import DataStructure.ListsUser.UserList;
import DataStructure.UserData.*;
import FileProcess.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main is the launcher class and contais the methods to interact with the user and simulate the program.
 * @see FileProcess.ReadFile
 * @see FileProcess.SimpleListCreator
 * @see FileProcess.UserListCreator
 * @see DataStructure.UserData.DataSimple
 * @see DataStructure.UserData.DataUser
 * @see DataStructure.ListsUser.SimpleUserList
 * @see DataStructure.ListsUser.UserList
 * @see DataStructure.Data.Id
 * @see DataStructure.Data.Position
 * @see DataStructure.Data.Group
 * @see DataStructure.Data.Neighbours
 */
public class Main {


    /**
     * Laucher method.
     * @param args
     */
    public static void main(String[] args) {

        //Get Initial file.
        Scanner in = new Scanner(System.in);
        System.out.print("Fichero de estado: ");
        String inicFileName = in.nextLine();
        String[] pathDivide = inicFileName.split("/");

        //modules
        //is a relative path
        if (pathDivide.length==1){
            inicFileName = "../"+inicFileName;
        }

        String[] infoFileName=pathDivide[pathDivide.length-1].split("_");
        System.out.println("Parámetros: n = "+infoFileName[1]+", "+"d = "+infoFileName[2]);

        ReadFile readFileInic = new ReadFile(inicFileName);
        UserListCreator userListCreator = new UserListCreator(readFileInic.getFileInfo());
        UserList userList = userListCreator.getUsersList();

        String option = modeElection();

        if(option.equalsIgnoreCase("M")){

            System.out.print("Numero de ciclos: ");
            int numCycles = in.nextInt();
            System.out.println("Procesando...");
            medition(userList,numCycles);
        }

        else if (option.equalsIgnoreCase("D")) {
            System.out.print("Fichero de movimientos: ");
            String movsFilename = in.nextLine();

            ReadFile readFileMovs = new ReadFile(movsFilename);
            SimpleListCreator simpleListCreator = new SimpleListCreator(readFileMovs.getFileInfo());
            SimpleUserList movsList = simpleListCreator.getUsersList();

            System.out.println("Procesando...");
            debugging(userList,movsList);
        }
        in.close();
    }

    /**
     * Run the program in mode debuggin with the given parameters.
     * @param userList, Initial list with the users information.
     * @param movsList, List of the movements.
     */
    private static void debugging(UserList userList, SimpleUserList movsList){
        ListComparator listComparator = new ListComparator(userList,movsList);
        listComparator.compareListsDebugging();
    }

    /**
     * Run the program in medition for numOfCycles cycles.
     * @param userList, list with the users information.
     * @param numOfCycles
     */
    private static void medition(UserList userList, int numOfCycles){
        double maxRand =0.5;
        double minRand=-0.5;
        double[] cycleTimes = new double[numOfCycles];
        long timeNanoSeconds;
        double timeSeconds;

        SimpleUserList simpleList = userList.getSimpleUserList();
        ArrayList<DataSimple> infoList;

        UserList userListTemp = new UserList();
        for(int i=0;i<numOfCycles;i++){

            //New object because ListComparator.getTypeNeighbours edits the oldList
            userListTemp.setUsersList((ArrayList<DataUser>) userList.getUsersList().clone());

            //Create the moves random
            infoList = simpleList.getUsersList();
            for(DataSimple userSimple: infoList){
                userSimple.setPosition(userSimple.getPosition().getRandomize(maxRand,minRand));
            }
            SimpleUserList movsList =new SimpleUserList();
            movsList.setUsersList(infoList);

            ListComparator listComparator = new ListComparator(userListTemp,movsList);

            timeNanoSeconds = listComparator.compareListsMedition();
            timeNanoSeconds = timeNanoSeconds/100000;
            timeSeconds = ((double) (timeNanoSeconds))/10000;

            cycleTimes[i]=timeSeconds;
            System.out.println(timeSeconds+"s");
        }
        double total=0;
        for(double i: cycleTimes)total=total+i;
        double averageTime = total/numOfCycles;
        System.out.println("Promedio = "+averageTime+"s");

    }

    /**
     * Let choose on of the possible modes of the program.
     * @return A valid mode election
     */
    private static String modeElection(){
        Scanner cad = new Scanner(System.in);
        String modeElection;

        while(true){
            System.out.print("Modo[D]epuración o [M]edición? ");
            modeElection = cad.nextLine();
            if(modeElection.equalsIgnoreCase("M") || modeElection.equalsIgnoreCase("D"))break;
        }
        return modeElection;
    }
}