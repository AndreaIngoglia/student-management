package generatorevoti.services;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import generatorevoti.database.entities.Valutation;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class StatsPdfGenerator {
    public void generate(List<Valutation> valutations, String subject, String name, String surname, String academicYear, HttpServletResponse response) throws DocumentException, IOException {
        // Creating the Object of Document
        Document document = new Document(PageSize.A4);
        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());
        // Opening the created document to change it
        document.open();
        // Creating paragraph
        document.add(new Paragraph(subject + " " + name + " " + surname + " " + academicYear));
        // Creating a table of 4 columns
        PdfPTable table = new PdfPTable(2);
        // Setting width of the table, its columns and spacing
        table.setSpacingBefore(20);
        table.setWidthPercentage(100f);
        PdfPCell cell = new PdfPCell();
        // Setting the padding of the table cell
        cell.setPadding(10);
        // Adding Cell to table
        cell.setPhrase(new Phrase("Data"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Voto"));
        table.addCell(cell);
        // Iterating the list of valutations
        for (Valutation valutation: valutations) {
            // Adding date
            table.addCell(String.valueOf(valutation.getValutationId().getDate()));
            // Adding mark
            table.addCell(valutation.getMark());
        }
        // Adding the created table to the document
        document.add(table);
        // Closing the document
        document.close();
    }
}