package bosonhiggs;

import java.util.ArrayList;
import java.util.List;

public class CSVHandler {
    private List<List<Float>> data = new ArrayList<>(); 
    private List<String> nombreColumnas = new ArrayList<>(); // Lista de nombres de columnas
    private String directorio;
    
    public CSVHandler(String directorio) {
        this.directorio = directorio;
        readData();
    
    }

    public void writeData(List<List<Float>> data, String directorio) {
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter(directorio))) {
            bw.write(String.join(",", nombreColumnas));
            bw.newLine();
            for (List<Float> medicion : data) {
                bw.write(String.join(",", medicion.toString()));
                bw.newLine();
            }
        } catch (java.io.IOException e) {
            System.out.println("Error al escribir los datos" + directorio);
        }
    }

    private void readData() {
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(this.directorio))) {
        String linea;
        linea = br.readLine();
        nombreColumnas =  java.util.Arrays.asList(linea.split(",")); // Guardamos los nombres de las columnas
        while ((linea = br.readLine()) != null) {
            System.err.println(linea);
            String[] mediciones = linea.split(",");
            List<Float> medicion = new ArrayList<>();
            for (int i = 0; i < mediciones.length; i++) {
                medicion.add(Float.parseFloat(mediciones[i]));
            }
            data.add(medicion);
        }
    } catch (java.io.IOException e) {
        System.out.println("Error al cargar los datos" + this.directorio);
    }  
    }

        public List<List<Float>> getData() {
            return data;
    }
}
    

