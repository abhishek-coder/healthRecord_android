package health.tpg.com.health.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import health.tpg.com.health.R;
import health.tpg.com.health.adapter.ReportAdapter;
import health.tpg.com.health.base.BaseActivity;
import health.tpg.com.health.network.ApiClient;
import health.tpg.com.health.network.ApiInterface;
import health.tpg.com.health.pojo.Patient;
import health.tpg.com.health.pojo.PatientCaseList;
import health.tpg.com.health.util.SharedPrefsUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rahul.pandey on 7/28/2017.
 */

public class PatientReportListActivity extends BaseActivity implements ReportAdapter.onItemClickListner {
    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        id = getIntent().getStringExtra("id");
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        loadData(false);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                loadData(true);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu
        return true;
    }


    private void loadData(boolean isfromSwipeToRefresh) {

        if (!isfromSwipeToRefresh) showLoading("Featching data...", false);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<PatientCaseList> call = apiService.getListOfTreatmentById(id);
        call.enqueue(new Callback<PatientCaseList>() {
            @Override
            public void onResponse(Call<PatientCaseList> call, Response<PatientCaseList> response) {
                hideLoading();
                mSwipeRefreshLayout.setRefreshing(false);
                int statusCode = response.code();

                if (statusCode == 200) {
                    PatientCaseList gsonObj = response.body();
                    ReportAdapter adapter = new ReportAdapter(gsonObj.getCases(), PatientReportListActivity.this);
                    adapter.setOnSingleItemClickListener(PatientReportListActivity.this);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(PatientReportListActivity.this));
                    mRecyclerView.setAdapter(adapter);
                } else {
                    showMessage("Something went wrong!!!");
                }


            }

            @Override
            public void onFailure(Call<PatientCaseList> call, Throwable t) {
                // Log error here since request failed
                mSwipeRefreshLayout.setRefreshing(false);
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
    public void onItemClickListner(String caseId) {

        Intent intent = new Intent(PatientReportListActivity.this, PatientReportDetailActivity.class);
        intent.putExtra("caseId", caseId);
        intent.putExtra("id", id);
        startActivity(intent);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                showLoading("Logout..",false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SharedPrefsUtils.clearPref(PatientReportListActivity.this);
                        finish();
                    }
                },2000);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
