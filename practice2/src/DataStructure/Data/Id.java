package DataStructure.Data;
import java.lang.Object;

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
    @Override
    public boolean equals(Object id1){
        Id id2 = (Id) id1;
        if((id2.getId()).equals(getId()))return true;
        return false;
    }

    public Id clone(){
        return new Id(this.getId());
    }

    public String toString(){
        return id;
    }

}
