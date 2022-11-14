package DataStructure.Data;
import java.lang.Math;
/**
 * Position class identify the position of a user in a two-dimensional plane.
 * Position (x,y).
 * minDistance represents the minimum distance of user.
 * distance(Position), Returns the distance between two position.(The actual one and the given one).
 * inZone(Position), Returns if the distance of the given and the actual position are less than 2.
 * randomize(double, double), Returns a random number between (min,max).
 * getRandomize(double, double), Returns a Position object with [(x & y) + randomize(double, double)].
 * @version /4/11/2022/
 */

public class Position {

    private double x;
    private double y;
    private double minDistance;

    public Position(double x, double y){
        this.x=x;
        this.y=y;
        this.minDistance=2;
    }

    public double getPositionX(){
        return x;
    }

    public double getPositionY(){
        return y;
    }

    public double distance(Position pos){
        /**
         * This function get the distance between to positions in a two-dimensional plane.
         * |u1-u2| = sqrt((u1x-u2x)²+(u1y-u2y)²
         */
        return Math.sqrt((Math.pow((this.x-pos.getPositionX()), 2))+
                (Math.pow((this.y-pos.getPositionY()), 2)));
    }

    /**
     * This function has the purpose of see if two Postions are in the min distance.
     * @param pos Position to compare.
     * @return true(if are in the minimum distance).
     * @return false(if not are in the minimum distance).
     */
    public boolean inZone(Position pos){
        if(this.distance(pos)<minDistance) return true;
        return false;
    }

    /**
     * This function gives a random number between (min,max) with three decimal figures.
     * @param max
     * @param min
     * @return random Number.
     */
    private double randomize(double max, double min){

        double random= 1000*((Math.random() * (max - min)) + min);
        random=(Math.floor(random))/1000;
        return random;
    }

    /**
     * @param max
     * @param min
     * @return Returns a Position object with [(x & y) + randomize(double, double)].
     */
    public Position getRandomize(double max, double min){
        Position newPos = new
                Position((this.getPositionX())+randomize(max, min),(this.getPositionY())+randomize(max, min));
        return newPos;
    }

    public String toString(){
        return "("+this.x+", "+this.y+")";
    }
}
