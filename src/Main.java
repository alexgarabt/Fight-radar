
import DataStructure.ListsUser.SimpleUserList;
import DataStructure.ListsUser.UserList;
import DataStructure.UserData.*;
import FileProcess.*;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Fichero de estado: ");
        String inicFileName = in.nextLine();
        String[] pathDivide = inicFileName.split("/");

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
            System.out.println("Procesando...");

            ReadFile readFileMovs = new ReadFile(movsFilename);
            SimpleListCreator simpleListCreator = new SimpleListCreator(readFileMovs.getFileInfo());
            SimpleUserList movsList = simpleListCreator.getUsersList();

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
        long[] cycleTimes = new long[numOfCycles];

        SimpleUserList simpleList = userList.getSimpleUserList();
        for(int i=0;i<numOfCycles;i++){
            //Create the moves random
            ArrayList<DataSimple> infoList = simpleList.getUsersList();
            for(DataSimple userSimple: infoList){
                userSimple.setPosition(userSimple.getPosition().getRandomize(maxRand,minRand));
            }
            SimpleUserList movsList =new SimpleUserList();
            movsList.setUsersList(infoList);

            ListComparator listComparator = new ListComparator(userList,movsList);
            long time = listComparator.compareListsMedition();

            cycleTimes[i]=time;
            System.out.println(time);
        }
        long total=0;
        for(long i: cycleTimes)total=total+i;
        long averageTime = total/numOfCycles;
        System.out.println("Promedio= "+averageTime);

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