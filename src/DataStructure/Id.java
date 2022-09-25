package DataStructure;

public class Id {
    private String id;

    public Id(String id){
        this.id=id;
    }

    public String getId() {
        return id;
    }
    public boolean equals(Id id1){
        if((id1.getId()).equals(this.getId())) return true;
        return false;
    }

    public String toString(){
        return id;
    }

}
