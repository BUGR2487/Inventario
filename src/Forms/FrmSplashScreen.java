package Forms;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class FrmSplashScreen extends JFrame
{
    JLabel LblFondo, LblEtiqueta;

    int ancho = 450, alto = 250;

    public FrmSplashScreen()
    {
        this.setSize(ancho, alto);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.getContentPane().setBackground(Color.WHITE);
        setIconImage(new ImageIcon(getClass().getResource("Imagenes/IconoTprlogistics.png")).getImage());

        ImageIcon imagenFondo = new ImageIcon(this.getClass().getResource("Imagenes/IconoTprlogistics.png"));
        Icon iconoFondo = new ImageIcon(imagenFondo.getImage().getScaledInstance(ancho, alto,  java.awt.Image.SCALE_SMOOTH));

        LblEtiqueta = new JLabel();
        LblEtiqueta.setText("");
        LblEtiqueta.setName("LblEtiqueta");
        LblEtiqueta.setBounds(10, 210, 250, 40);
        LblEtiqueta.setBackground(Color.WHITE);
        LblEtiqueta.setFont(new Font("HELVETICA", Font.PLAIN, 20));
        LblEtiqueta.setVisible(true);
        this.add(LblEtiqueta);

        LblFondo = new JLabel();
        LblFondo.setBounds(0, 0, ancho, alto);
        LblFondo.setBackground(Color.WHITE);
        LblFondo.setIcon(iconoFondo);
        LblFondo.setVisible(true);
        this.add(LblFondo);

        iniciarHilo();
    }

    private void iniciarHilo()
    {
        Thread thread = new Thread(new Runnable()
        {
            int x = 0, tiempo = 10;
            @Override
            public void run()
            {
                while(x < 100)
                {
                    try
                    {
                        Thread.sleep(tiempo);
                        LblEtiqueta.setText("Analizando componentes...");
                        x += 20;
                        Thread.sleep(tiempo);
                        LblEtiqueta.setText("Iniciando componentes...");
                        x += 20;
                        Thread.sleep(tiempo);
                        LblEtiqueta.setText("Cardando controladores...");
                        x += 20;
                        Thread.sleep(tiempo);
                        LblEtiqueta.setText("Cargando...");
                        x += 20;
                        Thread.sleep(tiempo);
                        LblEtiqueta.setText("Conectando al servidor...");
                        x += 20;
                        Thread.sleep(tiempo);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                dispose();
                FrmIniciarSesion frmIniciarSesion = null;
                try {
                    frmIniciarSesion = new FrmIniciarSesion();
                } catch (SQLException throwables) {
                    System.exit(1);
                }
                frmIniciarSesion.show();
            }
        });
        thread.start();
    }
}
