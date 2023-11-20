/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bosonhiggs;

import java.util.List;
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
