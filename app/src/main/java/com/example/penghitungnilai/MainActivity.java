package com.example.penghitungnilai;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;


import com.example.penghitungnilai.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputEditText;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private TextView resultTextView;
    private TextInputEditText quiz1Input, quiz2Input, ETSInput, EASInput;

    private TextView perbaikanQuiz1TextView, perbaikanQuiz2TextView, perbaikanETSTextView, perbaikanEASTextView;
    private TextInputEditText perbaikanQuiz1Input, perbaikanQuiz2Input, perbaikanETSInput, perbaikanEASInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        quiz1Input = findViewById(R.id.quiz1_input);
        quiz2Input = findViewById(R.id.quiz2_input);
        ETSInput = findViewById(R.id.ets_input);
        EASInput = findViewById(R.id.eas_input);

        perbaikanQuiz1TextView = findViewById(R.id.perbaikanquiz1_label);
        perbaikanQuiz2TextView = findViewById(R.id.perbaikanquiz2_label);
        perbaikanETSTextView = findViewById(R.id.perbaikanets_label);
        perbaikanEASTextView = findViewById(R.id.perbaikaneas_label);

        perbaikanQuiz1Input = findViewById(R.id.perbaikanquiz1_input);
        perbaikanQuiz2Input = findViewById(R.id.perbaikanquiz2_input);
        perbaikanETSInput = findViewById(R.id.perbaikanets_input);
        perbaikanEASInput = findViewById(R.id.perbaikaneas_input);

        resultTextView = findViewById(R.id.result_text);

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

    private double perbaikan(double nilaiAsli, double nilaiPerbaikan) {

        if (nilaiAsli >= nilaiPerbaikan) return nilaiAsli;

        if (nilaiPerbaikan > 75) return 75;

        return nilaiPerbaikan;
    }

    private void showPerbaikan(TextView perbaikanLabel, TextInputEditText perbaikanInput) {
        perbaikanLabel.setVisibility(View.VISIBLE);
        perbaikanInput.setVisibility(View.VISIBLE);
    }

    private void hidePerbaikan(TextView perbaikanLabel, TextInputEditText perbaikanInput) {
        perbaikanLabel.setVisibility(View.GONE);
        perbaikanInput.setVisibility(View.GONE);
    }

    private double getInputValue(TextInputEditText input) {
        return input.getText().length() != 0 ? Double.parseDouble(input.getText().toString()) : 0;
    }

    public void hitungNilaiRataRata(View view) {
        double nilaiQuiz1 = getInputValue(quiz1Input);
        double nilaiQuiz2 = getInputValue(quiz2Input);
        double nilaiETS = getInputValue(ETSInput);
        double nilaiEAS = getInputValue(EASInput);

        double nilaiPerbaikanQuiz1 = getInputValue(perbaikanQuiz1Input);
        double nilaiPerbaikanQuiz2 = getInputValue(perbaikanQuiz2Input);
        double nilaiPerbaikanETS = getInputValue(perbaikanETSInput);
        double nilaiPerbaikanEAS = getInputValue(perbaikanEASInput);

        if (nilaiQuiz1 < 75) showPerbaikan(perbaikanQuiz1TextView, perbaikanQuiz1Input);
        else hidePerbaikan(perbaikanQuiz1TextView, perbaikanQuiz1Input);

        if (nilaiQuiz2 < 75) showPerbaikan(perbaikanQuiz2TextView, perbaikanQuiz2Input);
        else hidePerbaikan(perbaikanQuiz2TextView, perbaikanQuiz2Input);

        if (nilaiETS < 75) showPerbaikan(perbaikanETSTextView, perbaikanETSInput);
        else hidePerbaikan(perbaikanETSTextView, perbaikanETSInput);

        if (nilaiEAS < 75) showPerbaikan(perbaikanEASTextView, perbaikanEASInput);
        else hidePerbaikan(perbaikanEASTextView, perbaikanEASInput);

        nilaiQuiz1 = perbaikan(nilaiQuiz1, nilaiPerbaikanQuiz1);
        nilaiQuiz2 = perbaikan(nilaiQuiz2, nilaiPerbaikanQuiz2);
        nilaiETS = perbaikan(nilaiETS, nilaiPerbaikanETS);
        nilaiEAS = perbaikan(nilaiEAS, nilaiPerbaikanEAS);

        double average = (nilaiQuiz1 + nilaiQuiz2 + nilaiEAS + nilaiETS) / 4;
        resultTextView.setText(String.valueOf(average));
    }
}