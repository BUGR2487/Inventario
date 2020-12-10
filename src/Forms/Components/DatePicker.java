package Forms.Components;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePanel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class DatePicker extends JPanel implements JDatePicker {


    private static final long serialVersionUID = 2814777654384974503L;
    private Popup popup;
    private JFormattedTextField formattedTextField;
    private JButton button;
    private JDatePanelImpl datePanel;
    private InternalEventHandler internalEventHandler;
    private Icon icon_show;
    private Icon icon_hide;
    private boolean isTo = false;

    public Date getDateAsDate(){
        return (Date) this.getModel().getValue();
    }

    public Calendar getDateAsCalendar(){
        return (Calendar) this.getModel().getValue();
    }

    public java.sql.Date getDateAsSqlDate(){
        return new java.sql.Date(((java.util.Date)this.getModel().getValue()).getTime());
    }


    public static DatePicker buildDatePicker (boolean isTo) {
        UtilDateModel model = new UtilDateModel();
        if(isTo) {
            Date dt = new Date();
            model.setValue( dt );
        }
        else
        {
            Calendar aCalendar = Calendar.getInstance();
            aCalendar.add(Calendar.MONTH, -1);
            aCalendar.set(Calendar.DATE, 1);

            Date previousDate = aCalendar.getTime();
            model.setValue( previousDate );
        }
        Properties p = new Properties();
        p.put("text.today", "Hoy");
        p.put("text.month", "Mes");
        p.put("text.year", "Año");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        return new DatePicker(datePanel, new DateLabelFormatter(), isTo);
    }

    public DatePicker(JDatePanelImpl datePanel, JFormattedTextField.AbstractFormatter formatter, boolean isTo) {
        this.isTo = isTo;
        this.datePanel = datePanel;
        this.popup = null;
        datePanel.setBorder(BorderFactory.createLineBorder(Color.white));

        this.internalEventHandler = new InternalEventHandler();

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        this.formattedTextField = new JFormattedTextField(formatter);
        DateModel<?> model = datePanel.getModel();
        this.setTextFieldValue(this.formattedTextField, model.getYear(),
                model.getMonth(), model.getDay(), model.isSelected());
        this.formattedTextField.setEditable(false);
        this.add(this.formattedTextField);

        layout.putConstraint("West", this.formattedTextField, 0, "West", this);
        layout.putConstraint("South", this, 0, "South", this.formattedTextField);

        IconFontSwing.register(FontAwesome.getIconFont());

        this.icon_show = IconFontSwing.buildIcon(FontAwesome.CARET_DOWN	,28, Color.black);
        this.icon_hide = IconFontSwing.buildIcon(FontAwesome.CARET_UP	,28, Color.black);

        this.button = new JButton();
        this.button.setFocusable(true);
        this.button.setIcon( this.icon_show );
        this.button.setToolTipText("Presione para desplegar un calendario y seleccione una fecha deseada.");
        this.add(this.button);

        layout.putConstraint("West", this.button, 1, "East", this.formattedTextField);
        layout.putConstraint("East", this, 0, "East", this.button);
        layout.putConstraint("South", this, 0, "South", this.button);

        int h = (int)this.button.getPreferredSize().getHeight();
        int w = (int)datePanel.getPreferredSize().getWidth();

        this.button.setPreferredSize(new Dimension(h, h));

        this.formattedTextField.setPreferredSize(new Dimension(w - h - 1, h));

        this.addHierarchyBoundsListener(this.internalEventHandler);
        this.button.addActionListener(this.internalEventHandler);
        this.formattedTextField.addPropertyChangeListener("value", this.internalEventHandler);

        datePanel.addActionListener(this.internalEventHandler);
        datePanel.getModel().addChangeListener(this.internalEventHandler);
    }

    public void addActionListener(ActionListener actionListener) {
        this.datePanel.addActionListener(actionListener);
    }

    public void removeActionListener(ActionListener actionListener) {
        this.datePanel.removeActionListener(actionListener);
    }

    public DateModel<?> getModel() {
        return this.datePanel.getModel();
    }

    public void setTextEditable(boolean editable) {
        this.formattedTextField.setEditable(editable);
    }

    public boolean isTextEditable() {
        return this.formattedTextField.isEditable();
    }

    public void setButtonFocusable(boolean focusable) {
        this.button.setFocusable(focusable);
    }

    public boolean getButtonFocusable() {
        return this.button.isFocusable();
    }

    public JDatePanel getJDateInstantPanel() {
        return this.datePanel;
    }

    public JFormattedTextField getJFormattedTextField() {
        return this.formattedTextField;
    }

    public static long getTimeFromStringDate(String dateString){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateString);
            return date.getTime();
        } catch (ParseException e) {
            return Calendar.getInstance().getTimeInMillis();
        }
    }

    public void resetCalendar(){
        if(this.isTo) {
            Date dt = new Date();
            ((UtilDateModel)this.getModel()).setValue( dt );
        }
        else
        {
            Calendar aCalendar = Calendar.getInstance();
            aCalendar.add(Calendar.MONTH, -1);
            aCalendar.set(Calendar.DATE, 1);

            Date previousDate = aCalendar.getTime();
            ((UtilDateModel)this.getModel()).setValue( previousDate );
        }
    }

    public void showPopup() {
        if (this.popup == null) {
            PopupFactory fac = new PopupFactory();
            Point xy = this.getLocationOnScreen();
            this.datePanel.setVisible(true);
            this.popup = fac.getPopup(this, this.datePanel, (int)xy.getX(), (int)(xy.getY() + (double)this.getHeight()));
            this.popup.show();
            button.setIcon( icon_hide );
        }

    }

    public void hidePopup() {
        if (this.popup != null) {
            this.popup.hide();
            this.popup = null;
            button.setIcon( icon_show );
        }

    }

    public boolean isDoubleClickAction() {
        return this.datePanel.isDoubleClickAction();
    }

    public boolean isShowYearButtons() {
        return this.datePanel.isShowYearButtons();
    }

    public void setDoubleClickAction(boolean doubleClickAction) {
        this.datePanel.setDoubleClickAction(doubleClickAction);
    }

    public void setShowYearButtons(boolean showYearButtons) {
        this.datePanel.setShowYearButtons(showYearButtons);
    }

    private void setTextFieldValue(JFormattedTextField textField, int year, int month, int day, boolean isSelected)
    {
        if (!isSelected) {
            textField.setValue((Object)null);
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day, 0, 0, 0);
            calendar.set(14, 0);
            textField.setValue(calendar);
        }

    }

    private class InternalEventHandler implements
            ActionListener, HierarchyBoundsListener, ChangeListener,
            PropertyChangeListener
    {
        private InternalEventHandler() {
        }

        public void ancestorMoved(HierarchyEvent arg0) {
            DatePicker.this.hidePopup();
        }

        public void ancestorResized(HierarchyEvent arg0) {
            DatePicker.this.hidePopup();
        }

        public void actionPerformed(ActionEvent arg0) {
            if (arg0.getSource() == DatePicker.this.button) {
                if (DatePicker.this.popup == null) {
                    DatePicker.this.showPopup();

                } else {
                    DatePicker.this.hidePopup();

                }
            } else if (arg0.getSource() == DatePicker.this.datePanel) {
                DatePicker.this.hidePopup();
            }

        }

        public void stateChanged(ChangeEvent arg0) {
            if (arg0.getSource() == DatePicker.this.datePanel.getModel()) {
                DateModel<?> model = DatePicker.this.datePanel.getModel();
                DatePicker.this.setTextFieldValue(DatePicker.this.formattedTextField, model.getYear(), model.getMonth(), model.getDay(), model.isSelected());
            }

        }

        public void propertyChange(PropertyChangeEvent evt) {
            if (DatePicker.this.formattedTextField.isEditable() && DatePicker.this.formattedTextField.getValue() != null) {
                Calendar value = (Calendar)DatePicker.this.formattedTextField.getValue();
                DatePicker.this.datePanel.getModel().setDate(value.get(1), value.get(2), value.get(5));
                DatePicker.this.datePanel.getModel().setSelected(true);
            }

        }
    }
    
    public static class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }

    }
}
