package ilya.pengnix.com.ilyacsdn.task;

import android.content.Context;
import android.os.AsyncTask;

import ilya.pengnix.com.ilyacsdn.net.HttpUtil;

/**
 * Created by Avazu on 2015/10/26.
 */
public class HttpAsyncTask extends AsyncTask<String,Void,String>{


    public HttpAsyncTask(Context context) {
        super();
    }

    private OnResponseListener onResponseListener;

    @Override
    protected String doInBackground(String... params) {
        String result = HttpUtil.httpGet(params[0]);
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(onResponseListener != null){
            onResponseListener.Response(s);
        }
    }

    public void setOnResponseListener(OnResponseListener onResponseListener){
        this.onResponseListener = onResponseListener;
    }
}
