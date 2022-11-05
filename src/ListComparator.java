import DataStructure.Data.*;
import DataStructure.ListsUser.*;
import DataStructure.UserData.*;
import java.util.ArrayList;


/**
 * Compare the  initial list and the moves list.
 * @see DataStructure.Data.Neighbours
 * @see DataStructure.ListsUser.UserList
 * @see DataStructure.ListsUser.SimpleUserList
 * @version /06/10/2022/
 */
public class ListComparator {

    private UserList oldList;
    private SimpleUserList movsUserList;

    public ListComparator(UserList oldList, SimpleUserList movsUserList){
        this.oldList=oldList;
        this.movsUserList=movsUserList;
    }

    /**
     * Compare the oldList between the movsUserList and update the Neighbours in the oldList.
     * And print the different moves of the users.
     */
    public void compareListsDebugging(){
        for(DataSimple userSimple : movsUserList.getUsersList() ){
            Neighbours[] listType= getTypeNeighbours(userSimple);
            printMoves(userSimple.getId(),
                    listType[0],listType[1],listType[2]);
        }
    }

    /**
     * Compare the oldList between the movsUserList and update the Neighbours in the oldList,
     * and get the time that requires that.
     * @return The time in nanoseconds.
     */
    public long compareListsMedition(){
        long startTime = System.nanoTime();
        for(DataSimple userSimple : movsUserList.getUsersList() ){
            Neighbours[] listType= getTypeNeighbours(userSimple);
        }
        return System.nanoTime() - startTime;
    }

    /**
     * With the given Simple user information of the new position of the user,
     * searchs in oldList the new Neighbours of the moved user and update the oldList neighbours of each one.
     * @param userSimple
     * @return listType[0]=exitNeighbours
     * @return listType[2]=enterEnemyNeighbours
     * @return listType[1]=enterFriendNeighbours;
     */
    public Neighbours[] getTypeNeighbours(DataSimple userSimple){
        Id userId =userSimple.getId();

        ArrayList<DataUser>listInfoUser=oldList.getUsersList();
        int numPosUser = oldList.indexUser(userId);

        DataUser user = listInfoUser.get(numPosUser);
        Group userGroup= user.getGroup();

        Neighbours oldNeighbours = user.getNeighbours();

        //Add the new Position of the use to the oldList.
        user.setPosition(userSimple.getPosition());
        listInfoUser.set(numPosUser, user);
        oldList.setUsersList(listInfoUser);


        Neighbours newNeighbours = oldList.getUsersInZone(userId);

        user.setNeighbours(newNeighbours);
        //Add the new Neighbours in the oldList.
        listInfoUser.set(numPosUser, user);
        oldList.setUsersList(listInfoUser);



        Neighbours exitNeighbours = new Neighbours();
        Neighbours enterNeighbours = new Neighbours();
        Neighbours enterFriendNeighbours = new Neighbours();
        Neighbours enterEnemyNeighbours = new Neighbours();

        //Obtains the exitNeighbours
        for (Id oldOne: oldNeighbours.getNeighbours()){
            if(!newNeighbours.isNeighbour(oldOne)) exitNeighbours.addId(oldOne);
        }
        //Obtains the enterFriendsNeighbours and enterEnemyNeighbours
        for (Id newOne : newNeighbours.getNeighbours()){
            if(!oldNeighbours.isNeighbour(newOne)){
                enterNeighbours.addId(newOne);
                DataUser newUser = oldList.getUser(newOne);
                if(userGroup.equals(newUser.getGroup()))enterFriendNeighbours.addId(newOne);
                else enterEnemyNeighbours.addId(newOne);
            }
        }

        oldList.updateUserInNeighboursLists(userId,enterNeighbours,exitNeighbours);

        Neighbours[] listType =new Neighbours[3];
        listType[0]=exitNeighbours;
        listType[2]=enterEnemyNeighbours;
        listType[1]=enterFriendNeighbours;
        return listType;

    }

    /**
     * Function in charge to print the neighbours updates of the user
     * @param user that has moved.
     * @param exitNeighbours that now are not in the minDistance zone.
     * @param enterFriendNeighbours new Neighbours that have the same group that the moved user.
     * @param enterEnemyNeighbours new Neighbours that haven't the same group that the moved user.
     */
    public void printMoves(Id user,Neighbours exitNeighbours, Neighbours enterFriendNeighbours,
                                  Neighbours enterEnemyNeighbours){
        for(Id enterEnemyUser: enterEnemyNeighbours.getNeighbours()){
            System.out.println(user+"+/"+enterEnemyUser);
            System.out.println(enterEnemyUser+"+/"+user);
        }
        for(Id enterFriendUser: enterFriendNeighbours.getNeighbours()){
            System.out.println(user+"+="+enterFriendUser);
            System.out.println(enterFriendUser+"+="+user);
        }
        for(Id exitUser: exitNeighbours.getNeighbours()){
            System.out.println(user+"-"+exitUser);
            System.out.println(exitUser+"-"+user);
        }
        System.out.println("---");
    }
}
