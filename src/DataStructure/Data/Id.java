package DataStructure.Data;
/**
 *Id class has the purpose of identifying each user in the data structure.
 *The user is identified by the id.
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

    /**
     * Boolean function that returns if is id1==Id.
     * @param id1 Id to compare.
     * @return true(id1==Id) or false(id1!==Id).
     */
    public boolean equals(Id id1){
        if((id1.getId()).equals(this.getId())) return true;
        return false;
    }

    public String toString(){
        return id;
    }

}
