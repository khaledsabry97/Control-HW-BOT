package com.ashiaa.tanmo_app;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {


    private Spinner spinner1_hours;
    private Spinner spinner2_hours;
    private Spinner spinner1_minutes;
    private Spinner spinner2_minutes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {        //onCreate start
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner1_hours = findViewById(R.id.spinner1_hours);
        spinner2_hours = findViewById(R.id.spinner2_hours);
        spinner1_minutes = findViewById(R.id.spinner1_minutes);
        spinner2_minutes = findViewById(R.id.spinner2_minutes);

        setupSpinner();

//        buttonFunctions buttonFunctionsObject = new buttonFunctions(this);
//
//        buttonFunctionsObject.OnButtonFunction();
//        buttonFunctionsObject.OffButtonFunction();

    }
    private void setupSpinner() {

        ArrayAdapter hoursSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.hours_array, android.R.layout.simple_spinner_item);

        hoursSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinner1_hours.setAdapter(hoursSpinnerAdapter);



        ArrayAdapter hoursSpinnerAdapter2 = ArrayAdapter.createFromResource(this,
                R.array.hours_array, android.R.layout.simple_spinner_item);

        hoursSpinnerAdapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinner2_hours.setAdapter(hoursSpinnerAdapter2);





        ArrayAdapter MinutesSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.Minutes_array, android.R.layout.simple_spinner_item);

        MinutesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinner1_minutes.setAdapter(MinutesSpinnerAdapter);



        ArrayAdapter MinutesSpinnerAdapter2 = ArrayAdapter.createFromResource(this,
                R.array.Minutes_array2, android.R.layout.simple_spinner_item);

        MinutesSpinnerAdapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinner2_minutes.setAdapter(MinutesSpinnerAdapter2);
    }
}
