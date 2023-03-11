package ua.com.yatran.entities;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements a keyboard layout
 */
public class KeyBoardLayout implements LayoutManager2 {

    private Map<Component, KeyConstraint> mapComponents;
    private Map<KeyConstraint, Component> mapConstraints;
    private Matrix<Integer, java.util.List<JComponent>> matrix = new Matrix<>(); // Virtual grid...

    private Dimension gridSize;

    public KeyBoardLayout() {
        mapComponents = new HashMap<>(25);
        mapConstraints = new HashMap<>(25);
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
        throw new UnsupportedOperationException("addLayoutComponent(String, Comp) is not supported");
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        KeyConstraint kc = mapComponents.get(comp);
        mapComponents.remove(comp);
        if (kc != null) {
            mapConstraints.remove(kc);
            getCellContents(matrix, kc).remove(comp);
        }
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        int rowHeight = getRowHeight();
        Dimension size = new Dimension();
        size.width = getMaxRowWidth();
        size.height = rowHeight * matrix.getRowCount();
        return size;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return preferredLayoutSize(parent);
    }

    protected java.util.List<JComponent> getCellContents(Matrix matrix, KeyConstraint constraint) {
        return getCellContents(matrix, constraint.column, constraint.row);
    }

    protected java.util.List<JComponent> getCellContents(Matrix<Integer, java.util.List<JComponent>> matrix, int col, int row) {
        if (!matrix.contains(col, row)) {
            matrix.add(col, row, new ArrayList<>());
        }
        return matrix.get(col, row);
    }

    protected Dimension getGridSize() {
        if (gridSize == null) {
            int maxCellWidth = 0;
            int maxCellHeight = 0;
            for (int row = 0; row < matrix.getRowCount(); row++) {
                for (int col = 0; col < matrix.getColumnCount(); col++) {
                    java.util.List<JComponent> cell = getCellContents(matrix, col, row);
                    int cellWidth = 0;
                    int cellHeight = 0;
                    for (JComponent comp : cell) {
                        KeyConstraint kc = mapComponents.get(comp);
                        if (kc.span == 1) {
                            cellWidth = Math.max(cellWidth, comp.getPreferredSize().width);
                        }
                        cellHeight = Math.max(cellHeight, comp.getPreferredSize().height);
                    }
                    maxCellWidth = Math.max(cellWidth, maxCellWidth);
                    maxCellHeight = Math.max(cellHeight, maxCellHeight);
                }
            }
            gridSize = new Dimension(maxCellWidth, maxCellHeight);
        }
        return gridSize;
    }

    protected int getRowHeight() {
        Dimension size = getGridSize();
        return size.height;
    }

    protected int getRowWidth(int row) {
        int rowWidth = 0;
        for (int col = 0; col < matrix.getColumnCount(); col++) {
            Dimension size = getCellSize(col, row);
            rowWidth += size.width;
        }
        return rowWidth;
    }

    protected int getMaxRowWidth() {
        int rowWidth = 0;
        for (int row = 0; row < matrix.getRowCount(); row++) {
            rowWidth = Math.max(getRowWidth(row), rowWidth);
        }
        return rowWidth;
    }

    protected int getColumnWidth(int col) {
        int width = 0;
        for (int row = 0; row < matrix.getRowCount(); row++) {

            Dimension size = getCellSize(col, row);
            width = Math.max(size.width, width);

        }
        return width;
    }

    protected Dimension getCellSize(int col, int row) {
        java.util.List<JComponent> comps = matrix.get(col, row);
        Dimension size = new Dimension();
        size.height = getRowHeight();
        for (JComponent comp : comps) {
            Dimension subSize = getCellSize(col, row, comp);
            size.width = Math.max(size.width, subSize.width);
        }

        return size;
    }

    protected Dimension getCellSize(int col, int row, JComponent comp) {
        java.util.List<JComponent> comps = matrix.get(col, row);
        Dimension size = new Dimension();
        size.height = getRowHeight();
        int defaultWidth = getGridSize().width;
        KeyConstraint kc = mapComponents.get(comp);
        if (kc.span == 1) {
            size.width = defaultWidth;
        } else {
            int totalWidth = (int) Math.round(defaultWidth * kc.span);
            size.width = totalWidth;
        }

        return size;
    }

    @Override
    public void layoutContainer(Container parent) {
        int rowHeight = getRowHeight();
        int y = 0;
        for (int row = 0; row < matrix.getRowCount(); row++) {
            int x = 0;
            for (int col = 0; col < matrix.getColumnCount(); col++) {
                List<JComponent> comps = matrix.get(col, row);
                Rectangle bounds = new Rectangle();
                bounds.x = x;
                bounds.y = y;
                int maxWidth = 0;
                for (JComponent comp : comps) {

                    Dimension size = getCellSize(col, row, comp);
                    bounds.setSize(size);
                    maxWidth = Math.max(maxWidth, size.width);
                    comp.setBounds(bounds);

                }
                x += maxWidth;
            }
            y += rowHeight;
        }
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        if (constraints instanceof KeyConstraint) {
            mapComponents.put(comp, (KeyConstraint) constraints);
            mapConstraints.put((KeyConstraint) constraints, comp);
            getCellContents(matrix, (KeyConstraint) constraints).add((JComponent) comp);
        }
    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return preferredLayoutSize(target);
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0.5f;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0.5f;
    }

    @Override
    public void invalidateLayout(Container target) {
        gridSize = null;
    }
}