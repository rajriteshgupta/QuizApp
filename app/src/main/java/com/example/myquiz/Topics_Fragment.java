package com.example.myquiz;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Topics_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Topics_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Topics_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Topics_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Topics_Fragment newInstance(String param1, String param2) {
        Topics_Fragment fragment = new Topics_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_topics_, container, false);

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

        return view;
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
//                Intent intent = new Intent(view.getContext(),BlankActivity.class);
//                intent.putExtra("subject",subject);
//                intent.putExtra("no_question",question[0]);
//                startActivity(intent);
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
