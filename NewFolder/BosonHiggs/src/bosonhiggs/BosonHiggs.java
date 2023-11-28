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
        System.out.println("Introduce la variable a filtrar: ");
        int valorFiltrado = sc.nextInt();
        System.out.println("Que filtrado quieres hacer, sobre que columna? (0: Sin filtro \n1: > \n2: < \n3: =, \n4: !=, \n5: >=, \n6: <=)");
        int tipoFiltrado = sc.nextInt();
        System.out.println("Criterio a usar para filtrar: ");
        float criterio = sc.nextFloat();

        // Echar a andar el manager 



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
