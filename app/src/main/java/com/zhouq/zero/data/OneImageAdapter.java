package com.zhouq.zero.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.zhouq.zero.R;
import java.util.List;
import java.util.Random;

/**
 * Created by tucker on 16/10/3.
 */

public class OneImageAdapter extends RecyclerView.Adapter<OneImageAdapter.ViewHolder>{

  private LayoutInflater mInflater;
  private List<Integer> mData;
  private Random random = new Random();
  public OneImageAdapter(Context context, List<Integer> data){
    mInflater = LayoutInflater.from(context);
    mData = data;
  }
  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = mInflater.inflate(R.layout.item_one_img_two_text, parent, false);
    ViewHolder viewHolder = new ViewHolder(view);

    return viewHolder;
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.text1.setText(String.valueOf(position));
    int height = 120 + random.nextInt(160);
    Log.d("wang", "onBindViewHolder: height = "+height);
    holder.text2.setHeight(height);
  }

  @Override public int getItemCount() {
    return mData.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder{
    RelativeLayout container;
    ImageView image;
    TextView text1;
    TextView text2;
    public ViewHolder(View itemView) {
      super(itemView);
      container = (RelativeLayout) itemView.findViewById(R.id.item_container);
      image = (ImageView) itemView.findViewById(R.id.item_img);
      text1 = (TextView) itemView.findViewById(R.id.item_text1);
      text2 = (TextView) itemView.findViewById(R.id.item_text2);
    }
  }
}
