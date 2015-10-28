package ilya.pengnix.com.ilyacsdn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ilya.pengnix.com.ilyacsdn.R;
import ilya.pengnix.com.ilyacsdn.bean.BlogItem;

/**
 * Created by Avazu on 2015/10/27.
 */
public class BlogListAdapter extends BaseAdapter{

    private LayoutInflater layoutInflater;
    private Context mContext;
    private List<BlogItem> list;
    private ViewHolder holder;

    public BlogListAdapter(Context context) {
        super();
        this.mContext = context.getApplicationContext();
        this.layoutInflater = LayoutInflater.from(this.mContext);
        list = new ArrayList<BlogItem>();
    }

    public void setList(List<BlogItem> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public void addList(){
        this.list.addAll(list);
    }

    public void clearList(){
        this.list.clear();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.blog_item,null);
            holder = new ViewHolder();
            holder.title = (TextView)convertView.findViewById(R.id.blog_title);
            holder.date = (TextView)convertView.findViewById(R.id.blog_date);
            holder.content = (TextView)convertView.findViewById(R.id.blog_content);
            holder.img = (ImageView)convertView.findViewById(R.id.blog_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        BlogItem item = list.get(position);
        if(item != null){
            holder.title.setText(item.getTitle());
            holder.content.setText("\b\b\b\b\b\b\b"+item.getContent());
            holder.date.setText(item.getDate());
            holder.img.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    static private class ViewHolder{
        TextView title;
        TextView date;
        TextView content;
        ImageView img;
    }
}
