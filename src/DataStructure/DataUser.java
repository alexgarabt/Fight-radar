package DataStructure;

public class DataUser extends DataSimple{

    private Neighbours neighbours;
    private Group group;

    public DataUser(Id id, Position position, Group group, Neighbours neighbours) {
        super(id, position);
        this.group=group;
        this.neighbours=neighbours;
    }

    public Neighbours getNeighbours(){
        return neighbours;
    }
    public void setNeighbours(Neighbours neighbours){
        this.neighbours=neighbours;
    }

    public Group getGroup(){
        return group;
    }

    public String toString(){
        return super.toString()+", "+group.toString()+", "+neighbours.toString();
    }
}