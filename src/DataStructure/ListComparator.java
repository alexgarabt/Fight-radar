package DataStructure;

import java.util.ArrayList;

/**
 * Compare the  initial list and the moves list.
 * Print through the console the appropriate message for every move
 * @see UserList
 * @see SimpleUserList
 * @see Neighbours
 * @version /06/10/2022/
 */
public class ListComparator {
    private UserList oldList;
    private SimpleUserList movsUserList;

    public ListComparator(UserList oldList, SimpleUserList movsUserList){
        this.oldList=oldList;
        this.movsUserList=movsUserList;
    }



    public void compareLists(){
        /**
         *
         */
        for(DataSimple userSimple : movsUserList.getUsersList() ){

            Id userId =userSimple.getId();
            DataUser user = oldList.getUser(userId);
            Group userGroup= user.getGroup();

            Neighbours oldNeighbours = user.getNeighbours();

            user.setPosition(userSimple.getPosition());
            oldList.deleteUser(userId);
            oldList.addUser(user);

            Neighbours newNeighbours = oldList.getUsersInZone(userId);

            user.setNeighbours(newNeighbours);
            oldList.deleteUser(userId);
            oldList.addUser(user);

            ArrayList<DataUser> listToChange = oldList.getUsersList();
            for(DataUser i: listToChange){
                i.setNeighbours(oldList.getUsersInZone(i.getId()));
            }
            oldList.setUsersList(listToChange);

            Neighbours exitNeighbours = new Neighbours();
            Neighbours enterFriendNeighbours = new Neighbours();
            Neighbours enterEnemyNeighbours = new Neighbours();

            //Obtains the exitNeighbours
            for (Id oldOne: oldNeighbours.getNeighbours()){
                if(!newNeighbours.isNeighbour(oldOne)) exitNeighbours.addId(oldOne);
            }
            //Obtains the enterFriendsNeighbours and enterEnemyNeighbours
            for (Id newOne : newNeighbours.getNeighbours()){
                if(!oldNeighbours.isNeighbour(newOne)){
                    DataUser newUser = oldList.getUser(newOne);
                    if(userGroup.equals(newUser.getGroup()))enterFriendNeighbours.addId(newOne);
                    else enterEnemyNeighbours.addId(newOne);
                }
            }
            //print the moves of the exit-
            printMoves(userId,exitNeighbours,enterFriendNeighbours,enterEnemyNeighbours);

        }

    }
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
