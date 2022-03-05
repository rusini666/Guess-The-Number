package com.example.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private RadioButton radio2, radio3, radio4;
    private Button start;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        radio4 = findViewById(R.id.radio4);
        start = findViewById(R.id.start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GameActivity.class);
                if(!radio2.isChecked() && !radio3.isChecked() && !radio4.isChecked()){
                    Snackbar.make(view,"Please select one of the options!",Snackbar.LENGTH_LONG).show();
                }else{
                    if(radio2.isChecked()){
                        intent.putExtra("two",true);
                    }
                    if(radio3.isChecked()){
                        intent.putExtra("three",true);
                    }
                    if(radio4.isChecked()){
                        intent.putExtra("four",true);
                    }
                    startActivity(intent);
                }
            }
        });
    }
}