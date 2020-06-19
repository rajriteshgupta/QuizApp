package com.example.myquiz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

//    Intent intent;
    Session session;
    String result;
    ProgressDialog progressDialog;
    String[] name,number,email;
    int[] total_score,bda_score,cloud_score,networking_score,python_score,aptitude_score,gk_score;
    TextView mName,mNumber,mEmail,total,bda,cloud,networking,python,aptitude,gk;
    String address = "http://192.168.43.87/quiz/fetch_profile.php";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);
//        intent = getActivity().getIntent();
//        name = intent.getStringExtra("name");
//        number = intent.getStringExtra("number");
//        email = intent.getStringExtra("email");
//        total_score = intent.getIntExtra("total",0);
//        bda_score = intent.getIntExtra("bda",0);
//        cloud_score = intent.getIntExtra("cloud",0);
//        networking_score = intent.getIntExtra("networking",0);
//        python_score = intent.getIntExtra("python",0);
//        aptitude_score = intent.getIntExtra("aptitude",0);
//        gk_score = intent.getIntExtra("gk",0);
        session = new Session(view.getContext());
        mName = view.findViewById(R.id.name);
        mNumber = view.findViewById(R.id.phone);
        mEmail = view.findViewById(R.id.email);
        bda = view.findViewById(R.id.bda);
        cloud = view.findViewById(R.id.cloud);
        networking = view.findViewById(R.id.networking);
        python = view.findViewById(R.id.python);
        aptitude = view.findViewById(R.id.aptitude);
        gk = view.findViewById(R.id.gk);
        Button update = view.findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(),"Coming soon...",Toast.LENGTH_SHORT).show();
            }
        });
//
//        progressDialog = new ProgressDialog(view.getContext());
//        progressDialog.setMessage("loading....");
//        progressDialog.setTitle("Profile");
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDialog.show();

        prepareProfile();

//        progressDialog.dismiss();
        return view;
    }

    private void prepareProfile(){

        String user_name = session.getUsername();
        Log.i("username",user_name);
        try {
            URL url = new URL(address);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream= new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line=null;
            while ((line=br.readLine())!=null)
            {
                result+=line;
            }
            br.close();
            inputStream.close();
            httpURLConnection.disconnect();
            Log.i("result",result);

            try {
                String crappyPrefix = "null";

                if (result.startsWith(crappyPrefix)) {
                    result = result.substring(crappyPrefix.length(), result.length());
                }

                if(result.equals("not success")){
                    Toast.makeText(getContext(),"Data not Found",Toast.LENGTH_SHORT).show();
                }
                else {
//
//                JSONObject jsonObject = new JSONObject(result);
//                name = jsonObject.getString("name");
//                number = jsonObject.getString("number");
//                email = jsonObject.getString("email");
//                total_score = jsonObject.getInt("total_score");
//                bda_score = jsonObject.getInt("bda_score");
//                cloud_score = jsonObject.getInt("cloud_score");
//                networking_score = jsonObject.getInt("networking_score");
//                python_score = jsonObject.getInt("python_score");
//                aptitude_score = jsonObject.getInt("aptitude_score");
//                gk_score = jsonObject.getInt("gk_score");


                    JSONArray jsonArray = new JSONArray(result);
                    JSONObject jsonObject = null;

                    name = new String[jsonArray.length()];
                    number = new String[jsonArray.length()];
                    email = new String[jsonArray.length()];
                    total_score = new int[jsonArray.length()];
                    bda_score = new int[jsonArray.length()];
                    cloud_score = new int[jsonArray.length()];
                    networking_score = new int[jsonArray.length()];
                    python_score = new int[jsonArray.length()];
                    aptitude_score = new int[jsonArray.length()];
                    gk_score = new int[jsonArray.length()];

                    jsonObject = jsonArray.getJSONObject(0);
                    name[0] = jsonObject.getString("name");
                    number[0] = jsonObject.getString("number");
                    email[0] = jsonObject.getString("email");
                    total_score[0] = jsonObject.getInt("total_score");
                    bda_score[0] = jsonObject.getInt("bda_score");
                    cloud_score[0] = jsonObject.getInt("cloud_score");
                    networking_score[0] = jsonObject.getInt("networking_score");
                    python_score[0] = jsonObject.getInt("python_score");
                    aptitude_score[0] = jsonObject.getInt("aptitude_score");
                    gk_score[0] = jsonObject.getInt("gk_score");


                    mName.setText(name[0]);
                    mNumber.setText(number[0]);
                    mEmail.setText(email[0]);
                    bda.setText(Integer.toString(bda_score[0]));
                    cloud.setText(Integer.toString(cloud_score[0]));
                    networking.setText(Integer.toString(networking_score[0]));
                    python.setText(Integer.toString(python_score[0]));
                    aptitude.setText(Integer.toString(aptitude_score[0]));
                    gk.setText(Integer.toString(gk_score[0]));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e){

        } catch (IOException ex) {
            ex.printStackTrace();
        }
//        Toast.makeText(context, "PostExecution working", Toast.LENGTH_SHORT).show();
//        Log.i("result","result");
    }
}
