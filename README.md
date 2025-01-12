# **IllumioAssessment - Network Flow Log Parser**

## **Overview**
This project implements a **Network Flow Log Parser** in Java to parse and analyze flow log records from the Amazon VPC. The parser matches flow log entries against a lookup table to classify network traffic by tags and outputs various statistics.

The program:
- Supports **Amazon VPC Flow Logs** in **default format** (Version 2).
- Processes logs to generate:
  - Count of matches for each tag.
  - Count of matches for each port/protocol combination.

---

## **Requirements**
- **Java**: JDK 17 or later.
- **Maven**: Latest version (not required if you run using Method 2).

---

## **Assumptions**
1. The program supports only **default log format** as per [Amazon VPC Flow Logs Documentation](https://docs.aws.amazon.com/vpc/latest/userguide/flow-log-records.html).
2. Only **version 2** of the flow logs is supported.
3. The program assumes the following input files:
   - `<FlowRecords>.txt`: Contains flow log records.
   - `<LookupFile>.csv` or `<LookupFile>.txt`: Contains the lookup table with `dstport`, `protocol`, and `tag` mappings.
4. Output files will be generated in a directory as defined in `config.properties`.

---

## **Setup Instructions - Method 1**
### **1. Install Maven**
#### **Linux/Ubuntu**
```bash
sudo apt update
sudo apt install maven
```

#### **MacOS**
Using Homebrew:
```bash
brew install maven
```

#### **Windows**
- Download Maven from [Apache Maven](https://maven.apache.org/).
- Extract the files and configure the `MAVEN_HOME` and `Path` environment variables as per the installation guide.

Verify Maven installation:
```bash
mvn -v
```

### **2. Install JDK 17**
Ensure JDK 17 (or above) is installed and configured as the default JDK. Verify with:
```bash
java -version
```

---

## **Steps to Run**

### **Place Custom Files**
Place your custom `<flow_record>.txt`and `<lookupFile>.csv` in:
```
FlowLogParser/src/main/resources/files
```

### **Clone or Download the Repository**
Clone the repository:
```bash
git clone <repository-url>
```

Update the file names for 'lookupFilePath' and 'flowLogFilePath' in `config.properties` located in:
```
FlowLogParser/src/main/resources/
```

### **Compile the Project**
Run the following Maven commands in the project directory:
```bash
cd FlowLogParser
mvn clean compile
```

### **Run the Program**
Execute the main class using Maven:
```bash
mvn exec:java
```

### **View Output Files**
Output files are generated in the directory:
```
FlowLogParser/output
```

---

## **Setup Instructions - Method 2 (Simpler)**
### **1. Extract FlowLogParser_PlainJava.zip**
Extract the Zip file `FlowLogParser_PlainJava.zip` present in root.

---

## **Steps to Run**

### **Place Custom Files**
Place your custom `<flow_record>.txt`and `<lookupFile>.csv` in:
```
./files
```

Update the file names for 'lookupFilePath' and 'flowLogFilePath' in `config.properties` located in root

### **Compile the Project**
Run the following command in your command line:
```bash
javac Main.java
```

### **Run the Program**
Execute the main class using:
```bash
java Main
```

### **View Output Files**
Output files are generated in the directory:
```
./output
```

---


## **Output Details**
1. **Tag Counts**
   - Generated in `output/TagCounts.csv`:
   - Provides the count of matches for each tag.

2. **Port/Protocol Combination Counts**
   - Generated in `output/PortProtocolCounts.csv`:
   - Details the count of matches for each port and protocol combination.
   
## **My Analysis**   
Most flow log records used the TCP protocol.

**Reason:** TCP is the default protocol for many common applications, including web traffic (HTTP/HTTPS), email, and file transfers, due to its reliable connection-oriented nature.

**Scalability:** The parser uses BufferedReader for line-by-line processing, making it efficient for handling large files.

## **Testing & Validation**

I performed some basic validation and testing to ensure the application's functionality, reliability, and correctness.
1. **Sample Data Validation**:
   - Created sample flow log records and lookup table files for testing.
   - Verified that the output files (`tag_count.txt` and `portProtocol_count.txt`) were successfully generated with the expected data.

2. **Data Integrity Check**:
   - Implemented validation to ensure that each row in the lookup table contains exactly three comma-separated values (`dstport`, `protocol`, and `tag`).
   - Lines with invalid formatting were logged for debugging and skipped during processing.

3. **Blank Line Handling**:
   - Validated that the parser effectively skips blank lines or newlines in all input files to ensure clean processing.

4. **Error Handling**:
   - Integrated `try-catch` blocks to handle file writing errors gracefully.
   - Ensured meaningful error messages are logged to `System.err` if any issues occur during file writing.

5. **Edge Case Testing**:
   - Tested the parser with:
     - Empty input files to ensure no crashes or undefined behavior.
     - Unmapped protocols to confirm fallback logic assigns "Unassigned" or "Untagged" as default tags.
