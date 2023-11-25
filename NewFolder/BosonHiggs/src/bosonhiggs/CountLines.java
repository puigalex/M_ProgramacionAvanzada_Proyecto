package Proyecto;

import java.util.ArrayList;
import java.util.List;

public class CountLines {
    
    static String directorio;
    static List<String> nombreColumnas = new ArrayList<>(); // Lista de nombres de columnas


    public static int countData(String directorio) {
        int count = 0;
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(directorio))) {
            String linea;
            linea = br.readLine();
            nombreColumnas =  java.util.Arrays.asList(linea.split(",")); // Guardamos los nombres de las columnas
            while ((linea = br.readLine()) != null) {
                 count += 1; 
            }
        } catch (java.io.IOException e) {
            System.out.println("Error al cargar los datos" + directorio);
        }
        return count;
    }

    // ****Test:****

    // public static void main(String[] args){
    //     int lineas = CountLines.countData("/home/max/Hilos_codigos/Proyecto/Higgs.csv");
    //     System.out.println(lineas);

    // }


}

