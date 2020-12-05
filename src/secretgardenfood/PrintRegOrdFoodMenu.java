package secretgardenfood;

import com.itextpdf.text.BaseColor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * Print Regular Reservation - Order Food in Advance Class
 *
 * @author Japhet Mert Catilo Obsioma
 * @version 3.00, 10/28/2020
 */
public class PrintRegOrdFoodMenu {

    public static void print() throws IOException {
        Document document = new Document();
        try {

            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("receipts/RegReservationFoodOrder/"
                            + java.time.LocalDate.now() + "-"
                            + RegCusReservation.getNewReceipt().getReceiptNo() + ".pdf"));
            document.open();

            // Tables Setup
            PdfPTable tblHeading = new PdfPTable(1);
            tblHeading.setWidthPercentage(100);
            tblHeading.setSpacingBefore(10f);
            tblHeading.setSpacingAfter(10f);

            float[] columnWidthsHeading = {3f};
            tblHeading.setWidths(columnWidthsHeading);

            PdfPTable tblInfo = new PdfPTable(2);
            tblInfo.setWidthPercentage(100);
            tblInfo.setSpacingBefore(10f);
            tblInfo.setSpacingAfter(10f);

            float[] columnWidthsInfo = {2f, 1f};
            tblInfo.setWidths(columnWidthsInfo);

            PdfPTable tblCustomer = new PdfPTable(2);
            tblCustomer.setWidthPercentage(100);
            tblCustomer.setSpacingBefore(10f);
            tblCustomer.setSpacingAfter(10f);

            float[] columnWidthsCustomer = {2f, 1f};
            tblCustomer.setWidths(columnWidthsCustomer);

            PdfPTable tblItems = new PdfPTable(3);
            tblItems.setWidthPercentage(100);
            tblItems.setSpacingBefore(10f);
            tblItems.setSpacingAfter(10f);

            float[] columnWidthsItems = {1f, 1f, 1f};
            tblItems.setWidths(columnWidthsItems);

            PdfPTable tblTotal = new PdfPTable(2);
            tblTotal.setWidthPercentage(100);
            tblTotal.setSpacingBefore(10f);
            tblTotal.setSpacingAfter(10f);

            float[] columnWidthsTotal = {2f, 1f};
            tblTotal.setWidths(columnWidthsTotal);

            PdfPTable tblReservation = new PdfPTable(1);
            tblReservation.setWidthPercentage(100);
            tblReservation.setSpacingBefore(10f);
            tblReservation.setSpacingAfter(10f);

            float[] columnWidthsReservation = {3f};
            tblReservation.setWidths(columnWidthsReservation);

            // Cells
            PdfPCell cellRestaurant = new PdfPCell(new Paragraph("Secret Garden Food\n"
                    + "No. 2, Jalan Universiti, Bandar Sunway, 47500, Selangor"));
            cellRestaurant.setBorderColor(BaseColor.DARK_GRAY);
            cellRestaurant.setPaddingLeft(10);
            cellRestaurant.setPaddingTop(10);
            cellRestaurant.setPaddingBottom(10);
            cellRestaurant.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellRestaurant.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cellSpacer = new PdfPCell(new Paragraph(""));
            cellSpacer.setBorderColor(BaseColor.WHITE);

            PdfPCell cellReceipt = new PdfPCell(new Paragraph("Receipt No: "
                    + RegCusReservation.getNewReceipt().getReceiptNo()));
            cellReceipt.setBorderColor(BaseColor.DARK_GRAY);
            cellReceipt.setPaddingLeft(10);
            cellReceipt.setPaddingTop(10);
            cellReceipt.setPaddingBottom(10);
            cellReceipt.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellReceipt.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cellDate = new PdfPCell(new Paragraph("Date: "
                    + RegCusReservation.getNewReceipt().getReceiptDate()));
            cellDate.setBorderColor(BaseColor.DARK_GRAY);
            cellDate.setPaddingLeft(10);
            cellDate.setPaddingTop(10);
            cellDate.setPaddingBottom(10);
            cellDate.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellDate.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cellCustomerInfo = new PdfPCell(new Paragraph(
                    "ID: " + RegCustomer.getNewCustomer().getCustomerID()
                    + "\n\nName: " + RegCustomer.getNewCustomer().getCustomerName()
                    + "\n\nPhone: " + RegCustomer.getNewCustomer().getCustomerPhone()));
            cellCustomerInfo.setBorderColor(BaseColor.WHITE);
            cellCustomerInfo.setUseVariableBorders(true);
            cellCustomerInfo.setPaddingLeft(10);
            cellCustomerInfo.setPaddingTop(10);
            cellCustomerInfo.setPaddingBottom(10);
            cellCustomerInfo.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellCustomerInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);

