package com.alphasweater.MyUtil;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;

/*
 * This is a custom class that allows me to wrap the text of my tables' content
 * I found most of this class on stack overflow at this link below:
 * https://stackoverflow.com/questions/37768335/how-to-word-wrap-inside-a-jtable-row
 */

/**
 * The WordWrapRenderer can be used as a renderer for a JTable column. It will
 * allow the text to wrap to a new line and adjust the table row height.
 * <p>
 * Note: This renderer can only be used for a single column in the table.
 */
public class WordWrapRenderer extends JTextArea implements TableCellRenderer {
    public WordWrapRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
    }

    //----------------------------------------------------------------------------------------------------------------//
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        setText((value == null) ? "" : value.toString());

        // Calculate the preferred size based on the content and available column width
        int maxWidth = table.getColumnModel().getColumn(column).getWidth();
        setSize(new Dimension(maxWidth, Integer.MAX_VALUE));

        // Adjust the background and font color based on selection
        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        }

        // Set the font explicitly to match the other columns
        setFont(table.getFont());

        int preferredHeight = getPreferredSize().height;
        // Adjust the table row height
        if (table.getRowHeight(row) != preferredHeight) {
            table.setRowHeight(row, preferredHeight);
        }

        return this;
    }

    //----------------------------------------------------------------------------------------------------------------//
    // Method to set the preferred column width for wrapping text
    public static void setColumnWidth(JTable table, int column, int margin) {
        TableColumnModel columnModel = table.getColumnModel();
        int maxWidth = 0;

        for (int row = 0; row < table.getRowCount(); row++) {
            TableCellRenderer renderer = table.getCellRenderer(row, column);
            Component comp = table.prepareRenderer(renderer, row, column);
            maxWidth = Math.max(comp.getPreferredSize().width, maxWidth);
        }

        maxWidth += 2 * margin; // Add margin for better readability

        if (maxWidth > 0) {
            columnModel.getColumn(column).setPreferredWidth(maxWidth);
        }
    }
}

//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//