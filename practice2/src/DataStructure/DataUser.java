package DataStructure;

import DataStructure.Data.*;

/**
 * Class representation of the information of one user.
 * @see DataStructure.Data.Id;
 * @see DataStructure.Data.Position;
 * @see DataStructure.Data.Neighbours
 * @see DataStructure.Data.Group
 * @version 2.0
 */
public class DataUser{

    private Id id;
    private Position position;
    private Neighbours neighbours;
    private Group group;

    public DataUser(Id id, Position position, Group group, Neighbours neighbours) {
        this.id=id;
        this.position=position;
        this.group=group;
        this.neighbours=neighbours;
    }
    /**
     * Creates a new user with the information in the userInfo.
     * @param userInfo = "userID xPosition yPosition Group neighbour1 neighbour2 ..."
     */
    public DataUser(String userInfo){
        String[] parts = userInfo.split(" ");

        this.id= new Id(parts[0]);
        this.position = new Position((Double.parseDouble(parts[1])),Double.parseDouble(parts[2]));
        this.group = new Group(parts[3]);
        this.neighbours = new Neighbours();

        for(int i=4; i<parts.length;i++){
            Id newNeighbour = new Id(parts[i]);
            this.neighbours.add(newNeighbour);
        }

    }

    /**
     * Tells if two users are in the control zone.
     * @param user2
     * @return
     */
    public boolean inZone(DataUser user2){return position.inZone(user2.getPosition());}

    /**
     * Update the user position to the movement position.
     * @param movement Move object that has the position to move.
     */
    public void move(Move movement){this.position = movement.getPosition();}


    public Id getId(){
        return id;
    }
    public void setPosition(Position pos){
        this.position=pos;
    }
    public Position getPosition(){
        return position;
    }
    public Neighbours getNeighbours(){
        return neighbours;
    }
    public void setNeighbours(Neighbours neighbours){
        this.neighbours=neighbours;
    }
    public Group getGroup(){
        return group;
    }

    public String toString(){
        return id.toString()+", "+position.toString()+", "+group.toString()+", "+neighbours.toString();
    }
}