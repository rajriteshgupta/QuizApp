package com.example.myquiz;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Fragment extends Fragment {

    private RecyclerView recyclerView,recyclerView1;

    private TextView tv;
    private HomeAdapter homeAdapter;
    private  RecyclerView.LayoutManager layoutManager,layoutManager1;
    private List<HomeViewModel> homelist= new ArrayList<>();//without new it was giving null pointer exception

    public Home_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view =inflater.inflate(R.layout.fragment_home, container, false);
        tv = view.findViewById(R.id.tt);
        CardView bda = view.findViewById(R.id.card_view1);
        bda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(),QuestionActivity.class));
            }
        });
        recyclerView = view.findViewById(R.id.rv);
        layoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setHasFixedSize(true);//
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView1 = view.findViewById(R.id.rvv);
//        layoutManager1 = new LinearLayoutManager(getContext());
//        recyclerView1.setHasFixedSize(true);//
//        recyclerView1.setLayoutManager(layoutManager1);
        homeAdapter = new HomeAdapter(getContext(),homelist);

        recyclerView.setAdapter(homeAdapter);
        prepareHomeData();
        return view;
    }

    private void prepareHomeData()
    {
        int[] images={R.drawable.download6,R.drawable.download2,R.drawable.download3,
                R.drawable.download4,R.drawable.download5};

        HomeViewModel a = new HomeViewModel(images[0],"Fact1");
        homelist.add(a);
        homelist.add(new HomeViewModel(images[1],"Fact2"));
        homelist.add(new HomeViewModel(images[2],"Fact3"));
        homelist.add(new HomeViewModel(images[3],"Fact4"));
        homelist.add(new HomeViewModel(images[4],"Fact5"));
    }
}



