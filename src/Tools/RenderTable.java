package Tools;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class RenderTable extends DefaultTableCellRenderer
{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if(row % 2 == 0)
        {
            component.setBackground(Color.RED);
            component.setForeground(Color.WHITE);
        }
        else
        {
            component.setBackground(Color.BLUE);
            component.setForeground(Color.WHITE);
        }

        if(isSelected)
        {
            component.setBackground(Color.GREEN);
            component.setForeground(Color.WHITE);
        }

        return component;
    }
}
