package DataStructure;

public class Position {

    private double x;
    private double y;

    public Position(double x, double y){
        this.x=x;
        this.y=y;
    }

    public double getPositionX(){
        return x;
    }

    public double getPositionY(){
        return y;
    }

    public boolean inZone(Position pos){
        if(this.distance(pos)<4) return true;
        return false;
    }

    public double distance(Position pos){
        return Math.sqrt((Math.pow((this.x-pos.getPositionX()), 2))+
                (Math.pow((this.y-pos.getPositionY()), 2)));
    }
}
