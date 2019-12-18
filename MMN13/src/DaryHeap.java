
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
	/** The Size of the heap **/
	private int heapSize;
	/** The D-ray heap itself **/
	private int[] heap;

	/**
	 * Constructor
	 * 
	 * @param capacity
	 * @param numChild
	 */
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

		int max = heap[0];
		heap[1] = heap[heapSize - 1];
		heapSize--;
		maxHeapify(0);

		return max;
	}

	// 2- INSERT

	/**
	 * Function to insert element calls heapifyUp() once so Time complexity:
	 * O(logkn).
	 * 
	 * @param key - the key to be added
	 */
	public void insert(int key) {
		this.heapSize++; // Increase the heap size
		heap[heapSize - 1] = -999; // Set the new element with the default key
		increaseKey(heapSize - 1, key); // Call increaseKey() to inset the new key wothout damaging the D-ray
	}

	// 3- INCREASE-KEY

	/**
	 * Function to increse element 
	 * 
	 * @param i - the index to be changed
	 * @param key - the key to be added
	 * @exception IndexOutOfBoundsException when i is not in D-ray-heap
	 */
	public void increaseKey(int i, int key) {
		if (i < 0 || i >= this.heapSize) {
			throw new IndexOutOfBoundsException("Not a valid index");
		}
		if (this.heap[i] < key) {
			this.heap[i] = key;
			heapifyUp(i);// Correct the heap after the key update
		}
	}

	// 4- DELETE

	/**
	 * Function to delete element at an index calls heapifyDown() once, its
	 * complexity O(k logkn)
	 * 
	 * @param i - index of element to be deleted
	 * @return key of deleted item,
	 * @exception NoSuchElementException if heap is empty then raising an error
	 **/
	public int delete(int i) {
		if (isEmpty()) {
			throw new NoSuchElementException("Underflow Exception");
		}			
		int keyItem = heap[i];
		heap[i] = heap[heapSize - 1];
		heapSize--;
		maxHeapify(i);
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

		// Check if one of i's children are bigger then him and saves the largest one's
		// index
		for (int j = 0; j < d; j++) {
			if (kthChild(i, j) < heapSize && 
				heap[kthChild(i, j)] > heap[i] && 
				heap[kthChild(i, j)] > heap[largest]) {
				largest = kthChild(i, j);
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
		for (int i = Math.floorDiv(heapSize - 1 , d); i > 0; i--) {
			maxHeapify(i);
		}
	}
	

	/**
	 * Function to print heap
	 */
	public void printHeap() {
		System.out.println("\nHeap = " + heap[0]);	
		int heapHeight = Height();
		int curr = 1;
		
		for (int i = 0; i < heapHeight; i++) {
			for (int j = 0; j < Math.pow(d, i); j++) {
				for (int k = 0; k < d; k++) {
					System.out.print(heap[curr] + ", ");
					curr++;
				}
				System.out.print("   ");
			}
			System.out.println();
		}
	}

	/**
	 * Function to check if heap is empty
	 * 
	 * @return True if the heap is empty, false otherwise
	 */
	private boolean isEmpty() {
		return heapSize == 0;
	}

	/**
	 * Function to get index of k-th child of i
	 * 
	 * @param i This is the index of the parent
	 * @param k This is the index of the Child from right to left
	 * @return The value of th k-th child
	 */
	private int kthChild(int i, int k) {
		
		return (d * i) - d + 1 + k;
	}
	
	/**
	 * Get the index of the parent element
	 * @param index the index of the son element
	 * @return the index of the parent of the given element
	 */
	private int parent(int i) {
		return Math.floorDiv(i, d);
	}
	
	/**
	 * Function to get D-ray-heap height
	 */
	private int Height() {
		return Math.floorDiv((int)Math.log(heapSize - 1), (int)Math.log(d));
	}

	/**
	 * Function heapifyUp
	 * 
	 * @param i This is the index to heapify
	 */
	private void heapifyUp(int i) {
		int tmp;
		
		while (i > 0 && heap[i] > heap[parent(i)]) {
			// Exchange
			tmp = heap[i];
			heap[i] = heap[parent(i)];
			heap[parent(i)] = tmp;
			
			i = parent(i);
		}	
	}
}
