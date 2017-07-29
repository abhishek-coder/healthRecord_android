package health.tpg.com.health.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Created by rahul.pandey on 7/28/2017.
 */
@Data
public class RecordDetails {
    @SerializedName("notes")
    String notes;
    @SerializedName("records")
    List<Record> recordList = new ArrayList<>();
    @SerializedName("date")
    String date;
    @SerializedName("patient")
    String patient_id;
    @SerializedName("title")
    String title;
    @SerializedName("doctor_name")
    String doctor_name;

}
