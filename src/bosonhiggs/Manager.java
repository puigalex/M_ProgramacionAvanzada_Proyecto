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
        // Constructor del Manager
        this.directorioOrigen = directorioOrigen;
        this.columnasaFiltrar = columnasaFiltrar;
        this.tipoFiltrado = tipoFiltrado;
        this.varFiltrada = varFiltrada;
        this.criterioFiltrado = criterioFiltrado;
        this.numCPUs = numCPUs;
    }


    public void filtrarConcurrente(String dir) {
        // Metodo donde el Manager divide los archivos en multiples CSV
        //Crea un pool de workers
        //Asigna un CSV a cada uno de los workers
        int lineas = CSVHandler.countData(dir);
        int numHilos = numCPUs*10;
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

        CSVHandler.writeData(data, cwd + "/HiggsFiltrado.csv");

    }
    
}
