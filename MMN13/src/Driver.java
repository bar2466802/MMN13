import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.*;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("D ary Heap Test\n\n");
		
		// Gets the input for the D-ray-Heap
		System.out.println("Enter D-ary Heap");
		System.out.println("Please select the option to create the D-ary Heap, enter the option number");
		System.out.println("1. By entring the elements one by one");
		System.out.println("2. From a txt file");
		int option = scan.nextInt();
		List<Integer> list;
		
		// By console
		if (option == 1) {
			System.out.println("Please Enter in heap elements, if you wanna stop enter -999");
			list = new ArrayList<>();
			int num = scan.nextInt();
			while (num != -999) {
				list.add(num);
				num = scan.nextInt();
			}
		}
		// By file
		else if (option == 2) {
			list = Collections.emptyList();
			try {
				System.out.println("Please Enter the file path:");
				String fileName = scan.nextLine();
				lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Convert List to Array
		int heapSize = list.size();
		int[] drayHeap = new int[heapSize];
		
		for (int i = 0; i < heapSize; i++) {
			drayHeap[i] = list.get(i);
		}
		
		// Creates the D-ray-Heap
		DaryHeap dh = new DaryHeap(drayHeap, scan.nextInt());
		dh.printHeap();
		
		// What do you want to do? - print the D-ray-heap after each action
		System.out.println("What do you want to do?");
		System.out.println("Please select the option to by entering the option number");
		System.out.println("1. Insert new key");
		System.out.println("2. Delete existing key");
		System.out.println("3. Extract the larget number");
	}

}
