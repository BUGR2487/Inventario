package Tools;

import Forms.Login.Login;
import Forms.Principal.Principal;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowAdapterImplement extends WindowAdapter {

    private Login from;
    private Principal instance;

    public WindowAdapterImplement(Login fromJFrame, Principal panelInstance){
        this.setFrom(fromJFrame);
        this.setInstance(panelInstance);
    }

    public Login getFrom() {
        return from;
    }

    public void setFrom(Login from) {
        this.from = from;
    }

    public Principal getInstance() {
        return instance;
    }

    public void setInstance(Principal instance) {
        this.instance = instance;
    }

    public void windowClosing(WindowEvent windowEvent) {
        int choose = JOptionPane.showConfirmDialog(null,
                "De verdad quiere cerrar su sesi\u00f3n?", "Cerrar sesi\u00f3n?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (choose == JOptionPane.YES_OPTION)
        {
            instance.stopTimer();
            instance.setVisible(false);
            from.cerrarSesion();
            from.setVisible(true);
        }
    }

}
