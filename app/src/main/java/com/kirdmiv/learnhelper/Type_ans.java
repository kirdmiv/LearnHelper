package com.kirdmiv.learnhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static java.lang.System.nanoTime;

public class Type_ans extends AppCompatActivity {

    TextView tvNum, tvQuest;
    Button btnAns;
    EditText user_ans;

    TextView tvAns;
    Button btnShowAns;

    ArrayList<String> ans_part = new ArrayList<>();
    ArrayList<String> quest_part = new ArrayList<>();
    ArrayList<String> tmp_a = new ArrayList<>();
    boolean firstPart = true;

    Random randGen;
    int random_ind;

    String right_ans, current_ans;

    int size, num;

    int mistakes, failedQuest;
    boolean curFail;

    String text;
    int mode;

    public static final String EXTRA_MISTAKES = "com.kirdmiv.learnhelper.MISTAKES";
    public static final String EXTRA_FAILS = "com.kirdmiv.learnhelper.FAILS";
    public static final String EXTRA_TEXT = "com.kirdmiv.learnhelper.TEXT";
    public static final String EXTRA_MODE = "com.kirdmiv.learnhelper.MODE";
    public static final String EXTRA_ACTIVITY = "com.kirdmiv.learnhelper.ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_ans);

        tvNum = (TextView) findViewById(R.id.tvNumber);
        tvQuest = (TextView) findViewById(R.id.tvQuestion);
        btnAns = (Button) findViewById(R.id.btnAns);
        user_ans = (EditText) findViewById(R.id.etAnswer);

        btnShowAns = (Button) findViewById(R.id.btnShow);
        tvAns = (TextView) findViewById(R.id.tvAnswer);

        Intent intent = getIntent();
        text = intent.getStringExtra(MainActivity.EXTRA_TEXT);
        mode = intent.getIntExtra(MainActivity.EXTRA_MODE, 1);

        randGen = new Random();

        String part = "";
        ans_part.clear();
        quest_part.clear();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '-' && firstPart) {
                //System.out.println(part);
                if (mode == 1)
                    ans_part.add(part);
                else
                    quest_part.add(part);
                part = "";
                firstPart = false;
            } else if (text.charAt(i) == '\n') {
                if (mode == 2)
                    ans_part.add(part);
                else
                    quest_part.add(part);
                part = "";
                firstPart = true;
            } else if (i + 1 < text.length() && text.charAt(i + 1) != '-') {
                if (i == 0 || text.charAt(i - 1) != '-')
                    part += text.charAt(i);
            }
        }

        mistakes = failedQuest = 0;
        curFail = false;

        num = 0;
        size = quest_part.size();

        long seed = nanoTime();
        Collections.shuffle(ans_part, new Random(seed));
        Collections.shuffle(quest_part, new Random(seed));

        setNextQuestion();

        btnAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_ans = user_ans.getText().toString();
                if (current_ans.equals(right_ans) && num <size){
                    setNextQuestion();
                    tvAns.setText(getString(R.string.TypeAns_ans));
                    curFail = false;
                }
                else if (current_ans.equals(right_ans)){
                    showResults(view);
                }
                else {
                    mistakes++;
                    if (!curFail){
                        curFail = true;
                        failedQuest++;
                    }
                }
            }
        });

        btnShowAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvAns.setText(right_ans);
            }
        });
    }

    public void showResults(View view) {
        Intent intent = new Intent(this, Results.class);
        intent.putExtra(EXTRA_MISTAKES, mistakes);
        intent.putExtra(EXTRA_FAILS, failedQuest);
        intent.putExtra(EXTRA_TEXT, text);
        intent.putExtra(EXTRA_MODE, mode);
        intent.putExtra(EXTRA_ACTIVITY, 2);
        startActivity(intent);
    }

    void setNextQuestion(){
        num++;
        tvNum.setText(String.format("Текущий вопрос: %d из %d", num, size));
        tvQuest.setText(quest_part.get(num-1));
        right_ans = ans_part.get(num-1);
        user_ans.setText("");
    }
}
