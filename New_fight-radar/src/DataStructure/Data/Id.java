package DataStructure.Data;
/**
 *Id class is the user identifier.
 *
 * @version 2.0
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
