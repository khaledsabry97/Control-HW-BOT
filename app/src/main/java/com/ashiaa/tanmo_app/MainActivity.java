package com.ashiaa.tanmo_app;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {        //onCreate start
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonFunctions buttonFunctionsObject = new buttonFunctions(this);

        buttonFunctionsObject.OnButtonFunction();
        buttonFunctionsObject.OffButtonFunction();


    }                                                           //onCreate end

}       //MainActivity end
