package DataStructure;

import java.util.ArrayList;

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

    public void addUser(DataUser user){
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


    public DataUser getUser(Id id){
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

    public void setUsersNeighbours(){
        for(DataUser user: usersList){
            user.setNeighbours(this.getUsersInZone(user.getId()));
        }
    }


}
