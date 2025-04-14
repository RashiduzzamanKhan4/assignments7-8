import java.io.*;
import java.util.*;

public class WordCounter {

    public static void main(String[] args) {
        String inputFilePath = "in.txt";
        String outputFilePath = "out.txt";

        Map<String, Integer> wordCountMap = new TreeMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String word = line.trim().toLowerCase();

                if (!word.isEmpty()) {
                    wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to output file: " + e.getMessage());
        }

        System.out.println("Word counting completed successfully. Output written to: " + outputFilePath);
    }
}