            double newTotal = OrdFoodMenu.getNewFoodOrder().getFoodOrderTotal() + 10;

            PdfPCell cellTotal = new PdfPCell(new Paragraph("Total Amount: MYR "
                    + RegCusReservation.getNewReceipt().getReceiptTotalCost()));
            cellTotal.setBorderColor(BaseColor.DARK_GRAY);
            cellTotal.setPaddingLeft(10);
            cellTotal.setPaddingTop(10);
            cellTotal.setPaddingBottom(10);
            cellTotal.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTotal.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cellReservationInfo = new PdfPCell(new Paragraph(
                    "Reservation ID: " + RegCustomer.getNewReservation().getReservationID()
                    + "\n\nReservation Info: " + RegCustomer.getNewReservation().getInfo()
                    + "\n\nNo of Persons: " + RegCustomer.getNewReservation().getNoPersons()
                    + "\n\nReservation Date: " + RegCusReservation.getNewRoomTable().getDate()
                    + "\n\nReservation Place: " + RegCusReservation.getNewRoomTable().getRoomName()
                    + "\n\nReservation Time: " + RegCusReservation.getNewRoomTable().getTime()
                    + "\n\nReservation Table: " + RegCusReservation.getNewRoomTable().getTableName()));
            cellReservationInfo.setBorderColor(BaseColor.DARK_GRAY);
            cellReservationInfo.setPaddingLeft(10);
            cellReservationInfo.setPaddingTop(10);
            cellReservationInfo.setPaddingBottom(10);
            cellReservationInfo.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellReservationInfo.setVerticalAlignment(Element.ALIGN_MIDDLE);

            // Add Cells to Table
            tblHeading.addCell(cellRestaurant);

            tblInfo.addCell(cellSpacer);
            tblInfo.addCell(cellReceipt);

            tblInfo.addCell(cellSpacer);
            tblInfo.addCell(cellDate);

            tblCustomer.addCell(cellCustomerInfo);
            tblCustomer.addCell(cellSpacer);

            tblItems.addCell("Item Name".toUpperCase());
            tblItems.addCell("QTY".toUpperCase());
            tblItems.addCell("Amount (MYR)".toUpperCase());

            tblItems.addCell("Main Dining Hall Reservation Fee");
            tblItems.addCell("1");
            tblItems.addCell(String.valueOf(RegCustomer.getNewReservation().getCost()));

            for (int x = 0; x < OrdFoodMenu.getFoodOrderingFoodName().size(); x++) {
                tblItems.addCell(OrdFoodMenu.getFoodOrderingFoodName().get(x));
                tblItems.addCell(OrdFoodMenu.getFoodOrderingFoodQTY().get(x).toString());
                tblItems.addCell(OrdFoodMenu.getFoodOrderingFoodSubTotal().get(x).toString());
            }

            tblTotal.addCell(cellSpacer);
            tblTotal.addCell(cellTotal);

            tblReservation.addCell(cellReservationInfo);

            // Insert Tables to Document
            document.add(tblHeading);
            document.add(tblInfo);
            document.add(tblCustomer);
            document.add(tblReservation);
            document.add(tblItems);
            document.add(tblTotal);

            document.close();
            writer.close();
            Desktop.getDesktop().open(new File("receipts/RegReservationFoodOrder/"
                    + java.time.LocalDate.now() + "-"
                    + RegCusReservation.getNewReceipt().getReceiptNo() + ".pdf"));
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
