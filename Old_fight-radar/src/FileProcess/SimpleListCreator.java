package FileProcess;
import DataStructure.Data.*;
import DataStructure.DataSimple;
import DataStructure.UserData.*;
import DataStructure.ListsUser.*;
import java.util.ArrayList;


/**
 * Class in charge of create, the UserList with the information in a String[]
 * @see DataSimple
 * @see DataStructure.ListsUser.SimpleUserList
 * @see DataStructure.Data.Id
 * @see DataStructure.Data.Position
 * @version /06/10/2022/
 */

public class SimpleListCreator {
    private ArrayList<String> info;

    public SimpleListCreator(ArrayList info){
        this.info=info;
    }

    /**
     * Creates a DataSimple with the given information.
     * @param userInfo String with the simple information of one user. [id x y]
     * @return A DataSimple of the user.
     */
    private DataSimple createDataSimple(String userInfo){
        Id id;
        Position pos;

        String[] userInfoParts = userInfo.split(" ");
        id = new Id(userInfoParts[0]);
        pos = new Position((Double.parseDouble(userInfoParts[1])),Double.parseDouble(userInfoParts[2]));
        return (new DataSimple(id,pos));
    }

    /**
     * Creates a SimpleUserList with the information of the users stored in ArrayList info.
     * @return SimpleUserList with the users information
     */
    public SimpleUserList getUsersList(){
        SimpleUserList simpleList = new SimpleUserList();
        for(String userInfo: info){
            simpleList.addUser(createDataSimple(userInfo));
        }
        return simpleList;
    }
}

