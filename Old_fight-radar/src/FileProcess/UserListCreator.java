package FileProcess;
import DataStructure.Data.*;
import DataStructure.DataUser;
import DataStructure.UserData.*;
import DataStructure.ListsUser.*;
import java.util.ArrayList;


/**
 * Class in charge of create, the UserList with the information in a String[]
 * Each [*] is one user information.
 * @see DataUser
 * @see DataStructure.ListsUser.UserList
 * @see DataStructure.Data.Neighbours
 * @see DataStructure.Data.Id
 * @see DataStructure.Data.Position
 * @see DataStructure.Data.Group
 * @version /06/10/2022/
 */

public class UserListCreator {
    private ArrayList<String> info;

    public UserListCreator(ArrayList info){
        this.info=info;
    }

    /**
     * Creates a DataUser with the given information.
     * @param userInfo String with the comples information of one user. [id x y group neighbour1 neighbour2 ...]
     * @return A DataUser of the user.
     */
    private DataUser createDataUser(String userInfo){
        Id id;
        Neighbours neighbours = new Neighbours();
        Position pos;
        Group group;

        String[] userInfoParts = userInfo.split(" ");
        id = new Id(userInfoParts[0]);
        pos = new Position((Double.parseDouble(userInfoParts[1])),Double.parseDouble(userInfoParts[2]));

        if(userInfoParts[3].equals("N")) group = new Group(false);
        else group = new Group(true);

        for(int i=4; i<userInfoParts.length;i++){
            Id newNeighbour = new Id(userInfoParts[i]);
            neighbours.addId(newNeighbour);
        }
        return (new DataUser(id,pos,group,neighbours));
    }
    /**
     * Creates a UserList with the information of the users stored in ArrayList info.
     * @return UserList with the users information.
     */
    public UserList getUsersList(){
        UserList userList = new UserList();
        for(String userInfo: info){
            userList.addUser(createDataUser(userInfo));
        }
        return userList;
    }
}
