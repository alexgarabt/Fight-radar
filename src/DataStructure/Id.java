package DataStructure;
/**
 *Id class has the purpose of identifying each user in the data structure.
 *The user is identified by the id.
 *
 * getId() Returns the String associated.
 * equals() Compare a id between another.
 * @version /26/09/2022/
 */

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
