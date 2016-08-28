package com.zhouq.zero.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.zhouq.zero.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AsyncFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AsyncFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AsyncFragment extends Fragment {
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String BACKGROUNDID = "background";

  private int backgroundId;

  private OnFragmentInteractionListener mListener;

  private View mRoot;
  private Button mBtnStart;
  private TextView mTvCounter;
  private Handler counterHandler;
  MyThread worker;

  private int workerState = 0;

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param backgroundId
   * @return A new instance of fragment AsyncFragment.
   */
  public static AsyncFragment newInstance(int backgroundId) {
    AsyncFragment fragment = new AsyncFragment();
    Bundle args = new Bundle();
    args.putInt(BACKGROUNDID, backgroundId);
    fragment.setArguments(args);
    return fragment;
  }

  public AsyncFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      backgroundId = getArguments().getInt(BACKGROUNDID);
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    mRoot = inflater.inflate(R.layout.fragment_async, container, false);
    mRoot.findViewById(R.id.fragment_content).setBackgroundResource(backgroundId);
    mBtnStart = (Button) mRoot.findViewById(R.id.btn_start);
    mTvCounter = (TextView) mRoot.findViewById(R.id.tv_counter);

    worker = new MyThread(0);
    mBtnStart.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        if (workerState == 0) {
          worker.start();
          workerState = 1;
        } else if (workerState == 1) {

          worker.suspend();

          workerState = 2;
        } else {

          worker.resume();

          workerState = 1;
        }
      }
    }
   );

    return mRoot;
  }

  class MyThread implements Runnable{

    private int start;
    private Thread thread;
    public boolean isSuspend = false;

    public MyThread(int start){
      this.start = start;
    }
    @Override public void run() {
      int sum = 0;
      while (sum++ < 1000) {
        try {
          if(isSuspend){
            synchronized (this) {
              wait();
            }
          }
          //counterHandler.sendEmptyMessage(start++);
          mListener.updateCounter(start++);
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

    void start(){
      isSuspend = false;
      if(thread== null){
        thread = new Thread(this);
      }
      thread.start();
    }
    void suspend(){
      if(!isSuspend){
        isSuspend = true;
      }
    }

    synchronized void resume(){
      isSuspend = false;
      notify();
    }
  }
  @Override public void onResume() {
    super.onResume();
    counterHandler = new Handler(){
      @Override public void handleMessage(Message msg) {
        mTvCounter.setText(String.valueOf(msg.what));
      }
    };
  }

  public void updateCounter(int n){
    counterHandler.sendEmptyMessage(n);
  }
  @Override public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
    }
  }

  @Override public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  /**
   * This interface must be implemented by activities that contain this
   * fragment to allow an interaction in this fragment to be communicated
   * to the activity and potentially other fragments contained in that
   * activity.
   * <p>
   * See the Android Training lesson <a href=
   * "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface OnFragmentInteractionListener {
    void updateCounter(int count);
  }

  //Thread worker = new Thread(new Runnable() {
  //  @Override public void run() {
  //    int i = 0;
  //    while(true){
  //      counterHandler.sendEmptyMessage(i++);
  //      try {
  //        Thread.sleep(300);
  //      } catch (InterruptedException e) {
  //        e.printStackTrace();
  //      }
  //    }
  //  }
  //});
}
