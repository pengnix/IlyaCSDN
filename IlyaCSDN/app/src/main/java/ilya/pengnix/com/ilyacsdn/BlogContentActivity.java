package ilya.pengnix.com.ilyacsdn;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ilya.pengnix.com.ilyacsdn.bean.BlogItem;

/**
 * Created by Avazu on 2015/10/28.
 */
public class BlogContentActivity extends Activity{

    private WebView mWebView = null;
    private BlogItem mBlogItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        init();
    }

    private void init(){
        mBlogItem = (BlogItem) getIntent().getSerializableExtra("blogItem");
        if(mBlogItem != null){
            initWebView();
        }

    }

    private void initWebView(){
        mWebView = (WebView)findViewById(R.id.webview);
        Log.i("pengnix","url = " +mBlogItem.getLink());
        mWebView.loadUrl(mBlogItem.getLink());
        mWebView.setWebViewClient(new BlogWebViewClient());
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


