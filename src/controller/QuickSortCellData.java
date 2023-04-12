package controller;

import java.util.ArrayList;

import model.CellData;

public class QuickSortCellData {
    
    public static void sort(ArrayList<CellData> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        quickSort(list, 0, list.size() - 1);
    }

    private static void quickSort(ArrayList<CellData> list, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotIndex = partition(list, left, right);
        quickSort(list, left, pivotIndex - 1);
        quickSort(list, pivotIndex + 1, right);
    }

    private static int partition(ArrayList<CellData> list, int left, int right) {
        int pivot = list.get(right).getTreeCount();
        int i = left - 1;

        for (int j = left; j <= right - 1; j++) {
            if (list.get(j).getTreeCount() <= pivot) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, right);
        return i + 1;
    }

    private static void swap(ArrayList<CellData> list, int i, int j) {
        CellData temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}