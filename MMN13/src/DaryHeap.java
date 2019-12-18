
/**
 *   Java Program to Implement D-ary-Heap
 *   By Bar & Shaked
 */
 
import java.util.Scanner;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.lang.Error;
/** Class D-ary Heap **/
class DaryHeap    
{
    /** The number of children each node has **/
    private int d;
    private int heapSize;
    private int[] heap;
 
    /** Constructor **/    
    public DaryHeap(int capacity, int numChild)
    {
        heapSize = 0;
        d = numChild;
        heap = new int[capacity + 1];
        Arrays.fill(heap, -1);
    }
 
    /** Function to check if heap is empty **/
    public boolean isEmpty( )
    {
        return heapSize == 0;
    }
 
    /** Check if heap is full **/
    public boolean isFull( )
    {
        return heapSize == heap.length;
    }
 
    /** Clear heap */
    public void clear( )
    {
        heapSize = 0;
    }
 
    /** Function to  get index parent of i **/
    private int parent(int i) 
    {
        return (i - 1)/d;
    }
 
    /** Function to get index of k th child of i **/
    private int kthChild(int i, int k) 
    {
        return d * i + k;
    }
//    INCREASE-KEY(A,i,key)
//    1: if key < A[i] then
//    2: error �new key is smaller than current key �
//    3: end if
//    4: A[i] = key
//    5: while i > 1 and A[P ARENT(i) < A[i] do
//    6: exchange A[i] with A[P ARENT(i)]
//    7: i = P ARENT(i)
//    8: end while
    
    /** Function to insert element 
     * calls heapifyUp() once so Time complexity: O(logkn).
     * @param x - the key to be added
     * */
    public void increaseKey(int index, int key)
    {
    	if(index < 0) {
    		 throw new IndexOutOfBoundsException("Not a valid index");
    	}
    	if(this.heap[index] < key) {
    		System.out.println("Error: new key is smaller than current key");
    	}
    	else {
    		this.heap[index] = key;
    		heapifyUp(index);//Correct the heap after the key update
    	}
    }
 
    /** Function to insert element 
     * calls heapifyUp() once so Time complexity: O(logkn).
     * @param x - the key to be added
     * */
    public void insert(int x)
    {
        this.heapSize++; //Increase the heap size
        /** Percolate up **/
        heap[heapSize] = x; //Set the new element with the given key
        heapifyUp(heapSize - 1);//Correct the heap after the new addition
    }
 
    /** Function to find least element **/
    public int findMin( )
    {
        if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception");           
        return heap[0];
    }
 
    /** Function to delete element at an index 
     * calls heapifyDown() once, its complexity O(k logkn)
     * @param ind - index of element to be deleted
     * 
     * **/
    public int delete(int ind)
    {
        if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception");
        int keyItem = heap[ind];
        heap[ind] = heap[heapSize - 1];
        heapSize--;
        heapifyDown(ind);        
        return keyItem;
    }
 
    /** Function heapifyUp  **/
    private void heapifyUp(int childInd)
    {
        int tmp = heap[childInd];    
        while (childInd > 0 && tmp < heap[parent(childInd)])
        {
            heap[childInd] = heap[ parent(childInd) ];
            childInd = parent(childInd);
        }                   
        heap[childInd] = tmp;
    }
 
    /** Function heapifyDown **/
    private void heapifyDown(int ind)
    {
        int child;
        int tmp = heap[ ind ];
        while (kthChild(ind, 1) < heapSize)
        {
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
    private int minChild(int ind) 
    {
        int bestChild = kthChild(ind, 1);
        int k = 2;
        int pos = kthChild(ind, k);
        while ((k <= d) && (pos < heapSize)) 
        {
            if (heap[pos] < heap[bestChild]) 
                bestChild = pos;
            pos = kthChild(ind, k++);
        }    
        return bestChild;
    }
 
    /** Function to print heap **/
    public void printHeap()
    {
        System.out.print("\nHeap = ");
        for (int i = 0; i < heapSize; i++)
            System.out.print(heap[i] +" ");
        System.out.println();
    }     
}
