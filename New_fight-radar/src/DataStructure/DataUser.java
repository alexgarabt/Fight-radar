package DataStructure;

import DataStructure.Data.*;

/**
 * Class representation of the information of one user.
 * @see DataSimple
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
     * @param userInfo = "userId x y group Neighbour1 Neighbour2 ..."
     */
    public DataUser(String userInfo){
        String[] parts = userInfo.split(" ");
        this.id= new Id(parts[0]);
        this.position = new Position((Double.parseDouble(parts[1])),Double.parseDouble(parts[2]));

        if(parts[3].equals("N")) this.group = new Group(false);
        else this.group = new Group(true);

        this.neighbours = new Neighbours();
        for(int i=4; i<parts.length;i++){
            Id newNeighbour = new Id(parts[i]);
            this.neighbours.add(newNeighbour);
        }

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
        return super.toString()+", "+group.toString()+", "+neighbours.toString();
    }
}