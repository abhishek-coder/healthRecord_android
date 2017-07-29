package health.tpg.com.health.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import butterknife.BindView;
import butterknife.ButterKnife;
import health.tpg.com.health.R;
import health.tpg.com.health.adapter.DetailAdapter;
import health.tpg.com.health.adapter.ReportAdapter;
import health.tpg.com.health.base.BaseActivity;
import health.tpg.com.health.network.ApiClient;
import health.tpg.com.health.network.ApiInterface;
import health.tpg.com.health.pojo.Case;
import health.tpg.com.health.pojo.Patient;
import health.tpg.com.health.pojo.PatientCaseList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rahul.pandey on 7/28/2017.
 */

public class PatientReportDetailActivity extends BaseActivity implements DetailAdapter.ItemClickListner {
    TextView title;
    TextView doctorname;
    TextView date;
    TextView notes;
    Toolbar toolbar;
    RecyclerView mRecyclerView;
    String caseId;
    private String id;
    private LinearLayout header;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        caseId = getIntent().getStringExtra("caseId");
        id = getIntent().getStringExtra("id");
        title = (TextView) findViewById(R.id.title);
        header = (LinearLayout) findViewById(R.id.header);
        doctorname = (TextView) findViewById(R.id.doctorName);
        date = (TextView) findViewById(R.id.date);
        notes = (TextView) findViewById(R.id.notes);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView =(RecyclerView)findViewById(R.id.detailList);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        requestData();

    }

    private void requestData() {
        showLoading("Featching deatils...", false);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Case> call = apiService.getDetailByCaseId(id,caseId);
        call.enqueue(new Callback<Case>() {
            @Override
            public void onResponse(Call<Case> call, Response<Case> response) {
                hideLoading();
                int statusCode = response.code();
                  if(statusCode == 200) {
                      header.setVisibility(View.VISIBLE);
                      Case gsonObj = response.body();
                      title.setText(gsonObj.getRecordDetails().getTitle());
                      doctorname.setText(gsonObj.getRecordDetails().getDoctor_name());
                      date.setText(gsonObj.getRecordDetails().getDate());
                      notes.setText(gsonObj.getRecordDetails().getNotes());
                      DetailAdapter adapter = new DetailAdapter(gsonObj.getRecordDetails().getRecordList(), PatientReportDetailActivity.this);
                      mRecyclerView.setLayoutManager(new LinearLayoutManager(PatientReportDetailActivity.this));
                      adapter.setOnSingleItemClickListener(PatientReportDetailActivity.this);
                      mRecyclerView.setAdapter(adapter);
                  }else{
                      showMessage("Something went wrong!!!");
                  }



            }

            @Override
            public void onFailure(Call<Case> call, Throwable t) {
                // Log error here since request failed
                hideLoading();
                showMessage(t.getMessage());
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
    protected void initView() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClickListner(String url) {
        Intent intent = new Intent(PatientReportDetailActivity.this,StaticWebActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }
}
