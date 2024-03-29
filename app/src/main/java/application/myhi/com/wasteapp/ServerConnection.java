package application.myhi.com.wasteapp;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

/**
 * Created by root on 6/4/19.
 */

public class ServerConnection {

    private static String MAIN_URL="http://192.168.43.101:8000/api/";
    private static AsyncHttpClient asyncHttpClient=new AsyncHttpClient();

    public static void post(String url, RequestParams params, TextHttpResponseHandler responseHandler){
        asyncHttpClient.post(AbsoluteUrl(url),params,responseHandler);
    }
    public static void get(String url, RequestParams params, TextHttpResponseHandler responseHandler){
        asyncHttpClient.get(AbsoluteUrl(url),params,responseHandler);

    }

    public static void patch(String url, RequestParams params, TextHttpResponseHandler responseHandler){
        asyncHttpClient.patch(AbsoluteUrl(url),params,responseHandler);
    }

    public static String AbsoluteUrl(String url){
        return MAIN_URL+url;
    }

}
