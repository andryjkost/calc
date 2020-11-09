package com.hz.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.EmptyStackException;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonAdd, buttonSubtract, buttonDivide, buttonMultiply, buttonClean, showResult, buttonRightBracket, buttonLeftBracket;
    private Button num1, num2, num3, num4, num5, num6, num7, num8, num9, num0, num00;
    private EditText expression;
    private int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expression = (EditText) findViewById(R.id.expression);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonSubtract = (Button) findViewById(R.id.buttonSubtract);
        buttonDivide = (Button) findViewById(R.id.buttonDivide);
        buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        buttonClean = (Button) findViewById(R.id.buttonClean);
        showResult = (Button) findViewById(R.id.showResult);
        buttonRightBracket = (Button) findViewById((R.id.buttonRightBracket));
        buttonLeftBracket = (Button) findViewById(R.id.buttonLeftBracket);

        num1 = (Button) findViewById(R.id.num1);
        num2 = (Button) findViewById(R.id.num2);
        num3 = (Button) findViewById(R.id.num3);
        num4 = (Button) findViewById(R.id.num4);
        num5 = (Button) findViewById(R.id.num5);
        num6 = (Button) findViewById(R.id.num6);
        num7 = (Button) findViewById(R.id.num7);
        num8 = (Button) findViewById(R.id.num8);
        num9 = (Button) findViewById(R.id.num9);
        num0 = (Button) findViewById(R.id.num0);
        num00 = (Button) findViewById(R.id.num00);

        buttonAdd.setOnClickListener(this);
        buttonSubtract.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonClean.setOnClickListener(this);
        showResult.setOnClickListener(this);
        buttonRightBracket.setOnClickListener(this);
        buttonLeftBracket.setOnClickListener(this);

        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);
        num0.setOnClickListener(this);
        num00.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonClean:
               expression.setText("");
               flag = 0;
                break;
            case R.id.showResult:
                String res = expression.getText().toString();
                try {
                    String kek = Integer.toString(Calculator.Answer(Calculator.toRPN(res)));
                    expression.setText(kek);
                }
                catch(EmptyStackException e){
                Toast.makeText(this, "Неправильно введенно выражение", Toast.LENGTH_SHORT).show();
            }

                flag = 1;
                break;
            default:
                if (flag == 1) {
                    expression.setText("");
                    flag = 0;
                }
                String text = ((TextView)view).getText().toString();
                addSymbol(text);
                break;

        }

    }

    private void addSymbol(String symb){
        String text = expression.getText().toString();
        expression.setText(text+symb);
    }

    public static class Calculator{

        public static String toRPN(String expr){//перевод в польскую нотацию
            String current = "";
            Stack<Character> stack = new Stack<>();
            int priority;
            for (int i = 0; i < expr.length(); i++){
                priority = getP(expr.charAt(i));
                if (priority == 0){
                    current += expr.charAt(i);
                }
                if (priority == 1) stack.push(expr.charAt(i));
                if (priority > 1){
                    current += ' ';

                    while (!stack.empty()){

                        if (getP(stack.peek()) >= priority) {
                            current += stack.pop();
                            current += ' ';

                        }
                        else {
                            break;
                        }
                    }

                    stack.push(expr.charAt(i));

                }
                if (priority == -1){
                    while (getP(stack.peek()) != 1) {
                        current += ' ';
                        current += stack.pop();
                    }
                    stack.pop();
                }
            }
            current += ' ';
            while (!stack.empty()) {
                current += stack.pop();
                current += ' ';
            }

            return current;
        }


        public static int Answer(String rpn){//ответ
            String operand = new String();
            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i < rpn.length(); i++){
                if (rpn.charAt(i) == ' ') continue;
                if (getP(rpn.charAt(i)) == 0){
                    while (rpn.charAt(i) != ' ' && getP(rpn.charAt(i)) == 0){
                        operand += rpn.charAt(i++);
                        if (i == rpn.length()) break;
                    }
                    stack.push(Integer.parseInt(operand));
                    operand = new String();
                }
                if (getP(rpn.charAt(i)) > 1){
                    int a = stack.pop(), b = stack.pop();
                    if (rpn.charAt(i) == '+') stack.push(b+a);
                    if (rpn.charAt(i) == '-') stack.push(b-a);
                    if (rpn.charAt(i) == '*') stack.push(b*a);
                    if (rpn.charAt(i) == '/') stack.push(b/a);

                }

            }

            return stack.pop();
        }




        private static int getP(char token){//приоритет операторов
            if (token == '*' || token == '/') return 3;
            else if (token == '+' || token == '-') return 2;
            else if (token == '(') return 1;
            else if (token == ')') return -1;
            else return 0;
        }



    }
}