
import DataStructure.ListsUser.SimpleUserList;
import DataStructure.ListsUser.UserList;
import FileProcess.*;
import java.util.Scanner;

public class Main {
    private DataStructure.ListsUser.SimpleUserList SimpleUserList;
    private DataStructure.ListsUser.UserList UserList;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Fichero de estado: ");
        String inicFileName = in.nextLine();
        String[] pathDivide = inicFileName.split("/");

        String[] infoFileName=pathDivide[pathDivide.length-1].split("_");
        System.out.println("Parámetros: n = "+infoFileName[1]+", "+"d = "+infoFileName[2]);


        System.out.print("Modo[D]epuración o [M]edición? ");

        String modeElection = in.nextLine();
        Boolean mode=true;// mode == true carry the application to "Depuracion" mode.
                          // mode == false carry the application to "Medicion mode.
        if(modeElection.equalsIgnoreCase("D"))mode = true;
        else if (modeElection.equalsIgnoreCase("M")) {
            mode = false;
        }

        System.out.print("Fichero de movimientos: ");
        String movsFilename = in.nextLine();
        System.out.println("Procesando...");


        ReadFile readFileInic = new ReadFile(inicFileName);
        ReadFile readFileMovs = new ReadFile(movsFilename);

        UserListCreator userListCreator = new UserListCreator(readFileInic.getFileInfo());
        SimpleListCreator simpleListCreator = new SimpleListCreator(readFileMovs.getFileInfo());

        UserList userList = userListCreator.getUsersList();
        SimpleUserList movsList = simpleListCreator.getUsersList();

        if(mode==true){
            debugging(userList,movsList);
        }
        if(mode==false){
            medition(userList,movsList);
        }

    }
    private static void debugging(UserList userList, SimpleUserList movsList){
        ListComparator listComparator = new ListComparator(userList,movsList);
        listComparator.compareListsDebugging();
    }

    private static void medition(UserList userList, SimpleUserList movsList){
        //Prototype
        ListComparator listComparator = new ListComparator(userList,movsList);
        long time = listComparator.compareListsMedition();
        System.out.println(time);
    }
}