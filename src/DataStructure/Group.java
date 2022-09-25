package DataStructure;

public class Group {
    private boolean group;

    public Group(boolean group){
        this.group=group;
    }

    public boolean getGroup(){
        return group;
    }

    public boolean equals(Group group) {
        if(this.group==group.getGroup()) return true;
        return false;
    }
    public void setGroup(boolean group){
        this.group=group;
    }
    public String toString(){
        if (group) return "true";
        return "false";
    }


}
