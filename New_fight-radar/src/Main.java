import DataStructure.*;
import DataStructure.Data.*;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * Return the users that are in the control zone of the given "user";
     * <p>Efficiency: O(n)
     *
     * @param user The given user.
     * @param userList A map of all the users.
     * @return A Neighbours object with the users in the control zone.
     */
    public static Neighbours getNeighbours(DataUser user, HashMap<Id,DataUser> userList) {
        ArrayList<String> vecs = new ArrayList<>();
        Neighbours neighbours = new Neighbours();
        DataUser userTmp = null;
        //Iterate the Map
        for(Map.Entry<Id,DataUser> set: userList.entrySet()) {// n (Iteraciones).
            userTmp = set.getValue();
            if (user.inZone(userTmp)) {
                neighbours.add(userTmp.getId());//O(1)
            }
        }
        return neighbours;
    }








    /**
     * Creates the correct message for each case of move.
     * @param user the user that has moved.
     * @param userNeighbour the neighbour that enters or exits.
     * @param typeNeighbour true==(if the neighbour enters), false==(if the neighbour exits)
     * @return The correct message.
     */
    public String message(DataUser user, DataUser userNeighbour, boolean typeNeighbour){
        String message =user.getId().toString();

        if(typeNeighbour){//The userNeighbour enters
            message=message+"+";
            if(user.getGroup().equals(userNeighbour)) message=message+"=";//Same group.
            else message=message+"/";//Different group.
        }
        else message=message+"-";//The userNeighbour exits.

        message=message+userNeighbour.getId().toString();
        return message;
    }

    public void main(String[] args){
        Id id1 = new Id("hola");
        Id id2 = new Id("hola");
        Id id3 = new Id("adios");

        Neighbours neighbours = new Neighbours();
        neighbours.add(id1);
        System.out.println(neighbours.isNeighbour(id1));
    }


}
