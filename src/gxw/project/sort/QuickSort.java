package gxw.project.sort;

import java.util.Arrays;

public class QuickSort<E> {
	E[] array;
	
	public E[] sort(E[] array) {
		this.array = array;
		quickSort(0, this.array.length-1);
		return this.array;
	}
	
	public void swap(int from, int to) {
		E temp = array[from];
		array[from] = array[to];
		array[to] = temp;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void quickSort(int from, int to) {
		if(from >= to)
			return;
		
		E pivot = array[to];
		int counter = from;
		for(int i = counter; i<to; i++) {
			if(((Comparable)array[i]).compareTo(pivot) <= 0) {
				// move the index to the right of the element who is bigger than the pivot value
				swap(counter, i);
				counter++;
			}
		}
		swap(counter, to);
		quickSort(from, counter - 1);
		quickSort(counter + 1, to);
	}
	
	public static void main(String[] args) {
		Integer[] array = {7, 5, 6, 3, 4};
		QuickSort<Integer> qs = new QuickSort<Integer>();
		System.out.println(String.format("Sorted array: %s",Arrays.asList(qs.sort(array))));
	}
}
