package DataStructure;

public class DataUser extends DataPosition{

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
}