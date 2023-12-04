package bosonhiggs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Desviacion {
    public static float calcularDesviacionEstandar(String path, int columna) {
        float promedio = Promedio.promedio(path, columna);
        float suma = 0;
        int contador = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] row = linea.split(",");
                float dato = Float.parseFloat(row[columna]);
                suma += dato;
                contador++;
                float diferencia = dato - promedio;
                suma += Math.pow(diferencia, 2);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los datos" + path);
        }

        float varianza = suma / contador;
        float desviacionEstandar = (float) Math.sqrt(varianza);
        System.out.println("La desviación estándar de la columna " + columna + " es: " + desviacionEstandar);
        return desviacionEstandar;
    }
}