package DataStructure.UserData;

import DataStructure.Data.*;

/**
 * Class representation of the information of one user.
 * @see DataSimple
 * @see DataStructure.Data.Neighbours
 * @see DataStructure.Data.Group
 * @version /26/09/2022/
 */
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