package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class TextFileGenerator {

    public void generateTagCount(String fileName, Map<String, Integer> tagCounter) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write("Tag Counts:\nTag,Count\n");
            for (String tag : tagCounter.keySet()) {
                fileWriter.write(tag + "," + tagCounter.get(tag) + "\n");
            }
            System.out.println("Data written successfully to " + fileName);
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void generatePortProtocolCount(String fileName, Map<String, Integer> portProtocolCounter) {

        try (FileWriter writer = new FileWriter(fileName)) {
            // Write the header
            writer.write("Port/Protocol Combination Counts:\nPort,Protocol,Count\n");
            for (String key : portProtocolCounter.keySet()) {

                String[] parts = key.split("_");
                if (parts.length == 2) {
                    String port = parts[0];
                    String protocol = parts[1];
                    int count = portProtocolCounter.get(key);
                    writer.write(port + "," + protocol + "," + count + "\n");
                } else {
                    System.err.println("Invalid key format: " + key);
                }
            }

            System.out.println("Data written successfully to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
