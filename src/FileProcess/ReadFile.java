package FileProcess;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.*;

/**
 * This class read the information of a file
 * Function getFileInfo() returns the information in a String Array.
 * @version /06/10/2022/
 */
public class ReadFile {
    private String fileName;

    public ReadFile(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList getFileInfo() {
        File file = new File(fileName);
        Scanner open;
        try {
            open = new Scanner(file);
            ArrayList<String> listInfo = new ArrayList<>();

            while (open.hasNextLine()) listInfo.add(open.nextLine());
            open.close();
            return listInfo;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Error");
        return null;
    }
}
