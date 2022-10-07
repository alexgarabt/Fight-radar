package FileProcess;

import DataStructure.*;

import java.util.ArrayList;


/**
 * Class in charge of create, the UserList with the information in a String[]
 * @see DataStructure.DataSimple
 * @see DataStructure.SimpleUserList
 * @see DataStructure.Id
 * @see DataStructure.Position
 * @version /06/10/2022/
 */

public class SimpleListCreator {
    private ArrayList<String> info;

    public SimpleListCreator(ArrayList info){
        this.info=info;
    }
    private DataSimple createDataSimple(String userInfo){
        Id id;
        Position pos;

        String[] userInfoParts = userInfo.split(" ");
        id = new Id(userInfoParts[0]);
        pos = new Position((Double.parseDouble(userInfoParts[1])),Double.parseDouble(userInfoParts[2]));
        return (new DataSimple(id,pos));
    }
    public SimpleUserList getUsersList(){
        SimpleUserList simpleList = new SimpleUserList();
        for(String userInfo: info){
            simpleList.addUser(createDataSimple(userInfo));
        }
        return simpleList;
    }
}

