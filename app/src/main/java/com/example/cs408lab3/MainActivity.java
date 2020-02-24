package com.example.cs408lab3;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private String right_side = ""; // right side of operator
    private String left_side = ""; // left side of operator
    private String operator = ""; // math operation to perform
    private String calculation = ""; // result of performing the operation
    private String last_input = ""; // previous input from user

    private int num1; // holds left_side number form
    private int num2; // hold right_side number form

    private boolean equal_used = false; // determine if last input was equal
    private boolean math_in_progress = false; // determine if calculations are being done

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

        TextView output = (TextView) findViewById(R.id.output);
        output.setText("0");
    }

    public void buttonClicked(View v) {

        TextView output = (TextView) findViewById(R.id.output);
        String buttonText = ((Button) v).getText().toString();

        switch (buttonText) {

            case "\u221A":

                left_side = buttonText;
                break;

            case "C":

                right_side = "";
                left_side = "";
                operator = "";
                break;

            case "+" :

                if (operator == ""){
                    operator = "+";
                    left_side = right_side;

                }
                else if(equal_used){

                    operator = "+";
                    left_side = calculation;
                    right_side = "";

                }
                else
                {
                    calculation = calculate(left_side, right_side, operator);
                    operator = "+";
                    left_side = calculation;
                }

                equal_used = false;

                break;

            case "%" :
                left_side = buttonText;
                break;

            case "\u00D7":

                if (operator == ""){
                    operator = "\u00D7";
                    left_side = right_side;

                }
                else if(equal_used){

                    operator = "\u00D7";
                    left_side = calculation;
                    right_side = "";

                }
                else
                {
                    calculation = calculate(left_side, right_side, operator);
                    operator = "\u00D7";
                    left_side = calculation;
                }

                equal_used = false;

                break;

            case "-" :
                if (operator == ""){
                    operator = "-";
                    left_side = right_side;

                }
                else if(equal_used){

                    operator = "-";
                    left_side = calculation;
                    right_side = "";

                }
                else
                {
                    calculation = calculate(left_side, right_side, operator);
                    operator = "-";
                    left_side = calculation;
                }

                equal_used = false;

                break;

            case "\u00B1":
                int right = Integer.parseInt(right_side);
                right = -right;
                right_side = String.valueOf(right);
                break;

            case "." :
                left_side = buttonText;
                break;

            case "/":
                left_side = buttonText;
                break;

            case "=" :
                if(right_side == "" && operator != "")
                {
                    right_side = left_side;
                    calculation = calculate(left_side, right_side, operator);
                    left_side = calculation;
                }
                else{
                    calculation = calculate(left_side, right_side, operator);
                    left_side = calculation;
                }

                equal_used = true;

                break;

            default:
                if(right_side == "0" || last_input.equals(operator))
                    right_side = buttonText;
                else
                    right_side += buttonText;

        }

        if(left_side == "" && right_side == "")
            output.setText("0");
        else if(last_input.equals(operator))
            output.setText(calculation);

        output.setText(calculation);

        last_input = buttonText;

    }

    public String calculate (String left, String right, String operand){

        num1 = Integer.parseInt(left);
        num2 = Integer.parseInt(right);
        int cal;
        String result;

        switch (operand){

            case "+":
                cal = num1 + num2;
                result = String.valueOf(cal);
                break;
            case "-":
                cal = num1 - num2;
                result = String.valueOf(cal);
                break;
            case "\u00D7":
                cal = num1 * num2;
                result = String.valueOf(cal);
                break;
            case "\\":
                cal = num1 / num2;
                result = String.valueOf(cal);
                break;
            default:
                result = "Not avaliable!";

        }

        return result;
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
