package com.zhouq.zero.utility;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by tucker on 16/9/5.
 */
public interface DownloadApi {
  @Streaming
  @GET
  Observable<ResponseBody> downloadFile(@Url String url);
}
