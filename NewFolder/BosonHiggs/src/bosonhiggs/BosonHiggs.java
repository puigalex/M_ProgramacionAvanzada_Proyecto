/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bosonhiggs;

import java.util.List;
import java.util.Scanner;
import java.io.File;

/**
 *
 * @author alex
 */
public class BosonHiggs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* 
        CSVHandler csv = new CSVHandler("/Users/alex/Documents/GitHub/M_ProgramacionAvanzada_Proyecto/HIGGS10.csv");
        List<List<Float>> data = csv.getData();
        List<List<Float>> data_filtrada;
        System.out.println(data.get(0));
        //Filter data
        FiltradoCol.filterColumns(data, List.of(0, 1, 2));
        data_filtrada = FiltradoCol.getFilteredColumns();
        //Write data 
        csv.writeData(data_filtrada, "/Users/alex/Documents/GitHub/M_ProgramacionAvanzada_Proyecto/HIGGS10_test_filtrado.csv");
        //String path = "/Users/alex/Documents/GitHub/M_ProgramacionAvanzada_Proyecto/HIGGS10.csv";
        //float promedio = Promedio.promedio(path,2);
        //write data
        //csv.writeData(csv.getData(), "/Users/alex/Documents/GitHub/M_ProgramacionAvanzada_Proyecto/HIGGS10.csv");
        //System.out.println(data.get(0));
        */
        int numCPUs = getCPU();
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el directorio y nombre del archivo: ");
        String directorio = sc.nextLine();
        directorio = "/Users/alex/Documents/GitHub/M_ProgramacionAvanzada_Proyecto/HIGGS10.csv";
        CSVHandler csv = new CSVHandler(directorio);
        csv.desplegarHeaders();
        System.out.println("Introduce el numero de las columnas a filtrar (Separados por espacios): ");
        String columnas = sc.nextLine();
        csv.getColFiltadas(columnas);
        int[] columnasFiltradas = csv.getColumnasFiltradas();
        crearDirectorio();
        System.out.println("Que filtrado quieres hacer, sobre que columna? (1: >, 2: <, 3: =, 4: !=)");
        int tipoFiltrado = sc.nextInt();
        System.out.println("Introduce la variable de filtrada: ");
        int valorFiltrado = sc.nextInt();


    }




    private static int getCPU() {
        return Runtime.getRuntime().availableProcessors();
    }

    private static void crearDirectorio() {
        String path = "/Users/alex/Documents/GitHub/M_ProgramacionAvanzada_Proyecto/Temp/";
        File directorio = new File(path);
        try {
            directorio.mkdir();
        } catch (SecurityException e) {
            System.out.println("Error al crear directorio");
        }
    }


    
}
