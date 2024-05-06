package com.cookandroid.algcan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private EditText inputEditText;
    private Button startButton;
    private SortingView sortingView;
    private TextView resultTextView, progressTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEditText = findViewById(R.id.inputEditText);
        startButton = findViewById(R.id.startButton);
        sortingView = findViewById(R.id.sortingView);
        resultTextView = findViewById(R.id.resultTextView);
        progressTextView = findViewById(R.id.progressTextView);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSorting();
            }
        });
    }

    private void startSorting() {
        // 입력된 숫자를 가져와서 배열로 변환
        String inputString = inputEditText.getText().toString();
        String[] inputArray = inputString.split(" ");
        int[] numbers = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            numbers[i] = Integer.parseInt(inputArray[i]);
        }

        // 정렬 알고리즘 실행 (여기에서는 간단히 Arrays.sort() 메서드를 사용)
        Arrays.sort(numbers);

        // 정렬된 배열을 SortingView에 설정하여 표시
        sortingView.setArrayToSort(numbers);

        // 결과 텍스트뷰에 정렬된 결과 표시
        resultTextView.setText("정렬 결과: " + Arrays.toString(numbers));
    }
}