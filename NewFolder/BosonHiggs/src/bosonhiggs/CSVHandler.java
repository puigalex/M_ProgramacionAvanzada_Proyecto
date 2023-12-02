package bosonhiggs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHandler {
    private List<List<Float>> data = new ArrayList<>(); 
    private List<String> nombreColumnas = new ArrayList<>(); // Lista de nombres de columnas
    private String directorio;
    private String[] headers;
    private int[] columnasFiltradas;
    
    public CSVHandler(String directorio) {

        this.directorio = directorio;
        this.headers = getHeaders(directorio);
    }

    private String[] getHeaders(String directorio) {
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(directorio))) {
            String linea;
            linea = br.readLine();
            return linea.split(",");
        } catch (java.io.IOException e) {
            System.out.println("Error al cargar los datos" + directorio);
        }
        return null;
    }

    public void desplegarHeaders() {
        for (int i=0; i<this.headers.length; i++) {
            System.out.println(i + ": " + headers[i]);
        }
    }

    public void getColFiltadas(String columnas){
        String[] columnasFiltradas = columnas.split(" ");
        int[] columnasFiltradasInt = new int[columnasFiltradas.length];
        for (int i=0; i<columnasFiltradas.length; i++) {
            columnasFiltradasInt[i] = Integer.parseInt(columnasFiltradas[i]);
        }
        this.columnasFiltradas = columnasFiltradasInt;
    }

    public int[] getColumnasFiltradas() {
        return this.columnasFiltradas;
    }

    static List<String> splitCsv(String sourceFilePath, String destinationDirectory, int rowsPerFile) {
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




    public static void writeData(String header, List<List<String>> data, String filePath) {
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

    public static void writeData(List<List<Float>> data, String directorio) {
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter(directorio))) {
            for (List<Float> medicion : data) {
                bw.write(String.join(",", medicion.toString()));
                bw.newLine();
            }
        } catch (java.io.IOException e) {
            System.out.println("Error al escribir los datos" + directorio);
        }
    }



        public List<List<Float>> getData() {
            return data;
    }
}
    

