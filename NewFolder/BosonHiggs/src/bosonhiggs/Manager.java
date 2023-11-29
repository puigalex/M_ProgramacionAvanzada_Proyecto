package bosonhiggs;

import java.util.List;


public class Manager implements datosFiltrados {
    
    private Thread poolWorkers[];
    private String nombreArchivo;
    private int[] columnasFiltradas;
    private int tipoFiltrado;
    private int valorFiltrado;
    private float criterio = 0;
    private int numCPU;

    public Manager(String nombreArchivo, int[] columnasFiltradas, int tipoFiltrado, int valorFiltrado, float criterio, int numCPU) {
        this.nombreArchivo = nombreArchivo;
        this.columnasFiltradas = columnasFiltradas;
        this.tipoFiltrado = tipoFiltrado;
        this.valorFiltrado = valorFiltrado;
        this.criterio = criterio;
        this.numCPU = numCPU;
    }

    
    public void filtrarSerial(String dir)
    {

    }

    // recibirTarea()
    public void filtrarConcurrente(String dir) {
        
        int lineas = CountLines.countData(dir);
        //CSVHandler splitter = new CsvSplitter(lineas/(numCPU*10)); 
    
        List<String> paths = CSVHandler.splitCsv(nombreArchivo, "/Users/alex/Documents/GitHub/M_ProgramacionAvanzada_Proyecto/Temp/", lineas/(numCPU*10));

    
        int numWorkers = numCPU*10;
        this.poolWorkers = new Thread[numWorkers];

        // Empezar temporizador
        long startTime = System.currentTimeMillis();
       
        for (int i = 0; i < numWorkers; i++) {
            Worker worker = new Worker(paths.get(i), columnasFiltradas, tipoFiltrado, valorFiltrado, criterio);
            poolWorkers[i] = new Thread(worker);
            poolWorkers[i].start();
        }
        
        for (int i = 0; i < numWorkers; i++) {
            try {
                poolWorkers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Terminar temporizador
        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo de ejecucion: " + (endTime - startTime) + " milisegundos");
        System.out.println("Termino el filtrado, data contiene: " + data.size() + " registros");
        // Escribir CSV
        CSVHandler.writeData(data, "/Users/alex/Documents/GitHub/M_ProgramacionAvanzada_Proyecto/Temp/" + "HiggsFiltrado_2M.csv");

    }
    
}
