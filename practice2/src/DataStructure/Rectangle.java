package DataStructure;

import DataStructure.Data.Position;

/**
 * Class that represents a rectangle in a two-dimensional euclidean space.
 * The rectangles are parallels to the coordinate axes.
 * The rectangle is represented using the center point, the height and the width.
 * @see DataStructure.Data.Position
 * @version 1.0
 */
public class Rectangle {
    private Position center;
    private double height;
    private double width;

    public Rectangle(Position center, double height, double width){
        this.center=center;
        this.width=width;
        this.height=height;
    }

    /**
     * Look the given point is inside or in the border of this rectangle.
     * if point.x is in the range[center.x-width/2, center.x+width/2] and
     *    point.y is in the range[center.y-height/2, center.y+height/2]
     * @param point
     * @return True if the point is inside the rectangle.
     *         False if the point is outside the rectangle.
     */
    public boolean containsPoint(Position point){
        boolean xIs = xIntersects(point.getPositionX());
        boolean yIs = yIntersects(point.getPositionY());
        return (xIs && yIs);
    }

    /**
     * Look if the given x position intersects this rectangle in the x axe;
     * @param x
     * @return True if it intersects.
     *         False if not.
     */
    public boolean xIntersects(double x){
        return (x>=(center.getPositionX()-(width/2))) &&
                (x<=(center.getPositionX()+(width/2)));
    }
    /**
     * Look if the given y position intersects this rectangle in the y axe;
     * @param y
     * @return True if it intersects.
     *         False if not.
     */
    public boolean yIntersects(double y){
        return (y>=(center.getPositionY()-(height/2))) &&
                (y<=(center.getPositionY()+(height/2)));
    }

    /**
     * Look if this rectangle and the given rectangle intersects in the xy plane.
     * First look if it intersects in the x axe;
     * Second look if it intersects in the y axe;
     * @param rectangleA
     * @return True If First and Second are True.
     *         False if else.
     */
    public boolean intersectsR(Rectangle rectangleA){
    Position centerA = rectangleA.getCenter();
    double heightA = rectangleA.getHeight();
    double widthA = rectangleA.getWidth();

    boolean xAIn = xIntersects((centerA.getPositionX()+(widthA/2))) ||
                   xIntersects((centerA.getPositionX()-(widthA/2)));
    boolean xIn = rectangleA.xIntersects((center.getPositionX()+(width/2))) ||
                  rectangleA.xIntersects((center.getPositionX()-(width/2)));

    boolean yAIn = yIntersects((centerA.getPositionY()+(heightA/2))) ||
                   yIntersects((centerA.getPositionY()-(heightA/2)));
    boolean yIn = rectangleA.yIntersects((center.getPositionY()+(height/2))) ||
                  rectangleA.yIntersects((center.getPositionY()-(height/2)));

    return ((xAIn || xIn) && (yAIn || yIn));
    }

    public Position getCenter() {
        return center;
    }
    public void setCenter(Position center) {
        this.center = center;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
}
