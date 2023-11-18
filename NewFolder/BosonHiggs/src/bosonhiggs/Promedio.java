/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bosonhiggs;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author alex
 */
public class Promedio {
    static float suma;
    static int contador;
    static float promedio;

    static float promedio(String path, int columna) {
    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
        String linea = reader.readLine();
        while ((linea = reader.readLine()) != null) {
            String[] row = linea.split(",");
            suma += Float.parseFloat(row[columna]);
            contador++;
        }
        promedio = suma / contador;
        System.out.println("El promedio de la columna " + columna + " es: " + promedio);
    } catch (IOException e) {
        System.out.println("Error al cargar los datos" + path);
    }
    return promedio;
    }
}
