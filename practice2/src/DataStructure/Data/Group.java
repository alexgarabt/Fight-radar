package DataStructure.Data;
/**
 * Group class identify to what group the user belongs to.
 *
 * @version 2.0
 */
public class Group {

    private String group;

    public Group(String group){
        this.group=group;
    }

    public String getGroup(){
        return group;
    }

    /**
     * This function compare two Groups and return if are equal.
     * @param group
     * @return true if  group==Group.
     * @return false id group!=Group.
     */
    public boolean equals(Group group) {
        if(this.group==group.getGroup()) return true;
        return false;
    }
    public void setGroup(String group){
        this.group=group;
    }
    public String toString(){
        return group;
    }


}
