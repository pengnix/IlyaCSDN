package ilya.pengnix.com.ilyacsdn;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import ilya.pengnix.com.ilyacsdn.bean.Blogger;
import ilya.pengnix.com.ilyacsdn.task.HttpAsyncTask;
import ilya.pengnix.com.ilyacsdn.task.OnResponseListener;
import ilya.pengnix.com.ilyacsdn.utils.URLUtil;
import me.maxwin.view.XListView;

/**
 * Created by Avazu on 2015/10/26.
 */
public class BlogListActivity extends Activity implements AdapterView.OnItemClickListener{

    private XListView mListView;
    private Blogger mBlogger;
    private String mBlogerBaseUrl;
    private String mUserId;

    HttpAsyncTask mAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_bloglist);
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
        mListView = (XListView)findViewById(R.id.listview_blog);
    }

    private void initListView(){

    }

    private void getData(int page){
        if(mAsyncTask != null){
            mAsyncTask.cancel(true);
        }
        mAsyncTask = new HttpAsyncTask(this);
        String url = URLUtil.getBlogListURL(mBlogerBaseUrl,page);
        mAsyncTask.setOnResponseListener(mOnResponseListener);
        mAsyncTask.execute(url);
    }

    private OnResponseListener mOnResponseListener = new OnResponseListener() {
        @Override
        public void Response(String result) {

        }
    };


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
