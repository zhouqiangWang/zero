package com.zhouq.zero.utility;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.Log;

/**
 * Created by tucker on 16/10/2.
 */

public class Utils {
  private Utils(){

  }
  static private class SingletonHolder{
    static Utils instance = new Utils();
  }
  public static Utils getInstance(){
    return SingletonHolder.instance;
  }
  private static boolean isDebugable;
  private static boolean hasDebugInit = false;
  public static boolean isDebug(Context context){
    Log.d("wang", "isDebug: build.TYPE = " + Build.TYPE+", flag = "+context.getApplicationInfo().flags+", & = "+(context.getApplicationInfo().flags&ApplicationInfo.FLAG_DEBUGGABLE));
    if(!hasDebugInit){
      isDebugable = Build.TYPE.equals("eng")
          || (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }
    return isDebugable;
  }
}
