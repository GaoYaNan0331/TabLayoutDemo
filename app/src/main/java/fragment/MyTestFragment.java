package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.xllistviewlhl10.XListView;

import liuhongliang2017411.tablayoutdemo.R;
import utils.XUtilsHTTP;

/**
 * date:2017/4/12.
 * author:刘宏亮.
 * function:
 */

public class MyTestFragment extends Fragment implements XListView.IXListViewListener{

    private XListView mXlistView;
    private String mUri;

    public static Fragment getInstance(String uri){
        MyTestFragment fragment = new MyTestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("uri",uri);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        Bundle bundle = getArguments();
        mUri = bundle.getString("uri");
        initData(mUri);

    }

    private void initData(String uri) {

        new XUtilsHTTP(getActivity(),mXlistView).showList(uri);
    }

    private void initView() {
        mXlistView = (XListView) getView().findViewById(R.id.frag_xlist);
        mXlistView.setPullRefreshEnable(true);
        mXlistView.setPullLoadEnable(true);
        mXlistView.setXListViewListener(this);
    }

    @Override
    public void onRefresh() {
        onLoad();
    }

    @Override
    public void onLoadMore() {
        onLoad();
    }
    public void onLoad(){
        mXlistView.stopLoadMore();
        mXlistView.stopRefresh();
    }
}
