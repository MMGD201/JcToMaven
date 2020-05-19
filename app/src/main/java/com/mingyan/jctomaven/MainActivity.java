package com.mingyan.jctomaven;
/**20200519AndroidStudio3.5.3。Publish Maven from JCenter, fail two time. Strive try again.*/
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.mingyan.jctomc.ToastTalk;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickShort(View view){
        ToastTalk.vShort(view.getContext(), "Short Hello!");
    }

    public void clickLong(View view){
        ToastTalk.vLong(getApplicationContext(),"Long Hello!!!");
    }

}
