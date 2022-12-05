package DataStructure;
import DataStructure.Data.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class has the purpose of store the users
 * and give quick access to DataUser objects using the id.
 *
 * DataStructure--> HashMap<String,DataUser>.
 * @see DataStructure.DataUser
 * @version 1.0
 */
public class UsersMap {
    //usersMap Map of users. key=Id of the user, value=DataUser of the user.
    private HashMap<String, DataUser> usersMap;

    public UsersMap(){usersMap = new HashMap<>();}

    /**
     * Creates a new HasMap with the initial(n) capacity.
     * @param n
     */
    public UsersMap(int n){this.usersMap = new HashMap<>(n);}

    /**
     * Search for the user in the Map with the given unique id identifier.
     * <p>Efficiency: O(1).
     *
     * @param userId Identifier of the user.
     * @return The user with these Id.
     */
    public DataUser search(Id userId) {
        String stringId = userId.getId();
        if(!usersMap.containsKey(stringId)){throw new Error("No existe ningún usuario con ese identificador");}
        return usersMap.get(stringId);
    }

    /**
     * Adds the new user.
     * @param user
     */
    public void addUser(DataUser user) {usersMap.put(user.getId().getId(),user);}

    /**
     * Return the users that are in the control zone of the given "user";
     * <p>Efficiency: O(n)
     *
     * @param user The given user.
     * @return A Neighbours object with the users in the control zone.
     */
    public Neighbours getNewNeighbours(DataUser user) {
        Neighbours neighbours = new Neighbours();
        DataUser userTmp;
        //Iterate the Map
        for(Map.Entry<String,DataUser> set: usersMap.entrySet()) {// n (Iteration).
            userTmp = set.getValue();
            if (user.inZone(userTmp) && !(user.getId().equals(userTmp.getId()))) {
                neighbours.add(userTmp.getId());//O(1)
            }
        }
        return neighbours;
    }

    /**
     * Return the max distant axes point in the usersMap.
     * @return
     */
    public Position maxDistanPos(){
        double x=0;
        double y=0;
        DataUser userTmp;
        double tmpX,tmpY;
        for(Map.Entry<String,DataUser> set: usersMap.entrySet()) {// n (Iteration).
            userTmp = set.getValue();
            tmpX= userTmp.getPosition().getPositionX();
            tmpY= userTmp.getPosition().getPositionY();
            if(tmpX>x)x=tmpX;
            if(tmpY>y)y=tmpY;
        }
        return new Position(x,y);
    }

    public HashMap<String, DataUser> getUsersMap() {return usersMap; }

    public void setUsersMap(HashMap<String, DataUser> usersMap) {this.usersMap = usersMap;}

}
