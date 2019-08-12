package com.example.multi_graph;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calendar.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class NineGridFragment extends Fragment {

    private static final String ARG_LIST = "list";


    private RecyclerView mRecyclerView;
    private NineGridTest2Adapter adapter;
    private List<NineGridModel> mList;

    public NineGridFragment() {
    }

    public static NineGridFragment newInstance(String name,List<NineGridModel> list) {
        NineGridFragment nineGridFragment = new NineGridFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        bundle.putSerializable(ARG_LIST, (Serializable) list);
        nineGridFragment.setArguments(bundle);
        return nineGridFragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_nine_grid, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        Bundle arguments = getArguments();
        String name = arguments.getString("name");
        if ("all".equals(name)){

            mList = (List<NineGridModel>)arguments.getSerializable(ARG_LIST);
        }else if ("youtu".equals(name)){
            List<NineGridModel> nineGridModels = (List<NineGridModel>) arguments.getSerializable(ARG_LIST);
            mList = showImage(nineGridModels);
        }


        adapter = new NineGridTest2Adapter(getContext());
        adapter.setList(mList);
        mRecyclerView.setAdapter(adapter);
    }

    private List<NineGridModel> showImage(List<NineGridModel> mList) {
        List<NineGridModel> nineGridModels = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
//            判断是否有图片路径
            if (mList.get(i).urlList.size() > 0){
                nineGridModels.add(mList.get(i));
            }
        }
        return nineGridModels;
    }

    private void initView(View inflate) {

        mRecyclerView = (RecyclerView) inflate.findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


}
