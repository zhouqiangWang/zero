package com.zhouq.zero.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import com.zhouq.zero.R;

public class Activity3 extends AppCompatActivity {

  private static final String TAG = Activity3.class.getSimpleName()+"-wang";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d(TAG, "onCreate: "+savedInstanceState);
    setContentView(R.layout.activity_3);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show();
      }
    });
    findViewById(R.id.activity_stack).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), StacksActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
      }
    });
    findViewById(R.id.activity2).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), Activity2.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
      }
    });
    findViewById(R.id.activity3).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), Activity3.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
      }
    });
  }

  @Override protected void onNewIntent(Intent intent) {
    Log.d(TAG, "onNewIntent: "+intent);
    super.onNewIntent(intent);
  }

  @Override protected void onRestart() {
    Log.d(TAG, "onRestart: ");
    super.onRestart();
  }

  @Override protected void onStart() {
    Log.d(TAG, "onStart: ");
    super.onStart();
  }

  @Override protected void onResume() {
    Log.d(TAG, "onResume: ");
    super.onResume();
  }

  @Override protected void onPause() {
    Log.d(TAG, "onPause: ");
    super.onPause();
  }

  @Override protected void onStop() {
    Log.d(TAG, "onStop: ");
    super.onStop();
  }

  @Override protected void onDestroy() {
    Log.d(TAG, "onDestroy: ");
    super.onDestroy();
  }
}
