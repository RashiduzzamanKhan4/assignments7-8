import java.io.*;
import java.util.*;

public class MergeVsBubbleSort {
    
    // Generate an array of random integers between 0 and 100
    public static int[] createRandomArray(int arrayLength) {
        Random random = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = random.nextInt(101); // Range 0-100
        }
        return array;
    }
    
    // Write an array to a file
    public static void writeArrayToFile(int[] array, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int num : array) {
                writer.write(num + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    // Read an array from a file
    public static int[] readFileToArray(String filename) {
        List<Integer> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
    
    // Bubble Sort implementation
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Merge Sort implementation
    public static void mergeSort(int[] array) {
        if (array.length < 2) {
            return; // Base case: already sorted
        }
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        
        mergeSort(left);
        mergeSort(right);
        merge(array, left, right);
    }

    // Merge helper function for Merge Sort
    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            array[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        }
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    // Main function to handle user input
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Select an option:");
            System.out.println("1 - Generate random array and save to file");
            System.out.println("2 - Read file, sort contents, and save to another file");
            System.out.println("3 - Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter array length: ");
                    int length = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    int[] randomArray = createRandomArray(length);
                    System.out.print("Enter filename to save: ");
                    String filename = scanner.nextLine();
                    writeArrayToFile(randomArray, filename);
                    System.out.println("Array saved to " + filename);
                    break;
                
                case 2:
                    System.out.print("Enter filename to read from: ");
                    String inputFile = scanner.nextLine();
                    int[] array = readFileToArray(inputFile);

                    System.out.print("Choose sorting method (1 - Bubble Sort, 2 - Merge Sort): ");
                    int sortChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    long startTime = System.nanoTime();
                    if (sortChoice == 1) {
                        bubbleSort(array);
                        System.out.println("Array sorted using Bubble Sort.");
                    } else if (sortChoice == 2) {
                        mergeSort(array);
                        System.out.println("Array sorted using Merge Sort.");
                    } else {
                        System.out.println("Invalid sorting method. Defaulting to Bubble Sort.");
                        bubbleSort(array);
                    }
                    long endTime = System.nanoTime();
                    
                    System.out.print("Enter filename to save sorted array: ");
                    String outputFile = scanner.nextLine();
                    writeArrayToFile(array, outputFile);
                    System.out.println("Sorted array saved to " + outputFile);
                    System.out.println("Sorting took " + (endTime - startTime) / 1_000_000 + " ms.");
                    break;
                
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
