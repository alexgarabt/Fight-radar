package DataStructure.UserData.DataStructure.Data;
/**
 * Group class identify the user group.
 *
 * @version 2.0
 */
public class Group {

    private boolean group;

    public Group(boolean group){
        this.group=group;
    }

    public boolean getGroup(){
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
    public void setGroup(boolean group){
        this.group=group;
    }
    public String toString(){
        if (group) return "S";
        return "N";
    }


}
