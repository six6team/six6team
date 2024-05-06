package com.cookandroid.algcan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Arrays;

public class SortingView extends View {
    private int[] arrayToSort;
    private Paint paint;

    public SortingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        arrayToSort = new int[]{};
    }

    public void setArrayToSort(int[] arrayToSort) {
        this.arrayToSort = arrayToSort;
        invalidate(); // View 다시 그리기
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

        int barWidth = width / arrayToSort.length;

        // 배열의 최대값 찾기
        int maxValue = Integer.MIN_VALUE;
        for (int value : arrayToSort) {
            maxValue = Math.max(maxValue, value);
        }

        // 막대와 화면의 좌우 마진 설정
        float margin = 20f;
        float availableWidth = width - 2 * margin;
        barWidth = (int) (availableWidth / arrayToSort.length);

        // 배열의 각 요소를 막대로 그림
        for (int i = 0; i < arrayToSort.length; i++) {
            float left = margin + i * barWidth;
            float barHeight = (float) arrayToSort[i] / maxValue * height; // 막대의 높이를 배열 값의 비율로 조정
            float top = height - barHeight;
            float right = margin + (i + 1) * barWidth;
            float bottom = height;

            // 각 막대의 색과 크기 설정
            paint.setColor(0xFF000000); // 검정색
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(left, top, right, bottom, paint);
        }
    }
}