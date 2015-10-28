package ilya.pengnix.com.ilyacsdn;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ilya.pengnix.com.ilyacsdn.bean.BlogItem;
import ilya.pengnix.com.ilyacsdn.task.HttpAsyncTask;
import ilya.pengnix.com.ilyacsdn.task.OnResponseListener;

/**
 * Created by Avazu on 2015/10/28.
 */
public class BlogContentActivity extends Activity implements OnResponseListener{

    private WebView mWebView = null;
    private BlogItem mBlogItem;
    private String mUrl;
    private static final int MSG_RELOAD_DATA = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        init();
        initComponent();
        mHandler.sendEmptyMessage(MSG_RELOAD_DATA);
    }

    private void init(){
        mBlogItem = (BlogItem) getIntent().getSerializableExtra("blogItem");
        if(mBlogItem != null){
            mUrl = mBlogItem.getLink();
        }
    }

    private void initComponent(){
        initWebView();
    }

    private void initWebView(){
        mWebView = (WebView)findViewById(R.id.webview);
        //Log.i("pengnix","url = " +mBlogItem.getLink());
        //mWebView.loadUrl(mBlogItem.getLink());
        mWebView.setWebViewClient(new BlogWebViewClient());
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case MSG_RELOAD_DATA:
                    getData(mUrl);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private void getData(String url){
        requestData(url);
    }

    private void requestData(String url){
        HttpAsyncTask httpAsyncTask = new HttpAsyncTask(this);
        httpAsyncTask.setOnResponseListener(this);
        httpAsyncTask.execute(url);
    }

    @Override
    public void Response(String result) {

    }

    class BlogWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //return super.shouldOverrideUrlLoading(view, url);
            view.loadUrl(url);
            return true;
        }
    }

}


