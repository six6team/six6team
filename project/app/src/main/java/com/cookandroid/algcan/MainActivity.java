package com.cookandroid.algcan;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.lang.ref.WeakReference;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private EditText inputEditText;
    private TextView resultTextView;
    private SortingView sortingView;
    private Handler handler;
    private Runnable sortingRunnable;
    private int[] numbers;
    private int selectedSortAlgorithm;
    private WeakReference<MainActivity> activityRef; // MainActivity의 약한 참조

    // 정렬 알고리즘 상수
    private static final int SELECTION_SORT = 0;
    private static final int INSERTION_SORT = 1;
    private static final int BUBBLE_SORT = 2;
    private static final int QUICK_SORT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEditText = findViewById(R.id.inputEditText);
        resultTextView = findViewById(R.id.resultTextView);
        sortingView = findViewById(R.id.sortingView);
        handler = new Handler();
        activityRef = new WeakReference<>(this); // MainActivity의 약한 참조

        // 시작 버튼을 찾고 클릭 이벤트를 설정
        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSorting();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        resultTextView.setText("정렬 결과: ");
        if (id == R.id.action_selection_sort) {
            selectedSortAlgorithm = SELECTION_SORT;
        } else if (id == R.id.action_insertion_sort) {
            selectedSortAlgorithm = INSERTION_SORT;
        } else if (id == R.id.action_bubble_sort) {
            selectedSortAlgorithm = BUBBLE_SORT;
        } else if (id == R.id.action_quick_sort) {
            selectedSortAlgorithm = QUICK_SORT;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startSorting() {
        // 입력된 숫자를 가져와서 배열로 변환
        String inputString = inputEditText.getText().toString();
        String[] inputArray = inputString.split(" ");
        numbers = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            numbers[i] = Integer.parseInt(inputArray[i]);
        }
        // 선택한 정렬 알고리즘에 따라 실행
        switch (selectedSortAlgorithm) {
            case SELECTION_SORT:
                startSelectionSortStep();
                break;
            case INSERTION_SORT:
                startInsertionSortStep();
                break;
            case BUBBLE_SORT:
                startBubbleSortStep();
                break;
            case QUICK_SORT:
                startQuickSortStep();
                break;
        }
        sortingView.setPivotIndex(-1);
        sortingView.setComparisonIndex(-1);
        sortingView.setQuestIndex(-1);
        sortingView.setArrayToSort(numbers);
    }

    // 선택 정렬 실행
    private void startSelectionSortStep() {
        int[] i = {0};
        int[] minIndex = {0};
        boolean[] swapped = {false};
        sortingRunnable = new Runnable() {
            @Override
            public void run() {
                Sort.selectionSortStep(numbers, i, minIndex, swapped, sortingView).run();
                if (i[0] < numbers.length - 1) {
                    handler.postDelayed(this, 50);
                } else {
                    resultTextView.setText("정렬 결과: " + Arrays.toString(numbers));
                }
            }
        };

        handler.post(sortingRunnable);
    }

    // 삽입 정렬 실행
    private void startInsertionSortStep() {
        int[] i = {0};
        int[] j = {0};
        sortingRunnable = new Runnable() {
            @Override
            public void run() {
                Sort.insertionSortStep(numbers, i, j, sortingView).run();
                if (!isSorted(numbers)) {
                    handler.postDelayed(this, 10);
                } else {
                    resultTextView.setText("정렬 결과: " + Arrays.toString(numbers));
                }
            }
        };

        handler.post(sortingRunnable);
    }

    // 버블 정렬 실행
    private void startBubbleSortStep() {
        int[] i = {0};
        int[] j = {0};
        sortingRunnable = new Runnable() {
            @Override
            public void run() {
                Sort.bubbleSortStep(numbers, i, j, sortingView).run();
                if (!isSorted(numbers)) {
                    handler.postDelayed(this, 10);
                } else {
                    sortingView.setArrayToSort(numbers);
                    resultTextView.setText("정렬 결과: " + Arrays.toString(numbers));
                }
            }
        };
        handler.post(sortingRunnable);
    }
    // 퀵 정렬 실행
    private void startQuickSortStep() {
        int low = 0;
        int high = numbers.length - 1;
        sortingRunnable = new Runnable() {
            @Override
            public void run() {
                Sort.quickSortStep(numbers, low, high, sortingView).run();
                if (!isSorted(numbers)) {
                    handler.postDelayed(this, 10);
                } else {
                    resultTextView.setText("정렬 결과: " + Arrays.toString(numbers));
                }
            }
        };
        handler.post(sortingRunnable);
    }


    // 배열이 정렬되었는지 확인하는 유틸리티 메서드
    private boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
