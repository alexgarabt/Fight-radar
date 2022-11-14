package DataStructure.UserData.DataStructure.Data;
import java.util.ArrayList;
import java.util.HashSet;
//Change to a set of Id


/**
 * Neighbours class is used to represent a list of users id.
 * @see Id
 * @version 2.0
 */
public class Neighbours {


    private ArrayList<Id> neighbours;

    public Neighbours(){
        this.neighbours = new ArrayList<Id>();
    }

    public void setNeighbours(ArrayList<Id> neighbours) {
        this.neighbours = neighbours;
    }

    /**
     * Returns if the given Id is in the Neighbours list.
     * @param id
     * @return true if (id) is in neighbours.
     * @return false if not.
     */
    public Boolean isNeighbour(Id id){
        for (Id userId: this.neighbours){
            if(id.equals(userId)) return true;
        }
        return false;
    }

    public void addId(Id id){
        neighbours.add(id);
    }

    public Id getId(int i){
        return neighbours.get(i);
    }

    public void removeId(int i){
        neighbours.remove(i);
    }

    public void clear(){
        neighbours.clear();
    }

    public int size(){
        return neighbours.size();
    }

    public String toString(){
        String result ="[";
        Id user;
        for (Id i:neighbours){
            result +=i.toString()+", ";
        }
        result+="]";
        return result;
    }

    public ArrayList<Id> getNeighbours() {
        return neighbours;
    }
}
