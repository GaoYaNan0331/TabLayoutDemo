package liuhongliang2017411.tablayoutdemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import fragment.MyLeftFragment;
import fragment.MyTestFragment;
import utils.Urls;

public class MainActivity extends SlidingFragmentActivity {
    private static final String[] CONTENT = new String[] { "头条", "社会", "国内", "娱乐", "体育", "军事","科技","财经","时尚","国际" };
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setTabTextColors(Color.BLACK,Color.RED);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setTabTextColors(Color.BLACK,Color.RED);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initSlidingmenu();
    }
    public void initSlidingmenu(){
        Fragment leftMenuFragment = new MyLeftFragment();
        setBehindContentView(R.layout.left_menu_frame);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.id_left_menu_frame, leftMenuFragment).commit();
        SlidingMenu menu = getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        // menu.setBehindScrollScale(1.0f);
        menu.setSecondaryShadowDrawable(R.drawable.shadow);
        //设置右边（二级）侧滑菜单
    }
    public void showLeftMenu(View view)
    {
        getSlidingMenu().showMenu();
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        ImageView imageView= (ImageView) findViewById(R.id.image_shi);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }
    class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return MyTestFragment.getInstance(Urls.arr[position % CONTENT.length]);
        }

        @Override
        public int getCount() {
            return CONTENT.length;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }
    }
}
