package Forms.Principal.Transporte;

import Forms.Panel;
import Tools.Config;
import Tools.DataBase.Transporte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.sql.SQLException;

public class PanelTransportes extends JPanel implements ActionListener, KeyListener, Panel {

    // -- VARIABLES:
    // -- etiquetas
    private final JLabel CHOFER_LB              = new JLabel("Chofer");
    private final JLabel EMPRESA_LB             = new JLabel("Empresa");

    private final JLabel PLACAS_LB              = new JLabel("Placas");
    private final JLabel TRACTO_CAMION_LB       = new JLabel("Tracto camion");

    // -- campos de texto
    private final JTextField CHOFER_TXT         = new JTextField();
    private final JTextField EMPRESA_TXT        = new JTextField();

    private final JTextField PLACAS_TXT         = new JTextField();
    private final JTextField TRACTO_CAMION_TXT  = new JTextField();

    // -- botones:
    private final JButton REGISTRAR_TRANSPORTE      = new JButton("Registrar transporte.");

    private final Transporte TRANSPORTE = new Transporte();

    // -- CONSTRUCTOR:

    public PanelTransportes() throws Config.EmptyProperty, SQLException, Config.ReadException {
        this.prepareAll();
        this.setLayout(new TransportesLayout(this));
    }

    // -- METODOS DE LA CLASE:

    private void prepareAll() {
        this.CHOFER_LB              .setFont(this.FUENTE_GENERAL_LB);
        this.EMPRESA_LB             .setFont(this.FUENTE_GENERAL_LB);

        this.PLACAS_LB              .setFont(this.FUENTE_GENERAL_LB);
        this.TRACTO_CAMION_LB       .setFont(this.FUENTE_GENERAL_LB);

        this.CHOFER_TXT             .setFont(this.FUENTE_GENERAL_TXT);
        this.EMPRESA_TXT            .setFont(this.FUENTE_GENERAL_TXT);

        this.PLACAS_TXT             .setFont(this.FUENTE_GENERAL_TXT);
        this.TRACTO_CAMION_TXT      .setFont(this.FUENTE_GENERAL_TXT);

        this.REGISTRAR_TRANSPORTE   .setFont(this.FUENTE_GENERAL_TXT);

        this.CHOFER_TXT             .setHorizontalAlignment(JTextField.CENTER);
        this.EMPRESA_TXT            .setHorizontalAlignment(JTextField.CENTER);
        this.PLACAS_TXT             .setHorizontalAlignment(JTextField.CENTER);
        this.TRACTO_CAMION_TXT      .setHorizontalAlignment(JTextField.CENTER);

        this.CHOFER_TXT             .addKeyListener( this );
        this.EMPRESA_TXT            .addKeyListener( this );
        this.PLACAS_TXT             .addKeyListener( this );
        this.TRACTO_CAMION_TXT      .addKeyListener( this );

        this.REGISTRAR_TRANSPORTE   .addActionListener( this );

    }

    private void guardarTransporte() {
        if(camposVacios())
        {
            JOptionPane.showMessageDialog(this,
                                                "Falta llenar campos",
                                                "Error",
                                                JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            this.TRANSPORTE.setChofer(this.getCHOFER_TXT().getText());
            this.TRANSPORTE.setEmpresa(this.getEMPRESA_TXT().getText());
            this.TRANSPORTE.setPlacas(this.getPLACAS_TXT().getText());
            this.TRANSPORTE.setTractoCamion(this.getTRACTO_CAMION_TXT().getText());

            int i = this.TRANSPORTE.insertar();

            if(i > 0) {
                vaciarTextos();
                JOptionPane.showMessageDialog(this,
                        "Transporte registrado!",
                        "Registro de Transportes",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // -- GET`S Y SET'S:

    public JButton getREGISTRAR_TRANSPORTE() {
        return REGISTRAR_TRANSPORTE;
    }

    public JLabel getCHOFER_LB() {
        return CHOFER_LB;
    }

    public JLabel getEMPRESA_LB() {
        return EMPRESA_LB;
    }

    public JLabel getPLACAS_LB() {
        return PLACAS_LB;
    }

    public JLabel getTRACTO_CAMION_LB() {
        return TRACTO_CAMION_LB;
    }

    public JTextField getCHOFER_TXT() {
        return CHOFER_TXT;
    }

    public JTextField getEMPRESA_TXT() {
        return EMPRESA_TXT;
    }

    public JTextField getPLACAS_TXT() {
        return PLACAS_TXT;
    }

    public JTextField getTRACTO_CAMION_TXT() {
        return TRACTO_CAMION_TXT;
    }

    // -- METODOS OVERRIDE:

    @Override
    public void vaciarTextos() {

        this.CHOFER_TXT             .setText( "" );
        this.EMPRESA_TXT            .setText( "" );
        this.PLACAS_TXT             .setText( "" );
        this.TRACTO_CAMION_TXT      .setText( "" );

    }

    @Override
    public boolean camposVacios(){

        return  this.CHOFER_TXT.getText().isEmpty() ||
                this.EMPRESA_TXT.getText().isEmpty() ||
                this.PLACAS_TXT.getText().isEmpty() ||
                this.TRACTO_CAMION_TXT.getText().isEmpty();

    }

    @Override
    protected void paintComponent(Graphics g){
        //obtengo rl graphics 2D del panel o lienzo como lo quieras ver:
        Graphics2D g2 = (Graphics2D) g.create();
        Rectangle clip = g2.getClipBounds();

        // saco las coordenadas maximas de la ventana:
        float x=this.getWidth();
        float y=this.getHeight();

        //el GradientPaint es el objeto que dibuja el degradado
        g2.setPaint(new GradientPaint(
                new Point2D.Float(x/2,0), //punto de donde inicia el primer color
                color2, // color 1
                new Point2D.Float(x,y), //punto donde empieza el segundo color
                color1)); // segundo color

        //dibujo en un rectangulo del tamaño de la ventana mi gradiente:
        g2.fillRect(clip.x, clip.y, clip.width, clip.height);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == this.REGISTRAR_TRANSPORTE)
        {
            this.guardarTransporte();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(this.getCHOFER_TXT().isFocusOwner())
        {
            if (!Character.isLetter(e.getKeyChar()) &&
                    !(e.getKeyChar() == KeyEvent.VK_SPACE) &&
                    !(e.getKeyChar() == KeyEvent.VK_BACK_SPACE))
            {
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            this.getREGISTRAR_TRANSPORTE().doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
