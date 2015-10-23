package ilya.pengnix.com.ilyacsdn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ilya.pengnix.com.ilyacsdn.R;
import ilya.pengnix.com.ilyacsdn.bean.Blogger;

/**
 * Created by Avazu on 2015/10/23.
 */
public class BloggerListAdapter extends BaseAdapter{

    private Context context;
    private List<Blogger> list;

    public BloggerListAdapter(Context context, List<Blogger> list) {
        super();
        this.context = context.getApplicationContext();
        this.list = list;
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
        return convertView;
    }

    static class ViewHolder{
        ImageView blogger;
        TextView blogTitle,blogDesc;
    }
}
