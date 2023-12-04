package bosonhiggs;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
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



        java.util.Date fecha = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMdd_HHmm");
        String fechaActual = sdf.format(fecha);
        String nombreArchivo = "Higgs_filtered(" + fechaActual + ").csv";
        String directorioFinal = getDirectorioEscritura(directorioOrigen, nombreArchivo);
        System.out.println("Escribiendo datos en: " + directorioFinal + nombreArchivo);
        CSVHandler.writeData(data, directorioFinal + nombreArchivo);

    }

    private static String getDirectorioEscritura(String directorio, String nombreArchivo) {
        Path filePath = Paths.get(directorio);
        Path parentDirectory = filePath.getParent(); //eliminar el nombre del archivo
        String fileSeparator = FileSystems.getDefault().getSeparator(); //obtener separador del S.O.
        return (parentDirectory != null) ? parentDirectory.toString() + fileSeparator + filePath.getFileName() : null; //Se regresa como string para que se pueda usar con la clase writeData
    }
    
}
