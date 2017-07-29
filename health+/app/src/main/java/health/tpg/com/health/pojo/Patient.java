package health.tpg.com.health.pojo;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by rahul.pandey on 7/28/2017.
 */
@Data
public class Patient {
    @SerializedName("name")
    private String mName;
    @SerializedName("patient_id")
    private String id;
}
