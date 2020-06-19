package com.example.myquiz;

import android.app.Dialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
                dialogBox(view,"BDA");
            }
        });

        CardView cloud = view.findViewById(R.id.card_view2);
        cloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBox(view,"Cloud");
            }
        });

        CardView networking = view.findViewById(R.id.card_view3);
        networking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBox(view,"Networking");
            }
        });

        CardView python = view.findViewById(R.id.card_view4);
        python.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(),"Coming soon...",Toast.LENGTH_SHORT).show();
            }
        });

        CardView aptitude = view.findViewById(R.id.card_view5);
        aptitude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(),"Coming soon...",Toast.LENGTH_SHORT).show();
            }
        });

        CardView gk = view.findViewById(R.id.card_view6);
        gk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(),"Coming soon...",Toast.LENGTH_SHORT).show();
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

    void dialogBox(final View view, final String subject){
        final Dialog dialog = new Dialog(view.getContext());
        dialog.setContentView(R.layout.custom);
        TextView text2 = dialog.findViewById(R.id.text2);
        text2.setAlpha(0.0f);
        final String[] question = {null};
        Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner);
        final String[] items = new String[] { "5", "10", "15", "20"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), R.layout.spinner_row, items);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                question[0] = items[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                question[0] = null;
            }
        });

        Button continue_ = (Button) dialog.findViewById(R.id.positive);
        Button cancel = (Button) dialog.findViewById(R.id.negative);
        continue_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                String type = "fetch_question";
                QuestionBackgroundWorker questionBackgroundWorker = new QuestionBackgroundWorker(view.getContext());
                questionBackgroundWorker.execute(type, subject, question[0]);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}



