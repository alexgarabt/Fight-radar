import DataStructure.*;
import DataStructure.Data.*;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

/**
 * Implementation of the second part of the practice.
 * Main class is in charge to interact with the user of the program,
 * get the inputs, read files, create structures and run the main program.
 *
 * List of users --> HashMap<StringId,DataUser>
 * List of moves --> LinkedList<Moves>
 * List of message --> LinkedList<String>
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
     * Reads the initial file that has information about users.
     *
     * @param filePaht File path.
     * @param numUsers Number of Users.
     * @return A Map with the numberUser DataUsers reads from the file.
     * Map<StringId,DataUser>.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static HashMap<String,DataUser> readInitFile(String filePaht, int numUsers) throws FileNotFoundException, IOException {

        BufferedReader br = new BufferedReader(new FileReader(new File(filePaht)));
        HashMap<String,DataUser> userList = new HashMap<>(numUsers);
        for (int i = 0; i <numUsers; i++) {
            DataUser user = new DataUser(br.readLine());
            userList.put(user.getId().getId(),user);
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
    public static LinkedList<Move> LeeFicheroMovs(String pathFile,
                                                  int numberMoves) throws FileNotFoundException, IOException {
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
    public static DataUser search(HashMap<String,DataUser> userList, Id userId) {
        String stringId = userId.getId();
        if(!userList.containsKey(stringId)){throw new Error("No existe ningún usuario con ese identificador");}
        return userList.get(stringId);
    }

    /**
     * Return the users that are in the control zone of the given "user";
     * <p>Efficiency: O(n)
     *
     * @param user The given user.
     * @param userList A map of all the users.
     * @return A Neighbours object with the users in the control zone.
     */
    public static Neighbours getNeighbours(DataUser user, HashMap<String,DataUser> userList) {
        Neighbours neighbours = new Neighbours();
        DataUser userTmp;
        //Iterate the Map
        for(Map.Entry<String,DataUser> set: userList.entrySet()) {// n (Iteration).
            userTmp = set.getValue();
            if (user.inZone(userTmp) && !(user.getId().equals(userTmp.getId()))) {
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
    public static String makeMessage(DataUser user, DataUser userNeighbour, boolean typeNeighbour){
        String message =user.getId().toString();

        if(typeNeighbour){//The userNeighbour enters
            message=message+"+";
            if(user.getGroup().equals(userNeighbour.getGroup())) message=message+"=";//Same group.
            else message=message+"/";//Different group.
        }
        else message=message+"-";//The userNeighbour exits.

        message=message+userNeighbour.getId().toString();
        return message;
    }

    /**
     * Process the user move os userMoves, makes the correct message for each case
     * and update the neighbours list of userMoves and the other affected users.
     * <p>Efficiency: O(n*d + d^2)
     *
     * @param userMoves User with the update position.
     * @param newNeighbours actual neighbours of userMoves.
     * @param userList Map with all the users.
     * @param messages List of messages is updated adding the new messages to the end.
     */
    public static void update(DataUser userMoves, Neighbours newNeighbours,
                              HashMap<String,DataUser> userList, LinkedList<String> messages) {
        Neighbours oldNeighbours = userMoves.getNeighbours();
        Id userId = userMoves.getId();
        DataUser tmpUser;
        Id tmpId;
        // Add the new users
        Iterator iterator = newNeighbours.getIterator();
        while (iterator.hasNext()) {                                 //O(d)
            tmpId = new Id((String) iterator.next());
            if (!oldNeighbours.isNeighbour(tmpId)) {
                tmpUser = search(userList, tmpId);                 //O(1)
                messages.add(makeMessage(userMoves, tmpUser, true)); //O(1)
                messages.add(makeMessage(tmpUser, userMoves, true)); //O(1)
                tmpUser.getNeighbours().add(userId);//O(1)
            }
            //Worst case O(d++).
        }

        //Eliminar los que han desaparecido
        iterator = oldNeighbours.getIterator();
        while (iterator.hasNext()) {                                 //O(d)
            tmpId = new Id((String) iterator.next());
            if (!newNeighbours.isNeighbour(tmpId)) {
                tmpUser = search(userList, tmpId);                   //O(1)
                messages.add(makeMessage(userMoves, tmpUser, false)); //O(1)
                messages.add(makeMessage(tmpUser, userMoves, false)); //O(1)
                tmpUser.getNeighbours().remove(userId);             //O(1)
            }
            //Worst case O(d++).
        }
        // Update the neighbours list of userMoves to newNeighbours.
        // O(1)
        userMoves.getNeighbours().setNeighbours(newNeighbours.getNeighbours());
    }

    /**
     * Algorithm of practice: Process de moves one by one, generates the messages of enter and exit
     * in the control zone.
     * <p>Efficiency: O(d*n^2 + n*d^2)
     *
     * @param userList Map of all the users with its old positions.
     * @param movesList List with the moves of each user and each new position.
     * @return The messages list.
     */
    public static LinkedList<String> algorithm(HashMap<String,DataUser> userList, LinkedList<Move> movesList) {
        LinkedList<String> messages = new LinkedList<>();
        for (Move move : movesList) { // n iterations
            Id userId = move.getId();
            DataUser user = search(userList,userId);// O(1)
            // Move to the new Postion.
            user.move(move); //O(1)
            Neighbours newNeighbours = getNeighbours(user,userList); //O(n)

            // Makes messages and update neighbours.
            update(user,newNeighbours,userList,messages); //O(d²)
            messages.add("---");
            // every iteration: O(n+d²)
        }
        // Total complexity: O(n+d).
        // cte*d*n + O(n*d)
        return messages;
    }

    public static void main(String[] args) throws IOException {
        HashMap<String,DataUser> userList; // Map of users.
        LinkedList<Move> movesList;        // List of moves
        LinkedList<String> messages;       // List of messages
        int n;                             // Number of users
        double d;                          // Total density (person/meter^2).

        Scanner in = new Scanner(System.in);
        System.out.println("PRACTICA EDA FIGHT-RADAR");
        System.out.print("Fichero de estado: ");

        String pathFile = in.nextLine();
        String[] paths= pathFile.split("/");
        String inicFile = paths[paths.length-1];

        String[] parts = inicFile.split("_");
        n = Integer.parseInt(parts[1]);
        d = Double.parseDouble(parts[2]);
        System.out.printf("Parámetros: n = %d, d = %.1f\n", n, d);

        userList = readInitFile(pathFile, n);

        System.out.print("Modo [D]epuración o [M]edición? ");
        String mode = in.nextLine().toUpperCase();

        if (mode.startsWith("D")) {
            // Mode debugging
            System.out.print("Fichero de movimientos: ");
            String movesFile = in.nextLine();

            if (movesFile.length() == 0) { // Autocomplete file name.
                movesFile = "movs_" + parts[1] + "_" + parts[2] + "_" + parts[3];
            }

            movesList = LeeFicheroMovs(movesFile, n);
            System.out.println("Procesando...");

            long t0 = System.nanoTime();
            messages = algorithm(userList,movesList);
            long t1 = System.nanoTime();
            System.out.printf("Tiempo: %.6f seg.\n", 1e-9 * (t1 - t0));

            System.out.print("Quieres guardar los mensajes en un fichero (S/N)? ");
            String save = in.nextLine().toUpperCase();
            if(save.startsWith("S")){
                // Save the messages in a file.
                Path file = Paths.get("msgs_" + parts[1] + "_" + parts[2] + "_" + parts[3]);
                Files.write(file, messages, StandardCharsets.UTF_8);
            }
            //If messages arent saved in a file, it will be printed.
            else for (String i: messages)System.out.println(i);

        } else {
            // Mode medition
            System.out.print("Número de ciclos: ");
            int num_ciclos = Integer.parseInt(in.nextLine());
            System.out.println("Procesando...");
            movesList = new LinkedList<>();

            for (Map.Entry<String,DataUser> set: userList.entrySet()){
                DataUser tmpUser = set.getValue();
                movesList.add(new Move(tmpUser.getId(),tmpUser.getPosition()));
            }

            double tpo_total = 0;
            for (int i = 0; i < num_ciclos; i++) {
                // Makes moves get random values.
                for (Move mov : movesList) { mov.moveRandom(0.5); } // pos + (-0.5,0.5)
                long t0 = System.nanoTime();
                algorithm(userList,movesList);
                long t1 = System.nanoTime();
                tpo_total += 1e-9 * (t1 - t0);
                System.out.printf("%.5f\n", 1e-9 * (t1 - t0));
            }
            System.out.printf("Promedio: %.5f\n", tpo_total / num_ciclos);
        }
    }
}

