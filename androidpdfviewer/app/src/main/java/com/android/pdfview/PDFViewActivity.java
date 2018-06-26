
package com.android.pdfview;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfPasswordException;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;


public class PDFViewActivity extends Activity implements OnPageErrorListener {

    private static final String TAG = PDFViewActivity.class.getSimpleName();

    private final static int REQUEST_CODE = 1;
    public static final int PERMISSION_CODE = 2;

    private static final String SAMPLE_FILE = "sample.pdf";
    private static final String PASSWORD_PROTECTED_FILE = "sample_password_protected.pdf";
    private String DEFAULT_URL = "https://www.hq.nasa.gov/alsj/a17/A17_FlightPlan.pdf";

    private int pageNumber = 0;

    private Button btnPickPDFFromAsset;
    private Button btnPickPDFFromURL;
    private Button btnPickPasswordProtectedPDFFromAsset;
    private Button btnPickPDFFromInternalStorage;

    private Uri uri;
    private PDFView pdfView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pdfView = findViewById(R.id.pdfView);
        pdfView.setBackgroundColor(Color.LTGRAY);
        btnPickPDFFromAsset = findViewById(R.id.btnPickPDFFromAsset);
        btnPickPDFFromURL = findViewById(R.id.btnPickPDFFromURL);
        btnPickPasswordProtectedPDFFromAsset = findViewById(R.id.btnPickPasswordProtectedPDFFromAsset);
        btnPickPDFFromInternalStorage = findViewById(R.id.btnPickPDFFromInternalStorage);
        btnPickPDFFromAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPDFFromAsset();
            }
        });

        btnPickPDFFromURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayRequestURLDialog();
            }
        });

        btnPickPasswordProtectedPDFFromAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPasswordDialog();
            }
        });

        btnPickPDFFromInternalStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                launchPicker();
            }
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(PDFViewActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            uri = intent.getData();
            displayFromDeviceContentUri(uri);
        }
    }

    /**
     * Listener for response to user permission request
     *
     * @param requestCode  Check that permission request code matches
     * @param permissions  Permissions that requested
     * @param grantResults Whether permissions granted
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PERMISSION_GRANTED) {
                launchPicker();
            }
        }
    }

    @Override
    public void onPageError(int page, Throwable t) {
        Log.e(TAG, "Cannot load page " + page);
    }

    void launchPicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        try {
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            //alert user that file manager not working
            Toast.makeText(this, R.string.toast_pick_file_error, Toast.LENGTH_SHORT).show();
        }
    }

    private void displayPDFFromAsset() {
        pdfView.fromAsset(SAMPLE_FILE)
                .defaultPage(pageNumber)
                .enableAnnotationRendering(true)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) // in dp
                .onPageError(this)
                .load();
    }

    private void displayPasswordProtectedPDFFromAsset(String password) {

        pdfView.fromAsset(PASSWORD_PROTECTED_FILE)
                .defaultPage(pageNumber)
                .password(password)
                .enableAnnotationRendering(true)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) // in dp
                .onPageError(this)
                .onError(new OnErrorListener() {
                    @Override
                    public void onError(Throwable t) {
                        if (t instanceof PdfPasswordException || t.getMessage().contains("Password required or incorrect password")) {
                            displayPasswordDialog();
                        }
                    }
                })
                .load();
    }

    private void displayFromDeviceContentUri(Uri uri) {
        pdfView.fromUri(uri)
                .defaultPage(pageNumber)
                .enableAnnotationRendering(true)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) // in dp
                .onPageError(this)
                .load();
    }

    private void displayPasswordDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(15, 0, 15, 0);
        edittext.setLayoutParams(params);
        edittext.setHint("Please enter password");
        alert.setTitle("Password Protected");

        alert.setView(edittext);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                displayPasswordProtectedPDFFromAsset(edittext.getText().toString());
                dialog.dismiss();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
                dialog.dismiss();
            }
        });

        alert.show();
    }

    private void displayRequestURLDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(15, 0, 15, 0);
        edittext.setLayoutParams(params);
        edittext.setHint("Please enter URL or tap ok to get default");
        alert.setTitle("URL");

        alert.setView(edittext);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String url = edittext.getText().toString();
                if (TextUtils.isEmpty(url)) {
                    new DownloadingTask(PDFViewActivity.this, DEFAULT_URL, pdfView).execute();
                } else {
                    new DownloadingTask(PDFViewActivity.this, url, pdfView).execute();
                }
                dialog.dismiss();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
                dialog.dismiss();
            }
        });

        alert.show();
    }
}
