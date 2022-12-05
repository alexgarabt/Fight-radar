package DataStructure;

import DataStructure.Data.Position;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * This class represents a point in the map.
 * Position, object the information that stores the point.
 * And a pointer to the list that stores the point.
 */
public class Point{
    private Position position;
    private Object object;
    private List<Point> pointer;

    public Point(Object object, Position position){
        this.object=object;
        this.position=position;
    }
    public Point(@NotNull DataUser user){
        this.object = user;
        this.position = user.getPosition();
    }

    /**
     * Delete this point object from the list pointed.
     * @return
     */
    public boolean removePointInList(){
        if (pointer==null)return false;
        return pointer.remove(this);
    }

    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public Object getObject() {
        return object;
    }
    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return position.toString()+", ";
    }
}