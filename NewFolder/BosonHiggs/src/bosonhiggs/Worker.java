package bosonhiggs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;
import java.util.List;

public class Worker implements Runnable, datosFiltrados {

    // private static List<List<Float>> data = new ArrayList<>();
    private static Lock lock = new ReentrantLock();
    private String nombreArchivo;
    private int[] columnasaFiltrar;
    private int tipoFiltrado;
    private int varFiltrada;
    private float criterioFiltrado = 0;

    public Worker(String nombreArchivo, int[] columnasaFiltrar, int tipoFiltrado, int varFiltrada, float criterioFiltrado) {
        this.nombreArchivo = nombreArchivo;
        this.columnasaFiltrar = columnasaFiltrar;
        this.tipoFiltrado = tipoFiltrado;
        this.varFiltrada = varFiltrada;
        this.criterioFiltrado = criterioFiltrado;
    }



    @Override
    public void run() {
        try {
            leerCSV();
            System.out.println("Se termino de leer el archivo y la varible data tiene: " + data.size());
        } catch (IOException e) {
            System.err.println("Error leyendo el csv: " + e.getMessage());
        }
    }
    
    private void leerCSV() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            reader.readLine();
            while ((linea = reader.readLine()) != null) {
                String[] row = linea.split(",");
                List<Float> medicion = new ArrayList<>();
                for (String dato : row) {
                    medicion.add(Float.parseFloat(dato));
                }
                //Filtar valor
                medicion = filtrarRenglon(medicion);
                //Filtrar columnas si medicion trae valor 
                if (medicion.size() > 0){
                medicion = filtrarColumnas(medicion);
                escribirResultado(medicion);}
                
            }
        }
        catch (IOException e) {
            System.err.println("Error leyendo el csv: " + e.getMessage());
        }
    }


    public static void escribirResultado(List<Float> registro) {
        lock.lock();
        try {
            data.add(registro);
            //System.out.println("Se escribio registro nuevo por en ID tipoFiltrado: " + Thread.currentThread().getId());
            //System.out.println(data.size());
        } finally {
            lock.unlock();
        }
    }

    private List<Float> filtrarColumnas(List<Float> registro) {
        List<Float> registroFiltrado = new ArrayList<>();
        for (int columna : columnasaFiltrar) {
            registroFiltrado.add(registro.get(columna));
        }
        return registroFiltrado;
    }

    private List<Float> filtrarRenglon(List<Float> registro){
        List<Float> registroFiltrado = new ArrayList<>();
        switch (tipoFiltrado) {
            case 0:
                registroFiltrado = registro;
                break;
            case 1:
                if (registro.get(varFiltrada) > criterioFiltrado) {
                    registroFiltrado = registro;
                }
                break;
            case 2:
                if (registro.get(varFiltrada) < criterioFiltrado) {
                    registroFiltrado = registro;
                }
                break;
            case 3:
                if (registro.get(varFiltrada) == criterioFiltrado) {
                    registroFiltrado = registro;
                }
                break;
            case 4:
                if (registro.get(varFiltrada) != criterioFiltrado) {
                    registroFiltrado = registro;
                }
                break;
            case 5:
                if (registro.get(varFiltrada) >= criterioFiltrado) {
                    registroFiltrado = registro;
                }
                break;
            case 6:
                if (registro.get(varFiltrada) <= criterioFiltrado) {
                    registroFiltrado = registro;
                }
                break;
            default:
                break;
        }
        return registroFiltrado;
    }
}
