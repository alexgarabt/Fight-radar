package DataStructure.Data;
import java.lang.Math;
/**
 * Position class identify the position of a user in a two-dimensional plane.
 * Position (x,y).
 * minDistance represents the minimum distance of user.
 * distance(Position) Returns the distance between two position.(The actual one and the given one).
 * inZone(Position) Returns if the distance of the given and the actual position are less than 2.
 * @version /26/09/2022/
 */

public class Position {

    private double x;
    private double y;
    private int minDistance;

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

    public boolean inZone(Position pos){
        if(this.distance(pos)<minDistance) return true;
        return false;
    }

    private double randomize(double max, double min){

        double random= 1000*((Math.random() * (max - min)) + min);
        random=(Math.floor(random))/1000;
        return random;
    }

    public Position getRandomize(double max, double min){
        Position newPos = new
                Position((this.getPositionX())+randomize(max, min),(this.getPositionY())+randomize(max, min));
        return newPos;
    }


    public double distance(Position pos){
        /**
         * This function get the distance between to positions in a two-dimensional plane.
         * |u1-u2| = sqrt((u1x-u2x)²+(u1y-u2y)²
         */
        return Math.sqrt((Math.pow((this.x-pos.getPositionX()), 2))+
                (Math.pow((this.y-pos.getPositionY()), 2)));
    }

    public String toString(){
        return "("+this.x+", "+this.y+")";
    }
}
