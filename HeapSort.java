import java.util.ArrayList;
import java.util.Map.Entry;

@SuppressWarnings("hiding")
public class HeapSort<T, Double> {

	ArrayList<Entry<T, Double>> list;

	public HeapSort(ArrayList<Entry<T, Double>> list) {
		
		this.list = list;
		
	}
	
	public void sortAsc() {
		
		sortAsc(list);
		
	}
	
	public ArrayList<Entry<T, Double>> getSortedAscList() {
		
		sortAsc();

		return list;
		
	}
	
	public void sortDesc() {
		
		sortDesc(list);
		
	}
	
	public ArrayList<Entry<T, Double>> getSortedDescList() {
		
		sortDesc();

		return list;
		
	}
	
	public ArrayList<Entry<T, Double>> getList() {
		
		return list;
		
	}
	
	
	/*
	 * Heap Sort
	 * 
	 * 
	 */
	private void sortAsc(ArrayList<Entry<T, Double>> arr) {
		int n = arr.size();

		// Build heap (rearrange array)
		for (int i = n / 2 - 1; i >= 0; i--)
			heapifyAsc(arr, n, i);

		// One by one extract an element from heap
		for (int i = n - 1; i >= 0; i--) {
			// Move current root to end
			Entry<T, Double> temp = arr.get(0);
			arr.set(0, arr.get(i));
			arr.set(i, temp);

			// call max heapify on the reduced heap
			heapifyAsc(arr, i, 0);
		}
	}

	// To heapify a subtree rooted with node i which is
	// an index in arr[]. n is size of heap
	private void heapifyAsc(ArrayList<Entry<T, Double>> arr, int n, int i) {
		int smallest = i; // Initialize smallest as root
		int l = 2 * i + 1; // left = 2*i + 1
		int r = 2 * i + 2; // right = 2*i + 2

		// If left child is smallest than root
		if (l < n && (double) arr.get(l).getValue() > (double) arr.get(smallest).getValue())
			smallest = l;

		// If right child is smaller than smallest so far
		if (r < n && (double) arr.get(r).getValue() > (double) arr.get(smallest).getValue())
			smallest = r;

		// If smallest is not root
		if (smallest != i) {
			Entry<T, Double> swap = arr.get(i);
			arr.set(i, arr.get(smallest));
			arr.set(smallest, swap);

			// Recursively heapify the affected sub-tree
			heapifyAsc(arr, n, smallest);
		}
	}
	
	
	/*
	 * Heap Sort Descending Order
	 * 
	 * 
	 */
	private void sortDesc(ArrayList<Entry<T, Double>> arr) {
		int n = arr.size();

		// Build heap (rearrange array)
		for (int i = n / 2 - 1; i >= 0; i--)
			heapifyDesc(arr, n, i);

		// One by one extract an element from heap
		for (int i = n - 1; i >= 0; i--) {
			// Move current root to end
			Entry<T, Double> temp = arr.get(0);
			arr.set(0, arr.get(i));
			arr.set(i, temp);

			// call max heapify on the reduced heap
			heapifyDesc(arr, i, 0);
		}
	}

	// To heapify a subtree rooted with node i which is
	// an index in arr[]. n is size of heap
	private void heapifyDesc(ArrayList<Entry<T, Double>> arr, int n, int i) {
		int largest = i; // Initialize largest as root
		int l = 2 * i + 1; // left = 2*i + 1
		int r = 2 * i + 2; // right = 2*i + 2

		// If left child is larger than root
		if (l < n && (double) arr.get(l).getValue() < (double) arr.get(largest).getValue())
			largest = l;

		// If right child is larger than largest so far
		if (r < n && (double) arr.get(r).getValue() < (double) arr.get(largest).getValue())
			largest = r;

		// If largest is not root
		if (largest != i) {
			Entry<T, Double> swap = arr.get(i);
			arr.set(i, arr.get(largest));
			arr.set(largest, swap);

			// Recursively heapify the affected sub-tree
			heapifyDesc(arr, n, largest);
		}
	}
	
}