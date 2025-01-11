import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        ConfigLoader configLoader = new ConfigLoader("config.properties");
        // Get file paths from config.properties
        String lookupFilePath = configLoader.getProperty("lookupFilePath");
        String flowLogFilePath = configLoader.getProperty("flowLogFilePath");
        String protocolMappingFile = configLoader.getProperty("protocolMappingFile");
        String output1File = configLoader.getProperty("tagCounterFile");
        String output2File = configLoader.getProperty("portProtocolCountFile");

        FlowLogParser parser = new FlowLogParser();
        GenerateTextFile writeObj = new GenerateTextFile();

        parser.parseLookupFileTxt(lookupFilePath);
        parser.generateProtocolMappings(protocolMappingFile);
        parser.parseFlowLogs(flowLogFilePath);

        HashMap<String, Integer> tagC = parser.getTagCounter();
        tagC.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value + "\n"));
        HashMap<String, Integer> portP = parser.getPortProtocolCounter();
        portP.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value + "\n"));

        writeObj.generateTagCount(output1File);
        writeObj.generatePortProtocolCount(output2File);
    }
}

