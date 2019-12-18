
/**
 *   Java Program to Implement D-ary-Heap
 *   By Bar & Shaked
 */

import java.util.Scanner;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.lang.Error;

/** Class D-ary Heap **/
class DaryHeap {
	/** The number of children each node has **/
	private int d;
	private int heapSize;
	private int[] heap;

	/** Constructor */
	public DaryHeap(int capacity, int numChild) {
		heapSize = 0;
		d = numChild;
		heap = new int[capacity + 1];
		Arrays.fill(heap, -1);
	}

	// Actual Answers

	// 1- EXTRACT-MAX

	/**			
	 * Function to Extract the largest value and keep the D-ray as it should be
	 * 
	 * @exception NoSuchElementException for empty heap
	 */
	public int extractMax() {
		if (isEmpty()) {
			throw new NoSuchElementException("Empty D-ray");
		}
		
		int max = heap[1];
		heap[1] = heap[heapSize];
		heapSize--;
		maxHeapify(1);
		
		return max;
	}

	// 2- INSERT

	/**
	 * Function to insert element calls heapifyUp() once so Time complexity:
	 * O(logkn).
	 * 
	 * @param x - the key to be added
	 */
	public void insert(int x) {
		this.heapSize++; // Increase the heap size
		/** Percolate up **/
		heap[heapSize] = x; // Set the new element with the given key
		heapifyUp(heapSize - 1);// Correct the heap after the new addition
	}

	// 3- INCREASE-KEY
	
	/**
	 * Function to insert element calls heapifyUp() once so Time complexity:
	 * O(logkn).
	 * 
	 * @param x - the key to be added
	 */
	public void increaseKey(int index, int key) {
		if (index < 0) {
			throw new IndexOutOfBoundsException("Not a valid index");
		}
		if (this.heap[index] < key) {
			System.out.println("Error: new key is smaller than current key");
		} else {
			this.heap[index] = key;
			heapifyUp(index);// Correct the heap after the key update
		}
	}

	// 4- DELETE

	/**
	 * Function to delete element at an index calls heapifyDown() once, its
	 * complexity O(k logkn)
	 * 
	 * @param ind - index of element to be deleted
	 * @return key of deleted item, if heap is empty then raising an error
	 **/
	public int delete(int ind) {
		if (isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		int keyItem = heap[ind];
		heap[ind] = heap[heapSize - 1];
		heapSize--;
		maxHeapify(ind);;
		return keyItem;
	}

	// Utils

	/**
	 * Function that makes the D-ary orderd from i down
	 * 
	 * @param i This is the index from it the D-ary will be sorted
	 */
	public void maxHeapify(int i) {
		
		int largest = i;
		 
		// Check if one of i's children is bigger then him and saves the largest one's index
		for (int j = 1; j <= d; j++) {	
			if (kthChild(i, j) <= heapSize && heap[kthChild(i, j)] > heap[i]) {
				largest = heap[kthChild(i, j)];
			}
		}
		
		if (largest != i) {
			// exchange
			int temp = heap[i];
			heap[i] = heap[largest];
			heap[largest] = temp;
			
			maxHeapify(largest);
		}	
	}
	
	/**
	 * Function to build the d-heap
	 */
	public void buildMaxHeap() {
		for (int i = (int)Math.floor((heapSize / d)); i > 0; i--) {
			maxHeapify(i);
		}
	}

	/**
	 * Function to check if heap is empty
	 * 
	 * @return True if the heap is empty, false otherwise
	 */
	public boolean isEmpty() {
		return heapSize == 0;
	}

	/**
	 * Check if heap is full
	 * 
	 * @return True if the heap is full, false otherwise
	 */
	public boolean isFull() {
		return heapSize == heap.length;
	}

	/** Clear heap */
	public void clear() {
		heapSize = 0;
	}

	/**
	 * Function to get index parent of i
	 * 
	 * @param
	 * @return
	 */
	private int parent(int i) {
		return (i - 1) / d;
	}

	/**
	 * Function to get index of k-th child of i
	 * 
	 * @param i This is the index of the parent
	 * @param k This is the index of the Child from right to left
	 * @return The value of th k-th child
	 */
	private int kthChild(int i, int k) {
		// return d * i + k;
		return d * i - d + 1 + k;
	}

	/** Function to find least element **/
	public int findMin() {
		if (isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		return heap[0];
	}

	/** Function heapifyUp **/
	private void heapifyUp(int childInd) {
		int tmp = heap[childInd];
		while (childInd > 0 && tmp < heap[parent(childInd)]) {
			heap[childInd] = heap[parent(childInd)];
			childInd = parent(childInd);
		}
		heap[childInd] = tmp;
	}

	/**
	 * Function heapifyDown
	 * 
	 */
	private void heapifyDown(int ind) {
		int child;
		int tmp = heap[ind];
		while (kthChild(ind, 1) < heapSize) {
			child = minChild(ind);
			if (heap[child] < tmp)
				heap[ind] = heap[child];
			else
				break;
			ind = child;
		}
		heap[ind] = tmp;
	}

	/** Function to get smallest child **/
	private int minChild(int ind) {
		int bestChild = kthChild(ind, 1);
		int k = 2;
		int pos = kthChild(ind, k);
		while ((k <= d) && (pos < heapSize)) {
			if (heap[pos] < heap[bestChild])
				bestChild = pos;
			pos = kthChild(ind, k++);
		}
		return bestChild;
	}

	/** Function to print heap **/
	public void printHeap() {
		System.out.print("\nHeap = ");
		for (int i = 0; i < heapSize; i++)
			System.out.print(heap[i] + " ");
		System.out.println();
	}
}
