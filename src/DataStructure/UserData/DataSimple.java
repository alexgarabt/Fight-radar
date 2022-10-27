package DataStructure.UserData;

import DataStructure.Data.*;

/**
 * Class representation of simple information of one user.
 * @see DataStructure.Data.Id
 * @see DataStructure.Data.Position
 * @version 1
 */
public class DataSimple {
    private Id id;
    private Position position;

    public DataSimple(Id id, Position position){
        this.id=id;
        this.position=position;
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
