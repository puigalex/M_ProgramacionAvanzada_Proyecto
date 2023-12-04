package bosonhiggs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;


public class CSVHandler {
    private List<List<Float>> data = new ArrayList<>(); 
    private String directorio;
    private String[] headers;
    private int[] columnasaFiltrar;
    
    public CSVHandler(String directorio) {
        this.directorio = directorio;
        this.headers = getHeaders(directorio);
    }

    public static String[] getHeaders(String directorio) {
        // Metodo que retorna el nombre de las columnas del archivo CSV
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
        //Metodo que despliega los nombres de las columnas del archivo CSV
        for (int i=0; i<this.headers.length; i++) {
            System.out.println(i + ": " + headers[i]);
        }
    }

    public void getColFiltadas(String columnas){
        // Metodo que recibe el String de columnas introducidas por el usuario, regresa un arreglo de enteros
        String[] columnasaFiltrar = columnas.split(" ");
        int[] columnasaFiltrarInt = new int[columnasaFiltrar.length];
        for (int i=0; i<columnasaFiltrar.length; i++) {
            columnasaFiltrarInt[i] = Integer.parseInt(columnasaFiltrar[i]);
        }
        this.columnasaFiltrar = columnasaFiltrarInt;
    }

    public int[] getcolumnasaFiltrar() {
        //Metodo que regreso el arreglo de enteros de las columnas a filtrar (La variable es privada)
        return this.columnasaFiltrar;
    }

    static List<String> splitCsv(String sourceFilePath, String destinationDirectory, int rowsPerFile) {
        // Metodo que divide el archivo CSV en varios archivos CSV dependiendo del numero de CPUs
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
        // Metodo que escribe los datos en un archivo CSV considerando el header
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
        // Metodo que escribe los datos en un archivo CSV, unicamente los datos, sin header
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter(directorio))) {
            for (List<Float> medicion : data) {
                bw.write(String.join(",", medicion.toString()));
                bw.newLine();
            }
        } catch (java.io.IOException e) {
            System.out.println("Error al escribir los datos" + directorio);
        }
    }


    public static int countData(String directorio) {
        // Metodo que cuenta el numero de datos en un archivo CSV
        int count = 0;
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(directorio))) {
            String linea;
            linea = br.readLine();
            while ((linea = br.readLine()) != null) {
                 count += 1; 
            }
        } catch (java.io.IOException e) {
            System.out.println("Error al cargar los datos" + directorio);
        }
        return count;
    }
        public List<List<Float>> getData() {
            return data;
    }


    public static void crearDirectorio() {
        // Metodo que crea un directorio para guardar los archivos CSV
        String path = System.getProperty("user.dir") + "/Temp";
        File directorio = new File(path);
        try {
            directorio.mkdir();
        } catch (SecurityException e) {
            System.out.println("Error al crear directorio");
        }
    }


    public static void eliminarDirectorio(){
        //Metodo que elimina el directorio creado para guardar los archivos CSV
        String path = System.getProperty("user.dir") + "/Temp";
        File directorio = new File(path);
        File[] archivos = directorio.listFiles();
        for (File archivo : archivos) {
            archivo.delete();
        }
        directorio.delete();
    }
}
    

