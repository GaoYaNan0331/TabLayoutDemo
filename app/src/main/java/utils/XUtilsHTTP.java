package utils;

import android.content.Context;
import android.widget.ListView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import adapter.CommonAdapter;
import adapter.ViewHolder;
import bean.JsonBean;
import liuhongliang2017411.tablayoutdemo.R;

/**
 * date:2017/4/12.
 * author:刘宏亮.
 * function:
 */

public class XUtilsHTTP {
    private Context context;
    private ListView lv;

    public XUtilsHTTP(Context context, ListView lv) {
        this.context = context;
        this.lv = lv;
    }

    public void showList(String uri){
        RequestParams params = new RequestParams(uri);

        org.xutils.x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析数据
                Gson gson = new Gson();
                JsonBean bean = gson.fromJson(result,JsonBean.class);
                List<JsonBean.ResultBean.DataBean> list = bean.getResult().getData();
                //将数据展示到ListView中
                lv.setAdapter(new CommonAdapter<JsonBean.ResultBean.DataBean>(context,list) {
                    @Override
                    public void convert(ViewHolder holder, JsonBean.ResultBean.DataBean item) {
                        holder.setText(R.id.textview,item.getTitle());
                        x.image().bind(holder.setImage(R.id.imageView),item.getThumbnail_pic_s());
                    }
                });

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }
}
