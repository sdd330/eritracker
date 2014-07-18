package com.bmw.lightapp.hello.page;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bmw.developer.cloud.c1.data.component.LinkColumns;
import com.bmw.developer.cloud.c1.data.component.Table;
import com.bmw.developer.cloud.c1.data.component.TableRow;
import com.bmw.developer.cloud.c1.data.page.CompositeListPage;
import com.bmw.lightapp.hello.constant.AppConstant;
import com.bmw.lightapp.hello.screen.TrackerScreen;

@SuppressWarnings("javadoc")
public class PageGenerator extends CompositeListPage {

    private static JSONArray poiListArray;

    public static String generateTrackerPage(String id) throws JSONException, IOException {
    	String poiListRequeString = "http://eritrack.duapp.com/public/dataprovider/getReturnEntitiesResult.ajax?id=" + id;
    	JSONObject json  = readJsonFromUrl(poiListRequeString);
         
        String name = json.getJSONObject("content").getString("title");
        String address = json.getJSONObject("content").getString("address");
        String price = json.getJSONObject("content").getString("price");
        Integer freeParkingCount = json.getJSONObject("content").getInt("leftParkingCount");
        Integer freeChargingCount = json.getJSONObject("content").getInt("leftPowerCharge");
        JSONArray location = json.getJSONObject("content").getJSONArray("location");
        String lat = (String) location.get(0);
        String lon = (String) location.get(1);
        String imgURL = "http://api.map.baidu.com/staticimage?center=" + lat + "," + lon + "&width=300&height=300&zoom=15&markers=" + lat + "," + lon + "&markerStyles=l,A";

        TrackerScreen trackerScreen = new TrackerScreen(name, address, price, freeParkingCount, freeChargingCount, imgURL);
        return trackerScreen.toJson();
    }

    public static String generatePakringListPage(String lat, String lon, String tag) throws JSONException, IOException {
        CompositeListPage page = new CompositeListPage();
        Table headline = new Table();
        TableRow headlineRow = new TableRow();
        headlineRow.addCell("Column.Name.Text");
        headlineRow.addCell("Column.Distance.Text");
        headlineRow.addCell("Column.Service.Text");
        headlineRow.addCell("Column.FreeParkingCount.Text");
        headlineRow.setHeader(true);
        headline.addRow(headlineRow);
        headline.setWidth("100%");
        page.addItem(headline);
        //mock data
        addMockRows(page, lat, lon, tag);
        return page.toJson();
    }

    private static void addMockRows(CompositeListPage page, String lat, String lon, String tag) throws JSONException, IOException {
        String poiListRequeString = "http://eritrack.duapp.com/public/dataprovider/getReturnEntitiesResult.ajax?location=" + lat + "," + lon;
         
        JSONObject json = readJsonFromUrl(poiListRequeString);
        poiListArray = parsingJSONObject(json);
        for (int i = 0; i<poiListArray.length(); i++) {
        	LinkColumns poiLinks = new LinkColumns(AppConstant.TRACKER_SCREEN_ID);
            poiLinks.addReturnParam(AppConstant.PARAMETER_SCREENID_KEY, poiListArray.getJSONObject(i).getString("id"));
            poiLinks.addTextCell(poiListArray.getJSONObject(i).getString("title"));
            poiLinks.addTextCell(poiListArray.getJSONObject(i).getInt("distance") + "M");
            poiLinks.addTextCell("parking");
            poiLinks.addTextCell(poiListArray.getJSONObject(i).getInt("leftParkingCount") + "");
            poiLinks.setWidth("100%");

            page.addItem(poiLinks);
        }
    }

    private static String readAll(Reader rd) throws IOException {
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
              sb.append((char) cp);
            }
            return sb.toString();
    }
     
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
            InputStream is = new URL(url).openStream();
            try {
              BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
              String jsonText = readAll(rd);
              JSONObject json = new JSONObject(jsonText);
              return json;
            } finally {
              is.close();
            }
    }
     
    private static JSONArray parsingJSONObject(JSONObject obj) throws JSONException {
        JSONArray jsonArray = obj.getJSONArray("returnEntities");
        return jsonArray;
    }
}
    