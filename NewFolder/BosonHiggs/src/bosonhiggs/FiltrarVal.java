package bosonhiggs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FiltrarVal {
    public static List<Float> query(String path, int columna, float valorFiltro) {
        List<Float> valoresFiltrados = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] row = linea.split(",");
                float dato = Float.parseFloat(row[columna]);

                // Verificar si el valor cumple con la condición y agregarlo a la lista
                if (dato == valorFiltro) {
                    valoresFiltrados.add(dato);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los datos" + path);
        }

        // Imprimir los valores que cumplen la condición
        System.out.println("Valores de la columna " + columna + " que cumplen con la condición " + valorFiltro + ":");
        for (float valor : valoresFiltrados) {
            System.out.println(valor);
        }

        return valoresFiltrados;
    }
}
