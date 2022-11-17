package DataStructure.Data;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Neighbours class is used to represent a list of users id.
 * @see Id
 * @version 2.0
 */
public class Neighbours {

    private HashSet neighbours;

    public Neighbours(){
        this.neighbours = new HashSet();
    }

    /**
     * Returns if the given Id has the same string that one in the Neighbours set.
     * @param id
     * @return true if (id) is in neighbours.
     * @return false if not.
     */
    public Boolean isNeighbour(Id id){
        return neighbours.contains(id.getId());
    }

    /**
     * The set store the string of the id to HashSet could compare.
     * @param id
     */
    public void add(Id id){
        //neighbours.add(id.clone()); adds a objectec that is not comparable by HashSet.contains();
        neighbours.add(id.getId());
    }

    /**
     * Remove the string in the set that are equals to the string id.getId()
     * @param id
     */
    public void remove(Id id){
        neighbours.remove(id.getId());
    }

    public void clear(){
        neighbours.clear();
    }

    /**
     * Creates a clone on this Neighbour object.
     * @return the Neighbours clone.
     */
    public Neighbours clone(){
        Neighbours clone = new Neighbours();
        clone.setNeighbours((HashSet) neighbours.clone());
        return clone;
    }

    /**
     * Return a iterator of the HashSet.
     * @return
     */
    public Iterator getIterator(){
        return neighbours.iterator();
    }

    /**
     * Tells if neighbours set is empty
     * @return true, if is empty.
     * false, if is not empty.
     */
    public boolean isEmpty(){
        return neighbours.isEmpty();
    }

    public void setNeighbours(HashSet neighbours) {
        this.neighbours = neighbours;
    }
    public HashSet getNeighbours() {
        return neighbours;
    }

    public String toString(){
        String result ="[";
        String user;
        Iterator iterator = getIterator();
        while(iterator.hasNext()){
            user = (String) iterator.next();
            result= result + user.toString()+",";
        }
        result+="]";
        return result;
    }
}
