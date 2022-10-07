package FileProcess;

import DataStructure.*;

import java.util.ArrayList;


/**
 * Class in charge of create, the UserList with the information in a String[]
 * Each [*] is one user information.
 * @see DataStructure.DataUser
 * @see DataStructure.UserList
 * @see DataStructure.Neighbours
 * @see DataStructure.Id
 * @see DataStructure.Position
 * @see DataStructure.Group
 * @version /06/10/2022/
 */

public class UserListCreator {
    private ArrayList<String> info;

    public UserListCreator(ArrayList info){
        this.info=info;
    }
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
    public UserList getUsersList(){
        UserList userList = new UserList();

        for(String userInfo: info){
            userList.addUser(createDataUser(userInfo));
        }
        return userList;
    }
}
