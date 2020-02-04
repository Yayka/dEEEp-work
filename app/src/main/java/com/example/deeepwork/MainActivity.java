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


public class MainActivity extends AppCompatActivity {



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

        Spinner hours_spinner = (Spinner) findViewById(R.id.hours_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> hours_adapter = ArrayAdapter.createFromResource(this,
                R.array.hours_options, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        hours_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        hours_spinner.setAdapter(hours_adapter);

        Spinner minutes_spinner = (Spinner) findViewById(R.id.minutes_spinner);
        ArrayAdapter<CharSequence> minutes_adapter = ArrayAdapter.createFromResource(this,
                R.array.minutes_options, android.R.layout.simple_spinner_item);
        minutes_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minutes_spinner.setAdapter(minutes_adapter);

        Spinner breaks_spinner = (Spinner) findViewById(R.id.breaks_spinner);
        ArrayAdapter<CharSequence> breaks_adapter = ArrayAdapter.createFromResource(this,
                R.array.breaks_options, android.R.layout.simple_spinner_item);
        breaks_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breaks_spinner.setAdapter(breaks_adapter);
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
}
