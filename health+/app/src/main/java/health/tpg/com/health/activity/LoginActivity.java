package health.tpg.com.health.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import health.tpg.com.health.R;
import health.tpg.com.health.base.BaseActivity;
import health.tpg.com.health.network.ApiClient;
import health.tpg.com.health.network.ApiInterface;
import health.tpg.com.health.pojo.Case;
import health.tpg.com.health.pojo.Patient;
import health.tpg.com.health.pojo.PatientCaseList;
import health.tpg.com.health.util.AppConstants;
import health.tpg.com.health.util.SharedPrefsUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText addharnoEt = (EditText) findViewById(R.id.addharNoTxt);
        final TextInputLayout inputLayout = (TextInputLayout) findViewById(R.id.input_layout);
        inputLayout.setErrorEnabled(true);
        findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(addharnoEt.getText()) && addharnoEt.getText().length() == 12) {
                    showLoading("Login...", false);
                    ApiInterface apiService =
                            ApiClient.getClient().create(ApiInterface.class);

                    Call<Patient> call = apiService.loginbyAdhar("123456789101");
                    call.enqueue(new Callback<Patient>() {
                        @Override
                        public void onResponse(Call<Patient> call, Response<Patient> response) {
                            hideLoading();
                            int statusCode = response.code();

                            if (statusCode == 200) {
                                Patient patient = response.body();
                                SharedPrefsUtils.setStringPreference(LoginActivity.this, AppConstants.Prefs.PATIENT_ID, patient.getId());
                                SharedPrefsUtils.setStringPreference(LoginActivity.this, AppConstants.Prefs.NAME, patient.getMName());
                                Intent intent = new Intent(LoginActivity.this, PatientReportListActivity.class);
                                intent.putExtra("id", patient.getId());
                                startActivity(intent);
                                finish();
                            }else{
                                showMessage("Something went wrong!!!");
                            }

                        }

                        @Override
                        public void onFailure(Call<Patient> call, Throwable t) {
                            hideLoading();
                            showMessage(t.getMessage());
                        }
                    });
                } else {
                    inputLayout.setError("Please enter valid number");

                }
            }
        });
        addharnoEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    protected int getResourceId() {
        return 0;
    }

    @Override
    protected int getMainFragmentContainerId() {
        return 0;
    }

    @Override
    public void initView() {

    }

}
