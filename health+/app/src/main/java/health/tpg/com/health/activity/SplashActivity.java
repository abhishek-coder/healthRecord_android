package health.tpg.com.health.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import health.tpg.com.health.R;
import health.tpg.com.health.base.BaseActivity;
import health.tpg.com.health.util.AppConstants;
import health.tpg.com.health.util.SharedPrefsUtils;

public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String id = SharedPrefsUtils.getStringPreference(SplashActivity.this,AppConstants.Prefs.PATIENT_ID);
                Intent intent;
                if(TextUtils.isEmpty(id)) {
                     intent = new Intent(SplashActivity.this, LoginActivity.class);
                }else{
                    intent = new Intent(SplashActivity.this, PatientReportListActivity.class);
                    intent.putExtra("id",id);
                }
                startActivity(intent);
                finish();
            }
        },2000);
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
