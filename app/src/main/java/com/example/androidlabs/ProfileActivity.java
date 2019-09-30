package com.example.androidlabs;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class ProfileActivity extends Activity {

    private Intent it;
    private EditText et;
    private ImageButton mImageButton;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private int requestCode;
    private int resultCode;
    private Intent data;

    public static final String ACTIVITY_NAME = "PROFILE_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        Log.e(ACTIVITY_NAME,"in function : onCreate");

        it = getIntent();
        String email = it.getStringExtra("email");

        et = findViewById(R.id.emailInput);
        et.setText(email);

        mImageButton = findViewById(R.id.takePictureImgBtn);
        mImageButton.setOnClickListener(new takePictureBtnListener());
    }

    private class takePictureBtnListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            this.dispatchTakePictureIntent();
        }

        private void dispatchTakePictureIntent(){
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(ACTIVITY_NAME,"in function : onActivityResult");
        if(requestCode==REQUEST_IMAGE_CAPTURE&&resultCode==RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageButton.setImageBitmap(imageBitmap);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(ACTIVITY_NAME,"in function : onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(ACTIVITY_NAME,"in function : onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(ACTIVITY_NAME,"in function : onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(ACTIVITY_NAME,"in function : onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(ACTIVITY_NAME,"in function : onDestroy");
    }
}
