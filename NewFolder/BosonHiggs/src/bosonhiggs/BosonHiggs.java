/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bosonhiggs;

import java.util.Scanner;
import java.io.File;


public class BosonHiggs {

 
    public static void main(String[] args) {
        boolean flag = true;
        String directorioOrigen = null;
        int[] columnasaFiltrar= null;
        int tipoFiltrado=0;
        int varFiltrada=0;
        float criterioFiltrado = 0;
        int numCPUs = getCPU();

        Scanner sc = new Scanner(System.in);
        while(flag){
        try {
            System.out.print("\033[H\033[2J");
            System.out.println("1) Introduce el directorio y nombre del archivo: ");
            directorioOrigen = sc.nextLine();
            directorioOrigen = "/Users/alex/Documents/GitHub/M_ProgramacionAvanzada_Proyecto/HIGGS_2M.csv";
            CSVHandler csv = new CSVHandler(directorioOrigen);
            csv.desplegarHeaders();
            System.out.print("\033[H\033[2J");
            System.out.println("2) Introduce el numero de las columnas a filtrar (Separados por espacios): ");
            String columnas = sc.nextLine();
            csv.getColFiltadas(columnas);
            columnasaFiltrar = csv.getcolumnasaFiltrar();
            CSVHandler.crearDirectorio();
            System.out.print("\033[H\033[2J");
            System.out.println("3) Introduce la variable a filtrar: ");
            varFiltrada = sc.nextInt();
            System.out.print("\033[H\033[2J");
            System.out.println("4) Que filtrado quieres hacer, sobre que columna? \n0: Sin filtro \n1: > \n2: < \n3: =, \n4: !=, \n5: >=, \n6: <=");
            tipoFiltrado = sc.nextInt();
            System.out.print("\033[H\033[2J");
            System.out.println("5) Criterio a usar para filtrar: ");
            criterioFiltrado = sc.nextFloat();
            flag = false;
        }

        catch (Exception e) {
            e.printStackTrace();
            System.out.print("\033[H\033[2J");
            System.out.println("Error al leer los datos [Presiona ENTER para continuar]");
            sc.nextLine();
            flag = true;
        }
        
        
    }
        
        // Echar a andar el manager 
        Manager manager = new Manager(directorioOrigen, columnasaFiltrar, tipoFiltrado, varFiltrada, criterioFiltrado, numCPUs);
        manager.filtrarConcurrente(directorioOrigen);
        CSVHandler.eliminarDirectorio();
    }




    private static int getCPU() {
        return Runtime.getRuntime().availableProcessors();
    }
}
