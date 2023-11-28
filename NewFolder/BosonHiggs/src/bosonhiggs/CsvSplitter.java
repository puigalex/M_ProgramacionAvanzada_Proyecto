package bosonhiggs;

import java.io.*;
import java.util.*;

public class CsvSplitter {
    private int rowsPerFile;

    public CsvSplitter(int rowsPerFile) {
        this.rowsPerFile = rowsPerFile;
    }

    public List<String> splitCsv(String sourceFilePath, String destinationDirectory) {
        List<String> paths = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFilePath))) {
            String header = br.readLine(); 
            List<List<String>> data = new ArrayList<>();
            String line;
            int rowCount = 0;
            int fileCount = 1;

            while ((line = br.readLine()) != null) {
                List<String> row = Arrays.asList(line.split(",")); 
                data.add(row);
                rowCount++;

                if (rowCount == rowsPerFile) {
                    writeData(header, data, destinationDirectory + "/part_" + fileCount + ".csv");
                    paths.add(destinationDirectory + "/part_" + fileCount + ".csv");
                    rowCount = 0;
                    fileCount++;
                    data.clear();
                }
            }

    
            if (!data.isEmpty()) {
                writeData(header, data, destinationDirectory + "/part_" + fileCount + ".csv");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paths;
    }

    private void writeData(String header, List<List<String>> data, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(header);
            bw.newLine();
            for (List<String> row : data) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ****Test:****

    // public static void main(String[] args) {
    //     CsvSplitter splitter = new CsvSplitter(10); 
    
    //     List<String> paths = splitter.splitCsv("/home/max/Hilos_codigos/Proyecto/Higgs.csv", "/home/max/Hilos_codigos/Proyecto/temp");
    
    //     for (int j = 0; j < paths.size(); j++) {
    //         System.out.println(paths.get(j) + " ");
    //     }
    // }
}


