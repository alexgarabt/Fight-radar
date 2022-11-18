package DataStructure;
import DataStructure.Data.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class has the purpose of store the users
 * and give quick access to DataUser objects using the id.
 *
 * DataStructure HashMap<String,DataUser>.
 * @see DataStructure.DataUser
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

    public HashMap<String, DataUser> getUsersMap() {return usersMap; }

    public void setUsersMap(HashMap<String, DataUser> usersMap) {this.usersMap = usersMap;}

}
