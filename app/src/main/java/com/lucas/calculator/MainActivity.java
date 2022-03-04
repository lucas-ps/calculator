package com.lucas.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.calcInput);
        // Stop keyboard from showing
        display.setShowSoftInputOnFocus(false);

        // Erase input when clicked
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });

    }

    /**
     * Adds char to where he cursor is
     * @param strToAdd
     */
    private void updateText(String strToAdd){
        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText("");
        }
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        display.setText((String.format("%s%s%s", leftStr, strToAdd, rightStr)));
        display.setSelection(cursorPos + 1);
    }

    public void zero(View view){
        updateText("0");
    }

    public void one(View view){
        updateText("1");
    }

    public void two(View view){
        updateText("2");
    }

    public void three(View view){
        updateText("3");
    }

    public void four(View view){
        updateText("4");
    }

    public void five(View view){
        updateText("5");
    }

    public void six(View view){
        updateText("6");
    }

    public void seven(View view){
        updateText("7");
    }

    public void eight(View view){
        updateText("8");
    }

    public void nine(View view){
        updateText("9");
    }

    public void clear(View view){
        display.setText("");
    }

    public void exponent(View view){
        updateText("^");
    }

    public void brackets(View view){
        int cursorPos = display.getSelectionStart();
        int openBrackets = 0;
        int closeBrackets = 0;
        int length = display.getText().toString().length();
        String text = display.getText().toString();

        for(int i = 0; i < cursorPos; i++){
            if (text.charAt(i) == '('){
                openBrackets += 1;
            }
        }

        for(int i = 0; i < cursorPos; i++){
            if (text.charAt(i) == ')'){
                closeBrackets += 1;
            }
        }

        if (openBrackets == closeBrackets || text.charAt(length - 1) == '('){
            updateText("(");
        }

        else if (closeBrackets < openBrackets && !(text.charAt(length - 1) == '(')){
            updateText(")");
        }
        display.setSelection(cursorPos + 1);

    }

    public void divide(View view){
        updateText("÷");
    }

    public void multiply(View view){
        updateText("×");
    }

    public void add(View view){
        updateText("+");
    }

    public void subtract(View view){
        updateText("-");
    }

    public void plusMinus(View view){
        updateText("±");
    }

    public void point(View view){
        updateText(".");
    }

    public void equals(View view){
        String mathExpression = display.getText().toString();
        mathExpression.replaceAll("÷", "/");
        mathExpression.replaceAll("×", "*");
        Expression expression = new Expression(mathExpression);

        String result = String.valueOf(expression.calculate());
        display.setText(result);
        display.setSelection(result.length());
    }

    public void backspace(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        if(cursorPos != 0 && textLen != 0){
            String oldStr = display.getText().toString();
            String newStr = (oldStr.substring(0, oldStr.length() - 1));
            display.setText(newStr);
            display.setSelection(cursorPos - 1);
        }
    }
}

