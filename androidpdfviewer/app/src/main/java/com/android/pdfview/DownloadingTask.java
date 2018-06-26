package com.android.pdfview;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadingTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = DownloadingTask.class.getSimpleName();

    private String downloadUrl;
    private String downloadFileName;
    private ProgressDialog progressDialog;

    File storageFile = null;
    File outputFile = null;
    private Context context;
    private PDFView pdfView;

    public DownloadingTask(Context context, String downloadUrl, PDFView pdfView) {
        this.context = context;
        this.downloadUrl = downloadUrl;
        this.pdfView = pdfView;
        downloadFileName = createFileName(downloadUrl);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Downloading...");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Void result) {
        try {
            if (outputFile != null) {
                progressDialog.dismiss();
                Toast.makeText(context, "Downloaded Successfully", Toast.LENGTH_SHORT).show();
                displayFromUri(Uri.fromFile(outputFile));
            } else {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 3000);

                Log.e(TAG, "Download Failed");

            }
        } catch (Exception e) {
            e.printStackTrace();

            //Change button text if exception occurs

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            }, 3000);
            Log.e(TAG, "Download Failed with Exception - " + e.getLocalizedMessage());

        }


        super.onPostExecute(result);
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        try {
            URL url = new URL(downloadUrl);//Create Download URl
            HttpURLConnection c = (HttpURLConnection) url.openConnection();//Open Url Connection
            c.setRequestMethod("GET");//Set Request Method to "GET" since we are grtting data
            c.connect();//connect the URL Connection

            //If Connection response is not OK then show Logs
            if (c.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.e(TAG, "Server returned HTTP " + c.getResponseCode()
                        + " " + c.getResponseMessage());

            }

            //Get File if SD card is present
            if (isSDCardPresent()) {
                storageFile = new File(
                        Environment.getExternalStorageDirectory() + "/"
                                + "PDF_FILES");
            } else {
                Toast.makeText(context, "Oops!! There is no SD Card.", Toast.LENGTH_SHORT).show();
            }

            //If File is not present create directory
            if (!storageFile.exists()) {
                storageFile.mkdir();
                Log.e(TAG, "Directory Created.");
            }

            outputFile = new File(storageFile, downloadFileName);//Create Output file in Main File

            //Create New File if not present
            if (!outputFile.exists()) {
                outputFile.createNewFile();
                Log.e(TAG, "File Created");
            }

            FileOutputStream fos = new FileOutputStream(outputFile);//Get OutputStream for NewFile Location

            InputStream is = c.getInputStream();//Get InputStream for connection

            byte[] buffer = new byte[1024];//Set buffer type
            int len1 = 0;//init length
            while ((len1 = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);//Write new file
            }

            //Close all connection after doing task
            fos.close();
            is.close();

        } catch (Exception e) {

            //Read exception if something went wrong
            e.printStackTrace();
            outputFile = null;
            Log.e(TAG, "Download Error Exception " + e.getMessage());
        }

        return null;
    }

    private void displayFromUri(Uri uri) {
        pdfView.fromUri(uri)
                .defaultPage(0)
                .enableAnnotationRendering(true)
                .scrollHandle(new DefaultScrollHandle(this.context))
                .spacing(10) // in dp
                .load();
    }

    //Create file name by picking download file name from URL
    private String createFileName(String URL) {
        return downloadUrl.substring(URL.lastIndexOf('/'), URL.length());
    }


    //Check If SD Card is present or not method
    private boolean isSDCardPresent() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}
