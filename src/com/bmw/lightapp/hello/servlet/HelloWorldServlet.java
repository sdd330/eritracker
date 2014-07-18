package com.bmw.lightapp.hello.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bmw.developer.cloud.c1.data.component.RemoteImage;
import com.bmw.developer.cloud.c1.data.component.Table;
import com.bmw.developer.cloud.c1.data.component.TableCell;
import com.bmw.developer.cloud.c1.data.component.TableRow;
import com.bmw.developer.cloud.c1.data.page.CompositeListPage;

public class HelloWorldServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(HelloWorldServlet.class.getName());

    @Override
    protected final void doGet(final HttpServletRequest pRequest, final HttpServletResponse pResponse)
            throws ServletException, IOException {

        String responseString = "";

        try {
            // create the page that is delivered
            CompositeListPage page = new CompositeListPage();
            //page.addItem(new Caption("Label.Text.firstPage"));
            Table trackerTable = new Table();
            TableRow numberRow = new TableRow();
            numberRow.addCell("0");
            numberRow.addCell("0");
            numberRow.addCell("0");
            trackerTable.addRow(numberRow);

            TableRow imageRow = new TableRow();
            imageRow.setLineHeight("100%");
            TableCell imageCell1 = new TableCell();
            imageCell1.setVariableContent(new RemoteImage("http://eripark.duapp.com/resources/parking_service.png"));
            imageRow.addCell(imageCell1);

            TableCell imageCell2 = new TableCell();
            imageCell2.setVariableContent(new RemoteImage("http://eripark.duapp.com/resources/charging_service.png"));
            imageRow.addCell(imageCell2);

            TableCell imageCell3 = new TableCell();
            imageCell3.setVariableContent(new RemoteImage("http://eripark.duapp.com/resources/food_service.png"));
            imageRow.addCell(imageCell3);

            trackerTable.addRow(imageRow);

            trackerTable.setWidth("100%");
            page.addItem(trackerTable);

            responseString = page.toJson();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "something went wrong", ex);
            pResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        // write answer to the response stream.
        pResponse.setCharacterEncoding("UTF-8");
        pResponse.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = pResponse.getWriter();
        writer.write(responseString);
        writer.flush();
        writer.close();
    }
}