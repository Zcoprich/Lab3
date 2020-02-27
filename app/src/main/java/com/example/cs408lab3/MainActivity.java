package com.example.cs408lab3;

import android.bluetooth.BluetoothGatt;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private String right_side = ""; // right side of operator
    private String left_side = ""; // left side of operator
    private String operator = ""; // math operation to perform
    private String last_input = ""; // previous input from user
    private String currentNumber; // number to appeear on screen

    private BigDecimal num1; // holds left_side number form
    private BigDecimal num2; // hold right_side number form

    private boolean equal_used = false; // determine if last input was equal
    private boolean decimal_added = false; // determine if decimal has been added

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

                resetCalculator();
                currentNumber = right_side;
                break;

            case "+" :

                if (operator == "")
                    left_side = right_side;


                else if(!equal_used && operator != "")
                    left_side = calculate(left_side, right_side, operator);

                operator = "+";
                right_side = "";
                currentNumber = left_side;

                equal_used = false;

                break;

            case "%" :

                if (operator == "")
                    left_side = right_side;


                else if(!equal_used && operator != "")
                    left_side = calculate(left_side, right_side, operator);

                operator = "%";
                right_side = "";
                currentNumber = left_side;

                equal_used = false;
                break;

            case "\u00D7":

                if (operator == "")
                    left_side = right_side;


                else if(!equal_used && operator != "")
                    left_side = calculate(left_side, right_side, operator);

                operator = "\u00D7";
                right_side = "";
                currentNumber = left_side;

                equal_used = false;

                break;

            case "-" :

                if (operator == "")
                    left_side = right_side;


                else if(!equal_used && operator != "")
                    left_side = calculate(left_side, right_side, operator);

                operator = "-";
                right_side = "";
                currentNumber = left_side;

                equal_used = false;

                break;

            case "\u00B1":
                int right = Integer.parseInt(right_side);
                right = -right;
                right_side = String.valueOf(right);

                currentNumber = right_side;

                break;

            case "." :
                break;

            case "/":

                if (operator == "")
                    left_side = right_side;


                else if(!equal_used && operator != "")
                    left_side = calculate(left_side, right_side, operator);

                operator = "/";
                right_side = "";
                currentNumber = left_side;

                equal_used = false;

                break;

            case "=" :
                if(right_side == "" && operator != "")
                    right_side = left_side;

                left_side = calculate(left_side, right_side, operator);
                currentNumber = left_side;

                equal_used = true;

                break;

            default:
                if(right_side == "0" || last_input.equals(operator))
                    right_side = buttonText;
                else
                    right_side += buttonText;

                currentNumber = right_side;

        }

        output.setText(currentNumber);
        last_input = buttonText;

    }

    public void resetCalculator(){
        right_side = "0";
        left_side = "";
        operator = "";
    }

    public String calculate (String left, String right, String operand){

        num1 = new BigDecimal(left);
        num2 = new BigDecimal(right);
        BigDecimal cal;
        BigDecimal rounded;
        String result;

        switch (operand){

            case "+":
                cal = num1.add(num2);
                break;
            case "-":
                cal = num1.subtract(num2);
                break;
            case "\u00D7":
                cal = num1.multiply(num2);
                break;
            case "/":
                cal = num1.divide(num2);
                break;
            case "%":
                cal = num1.remainder(num2);
                break;
            default:
                return "ERROR!";

        }

        rounded = cal.stripTrailingZeros();

        result = rounded.toString();
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
