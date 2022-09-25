package DataStructure;

import java.util.ArrayList;

public class SimpleUserList {

    //private ArrayList<DataPosition> usersList;
    public ArrayList<DataPosition> usersList;

    public SimpleUserList(){
        usersList = new ArrayList<DataPosition>();
    }

    public void setUsersList(ArrayList<DataPosition> userList){
        usersList=userList;
    }

    public ArrayList<DataPosition> getUsersList() {
        return usersList;
    }

    public void addUser(DataPosition user){
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


    public DataPosition getUser(Id id){
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
        DataPosition user= getUser(id);
        Position position = user.getPosition();
        Neighbours neighbours = new Neighbours();
        for (DataPosition userI: usersList){
            Id idUserI= userI.getId();
            if(id.equals(idUserI)) continue;
            if(position.inZone(userI.getPosition())) neighbours.addId(idUserI);
        }
        return neighbours;
    }

}
