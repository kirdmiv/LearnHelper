package com.kirdmiv.learnhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


import static java.lang.System.nanoTime;

public class Four_ans extends AppCompatActivity {

    TextView tvNum, tvQuest;
    Button btn1, btn2, btn3, btn4;

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
        setContentView(R.layout.activity_four_ans);

        tvNum = (TextView) findViewById(R.id.tvNumber);
        tvQuest = (TextView) findViewById(R.id.tvQuestion);

        btn1 = (Button) findViewById(R.id.btnFirst);
        btn2 = (Button) findViewById(R.id.btnSecond);
        btn3 = (Button) findViewById(R.id.btnThird);
        btn4 = (Button) findViewById(R.id.btnFourth);


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

        //System.out.println(quest_part);
        //System.out.println(ans_part);

        long seed = nanoTime();
        Collections.shuffle(ans_part, new Random(seed));
        Collections.shuffle(quest_part, new Random(seed));

        setNextQuestion();
        //System.out.println(quest_part);
        //System.out.println(ans_part);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_ans = btn1.getText().toString();
                if (current_ans.equals(right_ans) && num <size){
                    setNextQuestion();
                    curFail = false;
                }
                else if (current_ans.equals(right_ans)){
                    showResults(view);
                }
                else {
                    btn1.setClickable(false);
                    btn1.setVisibility(View.INVISIBLE);
                    mistakes++;
                    if (!curFail){
                        curFail = true;
                        failedQuest++;
                    }
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_ans = btn2.getText().toString();
                if (current_ans.equals(right_ans) && num <size){
                    setNextQuestion();
                    curFail = false;
                }
                else if (current_ans.equals(right_ans)){
                    showResults(view);
                }
                else {
                    btn2.setClickable(false);
                    btn2.setVisibility(View.INVISIBLE);
                    mistakes++;
                    if (!curFail){
                        curFail = true;
                        failedQuest++;
                    }
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_ans = btn3.getText().toString();
                if (current_ans.equals(right_ans) && num <size){
                    setNextQuestion();
                    curFail = false;
                }
                else if (current_ans.equals(right_ans)){
                    showResults(view);
                }
                else {
                    btn3.setClickable(false);
                    btn3.setVisibility(View.INVISIBLE);
                    mistakes++;
                    if (!curFail){
                        curFail = true;
                        failedQuest++;
                    }
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_ans = btn4.getText().toString();
                if (current_ans.equals(right_ans) && num <size){
                    setNextQuestion();
                    curFail = false;
                }
                else if (current_ans.equals(right_ans)){
                    showResults(view);
                }
                else {
                    btn4.setClickable(false);
                    btn4.setVisibility(View.INVISIBLE);
                    mistakes++;
                    if (!curFail){
                        curFail = true;
                        failedQuest++;
                    }
                }
            }
        });
    }

    public void showResults(View view) {
        Intent intent = new Intent(this, Results.class);
        intent.putExtra(EXTRA_MISTAKES, mistakes);
        intent.putExtra(EXTRA_FAILS, failedQuest);
        intent.putExtra(EXTRA_TEXT, text);
        intent.putExtra(EXTRA_MODE, mode);
        intent.putExtra(EXTRA_ACTIVITY, 1);
        startActivity(intent);
    }

    void setNextQuestion(){
        num++;
        tvNum.setText(String.format("Текущий вопрос: %d из %d", num, size));
        tvQuest.setText(quest_part.get(num-1));

        tmp_a.clear();
        right_ans = ans_part.get(num-1);
        tmp_a.add(ans_part.get(num-1));
        random_ind = randGen.nextInt(ans_part.size());
        tmp_a.add(ans_part.get(random_ind));
        random_ind = randGen.nextInt(ans_part.size());
        tmp_a.add(ans_part.get(random_ind));
        random_ind = randGen.nextInt(ans_part.size());
        tmp_a.add(ans_part.get(random_ind));

        long seed = nanoTime();
        Collections.shuffle(tmp_a, new Random(seed));

        btn1.setText(tmp_a.get(0));
        btn2.setText(tmp_a.get(1));
        btn3.setText(tmp_a.get(2));
        btn4.setText(tmp_a.get(3));

        btn1.setClickable(true);
        btn2.setClickable(true);
        btn3.setClickable(true);
        btn4.setClickable(true);

        btn1.setVisibility(View.VISIBLE);
        btn2.setVisibility(View.VISIBLE);
        btn3.setVisibility(View.VISIBLE);
        btn4.setVisibility(View.VISIBLE);
    }
}
