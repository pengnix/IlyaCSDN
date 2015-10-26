package ilya.pengnix.com.ilyacsdn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class HotBlogerListFragment extends Fragment{


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
        Log.i("pengnix", "size is " + mBlogerList.size());
        mListView.NotRefreshAtBegin();
        mListView.setAdapter(mAdapter);
    }

    private void initData(){
        new BlogerManager().init(getActivity().getApplicationContext(), mBlogerList, CategoryManager.CategoryName.ANDROID);
    }
}
