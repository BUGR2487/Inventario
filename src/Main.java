import com.itextpdf.text.*;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import static com.itextpdf.text.Element.*;
import static com.itextpdf.text.PageSize.A4;

public class Main
{
    public static void main(String[] args)
    {
        crearPDF();
        /*
        FrmSplashScreen frmSplashScreen = new FrmSplashScreen();
        frmSplashScreen.show();
         */

        /*
        FrmPrincipal frmPrincipal = new FrmPrincipal();
        frmPrincipal.show();
         */

        /*
        FrmIniciarSesion frmIniciarSesion = new FrmIniciarSesion();
        frmIniciarSesion.show();
        */

        /*
        FrmUsuario frmUsuario = new FrmUsuario();
        frmUsuario.show();
        */

        /*
        FrmEntradas frmEntradas = new FrmEntradas();
        frmEntradas.show();
         */

        /*
        FrmSalidas frmSalidas = new FrmSalidas();
        frmSalidas.show();
         */

        /*
        FrmInventario frmInventario = new FrmInventario();
        frmInventario.show();
         */

        /*
        FrmTransporte frmTransporte = new FrmTransporte();
        frmTransporte.show();
        */
    }

    private static void crearPDF()
    {
        Document document = new Document();
        document.setPageSize(A4.rotate());

        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("Salida.pdf");
            PdfWriter.getInstance(document, fileOutputStream);
            document.open();

            Paragraph EncabezadoDerecha = new Paragraph("TAPATRANSPORTES S.A. DE C.V. \n " +
                                                              "RFC: TAP100616185 \n" +
                                                              "CALLE 15 NUM 11 \n" +
                                                              "JOSE CLEMENTE OROZCO, \n" +
                                                              "C.P. 44970.GUADALAJARA, JALISCO, MÉXICO",
                                                              FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            EncabezadoDerecha.setAlignment(Element.ALIGN_RIGHT);
            document.add(EncabezadoDerecha);

            Paragraph titulo = new Paragraph("Orden de Salida.", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            titulo.setAlignment(ALIGN_CENTER);
            document.add(titulo);

            Paragraph datosEmpresa = new Paragraph("tpr@tapatransportes.com | Tel: 33 - 2001 - 0553", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            datosEmpresa.setAlignment(Element.ALIGN_RIGHT);
            document.add(datosEmpresa);

            document.add(Chunk.NEWLINE);

            /* Inicio primera fila de tablas */
            PdfPTable TblNoSalida = new PdfPTable(5);
            float[] anchoCeldas = {30f, 385f, 60f, 60f, 60f};
            TblNoSalida.setWidthPercentage(anchoCeldas, A4);

            PdfPCell NoSalida = new PdfPCell(new Paragraph("No.Salida", FontFactory.getFont("Arial", 6, Font.BOLD, BaseColor.BLACK)));
            NoSalida.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblNoSalida.addCell(NoSalida);

            PdfPCell ColumnaBlanco = new PdfPCell(new Paragraph(""));
            ColumnaBlanco.setRowspan(2);
            ColumnaBlanco.setBorder(Rectangle.NO_BORDER);
            ColumnaBlanco.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblNoSalida.addCell(ColumnaBlanco);

            PdfPCell FechaEnvio = new PdfPCell(new Phrase("Fecha de Envío", FontFactory.getFont("Arial", 6, Font.BOLD, BaseColor.BLACK)));
            FechaEnvio.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblNoSalida.addCell(FechaEnvio);

            PdfPCell Hora = new PdfPCell(new Phrase("Hora", FontFactory.getFont("Arial", 6, Font.BOLD, BaseColor.BLACK)));
            Hora.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblNoSalida.addCell(Hora);

            PdfPCell Almacenista = new PdfPCell(new Phrase("Almacenista", FontFactory.getFont("Arial", 6, Font.BOLD, BaseColor.BLACK)));
            Almacenista.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblNoSalida.addCell(Almacenista);

            PdfPCell ClNoSalida = new PdfPCell(new Paragraph("81", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClNoSalida.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblNoSalida.addCell(ClNoSalida);

            PdfPCell ClHora = new PdfPCell(new Paragraph("10:15:26 p.m.", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClHora.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblNoSalida.addCell(ClHora);

            PdfPCell ClFechaEnvio = new PdfPCell(new Paragraph("06/06/2020", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClFechaEnvio.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblNoSalida.addCell(ClFechaEnvio);

            PdfPCell ClAlmacenista = new PdfPCell(new Paragraph("ALAN R.", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClAlmacenista.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblNoSalida.addCell(ClAlmacenista);

            document.add(TblNoSalida);
            /* Fin primera fila de tablas */

            document.add(Chunk.NEWLINE);

            /* Inicio segunda fila de tablas */
            PdfPTable TblLugarOrigen = new PdfPTable(5);
            float[] anchoCeldas2 = {60f, 220f, 10f, 60f, 220f};
            TblLugarOrigen.setWidthPercentage(anchoCeldas2, A4);

            PdfPCell LugarOrigen = new PdfPCell(new Phrase("Lugar de origen:", FontFactory.getFont("Arial", 6, Font.BOLD, BaseColor.BLACK)));
            LugarOrigen.setRowspan(2);
            LugarOrigen.setHorizontalAlignment(Element.ALIGN_CENTER);
            LugarOrigen.setVerticalAlignment(Element.ALIGN_MIDDLE);
            LugarOrigen.setFixedHeight(50f);
            TblLugarOrigen.addCell(LugarOrigen);

            PdfPCell DatosLugarOrigen = new PdfPCell(new Phrase("TAPATRANSPORTES S.A. DE C.V. S/C Almacén.   PROL. LOPEZ \n\n MATEOS SUR #1800 PRIV. NO.23 SANTACRUZ DE LAS FLORES, TLAJOMULCO DE ZÚÑIGA, JAL. MEX.", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            DatosLugarOrigen.setRowspan(2);
            DatosLugarOrigen.setHorizontalAlignment(Element.ALIGN_CENTER);
            DatosLugarOrigen.setVerticalAlignment(Element.ALIGN_MIDDLE);
            TblLugarOrigen.addCell(DatosLugarOrigen);

            PdfPCell ColumnaBlanco2 = new PdfPCell(new Paragraph(""));
            ColumnaBlanco2.setRowspan(2);
            ColumnaBlanco2.setBorder(Rectangle.NO_BORDER);
            ColumnaBlanco2.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblLugarOrigen.addCell(ColumnaBlanco2);

            PdfPCell ClienteExpedidor = new PdfPCell(new Phrase("Cliente Expedidor:", FontFactory.getFont("Arial", 6, Font.BOLD, BaseColor.BLACK)));
            ClienteExpedidor.setHorizontalAlignment(Element.ALIGN_CENTER);
            ClienteExpedidor.setVerticalAlignment(Element.ALIGN_MIDDLE);
            ClienteExpedidor.setFixedHeight(25f);
            TblLugarOrigen.addCell(ClienteExpedidor);

            PdfPCell DatosClienteExpedidor = new PdfPCell(new Phrase("INTERNATIONAL PAPER MÉXICO COMPANY, S DE RL DE CV.", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            DatosClienteExpedidor.setHorizontalAlignment(Element.ALIGN_CENTER);
            DatosClienteExpedidor.setVerticalAlignment(Element.ALIGN_MIDDLE);
            TblLugarOrigen.addCell(DatosClienteExpedidor);

            PdfPCell Direccion = new PdfPCell(new Phrase("Dirección:", FontFactory.getFont("Arial", 6, Font.BOLD, BaseColor.BLACK)));
            Direccion.setHorizontalAlignment(Element.ALIGN_CENTER);
            Direccion.setVerticalAlignment(Element.ALIGN_MIDDLE);
            TblLugarOrigen.addCell(Direccion);

            PdfPCell DatosDireccion = new PdfPCell(new Phrase("AV. BICENTENARIO 372 COL. S/C, LOC. LA CANTERA, SAN JOSE ITURBIDE, GTO, MEX. C.P. 37988", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            DatosDireccion.setHorizontalAlignment(Element.ALIGN_CENTER);
            DatosDireccion.setVerticalAlignment(Element.ALIGN_MIDDLE);
            TblLugarOrigen.addCell(DatosDireccion);

            document.add(TblLugarOrigen);
            /* Fin segunda fila de tablas */

            document.add(Chunk.NEWLINE);

            /* Inicio tercera fila de tablas */
            PdfPTable TblEntregar = new PdfPTable(7);
            float[] anchoCeldas3 = {60f, 220f, 10f, 60f, 100f, 60f, 60f};
            TblEntregar.setWidthPercentage(anchoCeldas3, A4);

            PdfPCell Entregar = new PdfPCell(new Phrase("Entregar a:", FontFactory.getFont("Arial", 6, Font.BOLD, BaseColor.BLACK)));
            Entregar.setHorizontalAlignment(Element.ALIGN_CENTER);
            Entregar.setVerticalAlignment(Element.ALIGN_MIDDLE);
            TblEntregar.addCell(Entregar);

            PdfPCell DatosEntregar = new PdfPCell(new Phrase("NATURESWEET INVERNADEROS, S DE RL DE CV; AV. PABLO NERUDA", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            DatosEntregar.setHorizontalAlignment(Element.ALIGN_CENTER);
            DatosEntregar.setVerticalAlignment(Element.ALIGN_MIDDLE);
            TblEntregar.addCell(DatosEntregar);

            PdfPCell ColumnaBlanco3 = new PdfPCell(new Paragraph(""));
            ColumnaBlanco3.setBorder(Rectangle.NO_BORDER);
            ColumnaBlanco3.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblEntregar.addCell(ColumnaBlanco3);

            PdfPCell InstruccionesEmbarque = new PdfPCell(new Phrase("Instrucciones de embarque:", FontFactory.getFont("Arial", 6, Font.BOLD, BaseColor.BLACK)));
            InstruccionesEmbarque.setHorizontalAlignment(Element.ALIGN_CENTER);
            InstruccionesEmbarque.setVerticalAlignment(Element.ALIGN_MIDDLE);
            TblEntregar.addCell(InstruccionesEmbarque);

            PdfPCell DatosInstruccionesEmbarque = new PdfPCell(new Phrase("", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            DatosInstruccionesEmbarque.setHorizontalAlignment(Element.ALIGN_CENTER);
            DatosInstruccionesEmbarque.setVerticalAlignment(Element.ALIGN_MIDDLE);
            TblEntregar.addCell(DatosInstruccionesEmbarque);

            PdfPCell EntregaDia = new PdfPCell(new Phrase("ENTREGA EL DIA:", FontFactory.getFont("Arial", 6, Font.BOLD, BaseColor.BLACK)));
            EntregaDia.setHorizontalAlignment(Element.ALIGN_CENTER);
            EntregaDia.setVerticalAlignment(Element.ALIGN_MIDDLE);
            TblEntregar.addCell(EntregaDia);

            PdfPCell DatosEntregaDia = new PdfPCell(new Phrase("08/06/2020", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            DatosEntregaDia.setHorizontalAlignment(Element.ALIGN_CENTER);
            DatosEntregaDia.setVerticalAlignment(Element.ALIGN_MIDDLE);
            TblEntregar.addCell(DatosEntregaDia);

            document.add(TblEntregar);
            /* Fin tercera fila de tablas */

            document.add(Chunk.NEWLINE);

            /* Inicio cuarta fila de tablas */
            PdfPTable TblPrincipal = new PdfPTable(8);
            float[] anchoCeldas4 = {50f, 50f, 50f, 50f, 50f, 50f, 50f, 220f};
            TblPrincipal.setWidthPercentage(anchoCeldas4, A4);

            PdfPCell NoOrden = new PdfPCell(new Phrase("No. Orden", FontFactory.getFont("Arial", 6, Font.BOLD, BaseColor.BLACK)));
            NoOrden.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(NoOrden);

            PdfPCell NoSello = new PdfPCell(new Phrase("No. De Sello", FontFactory.getFont("Arial", 6, Font.BOLD, BaseColor.BLACK)));
            NoSello.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(NoSello);

            PdfPCell NoPedido = new PdfPCell(new Phrase("No. De pedido", FontFactory.getFont("Arial", 6, Font.BOLD, BaseColor.BLACK)));
            NoPedido.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(NoPedido);

            PdfPCell Placas = new PdfPCell(new Phrase("Placas", FontFactory.getFont("Arial", 6, Font.BOLD, BaseColor.BLACK)));
            Placas.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(Placas);

            PdfPCell ViaEmbarque = new PdfPCell(new Phrase("Vía de Embarque", FontFactory.getFont("Arial", 6, Font.BOLD, BaseColor.BLACK)));
            ViaEmbarque.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(ViaEmbarque);

            PdfPCell Chofer = new PdfPCell(new Phrase("Chofer", FontFactory.getFont("Arial", 6, Font.BOLD, BaseColor.BLACK)));
            Chofer.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(Chofer);

            PdfPCell Empresa = new PdfPCell(new Phrase("Empresa", FontFactory.getFont("Arial", 6, Font.BOLD, BaseColor.BLACK)));
            Empresa.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(Empresa);

            PdfPCell Observaciones = new PdfPCell(new Phrase("Observaciones", FontFactory.getFont("Arial", 6, Font.BOLD, BaseColor.BLACK)));
            Observaciones.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(Observaciones);

            PdfPCell ClNoOrden = new PdfPCell(new Phrase("81", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClNoOrden.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(ClNoOrden);

            PdfPCell ClNoSello = new PdfPCell(new Phrase("3168947", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClNoSello.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(ClNoSello);

            PdfPCell ClNoGuia = new PdfPCell(new Phrase("1-7136A", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClNoGuia.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(ClNoGuia);

            PdfPCell ClPlacas = new PdfPCell(new Phrase(" ", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClPlacas.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(ClPlacas);

            PdfPCell ClViaEmbarque = new PdfPCell(new Phrase(" ", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClViaEmbarque.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(ClViaEmbarque);

            PdfPCell ClChofer = new PdfPCell(new Phrase("PABLO CISNEROS", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClChofer.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(ClChofer);

            PdfPCell ClEmpresa = new PdfPCell(new Phrase("CALDERON", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClEmpresa.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(ClEmpresa);

            PdfPCell ClObservaciones = new PdfPCell(new Phrase("N/A", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClObservaciones.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(ClObservaciones);

            document.add(TblPrincipal);
            /* Fin cuarta fila de tablas */

            document.add(Chunk.NEWLINE);

            /* Inicio quinta fila de tablas */
            PdfPTable TblPrincipal2 = new PdfPTable(8);
            float[] anchoCeldas5 = {50f, 50f, 50f, 50f, 50f, 50f, 50f, 220f};
            TblPrincipal2.setWidthPercentage(anchoCeldas5, A4);

            PdfPCell SKU = new PdfPCell(new Phrase("SKU", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            SKU.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal2.addCell(SKU);

            PdfPCell Diseno = new PdfPCell(new Phrase("Diseño", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            Diseno.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal2.addCell(Diseno);

            PdfPCell Descripcion = new PdfPCell(new Phrase("Descripción", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            Descripcion.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal2.addCell(Descripcion);

            PdfPCell ColumnaBlanco4 = new PdfPCell(new Paragraph(""));
            ColumnaBlanco4.setRowspan(2);
            ColumnaBlanco4.setBorder(Rectangle.NO_BORDER);
            ColumnaBlanco4.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal2.addCell(ColumnaBlanco4);

            PdfPCell Total = new PdfPCell(new Phrase(" ", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            Total.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal2.addCell(Total);

            PdfPCell CantidadPallets = new PdfPCell(new Phrase("Cantidad Pallets", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            CantidadPallets.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal2.addCell(CantidadPallets);

            PdfPCell PiezasPallet = new PdfPCell(new Phrase("Piezas por Pallet", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            PiezasPallet.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal2.addCell(PiezasPallet);

            PdfPCell TotalUnidades = new PdfPCell(new Phrase("Total de Unidades", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            TotalUnidades.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal2.addCell(TotalUnidades);

            PdfPCell ClSKU = new PdfPCell(new Phrase("EM0020394", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClSKU.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal2.addCell(ClSKU);

            PdfPCell ClDiseno = new PdfPCell(new Phrase("3070050", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClDiseno.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal2.addCell(ClDiseno);

            PdfPCell ClDescripcion = new PdfPCell(new Phrase("Caja Universal 10 OZ AUTO 3070050", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClDescripcion.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal2.addCell(ClDescripcion);

            PdfPCell ClTotal = new PdfPCell(new Phrase("Total", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal2.addCell(ClTotal);

            PdfPCell ClCantidadPallets = new PdfPCell(new Phrase("11", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClCantidadPallets.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal2.addCell(ClCantidadPallets);

            PdfPCell ClPiezasPallet = new PdfPCell(new Phrase("500", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClPiezasPallet.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal2.addCell(ClPiezasPallet);

            PdfPCell ClTotalUnidades = new PdfPCell(new Phrase("5,500", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClTotalUnidades.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPrincipal2.addCell(ClTotalUnidades);

            document.add(TblPrincipal2);
            /* Fin quinta fila de tablas */

            document.add(Chunk.NEWLINE);

            /* Inicio clausula */
            PdfPTable TblTexto = new PdfPTable(1);
            TblTexto.setWidthPercentage(96f);
            TblTexto.setHorizontalAlignment(ALIGN_CENTER);

            Paragraph Texto = new Paragraph("SALVO QUE SE TRATE DE CARGA CONSOLIDADA, LA RESPONSABILIDAD DE TAPATRANSPORTES, S.A. DE C.V. POR PÉRDIDA, DEMORA O CUALQUIERA OTRA CAUSA SE LIMITARA AL EMBARQUE DE LAS MERCANCÍAS A NOMBRE DEL CLIENTE EXPEDIDOR, EN LOS TÉRMINOS Y CONDICIONES INDICADOS EN LA PRESENTE ORDEN DE SALIDA. EL CLIENTE EXPEDIDOR PROPORCIONARÁ AL TRANSPORTISTA LA INFORMACIÓN ADICIONAL QUE PUDIERA REQUERIR PARA DETERMINAR LAS RUTAS, EL MODO Y MEDIO DE TRANSPORTE DE LA MERCANÍA Y ASUMIRÁ TODA LA RESPONSABILIDAD POR LA IDONEIDAD DE SUS INSTRUCCIONES Y/O DE LA ELECCIÓN QUE HAGA DE DICHAS RUTAS, MODOS Y MEDIOS. ASIMISMO, SALVO INDICACIONES ESPECIALES DEL CLEINTE EXSPEDIDOR, EL PESO ORDENADO PARA EL EMBARQUE DEBERÁ CUMPLIR CON LA NORMATIVIDAD APLICABLE Y EL VALOR DE LAS MERCANCIAS, NO SUPERARÁ LOS $150,000.00.", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            Texto.setAlignment(Element.ALIGN_CENTER);

            TblTexto.addCell(Texto);
            document.add(TblTexto);
            /* Fin clausula */

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            /* Inicio zona de firmas */
            PdfPTable TblZonaFirmas = new PdfPTable(3);
            float[] anchoCeldas6 = {140f, 200f, 140f};
            TblZonaFirmas.setWidthPercentage(anchoCeldas6, A4);

            PdfPCell ZonaFirmaTransportista = new PdfPCell(new Phrase("____________________________________________________", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ZonaFirmaTransportista.setHorizontalAlignment(Element.ALIGN_CENTER);
            ZonaFirmaTransportista.setBorder(Rectangle.NO_BORDER);
            TblZonaFirmas.addCell(ZonaFirmaTransportista);

            PdfPCell ColumnaBlanco5 = new PdfPCell(new Paragraph(""));
            ColumnaBlanco5.setRowspan(2);
            ColumnaBlanco5.setBorder(Rectangle.NO_BORDER);
            ColumnaBlanco5.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblZonaFirmas.addCell(ColumnaBlanco5);

            PdfPCell ZonaFirmaRemitente = new PdfPCell(new Phrase("____________________________________________________", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ZonaFirmaRemitente.setHorizontalAlignment(Element.ALIGN_CENTER);
            ZonaFirmaRemitente.setBorder(Rectangle.NO_BORDER);
            TblZonaFirmas.addCell(ZonaFirmaRemitente);

            PdfPCell TextoTransportista = new PdfPCell(new Phrase("Nombre y Firma Transportista.", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            TextoTransportista.setHorizontalAlignment(Element.ALIGN_CENTER);
            TextoTransportista.setBorder(Rectangle.NO_BORDER);
            TblZonaFirmas.addCell(TextoTransportista);

            PdfPCell TextoRemitente = new PdfPCell(new Phrase("Nombre y Firma Remitente.", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            TextoRemitente.setHorizontalAlignment(Element.ALIGN_CENTER);
            TextoRemitente.setBorder(Rectangle.NO_BORDER);
            TblZonaFirmas.addCell(TextoRemitente);

            document.add(TblZonaFirmas);
            /* Fin zona de firmas */

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            /* Inicio encabezado segunda pagina */
            PdfPTable TblClienteAEntregar = new PdfPTable(6);
            float[] anchoCeldas7 = {150f, 150f, 50f, 50f, 50f, 50f};
            TblClienteAEntregar.setWidthPercentage(anchoCeldas7, A4);

            PdfPCell TextoEntrega = new PdfPCell(new Phrase("Entregar a: NATURESWEET INVERNADERO", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            TextoEntrega.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblClienteAEntregar.addCell(TextoEntrega);

            PdfPCell ColumnaBlanco6 = new PdfPCell(new Paragraph(""));
            ColumnaBlanco6.setBorder(Rectangle.NO_BORDER);
            TblClienteAEntregar.addCell(ColumnaBlanco6);

            PdfPCell FechaEntrega = new PdfPCell(new Phrase("FECHA", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            FechaEntrega.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblClienteAEntregar.addCell(FechaEntrega);

            PdfPCell DatosFechaEntrega = new PdfPCell(new Phrase("06/06/2020", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            DatosFechaEntrega.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblClienteAEntregar.addCell(DatosFechaEntrega);

            PdfPCell HoraEntrega = new PdfPCell(new Phrase("HORA", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            HoraEntrega.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblClienteAEntregar.addCell(HoraEntrega);

            PdfPCell DatosHoraEntrega = new PdfPCell(new Phrase("10:16:53 p.m.", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            DatosHoraEntrega.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblClienteAEntregar.addCell(DatosHoraEntrega);

            document.add(TblClienteAEntregar);
            /* Fin encabezado segunda pagina */

            document.add(Chunk.NEWLINE);

            /* Inicio tabla de pallets */
            Paragraph foliosCargados = new Paragraph("Folios cargados", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            foliosCargados.setAlignment(Element.ALIGN_LEFT);
            document.add(foliosCargados);

            document.add(Chunk.NEWLINE);

            PdfPTable TblPallets = new PdfPTable(2);
            float[] anchoCeldas8 = {50f, 50f};
            TblPallets.setWidthPercentage(anchoCeldas8, A4);

            PdfPCell NoPLT = new PdfPCell(new Phrase("No. PLT'S", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            NoPLT.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPallets.addCell(NoPLT);

            PdfPCell Folio = new PdfPCell(new Phrase("FOLIO", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            Folio.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPallets.addCell(Folio);

            PdfPCell ClNoPLT = new PdfPCell(new Phrase("1", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClNoPLT.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPallets.addCell(ClNoPLT);

            PdfPCell ClFolio = new PdfPCell(new Phrase("02886335", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClFolio.setHorizontalAlignment(Element.ALIGN_CENTER);
            TblPallets.addCell(ClFolio);

            document.add(TblPallets);
            /* Fin tabla de pallets */

            document.close();
        }
        catch (DocumentException | IOException e)
        {
            e.printStackTrace();
        }
    }
}
