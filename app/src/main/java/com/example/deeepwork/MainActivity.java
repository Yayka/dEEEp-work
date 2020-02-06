package com.example.deeepwork;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private int hours = 0;
    private int mins = 0;
    private int breakFreqs = 0;
    private Spinner hours_spinner;
    private Spinner minutes_spinner;
    private Spinner breaks_spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        hours_spinner = (Spinner) findViewById(R.id.hours_spinner);
        ArrayAdapter<CharSequence> hours_adapter = ArrayAdapter.createFromResource(this,
                R.array.hours_options, android.R.layout.simple_spinner_item);
        hours_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hours_spinner.setAdapter(hours_adapter);
        hours_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                int spinner_pos = hours_spinner.getSelectedItemPosition();
                String[] hours_values = getResources().getStringArray(R.array.hours_options);
                int time = Integer.valueOf(hours_values[spinner_pos]);
                System.out.println(time);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        minutes_spinner = (Spinner) findViewById(R.id.minutes_spinner);
        ArrayAdapter<CharSequence> minutes_adapter = ArrayAdapter.createFromResource(this,
                R.array.minutes_options, android.R.layout.simple_spinner_item);
        minutes_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minutes_spinner.setAdapter(minutes_adapter);
        minutes_spinner.setOnItemSelectedListener(this);


        breaks_spinner = (Spinner) findViewById(R.id.breaks_spinner);
        ArrayAdapter<CharSequence> breaks_adapter = ArrayAdapter.createFromResource(this,
                R.array.breaks_options, android.R.layout.simple_spinner_item);
        breaks_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breaks_spinner.setAdapter(breaks_adapter);
        breaks_spinner.setOnItemSelectedListener(this);

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


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
}
