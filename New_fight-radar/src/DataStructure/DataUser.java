package DataStructure.UserData.DataStructure;

import DataStructure.DataSimple;
import DataStructure.UserData.DataStructure.Data.Group;

/**
 * Class representation of the complex information of one user.
 * @see DataStructure.DataSimple
 * @see DataStructure.UserData.DataStructure.Data.Neighbours
 * @see DataStructure.UserData.DataStructure.Data.Group
 * @version /26/09/2022/
 */
public class DataUser extends DataSimple {

    private DataStructure.UserData.DataStructure.Data.Neighbours neighbours;
    private DataStructure.UserData.DataStructure.Data.Group group;

    public DataUser(DataStructure.UserData.DataStructure.Data.Id id, DataStructure.UserData.DataStructure.Data.Position position, DataStructure.UserData.DataStructure.Data.Group group, DataStructure.UserData.DataStructure.Data.Neighbours neighbours) {
        super(id, position);
        this.group=group;
        this.neighbours=neighbours;
    }

    public DataStructure.UserData.DataStructure.Data.Neighbours getNeighbours(){
        return neighbours;
    }
    public void setNeighbours(DataStructure.UserData.DataStructure.Data.Neighbours neighbours){
        this.neighbours=neighbours;
    }

    public Group getGroup(){
        return group;
    }

    public String toString(){
        return super.toString()+", "+group.toString()+", "+neighbours.toString();
    }
}