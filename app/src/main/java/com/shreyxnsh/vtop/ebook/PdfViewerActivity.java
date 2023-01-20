package com.shreyxnsh.vtop.ebook;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
import com.github.barteksc.pdfviewer.PDFView;
import com.shreyxnsh.vtop.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PdfViewerActivity extends AppCompatActivity {

    private String url;
    private PDFView pdfView;
    private LottieAnimationView pdf_progress;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        url = getIntent().getStringExtra("pdfUrl");
        pdfView = findViewById(R.id.pdfView);
        pdf_progress = findViewById(R.id.pdf_progress);

        new PdfDownload().execute(url);


    }

    private class PdfDownload extends AsyncTask<String, Void, InputStream>{

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;

            try {
                URL url = new URL(strings[0]);
                // building connection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                // if connection is succesful
                if (urlConnection.getResponseCode() == 200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).load();
            pdf_progress.setVisibility(View.GONE);
        }

        protected void onProgressUpdate(Integer... progress) {
            setProgress(progress[0]);
        }
    }
}