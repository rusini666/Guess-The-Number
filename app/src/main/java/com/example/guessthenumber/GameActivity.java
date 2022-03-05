package com.example.guessthenumber;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private TextView tries, message, textView1, textView2;
    private EditText userInput;
    private Button confirm;

    boolean twoDigits, threeDigits, fourDigits;

    Random r = new Random();
    int random;
    int lives = 10;
    ArrayList<Integer> guessesList = new ArrayList<Integer>();
    int userAttempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        tries = findViewById(R.id.tries);
        message = findViewById(R.id.message);
        userInput = findViewById(R.id.userInput);
        confirm = findViewById(R.id.confirm);

        twoDigits = getIntent().getBooleanExtra("two",false);
        threeDigits = getIntent().getBooleanExtra("three",false);
        fourDigits = getIntent().getBooleanExtra("four",false);

        if(twoDigits){
            random = r.nextInt(90)+10;
        }
        if(threeDigits){
            random = r.nextInt(900)+100;
        }
        if(fourDigits){
            random = r.nextInt(9000)+1000;
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = userInput.getText().toString();
                if (number.matches("")) {
                    Toast.makeText(GameActivity.this,"Please enter your guess!", Toast.LENGTH_SHORT).show();
                }else {
                    guessesList.add(Integer.valueOf(number));

                    textView1.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.VISIBLE);

                    textView1.setText("Your last game:  "+number);

                    tries.setVisibility(View.VISIBLE);

                    userAttempts++;
                    lives--;

                    tries.setText(String.valueOf(lives));


                    if (Integer.valueOf(number).equals(random)) {
                        message.setText("Congratulations, your guess is correct!");

                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Guessing Game");
                        builder.setCancelable(false);
                        builder.setMessage("Congratulations, my guess was "+random
                                +"\n\n You got my number in "+userAttempts
                                +" attempts. \n\n Your guesses : "+guessesList
                                +"\n\n Would you like to Play Again?");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(GameActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        builder.create().show();
                    }

                    if (Integer.parseInt(number) > random) {
                        message.setText("Guess is too high!");
                    }

                    if (Integer.parseInt(number) < random) {
                        message.setText("Guess is too low!");
                    }

                    if(lives==0){
                        message.setText("GAME OVER");
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Guessing Game");
                        builder.setCancelable(false);
                        builder.setMessage("Sorry, you ran out of tries!"
                            +" \n\n My guess was "+random
                            +" \n\n Your guesses : "+guessesList
                            +" \n\n Would you like to Play Again?");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(GameActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        builder.create().show();
                    }
                   
                }

            }
        });

    }
}