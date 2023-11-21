package vamk.example.assignment4_calc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView displayText;
    private StringBuilder currentInput = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayText = findViewById(R.id.displayText);
        findViewById(R.id.gridLayout).getTouchables().stream()
                .filter(view -> view instanceof Button)
                .forEach(view -> view.setOnClickListener(this::onClick));
    }

    private void onClick(View v) {
        String buttonText = ((Button) v).getText().toString();

        if ("=".equals(buttonText)) calculateResult();
        else if ("C".equals(buttonText)) clearInput();
        else appendToInput(buttonText);
    }

    private void appendToInput(String text) {
        displayText.setText(currentInput.append(text).toString());
    }

    private void clearInput() {
        displayText.setText("");
        currentInput.setLength(0);
    }

    private void calculateResult() {
        try {
            displayText.setText(String.valueOf(evaluateExpression(currentInput.toString())));
        } catch (Exception e) {
            displayText.setText("Error");
        }
    }

    private double evaluateExpression(String expression) {
        String[] tokens = expression.split("(?<=\\d)(?=[+\\-*/%])|(?<=[+\\-*/%])(?=\\d)");

        double operand1 = Double.parseDouble(tokens[0]);
        String operator = tokens[1];
        double operand2 = Double.parseDouble(tokens[2]);

        switch (operator) {
            case "+": return operand1 + operand2;
            case "-": return operand1 - operand2;
            case "*": return operand1 * operand2;
            case "/": return operand2 != 0 ? operand1 / operand2 : Double.NaN;
            case "%": return operand1 % operand2;
            default: throw new UnsupportedOperationException("Unsupported operator");
        }
    }
}
