package bosonhiggs;

import java.util.List;

public class Manager {
    

    private Thread poolWorkers[];
    
    // recibirTarea()
    public void filtrarConcurrente(String dir) {
                
        
        CsvSplitter splitter = new CsvSplitter(10); 
    
        List<String> paths = splitter.splitCsv("/home/max/Hilos_codigos/Proyecto/Higgs.csv", "/home/max/Hilos_codigos/Proyecto/temp");
        // Fin metodo recibirTarea()
        
        // crearWorkers(int cantidadWorkers)
        int numWorkers = 8;
        this.poolWorkers = new Thread[numWorkers];
        // Fin del metodo  crearWorkers(int cantidadWorkers)
       
        for (int i = 0; i < numWorkers; i++) {
            Worker worker = new Worker(paths.get(i), new int[]{0,1,2},i,i,0.5f);
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
    }
    
}
