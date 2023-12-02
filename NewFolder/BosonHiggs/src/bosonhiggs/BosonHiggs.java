/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bosonhiggs;

import java.util.Scanner;
import java.io.File;

/**
 *
 * @author alex
 */
public class BosonHiggs {

 
    public static void main(String[] args) {
        boolean flag = true;
        String directorio = null;
        int[] columnasFiltradas= null;
        int tipoFiltrado=0;
        int valorFiltrado=0;
        float criterio = 0;
        int numCPUs = getCPU();
        
        while(flag){
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Introduce el directorio y nombre del archivo: ");
            directorio = sc.nextLine();
            directorio = "/Users/alex/Documents/GitHub/M_ProgramacionAvanzada_Proyecto/HIGGS_2M.csv";
            CSVHandler csv = new CSVHandler(directorio);
            csv.desplegarHeaders();
            System.out.println("Introduce el numero de las columnas a filtrar (Separados por espacios): ");
            String columnas = sc.nextLine();
            csv.getColFiltadas(columnas);
            columnasFiltradas = csv.getColumnasFiltradas();
            crearDirectorio();
            System.out.println("Introduce la variable a filtrar: ");
            valorFiltrado = sc.nextInt();
            System.out.println("Que filtrado quieres hacer, sobre que columna? (0: Sin filtro \n1: > \n2: < \n3: =, \n4: !=, \n5: >=, \n6: <=)");
            tipoFiltrado = sc.nextInt();
            System.out.println("Criterio a usar para filtrar: ");
            criterio = sc.nextFloat();
            flag = false;
        }

        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al leer los datos");
        }
        
    }
        

        // Echar a andar el manager 
        Manager manager = new Manager(directorio, columnasFiltradas, tipoFiltrado, valorFiltrado, criterio, numCPUs);
        manager.filtrarConcurrente(directorio);
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
