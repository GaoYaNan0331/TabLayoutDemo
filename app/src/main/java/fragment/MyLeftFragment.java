package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bean.Bean;
import liuhongliang2017411.tablayoutdemo.R;

/**
 * date:2017/4/12.
 * author:刘宏亮.
 * function:
 */

public class MyLeftFragment extends Fragment {
    private ListView mListView;
    private List<Bean> mList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.leftmenu,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        mListView.setAdapter(new MyBaseAdapter());

    }
    private void initData() {
        mList.add(new Bean(android.R.drawable.ic_menu_search,"搜索"));
        mList.add(new Bean(android.R.drawable.star_big_on,"收藏"));
        mList.add(new Bean(android.R.drawable.sym_action_chat,"收藏"));
        mList.add(new Bean(android.R.drawable.sym_action_chat,"收藏"));
        mList.add(new Bean(android.R.drawable.ic_menu_search,"搜索"));
        mList.add(new Bean(android.R.drawable.star_big_on,"收藏"));
        mList.add(new Bean(android.R.drawable.sym_action_chat,"收藏"));
        mList.add(new Bean(android.R.drawable.sym_action_chat,"收藏"));


    }

    private void initView() {
        mListView = (ListView)getView().findViewById(R.id.main_listview);

    }
    class MyBaseAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView ==null){
                convertView=View.inflate(getActivity(),R.layout.lv_item,null);
                viewHolder=new ViewHolder();
                viewHolder.mImageView= (ImageView) convertView.findViewById(R.id.lv_image);
                viewHolder.mTextView= (TextView) convertView.findViewById(R.id.lv_text);
                convertView.setTag(viewHolder);
            }else{
                viewHolder= (ViewHolder) convertView.getTag();
            }
            viewHolder.mTextView.setText(mList.get(position).getName());
            viewHolder.mImageView.setImageResource(mList.get(position).getImage());
            return convertView;
        }
    }
    class ViewHolder{
        TextView mTextView;
        ImageView mImageView;
    }
}
