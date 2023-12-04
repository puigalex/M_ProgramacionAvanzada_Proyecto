package bosonhiggs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MaxMin {
    public static float max(String path, int columna) {
        float maximo = Float.MIN_VALUE; // Valor inicial mínimo posible para el máximo

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] row = linea.split(",");
                float dato = Float.parseFloat(row[columna]);

                // Comprobar y actualizar el máximo
                if (dato > maximo) {
                    maximo = dato;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los datos" + path);
        }

        // Imprimir el resultado
        System.out.println("El máximo de la columna " + columna + " es: " + maximo);
        return maximo;
    }

    public static float min(String path, int columna) {
        float minimo = Float.MAX_VALUE; // Valor inicial máximo posible para el mínimo

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] row = linea.split(",");
                float dato = Float.parseFloat(row[columna]);

                // Comprobar y actualizar el mínimo
                if (dato < minimo) {
                    minimo = dato;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los datos" + path);
        }

        // Imprimir el resultado
        System.out.println("El mínimo de la columna " + columna + " es: " + minimo);
        return minimo;
    }
}
