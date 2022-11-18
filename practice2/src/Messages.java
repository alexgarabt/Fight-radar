import DataStructure.DataUser;
import java.util.LinkedList;
import java.util.Iterator;
/**
 * Class that creates and stores the correct messages.
 * Structure-->LinkedList<String>.
 *
 * @see DataStructure.DataUser
 * @see DataStructure.Data.Id
 * @see DataStructure.Data.Group
 */
public class Messages {
    private LinkedList<String> messagesList;

    public Messages(){
        this.messagesList = new LinkedList<>();
    }

    /**
     * Creates the correct message for each case of move.
     * @param user the user that has moved.
     * @param userNeighbour the neighbour that enters or exits.
     * @param enters true==(if the neighbour enters), false==(if the neighbour exits)
     * @return The correct message.
     */
    public static String makeMessage(DataUser user, DataUser userNeighbour, boolean enters){
        String message =user.getId().toString();

        if(enters){//The userNeighbour enters
            message=message+"+";
            if(user.getGroup().equals(userNeighbour.getGroup())) message=message+"=";//Same group.
            else message=message+"/";//Different group.
        }
        else message=message+"-";//The userNeighbour exits.

        message=message+userNeighbour.getId().toString();
        return message;
    }

    /**
     * Add a new message to the messages list.
     * @param user the user that has moved.
     * @param userNeighbour the neighbour that enters or exits.
     * @param enters true==(if the neighbour enters), false==(if the neighbour exits)
     */
    public void addMessage(DataUser user, DataUser userNeighbour, boolean enters){
        messagesList.add(makeMessage(user,userNeighbour,enters));
    }

    public void add(String text){
        messagesList.add(text);
    }

    /**
     * Returns the iterator of the message list.
     * @return Iterator.
     */
    public Iterator iterator(){
        return messagesList.iterator();
    }

    public LinkedList<String> getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(LinkedList<String> messagesList) {
        this.messagesList = messagesList;
    }
}
