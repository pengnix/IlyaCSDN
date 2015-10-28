package ilya.pengnix.com.ilyacsdn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import ilya.pengnix.com.ilyacsdn.adapter.BlogListAdapter;
import ilya.pengnix.com.ilyacsdn.bean.BlogItem;
import ilya.pengnix.com.ilyacsdn.bean.Blogger;
import ilya.pengnix.com.ilyacsdn.task.HttpAsyncTask;
import ilya.pengnix.com.ilyacsdn.task.OnResponseListener;
import ilya.pengnix.com.ilyacsdn.utils.JsoupUtil;
import ilya.pengnix.com.ilyacsdn.utils.URLUtil;
import me.maxwin.view.XListView;

/**
 * Created by Avazu on 2015/10/26.
 */
public class BlogListActivity extends Activity implements AdapterView.OnItemClickListener{

    public static final int MSG_PRELOAD_DATA = 1000;
    private XListView mListView;
    private Blogger mBlogger;
    private String mBlogerBaseUrl;
    private String mUserId;
    BlogListAdapter mAdapter;
    int mPage = 1;

    HttpAsyncTask mAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_bloglist);
        initData();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mAsyncTask != null){
            mAsyncTask.cancel(true);
        }
    }

    private void initData(){
        mBlogger = (Blogger)getIntent().getSerializableExtra("blogger");
        mUserId = mBlogger.getUserId();
        mBlogerBaseUrl = URLUtil.getBlogDefaultUrl(mUserId);
    }

    private void initView(){
        mAdapter = new BlogListAdapter(this);
        mListView = (XListView)findViewById(R.id.listview_blog);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        initListView();
    }

    private void initListView(){
        mHandlder.sendEmptyMessage(MSG_PRELOAD_DATA);
    }

    private void getData(int page){
        if(mAsyncTask != null){
            mAsyncTask.cancel(true);
        }
        //Log.i("pengnix3","getData");
        mAsyncTask = new HttpAsyncTask(this);
        String url = URLUtil.getBlogListURL(mBlogerBaseUrl,page);
        //Log.i("pengnix3","url = " + url);
        mAsyncTask.setOnResponseListener(mOnResponseListener);
        mAsyncTask.execute(url);
    }

    private Handler mHandlder = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_PRELOAD_DATA:
                    //Log.i("pengnix3","MSG_PRELOAD_DATA");
                    getData(mPage);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private OnResponseListener mOnResponseListener = new OnResponseListener() {
        @Override
        public void Response(String result) {
            //Log.i("pengnix3","result = " +result);
            if(result != null){
                List<BlogItem> list = JsoupUtil.getBlogItemList(result);
                mAdapter.setList(list);
            }
        }
    };


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BlogItem item = (BlogItem)mAdapter.getItem(position - 1);
        Intent i = new Intent(BlogListActivity.this,BlogContentActivity.class);
        i.putExtra("blogItem",item);
        startActivity(i);
        //Log.i("pengnix3","item is " + item.getTitle());
    }
}
