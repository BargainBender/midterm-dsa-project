package controller;

import java.util.LinkedList;

import model.CellData;

public class MergeSortCellData {

	public static void sort(LinkedList<CellData> list) {
		if (list == null || list.size() == 0) {
			return;
		}
		mergeSort(list, 0, list.size() - 1);
	}

	private static void mergeSort(LinkedList<CellData> list, int left, int right) {
		if (left < right) {
			int mid = left + (right - left) / 2;

			mergeSort(list, left, mid);
			mergeSort(list, mid + 1, right);

			merge(list, left, mid, right);
		}
	}

	private static void merge(LinkedList<CellData> list, int left, int mid, int right) {
		int n1 = mid - left + 1;
		int n2 = right - mid;

		LinkedList<CellData> leftList = new LinkedList<>();
		LinkedList<CellData> rightList = new LinkedList<>();

		for (int i = 0; i < n1; i++) {
			leftList.add(list.get(left + i));
		}

		for (int i = 0; i < n2; i++) {
			rightList.add(list.get(mid + 1 + i));
		}

		int i = 0, j = 0, k = left;
		while (i < n1 && j < n2) {
			if (leftList.get(i).getTreeCount() <= rightList.get(j).getTreeCount()) {
				list.set(k++, leftList.get(i++));
			} else {
				list.set(k++, rightList.get(j++));
			}
		}

		while (i < n1) {
			list.set(k++, leftList.get(i++));
		}

		while (j < n2) {
			list.set(k++, rightList.get(j++));
		}
	}
}
