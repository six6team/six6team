package com.cookandroid.algcan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.lang.ref.WeakReference;

public class SortingView extends View {
    private int[] arrayToSort;
    private Paint paint;
    private WeakReference<MainActivity> activityRef; // MainActivity의 약한 참조

    int pivotIndex = -1;
    int comparisonIndex = -1;
    int questIndex = -1;

    public SortingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        arrayToSort = new int[]{};
        if (context instanceof MainActivity) {
            activityRef = new WeakReference<>((MainActivity) context);
        }
    }

    public void setArrayToSort(int[] arrayToSort) {
        this.arrayToSort = arrayToSort;
        invalidateView(); // 변경된 경우에만 View 다시 그리기
    }

    public void setPivotIndex(int pivotIndex){
        this.pivotIndex = pivotIndex;
        invalidateView(); // 변경된 경우에만 View 다시 그리기
    }

    public void setComparisonIndex(int comparisonIndex){
        this.comparisonIndex = comparisonIndex;
        invalidateView(); // 변경된 경우에만 View 다시 그리기
    }
    public void setQuestIndex(int questIndex){
        this.questIndex = questIndex;
        invalidateView(); // 변경된 경우에만 View 다시 그리기
    }

    // View 다시 그리기 호출 최적화
    private void invalidateView() {
        MainActivity activity = activityRef.get();
        if (activity != null) {
            activity.runOnUiThread(() -> invalidate());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        // 배열의 요소가 없을 때는 그리지 않음
        if (arrayToSort.length == 0) {
            return;
        }

        // 막대와 화면의 좌우 마진 설정
        float margin = 20f;
        float padding = 5f; // 막대 사이의 패딩
        float availableWidth = width - 2 * margin - (arrayToSort.length - 1) * padding;
        int barWidth = (int) (availableWidth / arrayToSort.length);

        // 배열의 최대값 찾기
        int maxValue = Integer.MIN_VALUE;
        for (int value : arrayToSort) {
            maxValue = Math.max(maxValue, value);
        }

        // 배열의 각 요소를 막대로 그림
        for (int i = 0; i < arrayToSort.length; i++) {
            float left = margin + i * (barWidth + padding);
            float barHeight = (float) arrayToSort[i] / maxValue * height; // 막대의 높이를 배열 값의 비율로 조정
            float top = height - barHeight;
            float right = left + barWidth;
            float bottom = height;

            // 각 막대의 색과 크기 설정
            paint.setColor(0xFF000000); // 검정색
            if(i == pivotIndex){
                paint.setColor(0xFF0000FF); // 파란색
            }
            if (i == comparisonIndex) {
                paint.setColor(0xFFFFFF00); // 노란색
            }
            if (i == questIndex) {
                paint.setColor(0xFFFF22FF); // 핑크색
            }
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(left, top, right, bottom, paint);
        }
    }
}
