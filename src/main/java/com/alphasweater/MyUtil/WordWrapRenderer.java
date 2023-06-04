package com.alphasweater.MyUtil;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;


/*
 * This is a custom class that allows me to wrap the text of my tables' content
 * I found this class on stack overflow at this link below:
 * https://stackoverflow.com/questions/37768335/how-to-word-wrap-inside-a-jtable-row
 */


/**
 *  The WordWrapRenderer can be used as a renderer for a JTable column. It will
 *  allow the text to wrap to a new line and adjust the table row height.
 *
 *  Note. This renderer can only be used for a single column in the table.
 */
public class WordWrapRenderer extends JTextArea implements TableCellRenderer {
    public WordWrapRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        setSize(table.getColumnModel().getColumn(column).getWidth(), table.getRowHeight(row));

        //  Recalculate the preferred height now that the text and renderer width have been set.

        int preferredHeight = getPreferredSize().height;

        if (table.getRowHeight(row) != preferredHeight) {
            table.setRowHeight(row, preferredHeight);
        }

        return this;
    }
}
//--------------------------------------------------------------------------------------------------------------------//
//--------------------------------------------------------EOF---------------------------------------------------------//