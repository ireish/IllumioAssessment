import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class GenerateTextFile {

    FlowLogParser flowLogParserObj = new FlowLogParser();
    HashMap<String, Integer> tagCounter = flowLogParserObj.getTagCounter();
    HashMap<String, Integer> portProtocolCounter = flowLogParserObj.getPortProtocolCounter();

    public void generateTagCount(String fileName) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Tag Counts:\n");
            writer.write("\nTag,Count\n");

            for (String tag : tagCounter.keySet()) {
                writer.write(tag + "," + tagCounter.get(tag) + "\n");
            }
            System.out.println("Data written successfully to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void generatePortProtocolCount(String fileName) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Write the header
            writer.write("Port/Protocol Combination Counts:\n");
            writer.write("Port,Protocol,Count\n");

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

            System.out.println("Data exported successfully to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
