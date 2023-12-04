package bosonhiggs;

public class Validator {
    // Clase para revisar que los datos introducidos por el usuario sean correctos

    public static boolean revisarColumnas(String[] columnas, int[] columnasaFiltrar){
        // etodo para revisar que el usuario no ingrese columnas que no existen a filtrar
        for (int i = 0; i < columnasaFiltrar.length; i++) {
            if (columnasaFiltrar[i] > columnas.length) {
                System.out.println("La columna " + columnasaFiltrar[i] + " no existe");
                return false;
            }
        }
        return true;

    }

    public static boolean revisarVariableaFiltrar(int[] columnasaFiltrar, int varFiltrada){
        // Metodo para revisar que el usuario no ingrese una variable a filtrar que no este en las columnas seleccionadas
        for (int i = 0; i < columnasaFiltrar.length; i++) {
            if (columnasaFiltrar[i] == varFiltrada) {
                return true;
            }
        }
        return false;
    } 
}
