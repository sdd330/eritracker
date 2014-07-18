package com.bmw.lightapp.hello.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bmw.lightapp.hello.constant.AppConstant;
import com.bmw.lightapp.hello.page.PageGenerator;
import com.bmw.lightapp.hello.screen.TrackerScreen;

@SuppressWarnings("javadoc")
public class HelloWorldServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(HelloWorldServlet.class.getName());
    public static final String LIST_PAGE = "listPage";

    @Override
    protected final void doGet(final HttpServletRequest pRequest, final HttpServletResponse pResponse)
            throws ServletException, IOException {
        String responseString = "";
        final String screenID = pRequest.getParameter(AppConstant.PARAMETER_SCREENID_KEY);

        try {
            if (AppConstant.MAIN_SCREEN_ID.equals(screenID)) {
                // NONE
            } else if (AppConstant.LIST_SCREEN_ID.equals(screenID)) {
                responseString = PageGenerator.generatePakringListPage();
            } else if (AppConstant.TRACKER_SCREEN_ID.equals(screenID)) {
                TrackerScreen trackerScreen = new TrackerScreen();
                responseString = trackerScreen.toJson();
            }
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