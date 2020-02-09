package com.example.deeepwork;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScalesActivity extends AppCompatActivity {

    private int weight = 0;
    TextView weightView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scales);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        weightView = (TextView) findViewById(R.id.weightField);
        weightView.setText("30");

        Button submitButton = (Button) findViewById(R.id.bt_submitWeight);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveWeight();
                finish();
            }
        });

    }

    public void saveWeight() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        this.weight = Integer.parseInt(weightView.getText().toString());

        editor.putInt("weight", this.weight);
        System.out.println("WEIGHT SET TO" + this.weight);
        editor.apply();
    }

}
