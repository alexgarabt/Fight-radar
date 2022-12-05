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
 * List of users --> UsersMap(HashMap<String,DataUser>).
 * List of moves --> LinkedList<Moves>.
 * List of message --> Messages(LinkedList<String>).
 * Tree of Positions --> Quadtree.
 *
 * @see Messages
 * @see UsersMap
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
    public static UsersMap readInitFile(String filePaht, int numUsers) throws FileNotFoundException, IOException {

        BufferedReader br = new BufferedReader(new FileReader(new File(filePaht)));
        UsersMap usersMap = new UsersMap(numUsers);
        for (int i = 0; i <numUsers; i++) {
            DataUser user = new DataUser(br.readLine());
            Point p = new Point(user);
            user.setPoint(p);
            usersMap.addUser(user);
        }
        return usersMap;
    }

    /**
     * Creates the Quadtree Structure with max coordinates and users in usersMap.
     * @param usersMap Map with all the users.
     * @return quadtree initialized with all the points.
     */
    public static QuadTree initStructures(UsersMap usersMap){
        double desp=20;
        Position maxDist = usersMap.maxDistanPos();

        double width=maxDist.getPositionX()+desp;
        double height=maxDist.getPositionY()+desp;
        Rectangle r = new Rectangle(new Position(width/2,height/2),height,width);
        QuadTree quadTree = new QuadTree(r);
        DataUser tmpUser;
        for(Map.Entry<String,DataUser> set: usersMap.getUsersMap().entrySet()) {// n (Iteration).
            tmpUser=set.getValue();
            quadTree.insert(tmpUser.getPoint());
        }
        return quadTree;
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
    public static Neighbours getNewNeighbours(QuadTree quadTree, UsersMap usersMap, DataUser user){
        double dist=2*2;
        //First delete the old position od the user in the quadtree
        user.getPoint().removePointInList();
        Point point = new Point(user,user.getPosition());
        user.setPoint(point);
        //Insert now the new position of the user.
        quadTree.insert(point);
        //Create a rectangle range to search in.
        Rectangle range = new Rectangle(user.getPosition(),dist,dist);
        //Search in the quadtree.
        ArrayList<Point> posibleNeighbours = quadTree.queryRange(range);

        //Look what are real neighbours.
        DataUser userTmp;
        Neighbours neighbours = new Neighbours();
        for(Point p:posibleNeighbours){
            userTmp = (DataUser) p.getObject();
            if (user.inZone(userTmp) && !(user.getId().equals(userTmp.getId()))) {
                neighbours.add(userTmp.getId());//O(1)
            }
        }
        return neighbours;
    }

    /**
     * Process the user move os userMoves, makes the correct message for each case
     * and update the neighbours list of userMoves and the other affected users.
     *
     * @param userMoves User with the update position.
     * @param newNeighbours actual neighbours of userMoves.
     * @param usersMap Map with all the users.
     * @param messages List of messages is updated adding the new messages to the end.
     */
    public static void update(DataUser userMoves, Neighbours newNeighbours,
                              UsersMap usersMap, Messages messages) {
        Neighbours oldNeighbours = userMoves.getNeighbours();
        Id userId = userMoves.getId();
        DataUser tmpUser;
        Id tmpId;
        // Add the new users
        Iterator iterator = newNeighbours.getIterator();
        while (iterator.hasNext()) {                                 //O(d)
            tmpId = new Id((String) iterator.next());
            if (!oldNeighbours.isNeighbour(tmpId)) {
                tmpUser = usersMap.search(tmpId);                 //O(1)
                messages.addMessage(userMoves, tmpUser, true); //O(1)
                messages.addMessage(tmpUser, userMoves, true); //O(1)
                tmpUser.getNeighbours().add(userId);//O(1)
            }
            //Worst case O(d++).
        }

        //Eliminar los que han desaparecido
        iterator = oldNeighbours.getIterator();
        while (iterator.hasNext()) {                                 //O(d)
            tmpId = new Id((String) iterator.next());
            if (!newNeighbours.isNeighbour(tmpId)) {
                tmpUser = usersMap.search(tmpId);                   //O(1)
                messages.addMessage(userMoves, tmpUser, false); //O(1)
                messages.addMessage(tmpUser, userMoves, false); //O(1)
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
     * @param usersMap Map of all the users with its old positions.
     * @param movesList List with the moves of each user and each new position.
     * @return The messages list.
     */
    public static Messages algorithm(QuadTree quadTree,UsersMap usersMap, LinkedList<Move> movesList) {
        Messages messages = new Messages();
        for (Move move : movesList) { // n iterations
            Id userId = move.getId();
            DataUser user = usersMap.search(userId);// O(1)
            // Move to the new Postion.
            user.move(move); //O(1)
            //Neighbours newNeighbours = usersMap.getNewNeighbours(user); //O(n)
            Neighbours newNeighbours = getNewNeighbours(quadTree,usersMap,user);

            // Makes messages and update neighbours.
            update(user,newNeighbours,usersMap,messages); //O(d²)
            messages.add("---");
            // every iteration: O(n+d²)
        }
        // Total complexity: O(n+d).
        // cte*d*n + O(n*d)
        return messages;
    }

    public static void main(String[] args) throws IOException {
        UsersMap usersMap;                 // Map of users.
        QuadTree quadTree;                 // Quadtree that stores positions.
        LinkedList<Move> movesList;        // List of moves
        Messages messages;                 // List of messages
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

        usersMap = readInitFile(pathFile, n);
        quadTree = initStructures(usersMap);

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
            messages = algorithm(quadTree,usersMap,movesList);
            long t1 = System.nanoTime();
            System.out.printf("Tiempo: %.6f seg.\n", 1e-9 * (t1 - t0));

            System.out.print("Quieres guardar los mensajes en un fichero (S/N)? ");
            String save = in.nextLine().toUpperCase();
            System.out.println();
            if(save.startsWith("S")){
                // Save the messages in a file.
                Path file = Paths.get("msgs_" + parts[1] + "_" + parts[2] + "_" + parts[3]);
                Files.write(file, messages.getMessagesList(), StandardCharsets.UTF_8);
            }
            //If messages arent saved in a file, it will be printed.
            else for (String i: messages.getMessagesList())System.out.println(i);

        } else {
            // Mode medition
            System.out.print("Número de ciclos: ");
            int num_ciclos = Integer.parseInt(in.nextLine());
            System.out.println("Procesando...");
            movesList = new LinkedList<>();

            for (Map.Entry<String,DataUser> set: usersMap.getUsersMap().entrySet()){
                DataUser tmpUser = set.getValue();
                movesList.add(new Move(tmpUser.getId(),tmpUser.getPosition()));
            }

            double tpo_total = 0;
            for (int i = 0; i < num_ciclos; i++) {
                // Makes moves get random values.
                for (Move mov : movesList) { mov.moveRandom(0.5); } // pos + (-0.5,0.5)
                long t0 = System.nanoTime();
                algorithm(quadTree,usersMap,movesList);
                long t1 = System.nanoTime();
                tpo_total += 1e-9 * (t1 - t0);
                System.out.printf("%.5f\n", 1e-9 * (t1 - t0));
            }
            System.out.printf("Promedio: %.5f\n", tpo_total / num_ciclos);
        }
    }
}

