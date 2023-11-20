package bosonhiggs;
import java.util.ArrayList;
import java.util.List;

public class FiltradoCol {
    private static List<List<Float>> filteredColumns;

    public static void filterColumns(List<List<Float>> data, List<Integer> columnsToKeep) {
        filteredColumns = new ArrayList<>();

        for (List<Float> row : data) {
            List<Float> filteredValues = new ArrayList<>();

            for (int columnIndex : columnsToKeep) {
                filteredValues.add(row.get(columnIndex));
            }

            filteredColumns.add(filteredValues);
        }
    }

    public static List<List<Float>> getFilteredColumns() {
        return filteredColumns;
    }}