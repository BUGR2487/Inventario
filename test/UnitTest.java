import start.Main;

public class UnitTest {

    //@Test
    public void runTest()
    {

        Forms.FrmSplashScreen frmSplashScreen = new Forms.FrmSplashScreen();
        frmSplashScreen.show();


        /*
        Forms.FrmPrincipal frmPrincipal = new Forms.FrmPrincipal();
        frmPrincipal.show();
         */

        /*
        Forms.FrmIniciarSesion frmIniciarSesion = new Forms.FrmIniciarSesion();
        frmIniciarSesion.show();
        */

        /*
        Forms.FrmUsuario frmUsuario = new Forms.FrmUsuario();
        frmUsuario.show();
        */

        /*
        Forms.FrmEntradas frmEntradas = new Forms.FrmEntradas();
        frmEntradas.show();
         */

        /*
        Forms.FrmSalidas frmSalidas = new Forms.FrmSalidas();
        frmSalidas.show();
         */

        /*
        Forms.FrmInventario frmInventario = new Forms.FrmInventario();
        frmInventario.show();
         */

        /*
        Forms.FrmTransporte frmTransporte = new Forms.FrmTransporte();
        frmTransporte.show();
        */
    }

   // @Test
    public void pdfTest()
    {
        Main.crearPDF();
    }

}
