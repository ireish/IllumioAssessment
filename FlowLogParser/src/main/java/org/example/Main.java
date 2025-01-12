package org.example;

public class Main {
    public static void main(String[] args) {

        ConfigLoader configLoader = new ConfigLoader("config.properties");
        System.out.println("Working Directory: " + System.getProperty("user.dir"));
        // Get file paths from config.properties
        String lookupFilePath = configLoader.getProperty("lookupFilePath");
        String flowLogFilePath = configLoader.getProperty("flowLogFilePath");
        String protocolMappingFile = configLoader.getProperty("protocolMappingFile");
        String tagCounterFile = configLoader.getProperty("tagCounterFile");
        String portProtocolCountFile = configLoader.getProperty("portProtocolCountFile");

        FlowLogParser parser = new FlowLogParser();
        TextFileGenerator textFileGenerator = new TextFileGenerator();

        parser.parseLookupFileTxt(lookupFilePath);
        parser.generateProtocolMappings(protocolMappingFile);
        parser.parseFlowLogs(flowLogFilePath);

        textFileGenerator.generateTagCount(tagCounterFile, parser.getTagCounter());
        textFileGenerator.generatePortProtocolCount(portProtocolCountFile, parser.getPortProtocolCounter());
    }
}

