package DataStructure;

public class DataPosition {
    private Id id;
    private Position position;

    public DataPosition(Id id, Position position){
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
