package DataStructure.UserData.DataStructure;

import DataStructure.UserData.DataStructure.Data.Position;

/**
 * Class representation of simple information of one user.
 * @see DataStructure.UserData.DataStructure.Data.Id
 * @see DataStructure.UserData.DataStructure.Data.Position
 * @version /26/09/2022/
 */
public class DataSimple {
    private DataStructure.UserData.DataStructure.Data.Id id;
    private DataStructure.UserData.DataStructure.Data.Position position;

    public DataSimple(DataStructure.UserData.DataStructure.Data.Id id, DataStructure.UserData.DataStructure.Data.Position position){
        this.id=id;
        this.position=position;
    }

    public DataStructure.UserData.DataStructure.Data.Id getId(){
        return id;
    }
    public void setPosition(DataStructure.UserData.DataStructure.Data.Position pos){
        this.position=pos;
    }
    public Position getPosition(){
        return position;
     }
    public String toString(){
        return id.toString()+", "+position.toString();
    }

}
