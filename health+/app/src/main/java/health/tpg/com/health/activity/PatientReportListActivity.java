package health.tpg.com.health.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rahul.pandey on 7/28/2017.
 */

public class PatientReportListActivity extends BaseActivity implements ReportAdapter.onItemClickListner {
    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
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


    private void loadData(boolean isfromSwipeToRefresh) {

            if (!isfromSwipeToRefresh) showLoading("Featching data...", false);
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);

            Call<Patient> call = apiService.test();
            call.enqueue(new Callback<Patient>() {
                @Override
                public void onResponse(Call<Patient> call, Response<Patient> response) {
                    hideLoading();
                    mSwipeRefreshLayout.setRefreshing(false);
                    int statusCode = response.code();
                    try {
                        AssetManager assetManager = getAssets();
                        InputStream ims = assetManager.open("file1.txt");

                        Gson gson = new Gson();
                        Reader reader = new InputStreamReader(ims);

                        PatientCaseList gsonObj = gson.fromJson(reader, PatientCaseList.class);
                        ReportAdapter adapter = new ReportAdapter(gsonObj.getCases(), PatientReportListActivity.this);
                        adapter.setOnSingleItemClickListener(PatientReportListActivity.this);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(PatientReportListActivity.this));
                        mRecyclerView.setAdapter(adapter);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onFailure(Call<Patient> call, Throwable t) {
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

        Intent intent = new Intent(PatientReportListActivity.this,PatientReportDetailActivity.class);
        intent.putExtra("caseId",caseId);
        startActivity(intent);

    }
}
