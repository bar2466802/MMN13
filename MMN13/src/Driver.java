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
		System.out.println("Enter D-ary Heap");
		int heapSize = 0;
		System.out.println("Please select the option to create the D-ary Heap, enter the option number");
		System.out.println("1. By entring the elements one by one");
		System.out.println("2. From a txt file");
		int option = scan.nextInt();
		if (option == 1) {
			System.out.println("Please Enter in heap elements, if you wanna stop enter -999");
			List<Integer> list = new ArrayList<>();
			int num = scan.nextInt();
			while (num != -999) {
				list.add(num);
				heapSize++;
				num = scan.nextInt();
			}
		} else {
			List<String> lines = Collections.emptyList();
			try {
				System.out.println("Please Enter the file path:");
				String fileName = scan.nextLine();
				lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
			}

			catch (IOException e) {
				e.printStackTrace();
			}
		}

		DaryHeap dh = new DaryHeap(heapSize, scan.nextInt());
	}

}
