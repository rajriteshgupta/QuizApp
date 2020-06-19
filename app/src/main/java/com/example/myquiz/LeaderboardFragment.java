package com.example.myquiz;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LeaderboardFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<LeaderboardViewModel> leaderboardViewModelList = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    private LeaderboardAdapter leaderboardAdapter;

    ProgressDialog progressDialog;

    String address = "http://192.168.43.87/quiz/fetchleaderboard.php";
    InputStream inputStream=null;
    String line=null;
    String result = null;
    String[] name;int[] rank;int[] score;

    TextView textView;

    public LeaderboardFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //allowing network in main thread
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        recyclerView = view.findViewById(R.id.rv);
        layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        leaderboardAdapter = new LeaderboardAdapter(leaderboardViewModelList);
        recyclerView.setAdapter(leaderboardAdapter);
        //  leaderboardViewModelList.add(new LeaderboardViewModel(1,"Shrish",100));
//
//        progressDialog = new ProgressDialog(view.getContext());
//        progressDialog.setMessage("loading....");
//        progressDialog.setTitle("Leaderboard");
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDialog.show();

        prepareLeaderboard();

//        progressDialog.dismiss();
        return view;
    }

    private void prepareLeaderboard() {
        /*String[] names = {"Shubham","Riyas","NIsha","Brajesh","Ravi","Shaniya","Chintu","Bablu","Bunty","Sonu Kumari"};
        for (int i=0;i<10;i++)
        {
            leaderboardViewModelList.add(new LeaderboardViewModel(i+1,names[i],100-i));
        }*/
        try {
            URL url = new URL(address);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            inputStream= new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            //StringBuilder stringBuilder= new StringBuilder();

            while ((line=br.readLine())!=null)/////
            {
                result+=line;
            }
            br.close();
            inputStream.close();
            httpURLConnection.disconnect();

            //          Log.d("satgeo","Bofore");

            try {
                String crappyPrefix = "null";//was https://stackoverflow.com/questions/36600076/value-null-of-type-org-json-jsonobject1-cannot-be-converted-to-jsonobject

                if(result.startsWith(crappyPrefix)){
                    result = result.substring(crappyPrefix.length(), result.length());
                }

                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject = null;

                name = new String[jsonArray.length()];
                rank = new int[jsonArray.length()];
                score = new int[jsonArray.length()];



                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    name[i] = jsonObject.getString("username");
                    score[i] = jsonObject.getInt("total_score");

                    leaderboardViewModelList.add(new LeaderboardViewModel(name[i], score[i]));

                }
                //Log.d("satge2","json values extracted");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e){

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}