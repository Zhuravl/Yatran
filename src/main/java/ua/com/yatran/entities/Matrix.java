package ua.com.yatran.entities;

import java.util.*;

/**
 * This class represents information about a keyboard matrix
 */
public class Matrix<I, O> {

    private Map<I, Map<I, O>> mapRows;

    public Matrix() {
    }

    protected Map<I, Map<I, O>> getRowMap() {
        if (mapRows == null) {
            mapRows = new HashMap<>(25);
        }
        return mapRows;
    }

    protected Map<I, O> getColumnMap(I row) {
        Map<I, Map<I, O>> rowMap = getRowMap();
        Map<I, O> mapCols = rowMap.get(row);
        if (mapCols == null) {
            mapCols = new HashMap<>(25);
            rowMap.put(row, mapCols);
        }
        return mapCols;
    }

    public void add(I col, I row, O obj) {
        Map<I, O> columnMap = getColumnMap(row);
        columnMap.put(col, obj);
    }

    public void remove(I col, I row, O obj) {
        if (contains(col, row)) {
            Map<I, O> columnMap = getColumnMap(row);
            columnMap.put(col, obj);
        }
    }

    public void removeColumn(I col) {
        for (I row : getRowMap().keySet()) {
            Map<I, O> columnMap = getRowMap().get(row);
            if (columnMap != null) {
                columnMap.remove(col);
            }
        }
    }

    public void removeRow(I row) {
        getRowMap().remove(row);
    }

    public int getRowCount() {
        return getRowMap().size();
    }

    public int getColumnCount() {
        int max = 0;
        for (I row : getRowMap().keySet()) {
            Map<I, O> mapColumns = getRowMap().get(row);
            max = Math.max(mapColumns.size(), max);
        }
        return max;
    }

    protected boolean containsRow(I row) {
        return getRowMap().containsKey(row);
    }

    protected boolean containsColumn(I col) {
        boolean contains = false;
        for (I row : getRowMap().keySet()) {
            Map<I, O> columnMap = getRowMap().get(row);
            if (columnMap != null && columnMap.containsKey(col)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    public boolean contains(I col, I row) {
        boolean contains = false;
        Map<I, O> colMap = getRowMap().get(row);
        if (colMap != null) {
            if (colMap.containsKey(col)) {
                contains = true;
            }
        }

        return contains;
    }

    public O get(I col, I row) {
        O value = null;
        if (contains(col, row)) {
            Map<I, O> columnMap = getRowMap().get(row);
            value = columnMap.get(col);
        }
        return value;
    }

    public boolean contains(O value) {
        boolean contains = false;
        for (I row : getRowMap().keySet()) {
            Map<I, O> mapColumns = getRowMap().get(row);
            for (I col : mapColumns.keySet()) {
                if (mapColumns.containsValue(value)) {
                    contains = true;
                    break;
                }
            }
        }
        return contains;
    }

    public boolean rowContains(I row, O value) {
        boolean contains = false;
        Map<I, O> mapColumns = getRowMap().get(row);
        for (I col : mapColumns.keySet()) {
            if (mapColumns.containsValue(value)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    public boolean columnContains(I column, O value) {
        boolean contains = false;
        for (I row : getRowMap().keySet()) {
            Map<I, O> mapColumns = getRowMap().get(row);
            O colValue = mapColumns.get(column);
            if (colValue == value) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    public O[] rowToArray(I row, O[] values) {
        List<O> lstValues = new ArrayList<O>(25);
        Map<I, O> mapColumns = getRowMap().get(row);
        lstValues.addAll(mapColumns.values());
        return lstValues.toArray(values);
    }

    public O[] columnToArray(I col, O[] values) {
        List<O> lstValues = new ArrayList<O>(25);
        for (I row : getRowMap().keySet()) {
            Map<I, O> mapCols = getRowMap().get(row);
            lstValues.add(mapCols.get(col));
        }
        return lstValues.toArray(values);
    }

    public Iterator<O> columnIterator(I col) {
        List<O> lstValues = new ArrayList<O>(25);
        for (I row : getRowMap().keySet()) {
            Map<I, O> mapCols = getRowMap().get(row);
            lstValues.add(mapCols.get(col));
        }
        return lstValues.iterator();
    }

    public Iterator<O> rowIterator(I row) {
        List<O> lstValues = new ArrayList<O>(25);
        Map<I, O> mapColumns = getRowMap().get(row);
        lstValues.addAll(mapColumns.values());
        return lstValues.iterator();
    }
}
