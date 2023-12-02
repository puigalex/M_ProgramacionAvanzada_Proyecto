package bosonhiggs;

import java.util.List;


public class Manager implements datosFiltrados {
    
    private Thread poolWorkers[];
    private String directorioOrigen;
    private int[] columnasaFiltrar;
    private int tipoFiltrado;
    private int varFiltrada;
    private float criterioFiltrado = 0;
    private int numCPUs;
    private String cwd = System.getProperty("user.dir");

    public Manager(String directorioOrigen, int[] columnasaFiltrar, int tipoFiltrado, int varFiltrada, float criterioFiltrado, int numCPUs) {
        this.directorioOrigen = directorioOrigen;
        this.columnasaFiltrar = columnasaFiltrar;
        this.tipoFiltrado = tipoFiltrado;
        this.varFiltrada = varFiltrada;
        this.criterioFiltrado = criterioFiltrado;
        this.numCPUs = numCPUs;
    }

    
    // public void filtrarSerial(String dir)
    // { //El manager aqui es el que filtra  el archivo de manera seria sin enviarlo a los workers
    //     try {
    //         CSVHandler csv = new CSVHandler(dir);
    //         csv.desplegarHeaders();
    //         csv.getColFiltadas(columnasaFiltrar);
    //         List<List<Float>> data = csv.getData();
    //         data = filtrarColumnas(data);
    //         data = filtrarRenglones(data);
    //         CSVHandler.writeData(data, cwd + "HiggsFiltrado.csv");
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         System.out.println("Error al leer los datos");
    //     }
    // }


    public void filtrarConcurrente(String dir) {
        
        int lineas = CSVHandler.countData(dir);
        //CSVHandler splitter = new CsvSplitter(lineas/(numCPUs*10)); 
        int numHilos = 1;
        List<String> paths = CSVHandler.splitCsv(directorioOrigen, cwd +"/Temp/", lineas/(numHilos));
        int numWorkers = numHilos;
        this.poolWorkers = new Thread[numWorkers];

        // Empezar temporizador
        long startTime = System.currentTimeMillis();
       
        for (int i = 0; i < numWorkers; i++) {
            Worker worker = new Worker(paths.get(i), columnasaFiltrar, tipoFiltrado, varFiltrada, criterioFiltrado);
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
        CSVHandler.writeData(data, cwd + "HiggsFiltrado.csv");

    }
    
}
