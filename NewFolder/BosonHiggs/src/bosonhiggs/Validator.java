package bosonhiggs;

public class Validator {

    public static boolean revisarColumnas(String[] columnas, int[] columnasaFiltrar){
        for (int i = 0; i < columnasaFiltrar.length; i++) {
            if (columnasaFiltrar[i] > columnas.length) {
                System.out.println("La columna " + columnasaFiltrar[i] + " no existe");
                return false;
            }
        }
        return true;

    }

    public static boolean revisarVariableaFiltrar(int[] columnasaFiltrar, int varFiltrada){
        for (int i = 0; i < columnasaFiltrar.length; i++) {
            if (columnasaFiltrar[i] == varFiltrada) {
                return true;
            }
        }
        return false;
    } 
}
