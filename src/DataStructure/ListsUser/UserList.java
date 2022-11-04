package DataStructure.ListsUser;

import DataStructure.Data.*;
import DataStructure.UserData.*;
import java.util.ArrayList;

/**
 *Class UserList is a List of all the users.
 * indexUser(Id) Returns the position of the id in the list. If not exist in the list, return -1.
 * addUser(DataUser) Add the user information at the tail of the list.
 * deleteUser(Id) If id exist in the list, it will be deleted.
 * getUser(Id) Returns the DataUser associated with the id, if exist.
 * getUsersInZone(Id) Returns a list of Neighbours with the users in
 * the list that are near that the min distance.
 *
 * @see DataStructure.UserData.DataUser
 * @version /26/09/2022/
 */
public class UserList{

    //private ArrayList<DataUser> usersList;
    public ArrayList<DataUser> usersList;

    public UserList(){
        this.usersList= new ArrayList<DataUser>();
    }

    public void setUsersList(ArrayList<DataUser> userList){
        usersList=userList;
    }

    public ArrayList<DataUser> getUsersList() {
        return usersList;
    }

    /**
     * Add the DataSimple to the userList.
     * @param user
     */
    public void addUser(DataUser user){
        usersList.add(user);
    }

    public int size(){
        return usersList.size();
    }

    /**
     * Secuencial search for the user with these Id in the userList, and return the position.
     * @param id, of the user to search.
     * @return (int) the position of the user in the userList.
     * @return (-1) if there is no user with the given Id.
     */
    public int indexUser(Id id){

        for (int i=0; i<size(); i++){
            if(id.equals(usersList.get(i).getId())) return i;
        }
        return -1;
    }

    /**
     * Search a user in the list with same id, and return the information.
     * This function use indexUser(Id) to get the position in the list.
     * @param id, of the user to search.
     * @return DataSimple of the user.
     * @return null if there is no user with that id.
     */
    public DataUser getUser(Id id){
        int pos=indexUser(id);
        if (pos==-1){ return null;}
        return usersList.get(pos);
    }
    /**
     * Deletes int the list, the DataSimple of the user with the given id.
     * If there is no user with that id, then nothing.
     * Use indexUser(Id), to get the position in the list.
     * @param id
     */
    public void deleteUser(Id id){
        int pos=indexUser(id);
        if (pos==-1) return;
        usersList.remove(pos);
    }

    /**
     * Returns a Neighbours object with the users in the list that are in closer
     * than de minDistance to the user with the given id.
     * @param id
     * @return Neighbours list.
     */
    public Neighbours getUsersInZone(Id id){
        DataUser user= getUser(id);
        Position position = user.getPosition();
        Neighbours neighbours = new Neighbours();
        for (DataUser userI: usersList){
            Id idUserI= userI.getId();
            if(id.equals(idUserI)) continue;
            if(position.inZone(userI.getPosition())) neighbours.addId(idUserI);
        }
        return neighbours;
    }

    /**
     * Set the new neighbours of the users in the list.
     * Used after a move of one user.
     */
    public void setUsersNeighbours(){
        for(DataUser user: usersList){
            user.setNeighbours(this.getUsersInZone(user.getId()));
        }
    }

    /**
     * Returns a list with the simple information of the users.
      * @return SimpleUserList.
     */
    public SimpleUserList getSimpleUserList(){
        SimpleUserList simpleList = new SimpleUserList();
        for(DataUser i: usersList){
            DataSimple simpleUser = new DataSimple(i.getId(),i.getPosition());
            simpleList.addUser(simpleUser);
        }
        return simpleList;
    }


}
