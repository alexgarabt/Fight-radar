import DataStructure.*;
import DataStructure.Data.*;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;

/**
 * Implementation of the second part of the practice.
 * Main class is in charge to interact with the user of the program,
 * get the inputs, read files, create structures and run the main program.
 *
 * @see DataStructure.Data.Id
 * @see DataStructure.Data.Position
 * @see DataStructure.Data.Neighbours
 * @see DataStructure.Data.Group
 * @see DataStructure.DataUser
 * @see DataStructure.Move
 */
public class Main {

    /**
     * Reads the initial file that has the users information.
     *
     * @param filePaht File path.
     * @param numUsers Number of Users.
     * @return A Map with the numberUser DataUsers reads from the file.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static HashMap<Id,DataUser> readInitFile(String filePaht, int numUsers) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(filePaht)));
        HashMap<Id,DataUser> userList = new HashMap<>(numUsers);
        for (int i = 0; i <numUsers; i++) {
            DataUser user = new DataUser(br.readLine());
            userList.put(user.getId(),user);
        }
        return userList;
    }

    /**
     * Reads a file with the users moves.
     *
     * @param pathFile Path of the file with the moves.
     * @param numberMoves Number of moves, is equal to the number of users.
     * @return A list with numberMoves Moves.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static LinkedList<Move> LeeFicheroMovs(String pathFile, int numberMoves) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(pathFile)));
        LinkedList<Move> moves = new LinkedList<>();
        for (int i = 0; i < numberMoves; i++) {
            moves.add(new Move(br.readLine()));
        }
        return moves;
    }

    /**
     * Search for the user in the Map with the given unique id identifier.
     * <p>Efficiency: O(1).
     *
     * @param userList Map of users. key=Id of the user, value=DataUser of the user.
     * @param userId Identifier of the user.
     * @return The user with these Id.
     */
    public static DataUser search(HashMap<Id, DataUser> userList, Id userId) {
        if(!userList.containsKey(Id)){throw new Error("No existe ningún usuario con ese identificador");}
        return userList.get(Id);
    }
}
