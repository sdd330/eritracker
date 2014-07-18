package com.bmw.lightapp.hello.screen;

import com.bmw.developer.cloud.c1.data.component.Link;
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
    public TrackerScreen() {
        page = new CompositeListPage();

        Table trackerTable = new Table();

        // Image Row
        TableRow imageRow = new TableRow();
        imageRow.setLineHeight("100%");
        TableCell imageCell1 = new TableCell();
        imageCell1.setVariableContent(new RemoteImage("http://eripark.duapp.com/resources/parking.jpg"));
        imageRow.addCell(imageCell1);

        TableCell imageCell2 = new TableCell();
        imageCell2.setVariableContent(new RemoteImage("http://eripark.duapp.com/resources/charging.jpg"));
        imageRow.addCell(imageCell2);

        TableCell imageCell3 = new TableCell();
        imageCell3.setVariableContent(new RemoteImage("http://eripark.duapp.com/resources/food.jpg"));
        imageRow.addCell(imageCell3);
        trackerTable.addRow(imageRow);

        // Number Row
        TableRow numberRow = new TableRow();
        numberRow.addCell("2");
        numberRow.addCell("0");
        numberRow.addCell("5");
        trackerTable.addRow(numberRow);

        trackerTable.setWidth("100%");
        page.addItem(trackerTable);

        Link link = new Link("listmore");
        link.setTopSeparator();
        link.setCaption("Label.Navigation.Text");
        page.addItem(link);
    }

    /**
     * @return screen data
     */
    public String toJson() {
        return page.toJson();
    }

}
