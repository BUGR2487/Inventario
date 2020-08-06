package start;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.itextpdf.text.Element.ALIGN_CENTER;

public class Main
{
    public static void main(String[] args)
    {

        //Forms.FrmSplashScreen frmSplashScreen = new Forms.FrmSplashScreen();
        //frmSplashScreen.show();
    }

    public static void crearPDF()
    {
        Document document = new Document();
        document.setPageSize(PageSize.A4.rotate());

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

            PdfPTable TblFilaUno = new PdfPTable(5);
            TblFilaUno.getDefaultCell().setBorder(0);

            PdfPTable TblNoSalida = new PdfPTable(1);
            //TblNoSalida.setWidthPercentage(1f);
            PdfPCell ClTituloNoSalida = new PdfPCell(new Paragraph("No.Salida", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClTituloNoSalida.setHorizontalAlignment(ALIGN_CENTER);
            TblNoSalida.addCell(ClTituloNoSalida);
            PdfPCell ClNoSalida = new PdfPCell(new Paragraph("81", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClNoSalida.setHorizontalAlignment(ALIGN_CENTER);
            TblNoSalida.addCell(ClNoSalida);
            //document.add(TblNoSalida);

            PdfPCell ClTblNoSalida = new PdfPCell(TblNoSalida);

            PdfPTable ColumnaBlanca = new PdfPTable(1);

            PdfPTable TblDatosUsuario = new PdfPTable(3);
            TblDatosUsuario.setWidthPercentage(100f);
            Paragraph FechaEnvio = new Paragraph("Fecha de Envío", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            FechaEnvio.setAlignment(ALIGN_CENTER);
            TblDatosUsuario.addCell(FechaEnvio);
            Paragraph Hora = new Paragraph("Hora", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            Hora.setAlignment(Element.ALIGN_CENTER);
            TblDatosUsuario.addCell(Hora);
            Paragraph Almacenista = new Paragraph("Almacenista", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            Almacenista.setAlignment(ALIGN_CENTER);
            TblDatosUsuario.addCell(Almacenista);
            PdfPCell ClHora = new PdfPCell(new Paragraph("10:15:26 p.m.", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClHora.setHorizontalAlignment(ALIGN_CENTER);
            TblDatosUsuario.addCell(ClHora);
            PdfPCell ClFechaEnvio = new PdfPCell(new Paragraph("06/06/2020", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClFechaEnvio.setHorizontalAlignment(ALIGN_CENTER);
            TblDatosUsuario.addCell(ClFechaEnvio);
            PdfPCell ClAlmacenista = new PdfPCell(new Paragraph("ALAN R.", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClAlmacenista.setHorizontalAlignment(ALIGN_CENTER);
            TblDatosUsuario.addCell(ClAlmacenista);
            //document.add(TblDatosUsuario);

            PdfPCell ClTblDatosUsuario = new PdfPCell(TblDatosUsuario);

            TblFilaUno.addCell(ClTblNoSalida);
            TblFilaUno.addCell(ColumnaBlanca);
            TblFilaUno.addCell(ColumnaBlanca);
            TblFilaUno.addCell(ColumnaBlanca);
            TblFilaUno.addCell(ClTblDatosUsuario);
            document.add(TblFilaUno);

            /*
            PdfPTable TblLugarOrigen = new PdfPTable(2);
            TblLugarOrigen.setWidthPercentage(20f);
            TblLugarOrigen.setHorizontalAlignment(Element.ALIGN_LEFT);
            Paragraph LugarOrigen = new Paragraph("Lugar de origen:", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            LugarOrigen.setAlignment(Element.ALIGN_CENTER);
            TblLugarOrigen.addCell(LugarOrigen);
            Paragraph DatosLugarOrigen = new Paragraph("TAPATRANSPORTES S.A. DE C.V. S/C Almacén.   PROL. LOPEZ \n MATEOS SUR #1800 PRIV. NO.23 SANTACRUZ DE LAS FLORES, TLAJOMULCO DE ZÚÑIGA, JAL. MEX.", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            DatosLugarOrigen.setAlignment(Element.ALIGN_CENTER);
            TblLugarOrigen.addCell(DatosLugarOrigen);
            document.add(TblLugarOrigen);

            PdfPTable TblClienteExpendidor = new PdfPTable(2);
            TblClienteExpendidor.setWidthPercentage(20f);
            TblClienteExpendidor.setHorizontalAlignment(Element.ALIGN_RIGHT);
            Paragraph ClienteExpedidor = new Paragraph("Cliente Expedidor:", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            ClienteExpedidor.setAlignment(Element.ALIGN_CENTER);
            TblClienteExpendidor.addCell(ClienteExpedidor);
            Paragraph DatosClienteExpedidor = new Paragraph("INTERNATIONAL PAPER MÉXICO COMPANY, S DE RL DE CV.", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            DatosClienteExpedidor.setAlignment(Element.ALIGN_CENTER);
            TblClienteExpendidor.addCell(DatosClienteExpedidor);
            Paragraph Direccion = new Paragraph("Dirección:", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            Direccion.setAlignment(Element.ALIGN_CENTER);
            TblClienteExpendidor.addCell(Direccion);
            Paragraph DatosDireccion = new Paragraph("AV. BICENTENARIO 372 COL. S/C, LOC. LA CANTERA, SAN JOSE ITURBIDE, GTO, MEX. C.P. 37988", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            DatosDireccion.setAlignment(Element.ALIGN_CENTER);
            TblClienteExpendidor.addCell(DatosDireccion);
            document.add(TblClienteExpendidor);

            PdfPTable TblEntregar = new PdfPTable(2);
            TblEntregar.setWidthPercentage(20f);
            TblEntregar.setHorizontalAlignment(Element.ALIGN_LEFT);
            Paragraph Entregar = new Paragraph("Entregar a:", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            Entregar.setAlignment(Element.ALIGN_CENTER);
            TblEntregar.addCell(Entregar);
            Paragraph DatosEntregar = new Paragraph("NATURESWEET INVERNADEROS, S DE RL DE CV; AV. PABLO NERUDA", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            DatosEntregar.setAlignment(Element.ALIGN_CENTER);
            TblEntregar.addCell(DatosEntregar);
            document.add(TblEntregar);

            PdfPTable TblIntruccionesEmbarque = new PdfPTable(3);
            TblIntruccionesEmbarque.setWidthPercentage(20f);
            TblIntruccionesEmbarque.setHorizontalAlignment(Element.ALIGN_RIGHT);
            Paragraph InstruccionesEmbarque = new Paragraph("Instrucciones de embarque:", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            InstruccionesEmbarque.setAlignment(Element.ALIGN_CENTER);
            TblIntruccionesEmbarque.addCell(InstruccionesEmbarque);
            Paragraph EntregaDia = new Paragraph("ENTREGA EL DIA:", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            EntregaDia.setAlignment(Element.ALIGN_CENTER);
            TblIntruccionesEmbarque.addCell(EntregaDia);
            Paragraph DatosEntregaDia = new Paragraph("08/06/2020", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            DatosEntregaDia.setAlignment(Element.ALIGN_CENTER);
            TblIntruccionesEmbarque.addCell(DatosEntregaDia);
            document.add(TblIntruccionesEmbarque);

            PdfPTable TblPrincipal = new PdfPTable(8);
            TblPrincipal.setWidthPercentage(50f);
            TblPrincipal.setHorizontalAlignment(ALIGN_CENTER);
            Paragraph NoOrden = new Paragraph("No. Orden", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            NoOrden.setAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(NoOrden);
            Paragraph NoSello = new Paragraph("No. De Sello", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            NoSello.setAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(NoSello);
            Paragraph NoPedido = new Paragraph("No. De Guia", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            NoPedido.setAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(NoPedido);
            Paragraph Placas = new Paragraph("Placas", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            Placas.setAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(Placas);
            Paragraph ViaEmbarque = new Paragraph("Vía de Embarque", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            ViaEmbarque.setAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(ViaEmbarque);
            Paragraph Chofer = new Paragraph("Chofer", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            Chofer.setAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(Chofer);
            Paragraph Empresa = new Paragraph("Empresa", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            Empresa.setAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(Empresa);
            Paragraph Observaciones = new Paragraph("Observaciones", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            Observaciones.setAlignment(Element.ALIGN_CENTER);
            TblPrincipal.addCell(Observaciones);
            PdfPCell ClNoOrden = new PdfPCell(new Paragraph("81", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClHora.setHorizontalAlignment(ALIGN_CENTER);
            TblPrincipal.addCell(ClNoOrden);
            PdfPCell ClNoSello = new PdfPCell(new Paragraph("3168947", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClFechaEnvio.setHorizontalAlignment(ALIGN_CENTER);
            TblPrincipal.addCell(ClNoSello);
            PdfPCell ClNoGuia = new PdfPCell(new Paragraph("1-7136A", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClAlmacenista.setHorizontalAlignment(ALIGN_CENTER);
            TblPrincipal.addCell(ClNoGuia);
            PdfPCell ClPlacas = new PdfPCell(new Paragraph(" ", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClHora.setHorizontalAlignment(ALIGN_CENTER);
            TblPrincipal.addCell(ClPlacas);
            PdfPCell ClViaEmbarque = new PdfPCell(new Paragraph(" ", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClFechaEnvio.setHorizontalAlignment(ALIGN_CENTER);
            TblPrincipal.addCell(ClViaEmbarque);
            PdfPCell ClChofer = new PdfPCell(new Paragraph("PABLO CISNEROS", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClAlmacenista.setHorizontalAlignment(ALIGN_CENTER);
            TblPrincipal.addCell(ClChofer);
            PdfPCell ClEmpresa = new PdfPCell(new Paragraph("CALDERON", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClHora.setHorizontalAlignment(ALIGN_CENTER);
            TblPrincipal.addCell(ClEmpresa);
            PdfPCell ClObservaciones = new PdfPCell(new Paragraph("N/A", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClFechaEnvio.setHorizontalAlignment(ALIGN_CENTER);
            TblPrincipal.addCell(ClObservaciones);
            document.add(TblPrincipal);

            PdfPTable TblPrincipal2 = new PdfPTable(7);
            TblPrincipal2.setWidthPercentage(50f);
            TblPrincipal2.setHorizontalAlignment(ALIGN_CENTER);
            Paragraph SKU = new Paragraph("SKU", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            SKU.setAlignment(Element.ALIGN_CENTER);
            TblPrincipal2.addCell(SKU);
            Paragraph Diseno = new Paragraph("Diseño", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            Diseno.setAlignment(Element.ALIGN_CENTER);
            TblPrincipal2.addCell(Diseno);
            Paragraph Descripcion = new Paragraph("Descripción", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            Descripcion.setAlignment(Element.ALIGN_CENTER);
            TblPrincipal2.addCell(Descripcion);
            Paragraph Total = new Paragraph("Total", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            Total.setAlignment(Element.ALIGN_CENTER);
            TblPrincipal2.addCell(Total);
            Paragraph CantidadPallets = new Paragraph("Cantidad Pallets", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            CantidadPallets.setAlignment(Element.ALIGN_CENTER);
            TblPrincipal2.addCell(CantidadPallets);
            Paragraph PiezasPallet = new Paragraph("Piezas por Pallet", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            PiezasPallet.setAlignment(Element.ALIGN_CENTER);
            TblPrincipal2.addCell(PiezasPallet);
            Paragraph TotalUnidades = new Paragraph("Total de Unidades", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            TotalUnidades.setAlignment(Element.ALIGN_CENTER);
            TblPrincipal2.addCell(TotalUnidades);
            PdfPCell ClSKU = new PdfPCell(new Paragraph("EM0020394", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClSKU.setHorizontalAlignment(ALIGN_CENTER);
            TblPrincipal2.addCell(ClSKU);
            PdfPCell ClDiseno = new PdfPCell(new Paragraph("3070050", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClDiseno.setHorizontalAlignment(ALIGN_CENTER);
            TblPrincipal2.addCell(ClDiseno);
            PdfPCell ClDescripcion = new PdfPCell(new Paragraph("Caja Universal 10 OZ AUTO 3070050", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClDescripcion.setHorizontalAlignment(ALIGN_CENTER);
            TblPrincipal2.addCell(ClDescripcion);
            PdfPCell ClTotal = new PdfPCell(new Paragraph("Total", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClTotal.setHorizontalAlignment(ALIGN_CENTER);
            TblPrincipal2.addCell(ClTotal);
            PdfPCell ClCantidadPallets = new PdfPCell(new Paragraph("11", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClCantidadPallets.setHorizontalAlignment(ALIGN_CENTER);
            TblPrincipal2.addCell(ClCantidadPallets);
            PdfPCell ClPiezasPallet = new PdfPCell(new Paragraph("500", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClPiezasPallet.setHorizontalAlignment(ALIGN_CENTER);
            TblPrincipal2.addCell(ClPiezasPallet);
            PdfPCell ClTotalUnidades = new PdfPCell(new Paragraph("5,500", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClTotalUnidades.setHorizontalAlignment(ALIGN_CENTER);
            TblPrincipal2.addCell(ClTotalUnidades);
            document.add(TblPrincipal2);

            PdfPTable TblTexto = new PdfPTable(1);
            TblTexto.setWidthPercentage(100f);
            TblTexto.setHorizontalAlignment(ALIGN_CENTER);
            Paragraph Texto = new Paragraph("SALVO QUE SE TRATE DE CARGA CONSOLIDADA, LA RESPONSABILIDAD DE TAPATRANSPORTES, S.A. DE C.V. POR PÉRDIDA, DEMORA O CUALQUIERA OTRA CAUSA SE LIMITARA AL EMBARQUE DE LAS MERCANCÍAS A NOMBRE DEL CLIENTE EXPEDIDOR, EN LOS TÉRMINOS Y CONDICIONES INDICADOS EN LA PRESENTE ORDEN DE SALIDA. EL CLIENTE EXPEDIDOR PROPORCIONARÁ AL TRANSPORTISTA LA INFORMACIÓN ADICIONAL QUE PUDIERA REQUERIR PARA DETERMINAR LAS RUTAS, EL MODO Y MEDIO DE TRANSPORTE DE LA MERCANÍA Y ASUMIRÁ TODA LA RESPONSABILIDAD POR LA IDONEIDAD DE SUS INSTRUCCIONES Y/O DE LA ELECCIÓN QUE HAGA DE DICHAS RUTAS, MODOS Y MEDIOS. ASIMISMO, SALVO INDICACIONES ESPECIALES DEL CLEINTE EXSPEDIDOR, EL PESO ORDENADO PARA EL EMBARQUE DEBERÁ CUMPLIR CON LA NORMATIVIDAD APLICABLE Y EL VALOR DE LAS MERCANCIAS, NO SUPERARÁ LOS $150,000.00.", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            Texto.setAlignment(Element.ALIGN_CENTER);
            TblTexto.addCell(Texto);
            document.add(TblTexto);

            PdfPTable TblFirmaTransportista = new PdfPTable(1);
            TblFirmaTransportista.setWidthPercentage(50f);
            TblFirmaTransportista.setHorizontalAlignment(ALIGN_LEFT);
            Paragraph ZonaFirmaTransportista = new Paragraph("____________________________________________________", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            ZonaFirmaTransportista.setAlignment(Element.ALIGN_CENTER);
            TblFirmaTransportista.addCell(ZonaFirmaTransportista);
            PdfPCell TextoTransportista = new PdfPCell(new Paragraph("Nombre y Forma Transportista.", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            TextoTransportista.setHorizontalAlignment(ALIGN_CENTER);
            TblFirmaTransportista.addCell(TextoTransportista);
            document.add(TblFirmaTransportista);

            PdfPTable TblFirmaRemitente = new PdfPTable(1);
            TblFirmaRemitente.setWidthPercentage(50f);
            TblFirmaRemitente.setHorizontalAlignment(ALIGN_RIGHT);
            Paragraph ZonaFirmaRemitente = new Paragraph("____________________________________________________", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            ZonaFirmaRemitente.setAlignment(Element.ALIGN_CENTER);
            TblFirmaRemitente.addCell(ZonaFirmaRemitente);
            PdfPCell TextoRemitente = new PdfPCell(new Paragraph("Nombre y Forma Remitente.", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            TextoRemitente.setHorizontalAlignment(ALIGN_CENTER);
            TblFirmaRemitente.addCell(TextoRemitente);
            document.add(TblFirmaRemitente);

            PdfPTable TblEntregarA = new PdfPTable(1);
            TblEntregarA.setWidthPercentage(100f);
            TblEntregarA.setHorizontalAlignment(ALIGN_LEFT);
            Paragraph TextoEntrega = new Paragraph("Entregar a: NATURESWEET INVERNADERO", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            TextoEntrega.setAlignment(Element.ALIGN_CENTER);
            TblEntregarA.addCell(TextoEntrega);
            document.add(TblEntregarA);

            PdfPTable TblInformacionEntrega = new PdfPTable(2);
            TblInformacionEntrega.setWidthPercentage(20f);
            TblInformacionEntrega.setHorizontalAlignment(Element.ALIGN_RIGHT);
            Paragraph FechaEntrega = new Paragraph("FECHA", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            FechaEntrega.setAlignment(Element.ALIGN_CENTER);
            TblInformacionEntrega.addCell(FechaEntrega);
            Paragraph DatosFechaEntrega = new Paragraph("06/06/2020", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            DatosFechaEntrega.setAlignment(Element.ALIGN_CENTER);
            TblInformacionEntrega.addCell(DatosFechaEntrega);
            Paragraph HoraEntrega = new Paragraph("HORA", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            HoraEntrega.setAlignment(Element.ALIGN_CENTER);
            TblInformacionEntrega.addCell(HoraEntrega);
            Paragraph DatosHoraEntrega = new Paragraph("10:16:53 p.m.", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            DatosHoraEntrega.setAlignment(Element.ALIGN_CENTER);
            TblInformacionEntrega.addCell(DatosHoraEntrega);
            document.add(TblInformacionEntrega);

            PdfPTable TblSalidas = new PdfPTable(2);
            TblSalidas.setWidthPercentage(50f);
            TblSalidas.setHorizontalAlignment(ALIGN_CENTER);
            Paragraph NoPLT = new Paragraph("No. PLT'S", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            NoPLT.setAlignment(Element.ALIGN_CENTER);
            TblSalidas.addCell(NoPLT);
            Paragraph Folio = new Paragraph("FOLIO", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK));
            Folio.setAlignment(Element.ALIGN_CENTER);
            TblSalidas.addCell(Folio);
            PdfPCell ClNoPLT = new PdfPCell(new Paragraph("1", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClNoPLT.setHorizontalAlignment(ALIGN_CENTER);
            TblSalidas.addCell(ClNoPLT);
            PdfPCell ClFolio = new PdfPCell(new Paragraph("02886335", FontFactory.getFont("Arial", 6, Font.PLAIN, BaseColor.BLACK)));
            ClFolio.setHorizontalAlignment(ALIGN_CENTER);
            TblSalidas.addCell(ClFolio);
            document.add(TblSalidas);
            */
            document.close();
        }
        catch (DocumentException | IOException e)
        {
            e.printStackTrace();
        }
    }
}
