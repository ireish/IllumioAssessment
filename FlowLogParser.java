import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FlowLogParser {

    private final HashMap<String, String> tagLookup = new HashMap<>();
    private final HashMap<Integer, String> protocolMap = new HashMap<>();
    private final HashMap<String, Integer> tagCounter = new HashMap<>();
    private final HashMap<String, Integer> portProtocolCounter = new HashMap<>();

    public void parseLookupFileTxt(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String header = br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length != 3) {
                    System.out.println("Invalid line format: " + line);
                    continue;
                }

                int dstPort = Integer.parseInt(parts[0].trim());
                String protocol = parts[1].trim();
                String tag = parts[2].trim();

                String key = dstPort + "_" + protocol.toLowerCase();

                tagLookup.put(key.toLowerCase(), tag);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateProtocolMappings(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.contains(",")) {
                    continue;
                }

                String[] parts = line.split(",", 2);

                try {
                    int key = Integer.parseInt(parts[0].trim());  // Protocol Number
                    String value = parts[1].trim().toLowerCase();  // Protocol Name
                    protocolMap.put(key, value.toLowerCase());
                } catch (NumberFormatException e) {
                    System.err.println("Invalid key format: " + parts[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseFlowLogs(String filepath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+");

                if (parts.length >= 8) {
                    String dstPort = parts[6];
                    int protocolNum = Integer.parseInt(parts[7]);
                    String protocolName = protocolMap.getOrDefault(protocolNum, "Unassigned");
                    String lookupKey = protocolNum + "_" + protocolName.toLowerCase();

                    String tagName = tagLookup.getOrDefault(lookupKey, "Untagged");
                    tagCounter.put(tagName, tagCounter.getOrDefault(tagName, 0) + 1);
                    portProtocolCounter.put(lookupKey, portProtocolCounter.getOrDefault(lookupKey, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Integer> getTagCounter() {
        return tagCounter;
    }

    public HashMap<String, Integer> getPortProtocolCounter() {
        return portProtocolCounter;
    }
}
