/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bosonhiggs;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author alex
 */
public class BosonHiggs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CSVHandler csv = new CSVHandler("/Users/alex/Documents/GitHub/M_ProgramacionAvanzada_Proyecto/HIGGS10.csv");
        List<Map<String, Double>> data = csv.getData();
        System.out.println(data.get(0));

        /*
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el directorio: ");
        String directorio = sc.nextLine();
        CSVHandler csv2 = new CSVHandler(directorio);
        List<Map<String, Double>> data2 = csv2.getData();
        System.out.println(data2.get(0));

        */

    }
    
}
