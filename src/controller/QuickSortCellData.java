package controller;

import model.CellData;

public class QuickSortCellData {
    
    public static void sort(CellData[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(CellData[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotIndex = partition(arr, left, right);
        quickSort(arr, left, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, right);
    }

    private static int partition(CellData[] arr, int left, int right) {
        int pivot = arr[right].getTreeCount();
        int i = left - 1;

        for (int j = left; j <= right - 1; j++) {
            if (arr[j].getTreeCount() <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, right);
        return i + 1;
    }

    private static void swap(CellData[] arr, int i, int j) {
        CellData temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}