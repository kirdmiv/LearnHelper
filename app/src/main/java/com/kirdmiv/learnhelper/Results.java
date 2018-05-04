package com.kirdmiv.learnhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Results extends AppCompatActivity {

    TextView tvMistakes, tvFails;
    Button again, new_test;

    String text;
    int mode, activity;

    public static final String EXTRA_TEXT = "com.kirdmiv.learnhelper.TEXT";
    public static final String EXTRA_MODE = "com.kirdmiv.learnhelper.MODE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        int mistakes = intent.getIntExtra(Four_ans.EXTRA_MISTAKES, 0);
        int fails = intent.getIntExtra(Four_ans.EXTRA_FAILS, 0);
        text = intent.getStringExtra(Four_ans.EXTRA_TEXT);
        mode = intent.getIntExtra(Four_ans.EXTRA_MODE, 1);
        activity = intent.getIntExtra(Four_ans.EXTRA_ACTIVITY, 1);

        tvMistakes = (TextView) findViewById(R.id.tvMistakes);
        tvFails = (TextView) findViewById(R.id.tvFails);
        again = (Button) findViewById(R.id.btnAgain);
        new_test = (Button) findViewById(R.id.btnNew);

        tvMistakes.setText(String.format("Количество ошибок: %d", mistakes));
        tvFails.setText(String.format("Количество вопросов, на которые ответил " +
                "не с первого раза: %d", fails));

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start_again(view);
            }
        });

        new_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start_new(view);
            }
        });
    }

    public void start_again(View view){
        Intent intent;
        if (activity == 1)
            intent = new Intent(this, Four_ans.class);
        else
            intent = new Intent(this, Type_ans.class);
        intent.putExtra(EXTRA_TEXT, text);
        intent.putExtra(EXTRA_MODE, mode);
        startActivity(intent);
    }

    public void start_new(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
