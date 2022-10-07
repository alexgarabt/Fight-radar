import DataStructure.*;
import FileProcess.*;
import java.util.Scanner;

public class Main {
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
        String movsFilePath = in.nextLine();
        System.out.println("Procesando...");

        if(mode==true){
            debugging(inicFileName,movsFilePath);
        }

    }
    private static void debugging(String inicFileName, String movsFilename){
        ReadFile readFileInic = new ReadFile(inicFileName);
        ReadFile readFileMovs = new ReadFile(movsFilename);
        //problemas con la clase readFileInic devuelve listas vacios

        UserListCreator userListCreator = new UserListCreator(readFileInic.getFileInfo());
        SimpleListCreator simpleListCreator = new SimpleListCreator(readFileMovs.getFileInfo());
        ListComparator listComparator = new ListComparator(userListCreator.getUsersList(), simpleListCreator.getUsersList());
        listComparator.compareLists();

    }
}