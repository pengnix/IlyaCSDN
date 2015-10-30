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
import ilya.pengnix.com.ilyacsdn.utils.JsoupUtil;

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
        String content = JsoupUtil.getContent(result);
        //String html = adjustPicSize(content);
        loadHtml(content);
    }

    private void loadHtml(String html){
        mWebView.loadDataWithBaseURL("http://blog.csdn.net", html, "text/html", "utf-8", null);
    }

//    private String adjustPicSize(String paramString) {
//        if (TextUtils.isEmpty(paramString)) {
//            return null;
//        }
//        Element localElement = Jsoup.parse(paramString).getElementsByClass("details").get(0);
//        Iterator<?> localIterator = localElement.getElementsByTag("img").iterator();
//        while (true) {
//            if (!localIterator.hasNext())
//                return "<script type=\"text/javascript\" src=\"file:///android_asset/shCore.js\"></script><script type=\"text/javascript\" src=\"file:///android_asset/shBrushCpp.js\"></script><script type=\"text/javascript\" src=\"file:///android_asset/shBrushXml.js\"></script><script type=\"text/javascript\" src=\"file:///android_asset/shBrushJScript.js\"></script><script type=\"text/javascript\" src=\"file:///android_asset/shBrushJava.js\"></script><link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/shThemeDefault.css\"><link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/shCore.css\"><script type=\"text/javascript\">SyntaxHighlighter.all();</script>"
//                        + localElement.toString();
//            ((Element) localIterator.next()).attr("width", "100%");
//        }
//    }

    class BlogWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //return super.shouldOverrideUrlLoading(view, url);
            //view.loadUrl(url);
            return true;
        }
    }

}


