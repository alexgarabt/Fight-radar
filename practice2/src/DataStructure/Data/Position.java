package DataStructure.Data;
import java.lang.Math;
/**
 * Position class identify the position of a user in a two-dimensional plane.
 * Position (x,y).
 * minDistance represents the minimum distance of user.
 * @version 2.0
 */

public class Position {

    private double x;
    private double y;
    private double minDistance = 2;

    public Position(double x, double y){
        this.x=x;
        this.y=y;

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
     * This function gives adds a random number between (-max,max) to x and y.
     * @param max, max value of the random number.
     */
    public void randomize(double max){
        x += Math.random() - max;
        y += Math.random() - max;
    }

    public Position clone(){
        return new Position(x,y);
    }

    public String toString(){
        return "("+this.x+", "+this.y+")";
    }
}
