package com.zhouq.zero.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.zhouq.zero.R;
import com.zhouq.zero.data.ItemDecorationOneImage;
import com.zhouq.zero.data.OneImageAdapter;
import java.util.Arrays;

public class RecyclerActivity extends AppCompatActivity {

  private RecyclerView mRecyclerView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recycler);
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

    initRecyclerView();
  }

  private void initRecyclerView() {
    mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    mRecyclerView.setAdapter(new OneImageAdapter(this,
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)));

    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    //StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
    mRecyclerView.setLayoutManager(layoutManager);
    ItemDecorationOneImage itemDecoration = new ItemDecorationOneImage(this, ItemDecorationOneImage.VERTICAL_LIST);
    itemDecoration.setHeight(2);
    mRecyclerView.addItemDecoration(itemDecoration);
  }
}
