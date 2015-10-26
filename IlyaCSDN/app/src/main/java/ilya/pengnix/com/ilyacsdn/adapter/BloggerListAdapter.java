package ilya.pengnix.com.ilyacsdn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.List;

import ilya.pengnix.com.ilyacsdn.R;
import ilya.pengnix.com.ilyacsdn.bean.Blogger;
import ilya.pengnix.com.ilyacsdn.utils.VolleyManager;

/**
 * Created by Avazu on 2015/10/23.
 */
public class BloggerListAdapter extends BaseAdapter{

    private Context context;
    private List<Blogger> list;
    private ImageLoader mImageLoader;
    private float itemHeight;

    public BloggerListAdapter(Context context, List<Blogger> list) {
        super();
        this.context = context.getApplicationContext();
        this.list = list;
        mImageLoader = VolleyManager.getmInstance(this.context).getImageLoader();
        itemHeight = context.getResources().getDimension(R.dimen.listitem_blogger_height);
    }

    @Override
    public int getCount() {
        return list == null?0 : list.size();
    }

    @Override
    public Blogger getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.bloger_item,null);
            holder = new ViewHolder();
            holder.blogger = (ImageView)convertView.findViewById(R.id.imv_blogger);
            holder.blogTitle = (TextView)convertView.findViewById(R.id.tv_blog_title);
            holder.blogDesc = (TextView)convertView.findViewById(R.id.tv_blog_desc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.blogTitle.setText(getItem(position).getTitle());
        holder.blogDesc.setText(getItem(position).getDescription());
        mImageLoader.get(getItem(position).getImgUrl(),getImageListener(holder.blogger,context),(int)itemHeight,(int)itemHeight);

        return convertView;
    }

    private ImageLoader.ImageListener getImageListener(final ImageView imageView , final Context context){
        return new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                imageView.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
    }

    static class ViewHolder{
        ImageView blogger;
        TextView blogTitle,blogDesc;
    }
}
