package com.zhouq.zero;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.zhouq.zero.utility.DownloadApi;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Subscriber;

public class DownloaderActivity extends AppCompatActivity {

  @BindView(R.id.url) EditText mUrl;
  @BindView(R.id.progress_download) ProgressBar mProgress;
  @OnClick(R.id.fab) void download(){
    Snackbar.make(getCurrentFocus(), "wuwula", Snackbar.LENGTH_LONG)
        .setAction("Action", null)
        .show();
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_downloader);
    ButterKnife.bind(this);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }

  private void downloadFile(String url){
    Retrofit retrofit = new Retrofit.Builder().build();

    DownloadApi downloadApi = retrofit.create(DownloadApi.class);
    downloadApi.downloadFile(url);

  }
}
