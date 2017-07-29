package health.tpg.com.health.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import health.tpg.com.health.R;
import health.tpg.com.health.base.BaseActivity;
import health.tpg.com.health.network.ApiClient;

public class StaticWebActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = ApiClient.BASE_URL + getIntent().getStringExtra("url");
        if(url.contains(".pdf")){
            WebView webView=new WebView(this);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);

            //---you need this to prevent the webview from
            // launching another browser when a url
            // redirection occurs---
            webView.setWebViewClient(new Callback());

            String pdfURL = null;
            try {
                pdfURL = URLEncoder.encode(url, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            webView.loadUrl(
                    "http://docs.google.com/gview?embedded=true&url=" + pdfURL);

            setContentView(webView);
        }else {
            setContentView(R.layout.activity_web);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            WebView wv = (WebView) findViewById(R.id.webview1);
            wv.setWebViewClient(new Callback());
            WebSettings webSettings = wv.getSettings();
            webSettings.setBuiltInZoomControls(true);
            wv.getSettings().setJavaScriptEnabled(true);
            wv.getSettings().setPluginState(WebSettings.PluginState.ON);
            wv.loadUrl(
                    url);
        }



    }


    private class Callback extends WebViewClient {

        public Callback(){
            showLoading("Loading...",false);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return(false);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            hideLoading();
        }
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

    }
