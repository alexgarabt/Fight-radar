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
     * Returns if the given Id is in the Neighbours list.
     * @param id
     * @return true if (id) is in neighbours.
     * @return false if not.
     */
    public Boolean isNeighbour(Id id){
        return neighbours.contains(id);
    }

    public void add(Id id){
        neighbours.add(id);
    }

    public void remove(Id id){
        neighbours.remove(id);
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
        Id user;
        Iterator iterator = getIterator();
        while(iterator.hasNext()){
            result= iterator.next().toString()+",";
        }
        result+="]";
        return result;
    }
}