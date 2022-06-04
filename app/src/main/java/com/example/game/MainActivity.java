package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;

import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;


public class MainActivity<i> extends AppCompatActivity {

    List<Integer> operands;
    List<String> operation;
    List<Double> ans;
    GridLayout grid1, grid2, grid3, grid4, grid5, grid6;
    RelativeLayout Mainscreen;
    TextView live,points;
    String[] operators = {"+", "-", "x", "/"};
    String chosen;
    int lives = 3, score = 0;

    public void create() {

        operands = new ArrayList<Integer>();
        ans = new ArrayList<Double>();
        operation = new ArrayList<String>();
        int k = 0;
        while (k<10) {
            int rand = new Random().nextInt(100) + 1;
            boolean repeat = operands.contains(rand);
            if (repeat) {
                k--;
            }
            else
            {
                operands.add(rand);
            }
            k++;
        }
        for (int i = 0; i < grid2.getChildCount(); i++) {
            TextView cell2 = (TextView) grid2.getChildAt(i);
            int random = new Random().nextInt(4);
            cell2.setText(operators[random]);
            operation.add(operators[random]);
        }
        Log.i("operation",String.valueOf(operation));

        for (int i = 0; i < 5; ++i) {
            double operand1 = operands.get(2 * i);
            double operand2 = operands.get((2 * i) + 1);
            if (Objects.equals(operation.get(i), "+")) {
                double sum = operand1 + operand2;
                ans.add(sum);
            } else if (Objects.equals(operation.get(i), "-")) {
                double diff = operand1 - operand2;
                ans.add(diff);
            } else if (Objects.equals(operation.get(i), "x")) {
                double prod = operand1 * operand2;
                ans.add(prod);
            } else if (Objects.equals(operation.get(i), "/")) {
                double quo = operand1 / operand2;
                ans.add(quo);
            }
            Log.i("ans",String.valueOf(ans));

        }
        Collections.shuffle(operands);
        for (int i = 0; i < grid6.getChildCount(); i++) {
            Button child = (Button) grid6.getChildAt(i);
            child.setEnabled(true);
            child.setText(String.valueOf(operands.get(i)));
            child.setBackgroundColor(Color.BLUE);
        }
        for (int i = 0; i < grid5.getChildCount(); i++) {
            TextView box5 = (TextView) grid5.getChildAt(i);
            box5.setText(String.valueOf(ans.get(i)));
        }
        for (int i = 0; i < grid3.getChildCount(); i++)
        {
            TextView box3 = (TextView) grid3.getChildAt(i);
            box3.setText("");
        }
        for (int i = 0; i < grid1.getChildCount(); i++) {
            Button box1 = (Button) grid1.getChildAt(i);
            box1.setText("");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid1 = (GridLayout) findViewById(R.id.Grid1);
        grid2 = (GridLayout) findViewById(R.id.Grid2);
        grid3 = (GridLayout) findViewById(R.id.Grid3);
        grid4 = (GridLayout) findViewById(R.id.Grid4);
        grid5 = (GridLayout) findViewById(R.id.Grid5);
        grid6 = (GridLayout) findViewById(R.id.Grid6);
        Mainscreen = (RelativeLayout) findViewById(R.id.relative);
        live = (TextView) findViewById(R.id.score);
        points=(TextView) findViewById(R.id.points);
        Switch switching = findViewById(R.id.switch1);
        switching.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Mainscreen.setBackgroundColor(Color.BLACK);
                }
                else
                {
                    Mainscreen.setBackgroundColor(Color.WHITE);
                }
            }
        }
        );

        create();
    }


    public void highlight (View view) {
        view.setBackgroundColor(Color.GRAY);
        Button a = (Button) view;
        chosen =  (String) a.getText();
        a.setEnabled(false);
        for (int i =0;i<grid6.getChildCount();i++) {
            Button box1 = (Button) grid6.getChildAt(i);
            box1.setClickable(false);
        }
    }
    public void print (View view) {
        Button a = (Button) view;
        if (a.getText()=="") {
            a.setText(chosen);
            chosen="";
            for (int i =0;i<grid6.getChildCount();i++) {
                Button box1 = (Button) grid6.getChildAt(i);
                box1.setClickable(true);
            }
        }
        else{
            for (int i =0;i<grid6.getChildCount();i++){
                Button box1 = (Button)grid6.getChildAt(i);
                if (box1.getText()==a.getText())
                {
                    box1.setBackgroundColor(Color.BLUE);
                    a.setText("");
                    box1.setEnabled(true);
                }
            }

    }
    }
    public void Check(View view) {
        try {
            int x = 0;
            DecimalFormat decimalFormat = new DecimalFormat("#.000");
            for (int i = 0; i < grid2.getChildCount(); i++) {
                Button box1 = (Button) grid1.getChildAt(i);
                double operand1 = Double.parseDouble(String.valueOf(box1.getText()));
                TextView box2 = (TextView) grid2.getChildAt(i);
                String operate = String.valueOf(box2.getText());
                Button box3 = (Button) grid3.getChildAt(i);
                double operand2 = Double.parseDouble(String.valueOf(box3.getText()));
                TextView box5 = (TextView) grid5.getChildAt(i);
                double answer = Double.parseDouble(String.valueOf(box5.getText()));

                if (operate.equals("+")) {
                    if (operand1 + operand2 == answer) {
                        x += 1;
                    }
                }
                if (operate.equals("-")) {
                    if (operand1 - operand2 == answer) {
                        x += 1;
                    }
                }
                if (operate.equals("x")) {
                    if (operand1 * operand2 == answer) {
                        x += 1;
                    }
                }
                if (operate.equals("/")) {
                    if (operand1 / operand2 == answer) {
                        x += 1;
                    }
                }
            }
            if (x != 5) {
                lives -= 1;
                live.setText("Lives: " + String.valueOf(lives));
                Toast.makeText(this, "You lose a life", Toast.LENGTH_LONG).show();

            } else {
                score += 10;
                points.setText("Score: " + String.valueOf(score));
                Toast.makeText(this, "Congrats,You got it all right!", Toast.LENGTH_LONG).show();
                create();
            }

            if (lives == 0)
            {
                    Intent intent = new Intent(this,EndActivity.class);
                    intent.putExtra("Scoreinnewpage",score);
                    score=0;
                    lives=3;
                    startActivity(intent);

            };
        }
        catch(Exception e)
        {
            Toast.makeText(this,"Enter Answer Properly",Toast.LENGTH_LONG).show();
        }
    }

}