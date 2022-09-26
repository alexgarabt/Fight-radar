package DataStructure;

/**
 * Class representation of simple information of one user.
 * @see Id
 * @see Position
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
    public Position getPosition(){
        return position;
     }
    public String toString(){
        return id.toString()+", "+position.toString();
    }

}
