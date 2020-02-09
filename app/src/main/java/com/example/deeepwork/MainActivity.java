package com.example.deeepwork;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity{

    private int hours = 0;
    private int minutes = 0;
    private int breakFreqs = 0;

    private int weight = 0;

    private Spinner hours_spinner;
    private Spinner minutes_spinner;
    private Spinner breaks_spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get phone weight if saved otherwise to set weight screen
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);

        //SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.clear();
        //sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);



        System.out.println("SOMETHING ABOUT TO HAPPEN");
        this.weight = sharedPreferences.getInt("weight", 0);

        if(weight == 0) {
            System.out.println("NEED TO WEIGHT");
            launchSetWeight();
            sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
            this.weight = sharedPreferences.getInt("weight", 0);
            System.out.println("weight" + this.weight);
        }
        else {
            System.out.println("NO NEED TO WEIGHT");
            System.out.println("weight" + this.weight);
        }
        System.out.println("SOMETHING HAPPENED");

        //if(!sharedPreferences.contains("weight")){
/*            this.weight = sharedPreferences.getInt("weight", -1);
            if (weight == -1) {*/






        hours_spinner = (Spinner) findViewById(R.id.hours_spinner);
        ArrayAdapter<CharSequence> hours_adapter = ArrayAdapter.createFromResource(this,
                R.array.hours_options, android.R.layout.simple_spinner_item);
        hours_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hours_spinner.setAdapter(hours_adapter);
        hours_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //@Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                int spinner_pos = hours_spinner.getSelectedItemPosition();
                String[] hours_values = getResources().getStringArray(R.array.hours_options);
                if (spinner_pos != 0) {
                    hours = Integer.parseInt(hours_values[spinner_pos]);
                    System.out.println("hours" + hours);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        minutes_spinner = (Spinner) findViewById(R.id.minutes_spinner);
        ArrayAdapter<CharSequence> minutes_adapter = ArrayAdapter.createFromResource(this,
                R.array.minutes_options, android.R.layout.simple_spinner_item);
        minutes_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minutes_spinner.setAdapter(minutes_adapter);
        minutes_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //@Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                int spinner_pos = minutes_spinner.getSelectedItemPosition();
                String[] minutes_values = getResources().getStringArray(R.array.minutes_options);
                if (spinner_pos != 0) {
                    minutes = Integer.parseInt(minutes_values[spinner_pos]);
                    System.out.println("minutes " + minutes);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        breaks_spinner = (Spinner) findViewById(R.id.breaks_spinner);
        ArrayAdapter<CharSequence> breaks_adapter = ArrayAdapter.createFromResource(this,
                R.array.breaks_options, android.R.layout.simple_spinner_item);
        breaks_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breaks_spinner.setAdapter(breaks_adapter);
        breaks_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //@Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                int spinner_pos = breaks_spinner.getSelectedItemPosition();
                String[] breaks_values = getResources().getStringArray(R.array.breaks_options);
                if (spinner_pos != 0) {
                    breakFreqs = Integer.parseInt(breaks_values[spinner_pos]);
                    System.out.println("breaks" + breakFreqs);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        Button buttonStart = (Button) findViewById(R.id.bt_start);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void launchSetWeight() {
        Intent intent = new Intent(this, ScalesActivity.class);
        startActivity(intent);
    }



}
