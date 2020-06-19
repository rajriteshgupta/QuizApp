package com.example.myquiz;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;
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

public class QuestionBackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    ProgressDialog progressDialog;
    String[] questions,op1,op2,op3,op4;
    int[] answer;
    String result = null;
    String type,subject;
    QuestionBackgroundWorker(Context ctx) {
        context = ctx;
//        progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage("loading....");
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        type = params[0];
        String fetch_url = "http://192.168.43.87/quiz/fetch_question.php";
        String submit_url = "http://192.168.43.87/quiz/submit_answer.php";

        if(type.equals("fetch_question")){

            try {
                subject = params[1];
                String no_question = params[2];
                URL url = new URL(fetch_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("subject","UTF-8")+"="+URLEncoder.encode(subject,"UTF-8")+"&&"
                        +URLEncoder.encode("question","UTF-8")+"="+URLEncoder.encode(no_question,"UTF-8");
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
                Log.i("result1",result);
                return result;
            } catch (MalformedURLException e){

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if(type.equals("submit_answer")){

            try {
                String subject_score = params[1];
                String right_answer = params[2];
                String username = params[3];
                URL url = new URL(submit_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("subject_score","UTF-8")+"="+URLEncoder.encode(subject_score,"UTF-8")+"&&"
                        +URLEncoder.encode("answer","UTF-8")+"="+URLEncoder.encode(right_answer,"UTF-8")+"&&"
                        +URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");
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
                Log.i("result1",result);
                return result;
            } catch (MalformedURLException e){

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {

//        progressDialog.dismiss();
        String crappyPrefix = "null";

        if(result.startsWith(crappyPrefix)){
            result = result.substring(crappyPrefix.length(), result.length());
        }

        if(type.equals("fetch_question")){

            try {
                JSONArray jsonArray = new JSONArray(result);
                JSONObject jsonObject = null;

                questions = new String[jsonArray.length()];
                op1 = new String[jsonArray.length()];
                op2 = new String[jsonArray.length()];
                op3 = new String[jsonArray.length()];
                op4 = new String[jsonArray.length()];
                answer = new int[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    questions[i] = jsonObject.getString("question");
                    op1[i] = jsonObject.getString("option1");
                    op2[i] = jsonObject.getString("option2");
                    op3[i] = jsonObject.getString("option3");
                    op4[i] = jsonObject.getString("option4");
                    answer[i] = jsonObject.getInt("answer");
                }
                Intent intent = new Intent(context,QuestionActivity.class);
                intent.putExtra("subject",subject);
                intent.putExtra("question",questions);
                intent.putExtra("option1",op1);
                intent.putExtra("option2",op2);
                intent.putExtra("option3",op3);
                intent.putExtra("option4",op4);
                intent.putExtra("answer",answer);
                context.startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("submit_answer") && result.equals("success")){
            Toast.makeText(context,"Your answer submitted successfully",Toast.LENGTH_LONG).show();
        }
        else if(type.equals("submit_answer") && result.equals("not success")){
            Toast.makeText(context,"We are getting some error!!!",Toast.LENGTH_LONG).show();
        }
//        Toast.makeText(context, "PostExecution working", Toast.LENGTH_SHORT).show();
        Log.i("result",result);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
