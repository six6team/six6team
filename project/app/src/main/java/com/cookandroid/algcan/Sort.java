package com.cookandroid.algcan;

public class Sort {

    // 선택 정렬
    public static Runnable selectionSortStep(int[] array, int[] i, int[] minIndex, boolean[] swapped, SortingView sortingView) {
        return () -> {
            if (i[0] < array.length - 1) {
                if (sortingView != null) {
                    sortingView.setPivotIndex(i[0]);
                    sortingView.setArrayToSort(array);
                }
                if (!swapped[0]) {
                    minIndex[0] = i[0];
                    sortingView.setComparisonIndex(minIndex[0]);
                    for (int j = i[0] + 1; j < array.length; j++) {
                        if (sortingView != null) {
                            sortingView.setComparisonIndex(j);
                        }
                        if (array[j] < array[minIndex[0]]) {
                            minIndex[0] = j;
                            if (sortingView != null) {
                                sortingView.setQuestIndex(minIndex[0]);
                            }
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int temp = array[minIndex[0]];
                    array[minIndex[0]] = array[i[0]];
                    array[i[0]] = temp;
                    swapped[0] = true;
                    if (sortingView != null) {
                        sortingView.setArrayToSort(array);
                    }
                } else {
                    swapped[0] = false;
                    i[0]++;
                    if (sortingView != null) {
                        sortingView.setPivotIndex(i[0]);
                    }
                }
            } else {
                // 정렬이 완료될 때까지 반복하도록 설정
                i[0] = 0;
                minIndex[0] = 0;
                swapped[0] = false;
            }
        };
    }

    ;


    public static Runnable insertionSortStep(int[] array, int[] i, int[] j, SortingView sortingView) {
        return () -> {
            if (i[0] < array.length) {
                int key = array[i[0]];
                j[0] = i[0] - 1;
                if (sortingView != null) {
                    sortingView.setPivotIndex(i[0]);
                    sortingView.setComparisonIndex(j[0]);
                    sortingView.setArrayToSort(array);
                }
                while (j[0] >= 0 && array[j[0]] > key) {
                    if (sortingView != null) {
                        sortingView.setComparisonIndex(j[0]);
                        sortingView.setArrayToSort(array);
                    }
                    array[j[0] + 1] = array[j[0]];
                    j[0]--;
                }
                array[j[0] + 1] = key;
                i[0]++;
                if (sortingView != null) {
                    sortingView.setPivotIndex(i[0]);
                    sortingView.setComparisonIndex(j[0]);
                    sortingView.setArrayToSort(array);
                }
                try {
                    Thread.sleep(50); // 50밀리초 지연
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public static Runnable bubbleSortStep(int[] array, int[] i, int[] j, SortingView sortingView) {
        return () -> {
            if (i[0] < array.length - 1) {
                if (j[0] < array.length - 1 - i[0]) {
                    if (sortingView != null) {
                        sortingView.setPivotIndex(j[0]);
                        sortingView.setComparisonIndex(j[0] + 1);
                        sortingView.setArrayToSort(array);
                    }
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
                try {
                    Thread.sleep(50); // 50밀리초 지연
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public static Runnable quickSortStep(int[] array, int low, int high, SortingView sortingView) {
        return () -> {
            if (low < high) {
                int pivotIndex = hoarePartition(array, low, high, sortingView);
                if (sortingView != null) {
                    sortingView.setPivotIndex(pivotIndex);
                    sortingView.setArrayToSort(array);
                }
                try {
                    Thread.sleep(50); // 50밀리초 지연
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                quickSortStep(array, low, pivotIndex, sortingView);
                quickSortStep(array, pivotIndex + 1, high, sortingView);
                if (sortingView != null) {
                    sortingView.setArrayToSort(array);
                }
                try {
                    Thread.sleep(50); // 50밀리초 지연
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public static int hoarePartition(int[] arr, int low, int high, SortingView sortingView) {
        int pivot = arr[low + (high - low) / 2];
        int i = low - 1;
        int j = high + 1;
        while (true) {
            do {
                if (sortingView != null) {
                    sortingView.setComparisonIndex(i);
                }
                i++;
            } while (arr[i] < pivot);
            do {
                if (sortingView != null) {
                    sortingView.setQuestIndex(j);
                }
                j--;
            } while (arr[j] > pivot);
            if (i >= j)
                return j;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            if (sortingView != null) {
                sortingView.setArrayToSort(arr);
            }
        }
    }
}
