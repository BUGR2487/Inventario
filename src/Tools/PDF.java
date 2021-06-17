package Tools;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;

public class PDF {

    //Directorio donde se guardaran los pdfs
    private final String dirFile = "./Data/documents";
    private final String ext = ".pdf";
    private Document doc;
    private PdfWriter wrpdf;
    private String fileName;

    public Font textFont = new Font(Font.FontFamily.HELVETICA, 18);
    public Font titleFont = new Font(Font.FontFamily.HELVETICA, 25, Font.BOLD);
    public Font subtitleFont = new Font(Font.FontFamily.HELVETICA, 22, Font.BOLD);


    public PDF(String fileName) throws FileNotFoundException, DocumentException {

        if(fileName == null || fileName.isEmpty())
        {
            fileName = String.valueOf(Calendar.getInstance().getTimeInMillis());
        }

        this.fileName = fileName;
        StringBuilder sb = new StringBuilder().append(this.dirFile).append(fileName).append(this.ext);

        this.doc = new Document();
        this.wrpdf = PdfWriter.getInstance(this.doc, new FileOutputStream(sb.toString()));

    }

    private void addMetaData(){
        this.doc.open();
        this.doc.addTitle(this.fileName);
        this.doc.close();
    }

    public void closeDocument(){
        if(this.doc != null && this.doc.isOpen())
        {
            this.doc.close();
        }

        if (this.wrpdf != null)
        {
            this.wrpdf.close();
        }
    }

    public void addTitlePage(){

    }






}
