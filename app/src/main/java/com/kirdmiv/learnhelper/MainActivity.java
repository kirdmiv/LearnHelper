package com.kirdmiv.learnhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    EditText paste;
    Button btnTypeAnsFirst, btnTypeAnsSecond, btnFourAnsFirst, btnFourAnsSecond;

    public static final String EXTRA_TEXT = "com.kirdmiv.learnhelper.TEXT";
    public static final String EXTRA_MODE = "com.kirdmiv.learnhelper.MODE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.tv1);
        paste = (EditText) findViewById(R.id.raw_paste);
        btnFourAnsFirst = (Button) findViewById(R.id.btnFourAnsFirst);
        btnFourAnsSecond = (Button) findViewById(R.id.btnFourAnsSecond);
        btnTypeAnsFirst = (Button) findViewById(R.id.btnTypeAnsFirst);
        btnTypeAnsSecond = (Button) findViewById(R.id.btnTypeAnsSecond);


/*
        final ArrayList<String> first_part = new ArrayList<>();
        final ArrayList<String> second_part = new ArrayList<>();

        parse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = paste.getText().toString();
                String part = "";
                first_part.clear();
                second_part.clear();
                for (int i = 0; i < text.length(); i++) {
                    if (text.charAt(i) == '-') {
                        //System.out.println(part);
                        first_part.add(part);
                        part = "";
                    } else if (text.charAt(i) == '\n') {
                        second_part.add(part);
                        part = "";
                    } else if (i + 1 < text.length() && text.charAt(i + 1) != '-') {
                        if (i == 0 || text.charAt(i - 1) != '-')
                            part += text.charAt(i);
                    }
                }
                //System.out.println(first_part);
                //System.out.println(second_part);
            }
        });
*/
        btnFourAnsFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage1(view);
            }
        });

        btnTypeAnsFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage2(view);
            }
        });

        btnFourAnsSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage3(view);
            }
        });

        btnTypeAnsSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage4(view);
            }
        });
    }


    public void sendMessage1(View view) {
        Intent intent = new Intent(this, Four_ans.class);
        String text = paste.getText().toString();
        intent.putExtra(EXTRA_TEXT, text);
        intent.putExtra(EXTRA_MODE, 1);
        startActivity(intent);
    }

    public void sendMessage2(View view) {
        Intent intent = new Intent(this, Type_ans.class);
        String text = paste.getText().toString();
        intent.putExtra(EXTRA_TEXT, text);
        intent.putExtra(EXTRA_MODE, 1);
        startActivity(intent);
    }

    public void sendMessage3(View view) {
        Intent intent = new Intent(this, Four_ans.class);
        String text = paste.getText().toString();
        intent.putExtra(EXTRA_TEXT, text);
        intent.putExtra(EXTRA_MODE, 2);
        startActivity(intent);
    }

    public void sendMessage4(View view) {
        Intent intent = new Intent(this, Type_ans.class);
        String text = paste.getText().toString();
        intent.putExtra(EXTRA_TEXT, text);
        intent.putExtra(EXTRA_MODE, 2);
        startActivity(intent);
    }

}
