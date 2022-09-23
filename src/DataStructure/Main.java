package DataStructure;

public class Main {
    public static void main(String[] args) {

        Position pos1 = new Position(0,0);
        Position pos2 = new Position(0,4);

        System.out.println(pos1.inZone(pos2));
        System.out.println(pos1.distance(pos2));
    }
}