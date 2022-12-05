
package DataStructure;
import DataStructure.Data.Position;
import java.util.ArrayList;

/**
 *      N
 *  w       E
 *      S
 *
 * Quadtree structure is used for spatial indexing, stores the positions of the users.
 */
public class QuadTree {
    // Arbitrary constant to indicate how many elements can be stored in this quad tree node
    private static final int MAX_POINTS = 4;
    // Represent the rectangle of this quadtree
    Rectangle quadrant;
    // Points in this quad tree node
    private ArrayList<Point> points;

    // Children
    QuadTree northWest;
    QuadTree northEast;
    QuadTree southWest;
    QuadTree southEast;

    public QuadTree(Rectangle quadrant){
        this.quadrant=quadrant;
        points = new ArrayList<>(MAX_POINTS);
    }

    /**
     * Insert a point into the QuadTree in the correct quadrant.
     * @param point
     * @return True could be inserted
     *         False if not cloud be inserted.
     */
    public boolean insert(Point point){
        if(point==null)return false;
        Position position = point.getPosition();
        // Ignore objects that do not belong in this quadtree
        if (!quadrant.containsPoint(position)) return false; // object cannot be added
        // If there is space in this quadtree and if is a leaf, add the object here.
        if (points.size() < MAX_POINTS && isLeaf()) {
            points.add(point);
            return true;
        }
        // Otherwise, subdivide and then add the point to whichever node will accept it
        if (isLeaf()) subdivide();

        //Try to insert the new point
        if (northWest.insert(point)) return true;
        if (northEast.insert(point)) return true;
        if (southWest.insert(point)) return true;
        if (southEast.insert(point)) return true;

        // Otherwise, the point cannot be inserted for some unknown reason (this should never happen)
        return false;
    }

    /**
     * Create four children that fully divide this quad into four quads of equal area
     * add all points in this quadrant to appropriate sub-quadrants
     * clear points list.
     */
    private void subdivide(){

        //Create new sub quadrants and assign them.
        Position xyCenter = quadrant.getCenter();
        double x,y;
        x=xyCenter.getPositionX();
        y=xyCenter.getPositionY();
        double height = (quadrant.getHeight())/2;
        double width = (quadrant.getWidth())/2;
        double x1,y1,x2,y2,x3,y3,x4,y4;

        x1=x-width/2;
        y1=y-height/2;
        Position x1y1 = new Position(x1,y1);
        southWest = new QuadTree(new Rectangle(x1y1,height,width));

        x2=x-width/2;
        y2=y+height/2;
        Position x2y2 = new Position(x2,y2);
        northWest = new QuadTree(new Rectangle(x2y2,height,width));

        x3=x+width/2;
        y3=y-height/2;
        Position x3y3 = new Position(x3,y3);
        southEast = new QuadTree(new Rectangle(x3y3,height,width));

        x4=x+width/2;
        y4=y+height/2;
        Position x4y4 = new Position(x4,y4);
        northEast = new QuadTree(new Rectangle(x4y4,height,width));

        //Assign the points en position to their new quadrant.
        for(Point p: points){
            if(northWest.insert(p));
            else if(northEast.insert(p));
            else if(southWest.insert(p));
            else if(southEast.insert(p));
        }
        //Clear the points in this quadrant.
        points.clear();
    }

    /**
     * Return if the current quadrant is a leaf or not
     * @return True if childs==null else False.
     */
    public boolean isLeaf(){
        return northWest==null;
    }

    /**
     * Search all points in that fit in the given rectangle range.
     * @param range range to search points.
     * @return List with all the points in the quadtree that fit in the range
     */
    public ArrayList<Point> queryRange(Rectangle range){
        ArrayList<Point> results = new ArrayList<>();
        // check if region intersects with this quadrant
        if (!quadrant.intersectsR(range)) {
            return results;
        }
        // check if quadrant has sub-quadrants
        if (!isLeaf()) {
            // search sub-quadrants for points within the specified region
            results.addAll(northWest.queryRange(range));
            results.addAll(northEast.queryRange(range));
            results.addAll(southWest.queryRange(range));
            results.addAll(southEast.queryRange(range));
        }
        else {
            // check if points in this quadrant are within the specified region
            for (Point p : points) if (range.containsPoint(p.getPosition())) results.add(p);
        }
        return results;
    }


    public void print() {
        System.out.print("QUATREE=={\n");
        for(Point p:points){
            System.out.print(p+"\n");
        }
        System.out.print("}\n");
        if(isLeaf())return;
        northWest.print();
        southWest.print();
        northEast.print();
        southEast.print();
    }
    public ArrayList getPoints() {
        return points;
    }
    public void setPoints(ArrayList points) {
        this.points = points;
    }

}

