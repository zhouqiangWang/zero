package com.zhouq.zero;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import com.zhouq.zero.fragment.AsyncFragment;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * This is a test of running 1000+ AsyncTask at the same time.
 * which is come from a question in a phone Interview...
 * The answer is absolutely YES!
 *
 * 2016-08-26
 */
public class AsyncTestActivity extends AppCompatActivity implements AsyncFragment.OnFragmentInteractionListener{

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_async_test);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    //inti two fragment
    AsyncFragment fragmentTop = AsyncFragment.newInstance(android.R.color.holo_green_light);
    AsyncFragment fragmentBottom = AsyncFragment.newInstance(android.R.color.holo_orange_light);
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.add(R.id.fragment_top, fragmentTop);
    transaction.add(R.id.fragment_bottom, fragmentBottom);
    transaction.commit();

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Intent intent = new Intent(AsyncTestActivity.this, MainActivity.class);
        AsyncTestActivity.this.startActivity(intent);
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show();
      }
    });
  }

  @Override public void updateCounter(int count) {
    AsyncFragment fragment =
        (AsyncFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_bottom);
    fragment.updateCounter(count);

    AsyncTask<Integer, Void, Void> task = new AsyncTask<Integer, Void, Void>() {
      @Override protected Void doInBackground(Integer... integers) {
        int start = integers[0];
        while (true){
          Log.d("wang","Thread-"+Thread.currentThread().getId()+" : doInBackground: "+(start++));
          try {
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    };

    task.executeOnExecutor(mExecutor, count);

  }

  Executor mExecutor = Executors.newCachedThreadPool();
}
