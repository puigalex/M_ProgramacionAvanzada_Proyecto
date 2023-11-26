package bosonhiggs;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;
import java.util.List;

public class Worker implements Runnable {

    private static List<List<Float>> data = new ArrayList<>();
    private static Lock lock = new ReentrantLock();
    private String nombreArchivo;

    public Worker(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public void run() {
        try {
            leerCSV();
        } catch (IOException e) {
            System.err.println("Error leyendo el csv: " + e.getMessage());
        }
    }

    private void leerCSV() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Aplicar todos los filtros
                String[] row = linea.split(",");
                List<Float> medicion = new ArrayList<>();
                for (String dato : row) {
                    medicion.add(Float.parseFloat(dato));
                }
                escribirResultado(medicion);
                
            }
        }
    }


    public static void escribirResultado(List<Float> registro) {
        lock.lock();
        try {
            data.add(registro);
            System.out.println("Se escribio registro nuevo");
        } finally {
            lock.unlock();
        }
    }



    public static void main(String[] args) {
        // TEST ELIMINAR--------------------
        Thread thread = new Thread(new Worker("/Users/alex/Documents/GitHub/M_ProgramacionAvanzada_Proyecto/HIGGS10_test_filtrado.csv"));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
