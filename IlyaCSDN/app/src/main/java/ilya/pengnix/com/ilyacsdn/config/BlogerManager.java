package ilya.pengnix.com.ilyacsdn.config;

import android.content.Context;

import java.util.List;

import ilya.pengnix.com.ilyacsdn.R;
import ilya.pengnix.com.ilyacsdn.bean.Blogger;

/**
 * Created by Avazu on 2015/10/23.
 */
public class BlogerManager {

    private Blogger parseBlogger(String[] array){
        Blogger bgr = new Blogger();
        bgr.setUserId(array[0]);
        bgr.setTitle(array[1]);
        bgr.setDescription(array[2]);
        bgr.setImgUrl(array[3]);
        bgr.setLink(array[4]);
        bgr.setType(array[5]);
        bgr.setIsTop(0);
        bgr.setIsNew(0);
        bgr.setCategory(CategoryManager.CategoryName.MOBILE);
        return bgr;
    }


    public void init(Context context, List<Blogger> list, String type) {

        if(list == null){
            return;
        }
        list.clear();

        list.add(parseBlogger(context.getResources().getStringArray(R.array.blog_zhanghongyang)));
        list.add(parseBlogger(context.getResources().getStringArray(R.array.yayun)));
        list.add(parseBlogger(context.getResources().getStringArray(R.array.wwj)));
        list.add(parseBlogger(context.getResources().getStringArray(R.array.coder_pig)));
        list.add(parseBlogger(context.getResources().getStringArray(R.array.gao_chun)));
        list.add(parseBlogger(context.getResources().getStringArray(R.array.jiangwei0910410003)));
        list.add(parseBlogger(context.getResources().getStringArray(R.array.guolin_blog)));
        list.add(parseBlogger(context.getResources().getStringArray(R.array.singwhatiwanna)));
        list.add(parseBlogger(context.getResources().getStringArray(R.array.cym492224103)));

        list.add(parseBlogger(context.getResources().getStringArray(R.array.eclipsexys)));
        list.add(parseBlogger(context.getResources().getStringArray(R.array.zhaokaiqiang1992)));
        list.add(parseBlogger(context.getResources().getStringArray(R.array.xiaanming)));

    }
}
