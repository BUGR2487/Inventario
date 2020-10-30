package Forms.Components;

import Forms.Panel;

import javax.swing.*;
import java.awt.*;

public class Tabs extends JTabbedPane {


    public Tabs(boolean verticalLabels)
    {
        if( verticalLabels )
            this.setTabPlacement( JTabbedPane.BOTTOM );

        this.setFont(Panel.FUENTE_GENERAL_TXT);
    }

    public void addTab(Tab tab)
    {
        this.addTab(tab.getTitle(),
                    tab.getIcon(),
                    tab.getPanel(),
                    tab.getTip());
    }


    public static class Tab {

        private String title    = "titulo";
        private String tip      = "";
        private Icon icon       = null;
        private Component panel = null;

        public Tab(String title, Icon icon, Component panel, String tip) {
            this.title = title;
            this.tip = tip;
            this.icon = icon;
            this.panel = panel;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }

        public Icon getIcon() {
            return icon;
        }

        public void setIcon(Icon icon) {
            this.icon = icon;
        }

        public Component getPanel() {
            return panel;
        }

        public void setPanel(Component panel) {
            this.panel = panel;
        }
    }


}
