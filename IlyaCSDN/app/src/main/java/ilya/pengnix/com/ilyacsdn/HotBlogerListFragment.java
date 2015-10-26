package ilya.pengnix.com.ilyacsdn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import ilya.pengnix.com.ilyacsdn.adapter.BloggerListAdapter;
import ilya.pengnix.com.ilyacsdn.bean.Blogger;
import ilya.pengnix.com.ilyacsdn.config.BlogerManager;
import ilya.pengnix.com.ilyacsdn.config.CategoryManager;
import me.maxwin.view.XListView;

/**
 * Created by Avazu on 2015/10/22.
 */
public class HotBlogerListFragment extends Fragment
            implements AdapterView.OnItemClickListener{


    XListView mListView;
    List<Blogger> mBlogerList = new ArrayList<Blogger>();
    BloggerListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hotbloger_list_layout,null);
        initData();
        setupView(view);
        return view;
    }

    private void setupView(View root){
        mListView = (XListView)root.findViewById(R.id.bloger_list_view);
        mAdapter = new BloggerListAdapter(getActivity(),mBlogerList);
        //Log.i("pengnix", "size is " + mBlogerList.size());
        mListView.NotRefreshAtBegin();
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    private void initData(){
        new BlogerManager().init(getActivity().getApplicationContext(), mBlogerList, CategoryManager.CategoryName.ANDROID);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Blogger bloger = (Blogger)parent.getAdapter().getItem(position);
        Intent intent = new Intent(getActivity(), BlogListActivity.class);
        intent.putExtra("blogger",bloger);
        startActivity(intent);
    }
}
