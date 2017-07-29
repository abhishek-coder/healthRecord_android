package health.tpg.com.health.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Created by rahul.pandey on 7/28/2017.
 */
@Data
public class PatientCaseList {
    @SerializedName("count")
    int count;
    @SerializedName("cases")
    private List<TreatmentDetails> cases = new ArrayList<TreatmentDetails>();

}
