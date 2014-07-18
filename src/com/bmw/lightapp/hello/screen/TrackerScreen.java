package com.bmw.lightapp.hello.screen;

import com.bmw.developer.cloud.c1.data.component.RemoteImage;
import com.bmw.developer.cloud.c1.data.component.Table;
import com.bmw.developer.cloud.c1.data.component.TableCell;
import com.bmw.developer.cloud.c1.data.component.TableRow;
import com.bmw.developer.cloud.c1.data.page.CompositeListPage;

/**
 * TrackerScreen
 */
public class TrackerScreen {

    private CompositeListPage page;

    /**
     * Creates a new instance of <code>TrackerScreen</code>.
     */
    public TrackerScreen(String name, String address, String price, Integer freeParkingCount, Integer freeChargingCount, String mapURL) {
    	page = new CompositeListPage();
        
        Table trackerTable = new Table();
        TableCell leftCell = new TableCell();
        
        Table infoTable = new Table();
        // Name Row
        TableRow nameRow = new TableRow();
        TableCell nameCell = new TableCell();
        nameCell.setCaption(name);
        nameRow.addCell(nameCell);
        
        // Name Row
        TableRow priceRow = new TableRow();
        TableCell priceCell = new TableCell();
       	priceCell.setCaption("" + price);
        priceRow.addCell(priceCell);

        // Address Row
        TableRow addrRow = new TableRow();
        TableCell addrCell = new TableCell();
       	addrCell.setCaption(address);
        addrRow.addCell(addrCell);

        // Image Row
        TableRow imageRow = new TableRow();
        imageRow.setLineHeight("100%");
        TableCell imageCell1 = new TableCell();
        imageCell1.setVariableContent(new RemoteImage("http://eripark.duapp.com/resources/parking.jpg"));
        imageRow.addCell(imageCell1);

        TableCell imageCell2 = new TableCell();
        imageCell2.setVariableContent(new RemoteImage("http://eripark.duapp.com/resources/charging.jpg"));
        imageRow.addCell(imageCell2);

        // Number Row
        TableRow numberRow = new TableRow();
        numberRow.addCell(freeParkingCount.toString());
        numberRow.addCell(freeChargingCount.toString());

        infoTable.addRow(nameRow);
        infoTable.addRow(priceRow);
        infoTable.addRow(addrRow);
        infoTable.addRow(imageRow);
        infoTable.addRow(numberRow);

        infoTable.setWidth("60%");
        leftCell.setVariableContent(infoTable);
        
        TableCell rightCell = new TableCell();
        rightCell.setVariableContent(new RemoteImage(mapURL));
        
        TableRow trackerRow = new TableRow();
        trackerRow.addCell(leftCell);
        trackerRow.addCell(rightCell);
        trackerTable.addRow(trackerRow);
        
        page.addItem(trackerTable);        
    }

    /**
     * @return screen data
     */
    public String toJson() {
        return page.toJson();
    }

}
