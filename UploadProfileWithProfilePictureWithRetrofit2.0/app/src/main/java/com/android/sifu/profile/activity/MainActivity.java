package com.android.sifu.profile.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.sifu.profile.api.ProfileApi;
import com.android.sifu.profile.client.RetrofitClient;
import com.android.sifu.profile.model.SampleResponse;
import com.android.retrofituploadfileexample.R;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private static final int IMAGE_PICKER_REQUEST_CODE = 100;

    private EditText etFirstName;
    private EditText etLastName;
    private EditText etAddress;
    private ImageView ivThumbNail;
    private Button btnUpload;
    private Button btnPickImage;
    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkStoragePermissions(MainActivity.this);


        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etAddress = (EditText) findViewById(R.id.etAddress);
        ivThumbNail = (ImageView) findViewById(R.id.ivThumbNail);

        btnPickImage = (Button) findViewById(R.id.btnPickImage);
        btnUpload = (Button) findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etFirstName.getText().toString().isEmpty() || etLastName.getText().toString().isEmpty()
                        || etAddress.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Details", Toast.LENGTH_LONG).show();
                    return;
                }

                if(imagePath==null){
                    Toast.makeText(getApplicationContext(),"Please select image", Toast.LENGTH_LONG).show();
                    return;
                }

                uploadImage();
            }
        });
    }

    private void uploadImage() {
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("loading...");
        progressDialog.show();

        ProfileApi service = RetrofitClient.getApiService();

        File file = new File(imagePath);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("photo", file.getName(), requestFile);

        Call<SampleResponse> resultCall = service.uploadImage(
                RequestBody.create(MediaType.parse("text"), etFirstName.getText().toString()),
                RequestBody.create(MediaType.parse("text"), etLastName.getText().toString()),
                RequestBody.create(MediaType.parse("text"), etAddress.getText().toString()),
                body);

        resultCall.enqueue(new Callback<SampleResponse>() {
            @Override
            public void onResponse(Call<SampleResponse> call, Response<SampleResponse> response) {

                progressDialog.dismiss();

                // Response Success or Fail
                if (response.isSuccessful()) {
                    //Todo: Please parse your response object here
                    if(response.body().getMessage() != null && !response.body().getMessage().isEmpty()){
                        if(response.body().getMessage().equalsIgnoreCase("Success")) {
                            //Todo: you can proceed with your business logic here
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<SampleResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }


    public void showImagePopup(View view) {
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_PICK);

        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Choose image");
        startActivityForResult(chooserIntent, IMAGE_PICKER_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICKER_REQUEST_CODE) {
            if (data == null) {
                Toast.makeText(getApplicationContext(),"Unable to pick image, please try again",Toast.LENGTH_LONG).show();
                return;
            }

            Uri imageUri = data.getData();
            ivThumbNail.setImageURI(imageUri);
            imagePath = getRealPathFromURI(imageUri);
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(getApplicationContext(), contentUri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    public static void checkStoragePermissions(Activity activity) {
        // Check permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // prompt the user to grant the permission
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

}
