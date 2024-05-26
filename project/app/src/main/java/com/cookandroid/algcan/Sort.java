package com.cookandroid.algcan;

public class Sort {

    // 선택 정렬
    public static Runnable selectionSortStep(int[] array, int[] i, int[] minIndex, boolean[] swapped) {
        return () -> {
            if (i[0] < array.length - 1) {
                if (!swapped[0]) {
                    minIndex[0] = i[0];
                    for (int j = i[0] + 1; j < array.length; j++) {
                        if (array[j] < array[minIndex[0]]) {
                            minIndex[0] = j;
                        }
                    }
                    int temp = array[minIndex[0]];
                    array[minIndex[0]] = array[i[0]];
                    array[i[0]] = temp;
                    swapped[0] = true;
                } else {
                    swapped[0] = false;
                    i[0]++;
                }
            } else {
                // 정렬이 완료될 때까지 반복하도록 설정
                i[0] = 0;
                minIndex[0] = 0;
                swapped[0] = false;
            }
        };
    }

    // 삽입 정렬
    public static Runnable insertionSortStep(int[] array, int[] i, int[] j) {
        return () -> {
            if (i[0] < array.length) {
                int key = array[i[0]];
                j[0] = i[0] - 1;
                while (j[0] >= 0 && array[j[0]] > key) {
                    array[j[0] + 1] = array[j[0]];
                    j[0]--;
                }
                array[j[0] + 1] = key;
                i[0]++;
            }
        };
    }

    // 버블 정렬
    public static Runnable bubbleSortStep(int[] array, int[] i, int[] j) {
        return () -> {
            if (i[0] < array.length - 1) {
                if (j[0] < array.length - 1 - i[0]) {
                    if (array[j[0]] > array[j[0] + 1]) {
                        int temp = array[j[0]];
                        array[j[0]] = array[j[0] + 1];
                        array[j[0] + 1] = temp;
                    }
                    j[0]++;
                } else {
                    j[0] = 0;
                    i[0]++;
                }
            }
        };
    }

    // 퀵 정렬
    public static Runnable quickSortStep(int[] array, int low, int high, SortingView sortingView) {
        return () -> {
            if (low < high) {
                int pivotIndex = hoarePartition(array, low, high);
                if (sortingView != null) {
                    sortingView.setPivotIndex(pivotIndex);
                    sortingView.setArrayToSort(array);
                }
                try {
                    Thread.sleep(100); // 100밀리초 지연
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                quickSortStep(array, low, pivotIndex, sortingView);
                quickSortStep(array, pivotIndex + 1, high, sortingView);
            }
        };
    }

    public static int hoarePartition(int[] arr, int low, int high) {
        int pivot = arr[low + (high - low) / 2];
        int i = low - 1;
        int j = high + 1;
        while (true) {
            do {
                i++;
            } while (arr[i] < pivot);
            do {
                j--;
            } while (arr[j] > pivot);
            if (i >= j)
                return j;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
