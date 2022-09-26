package DataStructure;

/**
 * Position class identify the position of a user in a two-dimensional plane.
 * @version /26/09/2022/
 */

public class Position {

    private double x;
    private double y;

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

    public boolean inZone(Position pos){
        if(this.distance(pos)<2) return true;
        return false;
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
