package tn.conge.core.pdf;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import tn.conge.domain.entitites.User;

public class PDFGenerator {

    // List to hold all Students
    private List<User> users;

    public void generate(HttpServletResponse response) throws DocumentException, IOException {

        // Creating the Object of Document
        Document document = new Document(PageSize.A4);

        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());

        // Opening the created document to modify it
        document.open();

        // Creating font
        // Setting font style and size
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);

        // Creating paragraph
        Paragraph paragraph = new Paragraph("List of users", fontTiltle);

        // Aligning the paragraph in document
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph.setSpacingAfter(10);
        // Adding the created paragraph in document
        document.add(paragraph);

        PdfPTable table = new PdfPTable(3);

        table.setWidthPercentage(100f);
        table.setWidths(new int[] { 3, 3, 3 });
        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(CMYKColor.WHITE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.BLACK);

        cell.setPhrase(new Phrase("Id", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Role", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Phone", font));
        table.addCell(cell);

        for (User user : users) {
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getRole().toString());
            table.addCell(user.getPhone());
        }
        if ( users.isEmpty() ) {
            table.addCell("1");
            table.addCell("Manager");
            table.addCell("+33075420320");
        }
        document.add(table);

        document.close();

    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
