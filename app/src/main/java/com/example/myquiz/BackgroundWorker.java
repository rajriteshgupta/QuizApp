package com.example.myquiz;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class BackgroundWorker extends AsyncTask<String,Void,String> {

    Session session;
    Context context;
    ProgressDialog progressDialog;
    String type,user_name,password;
    String[] name,number,email;
    int[] total_score,bda_score,cloud_score,networking_score,python_score,aptitude_score,gk_score;

    BackgroundWorker (Context ctx) {
        context = ctx;
//        progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage("loading....");
//        progressDialog.setTitle("Logging In");
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDialog.show();
    }
    @Override
    protected String doInBackground(String... params) {
        type = params[0];
        String login_url = "http://192.168.43.87/quiz/login.php";
        String signUp_url = "http://192.168.43.87/quiz/signup.php";
        String questionPost_url = "http://192.168.43.87/quiz/question_post.php";
        if(type.equals("login")) {
            try {
                user_name = params[1];
                password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                    Log.i("Loop Check",result);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("signup")) {
            try {
                String name = params[1];
                String email = params[2];
                String age = params[3];
                String number = params[4];
                user_name = params[5];
                password = params[6];
                URL url = new URL(signUp_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&&"
                        +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&&"
                        +URLEncoder.encode("age","UTF-8")+"="+URLEncoder.encode(age,"UTF-8")+"&&"
                        +URLEncoder.encode("number","UTF-8")+"="+URLEncoder.encode(number,"UTF-8")+"&&"
                        +URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(type.equals("question_post")) {
            try {
                String subject = params[1];
                String question = params[2];
                String option1 = params[3];
                String option2 = params[4];
                String option3 = params[5];
                String option4 = params[6];
                String answer = params[7];
                URL url = new URL(questionPost_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("subject","UTF-8")+"="+URLEncoder.encode(subject,"UTF-8")+"&&"
                        +URLEncoder.encode("question","UTF-8")+"="+URLEncoder.encode(question,"UTF-8")+"&&"
                        +URLEncoder.encode("option1","UTF-8")+"="+URLEncoder.encode(option1,"UTF-8")+"&&"
                        +URLEncoder.encode("option2","UTF-8")+"="+URLEncoder.encode(option2,"UTF-8")+"&&"
                        +URLEncoder.encode("option3","UTF-8")+"="+URLEncoder.encode(option3,"UTF-8")+"&&"
                        +URLEncoder.encode("option4","UTF-8")+"="+URLEncoder.encode(option4,"UTF-8")+"&&"
                        +URLEncoder.encode("answer","UTF-8")+"="+URLEncoder.encode(answer,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
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

        if(result.equals("success")){
            Toast.makeText(context,"Login Successful",Toast.LENGTH_LONG).show();
            session = new Session(context);
            session.setUsername(user_name);
            session.setPassword(password);
            session.setLogin(true);
            Intent intent = new Intent(context,MainActivity.class);
            context.startActivity(intent);
//            try {
//                String crappyPrefix = "null";
//
//                if(result.startsWith(crappyPrefix)){
//                    result = result.substring(crappyPrefix.length(), result.length());
//                }
//
//                JSONArray jsonArray = new JSONArray(result);
//                JSONObject jsonObject = null;
//
//                name = new String[jsonArray.length()];
//                number = new String[jsonArray.length()];
//                email = new String[jsonArray.length()];
//                total_score = new int[jsonArray.length()];
//                bda_score = new int[jsonArray.length()];
//                cloud_score = new int[jsonArray.length()];
//                networking_score = new int[jsonArray.length()];
//                python_score = new int[jsonArray.length()];
//                aptitude_score = new int[jsonArray.length()];
//                gk_score = new int[jsonArray.length()];
//
//                jsonObject = jsonArray.getJSONObject(0);
//                name[0] = jsonObject.getString("name");
//                number[0] = jsonObject.getString("number");
//                email[0] = jsonObject.getString("email");
//                total_score[0] = jsonObject.getInt("total_score");
//                bda_score[0] = jsonObject.getInt("bda_score");
//                cloud_score[0] = jsonObject.getInt("cloud_score");
//                networking_score[0] = jsonObject.getInt("networking_score");
//                python_score[0] = jsonObject.getInt("python_score");
//                aptitude_score[0] = jsonObject.getInt("aptitude_score");
//                gk_score[0] = jsonObject.getInt("gk_score");
//
//                Toast.makeText(context,Integer.toString(total_score[0]),Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(context,MainActivity.class);
//                intent.putExtra("name",name[0]);
//                intent.putExtra("number",number[0]);
//                intent.putExtra("email",email[0]);
//                intent.putExtra("total",total_score[0]);
//                intent.putExtra("bda",bda_score[0]);
//                intent.putExtra("cloud",cloud_score[0]);
//                intent.putExtra("networking",networking_score[0]);
//                intent.putExtra("python",python_score[0]);
//                intent.putExtra("aptitude",aptitude_score[0]);
//                intent.putExtra("gk",gk_score[0]);
//                context.startActivity(intent);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
        }
        else if(result.equals("inserted")){
            Toast.makeText(context,"Sign Up successfully",Toast.LENGTH_LONG).show();
            session = new Session(context);
            session.setUsername(user_name);
            session.setPassword(password);
            session.setLogin(true);
            Intent intent = new Intent(context,MainActivity.class);
            context.startActivity(intent);
        }
        else if(result.equals("question inserted")){
            Toast.makeText(context,"Question Uploaded Successfully",Toast.LENGTH_LONG).show();
        }
        else if(result.equals("not success")){
            Toast.makeText(context,"Login failed",Toast.LENGTH_LONG).show();
        }
        else if(result.equals("not inserted")){
            Toast.makeText(context,"Sign Up failed",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context,"Error Occurred...please try again...",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}