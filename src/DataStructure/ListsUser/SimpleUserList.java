package DataStructure.ListsUser;

import DataStructure.UserData.*;
import DataStructure.Data.*;
import java.util.ArrayList;

/**
 *Class SimpleUserList is a List of Simple information of users.
 * indexUser(Id) Returns the position of the id in the list. If not exist in the list, return -1.
 * addUser(DataSimple) Add the user information at the tail of the list.
 * deleteUser(Id) If id exist in the list, it will be deleted.
 * getUser(Id) Returns the DataSimple associated with the id, if exist.
 * getUsersInZone(Id) Returns a list of Neighbours with the users in
 * the list that are near that the min distance.
 *
 * @see DataSimple
 * @version /26/09/2022/
 */
public class SimpleUserList {

    private ArrayList<DataSimple> usersList;

    public SimpleUserList(){
        usersList = new ArrayList<DataSimple>();
    }

    public void setUsersList(ArrayList<DataSimple> userList){
        usersList=userList;
    }

    public ArrayList<DataSimple> getUsersList() {
        return usersList;
    }

    public void addUser(DataSimple user){
        usersList.add(user);
    }

    public int size(){
        return usersList.size();
    }

    public int indexUser(Id id){

        for (int i=0; i<size(); i++){
            if(id.equals(usersList.get(i).getId())) return i;
        }
        return -1;
    }


    public DataSimple getUser(Id id){
        int pos=indexUser(id);
        if (pos==-1){ return null;}
        return usersList.get(pos);
    }

    public void deleteUser(Id id){
        int pos=indexUser(id);
        if (pos==-1) return;
        usersList.remove(pos);
    }

    public Neighbours getUsersInZone(Id id){
        DataSimple user= getUser(id);
        Position position = user.getPosition();
        Neighbours neighbours = new Neighbours();
        for (DataSimple userI: usersList){
            Id idUserI= userI.getId();
            if(id.equals(idUserI)) continue;
            if(position.inZone(userI.getPosition())) neighbours.addId(idUserI);
        }
        return neighbours;
    }

}
