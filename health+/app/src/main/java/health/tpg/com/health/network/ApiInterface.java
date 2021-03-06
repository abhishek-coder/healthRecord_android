package health.tpg.com.health.network;

import health.tpg.com.health.pojo.Case;
import health.tpg.com.health.pojo.Patient;
import health.tpg.com.health.pojo.PatientCaseList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rahul.pandey on 7/28/2017.
 */

public interface ApiInterface {
    @GET("/api/login")
    Call<Patient> loginbyAdhar(@Query("aadhar_number") String adhar);

    @GET("/api/patient/cases")
    Call<PatientCaseList> getListOfTreatmentById(@Query("patient_id") String patient_id);

    @GET("/users/1")
    Call<Patient> test();

    @GET("/api/patient/case")
    Call<Case> getDetailByCaseId(@Query("patient_id") String patient_id, @Query("case_id") String case_id);

}
