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
- **Maven**: Latest version (3.8.x recommended).

---

## **Assumptions**
1. The program supports only **default log format** as per [Amazon VPC Flow Logs Documentation](https://docs.aws.amazon.com/vpc/latest/userguide/flow-log-records.html).
2. Only **version 2** of the flow logs is supported.
3. The program assumes the following input files:
   - `Input.txt`: Contains flow log records.
   - `LookupFile.csv`: Contains the lookup table with `dstport`, `protocol`, and `tag` mappings.
4. Output files will always be generated in a predefined directory.

---

## **Setup Instructions**
### **1. Install Maven (if not present)**
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
### **Clone or Download the Repository**
Clone the repository:
```bash
git clone <repository-url>
cd FlowLogParser
```

### **Compile the Project**
Run the following Maven commands in the project directory:
```bash
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
FlowLogParser/src/main/java/org/example/output
```

### **Test with Custom Files**
Place your custom `Input.txt` (flow log records) and `LookupFile.csv` (lookup table) in:
```
FlowLogParser/src/main/resources/files
```

Update the file paths in `config.properties` located in:
```
FlowLogParser/src/main/resources/
```

---

## **Output Details**
1. **Tag Counts**
   - Generated in `output/TagCounts.csv`:
   - Provides the count of matches for each tag.

2. **Port/Protocol Combination Counts**
   - Generated in `output/PortProtocolCounts.csv`:
   - Details the count of matches for each port and protocol combination.
