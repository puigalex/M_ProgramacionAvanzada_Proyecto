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
    private int[] columnasFiltradas;
    private int tipoFiltrado;
    private int valorFiltrado;
    private float criterio = 0;

    public Worker(String nombreArchivo, int[] columnasFiltradas, int tipoFiltrado, int valorFiltrado, float criterio) {
        this.nombreArchivo = nombreArchivo;
        this.columnasFiltradas = columnasFiltradas;
        this.tipoFiltrado = tipoFiltrado;
        this.valorFiltrado = valorFiltrado;
        this.criterio = criterio;
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
            while ((linea = reader.readLine()) != null) {
                String[] row = linea.split(",");
                List<Float> medicion = new ArrayList<>();
                for (String dato : row) {
                    medicion.add(Float.parseFloat(dato));
                }
                //Filtar valor

                //Fultrar columnas
                escribirResultado(medicion);
                
            }
        }
    }


    public static void escribirResultado(List<Float> registro) {
        lock.lock();
        try {
            data.add(registro);
            System.out.println("Se escribio registro nuevo por en ID tipoFiltrado: " + Thread.currentThread().getId());
            System.out.println(data.size());
        } finally {
            lock.unlock();
        }
    }

    private List<Float> filtrarColumnas(List<Float> registro) {
        List<Float> registroFiltrado = new ArrayList<>();
        for (int columna : columnasFiltradas) {
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
                if (registro.get(valorFiltrado) > criterio) {
                    registroFiltrado = registro;
                }
                break;
            case 2:
                if (registro.get(valorFiltrado) < criterio) {
                    registroFiltrado = registro;
                }
                break;
            case 3:
                if (registro.get(valorFiltrado) == criterio) {
                    registroFiltrado = registro;
                }
                break;
            case 4:
                if (registro.get(valorFiltrado) != criterio) {
                    registroFiltrado = registro;
                }
                break;
            case 5:
                if (registro.get(valorFiltrado) >= criterio) {
                    registroFiltrado = registro;
                }
                break;
            case 6:
                if (registro.get(valorFiltrado) <= criterio) {
                    registroFiltrado = registro;
                }
                break;
            default:
                break;
        }
        return registroFiltrado;
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





    // public static void main(String[] args) {
    //     // TEST ELIMINAR--------------------
    //     Thread thread = new Thread(new Worker("/Users/alex/Documents/GitHub/M_ProgramacionAvanzada_Proyecto/HIGGS10c.csv", new int[]{0, 1, 2}, 0, 0));
    //     thread.start();
    //     try {
    //         thread.join();
    //     } catch (InterruptedException e) {
    //         e.printStackTrace();
    //     }
    // }

    private static Thread[] poolWorkers;
    public static void main(String[] args) {
        int numWorkers = 8;
        

        poolWorkers = new Thread[numWorkers];

        String[] csvFilePaths = {
            "/Users/alex/Documents/GitHub/M_ProgramacionAvanzada_Proyecto/HIGGS10c.csv",
            "/Users/alex/Documents/GitHub/M_ProgramacionAvanzada_Proyecto/HIGGS10c1.csv",
        };

        for (int i = 0; i < Math.min(numWorkers, csvFilePaths.length); i++) {
            Worker worker = new Worker(csvFilePaths[i], new int[]{0, 1, 2}, i, i, i);
            poolWorkers[i] = new Thread(worker);
            poolWorkers[i].start();
        }

        for (int i = 0; i < Math.min(numWorkers, csvFilePaths.length); i++) {
            try {
                poolWorkers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Guardar data en csv con writedata
        writeData(data, "/Users/alex/Documents/GitHub/M_ProgramacionAvanzada_Proyecto/HIGGS10c_filtrado.csv");
        

    }
}
