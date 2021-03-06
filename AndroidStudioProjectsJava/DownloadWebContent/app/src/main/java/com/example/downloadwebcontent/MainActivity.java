package com.example.downloadwebcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    DownloadTask downloadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadTask = new DownloadTask();
        String result = null;

        try {
            result = downloadTask.execute("https://www.zappycode.com").get();
        }catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("Result", result);
    }


    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != - 1) {
                    char  current = (char) data;
                    result += current;


                    data = reader.read();
                }
                return result;

            } catch (IOException e) {
                e.printStackTrace();


                return "Failed";
            }
        }
    }



}

