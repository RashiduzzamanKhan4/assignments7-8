import java.io.*;
import java.util.*;

public class BubbleSort {

    public static int[] createRandomArray(int arrayLength) {
        Random random = new Random();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = random.nextInt(101);
        }
        return array;
    }
    public static void writeArray_ToFile(int[] array, String filename){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            for (int num: array){
                writer.write(num+"\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file:" + e.getMessage());
    }
}
public static int[] readArray_ToArray(String filename){
    List<Integer> list = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
        String line;
        while ((line = reader.readLine()) != null){
            list.add(Integer.parseInt(line));
        }
    } catch (IOException e) {
        System.out.println("Error reading from file:" + e.getMessage());
    }
    return list.stream().mapToInt(i -> i).toArray();
}
public static void bubbleSort(int[] array){
    int n = array.length;
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (array[j] > array[j + 1]) {
                // swap array[j] and array[j+1]
                int temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
            }
        }
    }
}
public static void main (String[] args){
    Scanner scanner = new Scanner(System.in);
    while (true) {
        System.out.println("Srlect and option: ");
        System.out.println("1. Create a random array and write to file");
        System.out.println("2. Read array from file and sort it");
        System.out.println("3. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        switch (choice) {
            case 1:
            System.out.print("Enter the length of the array: ");
            int length = scanner.nextInt();
            scanner.nextLine();
            int[] randomArray = createRandomArray(length);
            System.out.print("Enter the filename to write the array to: ");
            String filename = scanner.nextLine();
            writeArray_ToFile(randomArray, filename);
            System.out.println("Array written to file: " + filename);

            break;
            case 2:
            System.out.print("Enter the filename to read from: ");
            String input_File = scanner.nextLine();
            int[] array = readArray_ToArray(input_File);
            bubbleSort(array);
            System.out.println("Enter filename to save sorted array: " );
            String output_File = scanner.nextLine();
            writeArray_ToFile(array, output_File);
            System.out.println("Sorted array written to file: " + output_File);
            break;
            case 3:
                System.out.println("Exiting...");
                scanner.close();
                return;

            default:
            System.out.println("Invalid choice. Can u please try again.");
                break;
        }
    }
}
}