package com.example.takequizzapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup choicesRadioGroup;
    private RadioButton choice1RadioButton, choice2RadioButton, choice3RadioButton, choice4RadioButton;
    private Button submitButton;

    private int currentQuestionIndex = 0;
    private int score = 0;

    private String[] questionsArray = {
            "Question 1: What is 2 + 2?",
            "Question 2: Which planet is closest to the Sun?",
            "Question 3: What is the capital of France?",
            // Add more questions as needed
    };

    private String[][] choicesArray = {
            {"1", "2", "3", "4"},
            {"Mars", "Venus", "Mercury", "Jupiter"},
            {"London", "Paris", "Berlin", "Madrid"},
            // Add more choices as needed
    };

    private String[] correctAnswersArray = {
            "4",
            "Mercury",
            "Paris",
            // Add more correct answers as needed
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        choicesRadioGroup = findViewById(R.id.choicesRadioGroup);
        choice1RadioButton = findViewById(R.id.choice1RadioButton);
        choice2RadioButton = findViewById(R.id.choice2RadioButton);
        choice3RadioButton = findViewById(R.id.choice3RadioButton);
        choice4RadioButton = findViewById(R.id.choice4RadioButton);
        // Add more RadioButtons for additional choices
        // ...

        submitButton = findViewById(R.id.submitButton);

        displayQuestion(currentQuestionIndex);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void displayQuestion(int questionIndex) {
        questionTextView.setText(questionsArray[questionIndex]);
        choice1RadioButton.setText(choicesArray[questionIndex][0]);
        choice2RadioButton.setText(choicesArray[questionIndex][1]);
        choice3RadioButton.setText(choicesArray[questionIndex][2]);
        choice4RadioButton.setText(choicesArray[questionIndex][3]);
        // Set the text for more RadioButtons for additional choices
        // ...
    }

    private void checkAnswer() {
        RadioButton selectedRadioButton = findViewById(choicesRadioGroup.getCheckedRadioButtonId());
        if (selectedRadioButton != null) {
            String selectedAnswer = selectedRadioButton.getText().toString();
            String correctAnswer = correctAnswersArray[currentQuestionIndex];

            if (selectedAnswer.equals(correctAnswer)) {
                score++;
            }

            // Move to the next question if available
            currentQuestionIndex++;
            if (currentQuestionIndex < questionsArray.length) {
                displayQuestion(currentQuestionIndex);
                choicesRadioGroup.clearCheck();
            } else {
                // Quiz completed
                showScore();
            }
        } else {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
        }
    }

    private void showScore() {
        setContentView(R.layout.activity_score);
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Your Score: " + score + " out of " + questionsArray.length);

        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartQuiz();
            }
        });
    }

    private void restartQuiz() {
        currentQuestionIndex = 0;
        score = 0;
        setContentView(R.layout.activity_main);
        displayQuestion(currentQuestionIndex);
    }
}
