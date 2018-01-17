package eu.lmre.baptiste.evalandroid;

/**
 * Created by lemairba on 17/01/18.
 */
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PeopleResult {
    @SerializedName("next")
    private String nextPage;

    @SerializedName("results")
    private List<People> peoples;

    public List<People> getPeoples() {
        return peoples;
    }
    public String getNextPage() {
        return nextPage;
    }
}
