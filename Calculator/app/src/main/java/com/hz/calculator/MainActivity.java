package com.hz.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonAdd, buttonSubtract, buttonDivide, buttonMultiply, buttonClean;
    private TextView operation, result;
    private EditText number1, number2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAdd = (Button)findViewById(R.id.buttonAdd);
        buttonSubtract = (Button)findViewById(R.id.buttonSubtract);
        buttonDivide = (Button)findViewById(R.id.buttonDivide);
        buttonMultiply = (Button)findViewById(R.id.buttonMultiply);
        buttonClean = (Button)findViewById(R.id.buttonClean);

        operation = (TextView)findViewById(R.id.operation);
        result = (TextView)findViewById(R.id.result);

        number1 = (EditText) findViewById(R.id.number1);
        number2 = (EditText) findViewById(R.id.number2);

        buttonAdd.setOnClickListener(this);
        buttonSubtract.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonClean.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        float num1 = 0, num2 = 0, res = 0;
        num1 = Float.parseFloat(number1.getText().toString());
        num2 = Float.parseFloat(number2.getText().toString());
        switch (view.getId()) {
            case R.id.buttonAdd:
                operation.setText("+");
                res = num1 + num2;
                break;
            case R.id.buttonSubtract:
                operation.setText("-");
                res = num1 - num2;
                break;
            case R.id.buttonDivide:
                operation.setText("/");
                res = num1 / num2;
                break;
            case R.id.buttonMultiply:
                operation.setText("*");
                res = num1 * num2;
                break;
            case R.id.buttonClean:
                number1.setText("");
                operation.setText("");
                number2.setText("");
                result.setText("");
                break;
        }

        result.setText(res+"");
        }
    }
