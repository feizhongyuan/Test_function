package com.example.multi_graph;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.calendar.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewExampleActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] mUrls = new String[]{"http://d.hiphotos.baidu.com/image/h%3D200/sign=201258cbcd80653864eaa313a7dca115/ca1349540923dd54e54f7aedd609b3de9c824873.jpg",
            "http://img3.fengniao.com/forum/attachpics/537/165/21472986.jpg",
            "http://d.hiphotos.baidu.com/image/h%3D200/sign=ea218b2c5566d01661199928a729d498/a08b87d6277f9e2fd4f215e91830e924b999f308.jpg",
            "http://img4.imgtn.bdimg.com/it/u=3445377427,2645691367&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=2644422079,4250545639&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=1444023808,3753293381&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=882039601,2636712663&fm=21&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=4119861953,350096499&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2437456944,1135705439&fm=21&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=3251359643,4211266111&fm=21&gp=0.jpg",
            "http://img4.duitang.com/uploads/item/201506/11/20150611000809_yFe5Z.jpeg",
            "http://img5.imgtn.bdimg.com/it/u=1717647885,4193212272&fm=21&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2024625579,507531332&fm=21&gp=0.jpg"};


    private ViewPager mViewPager;

    private List<NineGridModel> mList = new ArrayList<>();
    private List<Fragment> fragments;
    private TextView tv_all;
    private TextView tv_youtu;
    private RelativeLayout rl_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_example);

        initView();
        initData();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);

        tv_all = (TextView) findViewById(R.id.tv_all);
        tv_all.setOnClickListener(this);
        tv_youtu = (TextView) findViewById(R.id.tv_youtu);
        tv_youtu.setOnClickListener(this);
        rl_1 = (RelativeLayout) findViewById(R.id.rl_1);
        rl_1.setOnClickListener(this);
    }

    private void initData() {

        initListData();

        fragments = new ArrayList<>();
        fragments.add(NineGridFragment.newInstance("all",mList));
        fragments.add(NineGridFragment.newInstance("youtu",mList));
        FragmentAdapter2 fragmentAdapter = new FragmentAdapter2(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(fragmentAdapter);
        mViewPager.setCurrentItem(0);

    }

    private void initListData() {
        NineGridModel model1 = new NineGridModel();
        model1.urlList.add(mUrls[0]);
        model1.setName("name1");
        model1.setTime("2019-07-22");
        model1.setContent("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        mList.add(model1);

        NineGridModel model2 = new NineGridModel();
        model2.urlList.add(mUrls[4]);
        model2.setName("name2");
        model2.setTime("2019-07-12");
        model2.setContent("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        mList.add(model2);
//
//        NineGridModel model3 = new NineGridModel();
//        model3.urlList.add(mUrls[2]);
//        mList.add(model3);

        NineGridModel model4 = new NineGridModel();
        for (int i = 0; i < mUrls.length; i++) {
            model4.urlList.add(mUrls[i]);
        }
        model4.setName("name3");
        model4.setTime("2019-07-02");
        model4.setContent("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        model4.isShowAll = true;
        mList.add(model4);

        NineGridModel model5 = new NineGridModel();
        for (int i = 0; i < mUrls.length; i++) {
            model5.urlList.add(mUrls[i]);
        }
        model5.setName("name4");
        model5.setTime("2019-07-10");
        model5.setContent("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        model5.isShowAll = true;//显示全部图片
        mList.add(model5);

        NineGridModel model6 = new NineGridModel();
        for (int i = 0; i < 9; i++) {
            model6.urlList.add(mUrls[i]);
        }
        model6.setName("name5");
        model6.setTime("2019-07-10");
        model6.setContent("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        mList.add(model6);

        NineGridModel model7 = new NineGridModel();

        model7.setName("name6");
        model7.setTime("2019-07-10");
        model7.setContent("没有图可放了。。。。。。。。。。。。。。。。。。");

        mList.add(model7);

        NineGridModel model8 = new NineGridModel();

        model8.setName("name7");
        model8.setTime("2019-07-11");
        model8.setContent("我也没有图可放了。。。。。。。。。。。。。。。。。。");
        mList.add(model8);

        for (int i = 8; i < 19; i++) {
            NineGridModel model9 = new NineGridModel();
            model9.setName("name" + i);
            model9.setTime("2019-07-20");
            model9.setContent("我也没有图可放了。。。。。。。。。。。。。。。。。。");
            mList.add(model9);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_all:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.tv_youtu:
                mViewPager.setCurrentItem(1);
                break;
                default:
                    break;
        }
    }
}
