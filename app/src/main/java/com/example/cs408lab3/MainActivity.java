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

    private String right_side = "";
    private String left_side = "";
    private String operator = "";
    private int num1;
    private int num2;

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
                left_side = "0";
                break;
            case "+" :
                left_side = buttonText;
                break;
            case "%" :
                left_side = buttonText;
                break;
            case "\u00D7":
                left_side = buttonText;
                break;
            case "-" :
                left_side = buttonText;
                break;
            case "\u00B1":
                left_side = buttonText;
                break;
            case "." :
                left_side = buttonText;
                break;
            case "\\":
                left_side = buttonText;
                break;
            case "=" :
                left_side = buttonText;
                break;
            default:
                if(left_side == "0")
                    left_side = buttonText;
                else
                    left_side += buttonText;

        }

        output.setText(left_side);

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
