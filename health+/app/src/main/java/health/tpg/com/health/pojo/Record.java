package health.tpg.com.health.pojo;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by rahul.pandey on 7/28/2017.
 */
@Data
public class Record {
    @SerializedName("created")
    String date;
    @SerializedName("prescription")
    Detail prescription;
    @SerializedName("symptoms")
    String symptoms;
}
