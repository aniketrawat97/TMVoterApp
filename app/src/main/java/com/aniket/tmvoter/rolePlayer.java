package com.aniket.tmvoter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class rolePlayer extends AppCompatActivity {

    DatabaseHelper mydb;

    Spinner plus1;
    Spinner plus2;
    Spinner plus3;
    Spinner plus4;

    TextView tmofday;
    TextView ttmaster;
    TextView genevaluater;
    TextView grammerian;

    TextView tmofdaytitle;
    TextView ttmastertitle;
    TextView genevaluatertitle;
    TextView grammeriantitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roleplayer);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        mydb = new DatabaseHelper(this);

        plus1 = (Spinner) findViewById(R.id.plus1);
        plus2 = (Spinner) findViewById(R.id.plus2);
        plus3 = (Spinner) findViewById(R.id.plus3);
        plus4 = (Spinner) findViewById(R.id.plus4);

        tmofdaytitle = (TextView) findViewById(R.id.tmofday);
        ttmastertitle = (TextView) findViewById(R.id.ttmaster);
        genevaluatertitle = (TextView) findViewById(R.id.generalevaluater);
        grammeriantitle = (TextView) findViewById(R.id.grammerian);

        tmofday = (TextView) findViewById(R.id.tmofdayname);
        ttmaster = (TextView) findViewById(R.id.ttmastername);
        genevaluater = (TextView) findViewById(R.id.genralevaluaterName);
        grammerian = (TextView) findViewById(R.id.grammerianname);


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("TM Aniket Rawat");
        categories.add("TM Sonia Keswani");
        categories.add("TM Zakkiuddin Gorakhpurwala");
        categories.add("TM Rahul Kumar Sharma");
        categories.add("TM Badal Singh");
        categories.add("TM Aniket jain");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        plus1.setAdapter(dataAdapter);
        plus2.setAdapter(dataAdapter);
        plus3.setAdapter(dataAdapter);
        plus4.setAdapter(dataAdapter);



        //spinner1
        plus1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                // Notify the selected item text
                tmofday.setText(item);


                mydb.insertdata(item,tmofdaytitle.getText().toString(),"R",0);
                Toast.makeText(getApplicationContext(),"data stored",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //spinner2
        plus2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                // Notify the selected item text
                ttmaster.setText(item);

                mydb.insertdata(item,tmofdaytitle.getText().toString(),"R",0);
                Toast.makeText(getApplicationContext(),"data stored",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //spinner3
        plus3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                // Notify the selected item text
                genevaluater.setText(item);

                mydb.insertdata(item,genevaluatertitle.getText().toString(),"A",0);
                Toast.makeText(getApplicationContext(),"data stored",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //spinner4
        plus4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                // Notify the selected item text
                grammerian.setText(item);

                mydb.insertdata(item,grammeriantitle.getText().toString(),"R",0);
                Toast.makeText(getApplicationContext(),"data stored",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



}
