package bosonhiggs;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CSVHandler {
    private List<Map<String, Double>> data = new ArrayList<>(); // Lista de mapas para almacenar datos con el nombre de la columna
    private List<String> nombreColumnas = new ArrayList<>(); // Lista de nombres de columnas
    private String directorio;
    
    public CSVHandler(String directorio) {
        this.directorio = directorio;
        readData();
    
    }


    private void readData() {
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(this.directorio))) {
        String linea;
        linea = br.readLine();
        nombreColumnas =  java.util.Arrays.asList(linea.split(",")); // Guardamos los nombres de las columnas
        while ((linea = br.readLine()) != null) {
            String[] mediciones = linea.split(",");
            Map<String, Double> medicion = new java.util.HashMap<>();
            for (int i = 0; i < mediciones.length; i++) {
                medicion.put(nombreColumnas.get(i), Double.parseDouble(mediciones[i]));
            }
            data.add(medicion);
        }
    } catch (java.io.IOException e) {
        System.out.println("Error al cargar los datos" + this.directorio);
    }  
    }

        public List<Map<String, Double>> getData() {
            return data;
    }
}
    

