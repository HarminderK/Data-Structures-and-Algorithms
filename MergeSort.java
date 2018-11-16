import java.util.ArrayList;
import java.util.Map.Entry;

@SuppressWarnings("hiding")
public class MergeSort<T, Double> {

	ArrayList<Entry<T, Double>> list;

	public MergeSort(ArrayList<Entry<T, Double>> list) {

		this.list = list;

	}

	public void sortAsc() {

		 mergeSortAsc(0, list.size() - 1);

	}
	
	public void sortDsc() {

		 mergeSortDsc(0, list.size() - 1);

	}
	
	public ArrayList<Entry<T, Double>> list() {
		
		return list;
		
	}


	private void mergeSortAsc(int l, int r) {

		if (l < r) {
			// Get the middle
			int m = l + (r - l) / 2;
			// Split the ArrayList into two and merge by sorting data
			mergeSortAsc(l, m);
			mergeSortAsc(m + 1, r);
			mergeAsc( l, m, r);
		}
	}
	
	private void mergeSortDsc(int l, int r) {

		if (l < r) {
			// Get the middle
			int m = l + (r - l) / 2;
			// Split the ArrayList into two and merge by sorting data
			mergeSortDsc(l, m);
			mergeSortDsc(m + 1, r);
			mergeDsc( l, m, r);
		}
	}
	
	private void mergeDsc(int l, int m, int r) {

		// Sizes of the left and right sections
		int nl = m - l + 1;
		int nr = r - m;
		
		// To hold the pointers of each object temporarily
		ArrayList<Entry<T, Double>> larr = new ArrayList<Entry<T, Double>>();
		ArrayList<Entry<T, Double>>  rarr = new ArrayList<Entry<T, Double>>();

		// Store the pointers...
		for (int i = 0; i < nl; i++)
			larr.add(list.get(l + i));
		for (int j = 0; j < nr; j++)
			rarr.add(list.get(m + 1 + j));

		int i = 0;
		int j = 0;
		int k = l;

		// Compare and merge the two sections
		while (i < nl && j < nr) {
			if ((double) larr.get(i).getValue() >= (double)rarr.get(j).getValue()) {
				Entry<T, Double> e1 = larr.get(i);

				list.set(k, e1);

				i++;
			} else {
				Entry<T, Double> e1 = rarr.get(j);
				list.set(k, e1);
				j++;
			}
			k++;
		}

		// Remaining elements in the ArrayLists
		while (i < nl) {
			Entry<T, Double> e1 = larr.get(i);

			list.set(k, e1);
			i++;
			k++;
		}

		while (j < nr) {
			Entry<T, Double> e1 = rarr.get(j);
			list.set(k, e1);
			j++;
			k++;
		}
	}
	
	private void mergeAsc(int l, int m, int r) {

		// Sizes of the left and right sections
		int nl = m - l + 1;
		int nr = r - m;
		
		// To hold the pointers of each object temporarily
		ArrayList<Entry<T, Double>> larr = new ArrayList<Entry<T, Double>>();
		ArrayList<Entry<T, Double>>  rarr = new ArrayList<Entry<T, Double>>();

		// Store the pointers...
		for (int i = 0; i < nl; i++)
			larr.add(list.get(l + i));
		for (int j = 0; j < nr; j++)
			rarr.add(list.get(m + 1 + j));

		int i = 0;
		int j = 0;
		int k = l;

		// Compare and merge the two sections
		while (i < nl && j < nr) {
			if ((double) larr.get(i).getValue() <= (double)rarr.get(j).getValue()) {
				Entry<T, Double> e1 = larr.get(i);

				list.set(k, e1);

				i++;
			} else {
				Entry<T, Double> e1 = rarr.get(j);
				list.set(k, e1);
				j++;
			}
			k++;
		}

		// Remaining elements in the ArrayLists
		while (i < nl) {
			Entry<T, Double> e1 = larr.get(i);

			list.set(k, e1);
			i++;
			k++;
		}

		while (j < nr) {
			Entry<T, Double> e1 = rarr.get(j);
			list.set(k, e1);
			j++;
			k++;
		}
	}	

}