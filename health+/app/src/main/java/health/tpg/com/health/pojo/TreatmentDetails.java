package health.tpg.com.health.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import lombok.Data;

/**
 * Created by rahul.pandey on 7/28/2017.
 */
@Data
public class TreatmentDetails {
    @SerializedName("date")
    String date;
    @SerializedName("title")
    String title;
    @SerializedName("doctor_name")
    String doctor_name;
    @SerializedName("id")
    String id;
}
