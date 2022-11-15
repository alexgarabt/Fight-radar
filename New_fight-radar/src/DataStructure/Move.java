package DataStructure;

import DataStructure.Data.*;

/**
 * Represents a move of a user.
 *
 * @see DataStructure.Data.Id;
 * @see DataStructure.Data.Position;
 */

public class Move {

    private Id id;
    private Position position;

    public Move(Id id, Position position){
        this.id = id;
        this.position=position;
    }
    public Move(String info) {
        String[] trozos = info.split(" ");
        id = new Id(trozos[0]);
        double x = Double.parseDouble(trozos[1]);
        double y = Double.parseDouble(trozos[2]);
        position =  new Position(x,y);

    }

    /**
     * This function gives adds a random number between (-max,max) to x and y.
     * @param max, max value of the random number.
     */
    public void moveRandom(double max){
        position.randomize(max);
    }

    public Id getId(){
        return id;
    }
    public void setPosition(Position pos){
        this.position=pos;
    }
    public Position getPosition(){
        return position;
    }
    public String toString(){
        return id.toString()+", "+position.toString();
    }

}
