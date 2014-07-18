package com.bmw.lightapp.hello.manifestgen;

import java.io.File;
import java.io.FileNotFoundException;

import com.bmw.developer.cloud.c1.data.TableCellType;
import com.bmw.developer.cloud.c1.data.TableColRelWidth;
import com.bmw.developer.cloud.c1.data.TableConfigRelWidths;
import com.bmw.developer.cloud.c1.data.TableConfiguration;
import com.bmw.developer.cloud.c1.data.TableRowConfig;
import com.bmw.developer.cloud.c1.data.component.Caption;
import com.bmw.developer.cloud.c1.data.component.Checkbox;
import com.bmw.developer.cloud.c1.data.component.LinkTarget;
import com.bmw.developer.cloud.c1.data.component.SubmitLink;
import com.bmw.developer.cloud.c1.data.manifest.CompositeListPageScreen;
import com.bmw.developer.cloud.c1.data.manifest.ConstantData;
import com.bmw.developer.cloud.c1.data.manifest.FormPageScreen;
import com.bmw.developer.cloud.c1.data.manifest.Header;
import com.bmw.developer.cloud.c1.data.manifest.Manifest;
import com.bmw.developer.cloud.c1.data.manifest.RemoteData;
import com.bmw.developer.cloud.c1.data.page.FormPage;
import com.bmw.lightapp.hello.constant.AppConstant;

@SuppressWarnings("javadoc")
public class ManifestGenerator {
    private static String PAGE_GENERATOR_SERVLET_URL = "http://eripark.duapp.com/eritracker/hello";

    public static void main(String[] args) {

        Header header = new Header("EriTracker", "1.0", AppConstant.MAIN_SCREEN_ID, true, "icon-eritracker.png");
        Manifest manifest = new Manifest(header);

        // Main Screen
        RemoteData remoteData = new RemoteData(PAGE_GENERATOR_SERVLET_URL);
        FormPage mainPage = new FormPage("Please select what you needs:", AppConstant.TRACKER_SCREEN_ID);

        // Checkbox List
        Checkbox item1 = new Checkbox(new Caption("Parking Service"), AppConstant.TAG_PARKING, AppConstant.TAG_PARKING);
        mainPage.addItem(item1);

        Checkbox item2 = new Checkbox(new Caption("Charging Service"), AppConstant.TAG_CHARGING,
                AppConstant.TAG_CHARGING);
        mainPage.addItem(item2);

        Checkbox item3 = new Checkbox(new Caption("Coffee Service"), AppConstant.TAG_COFFEE, AppConstant.TAG_COFFEE);
        mainPage.addItem(item3);

        Checkbox item4 = new Checkbox(new Caption("Free Wifi Service"), AppConstant.TAG_FREEWIFI,
                AppConstant.TAG_FREEWIFI);
        mainPage.addItem(item4);

        // Submit Link
        SubmitLink submitLink = new SubmitLink(new Caption("Track Destination"));
        submitLink.target(new LinkTarget(AppConstant.LIST_SCREEN_ID));
        submitLink.setTopSeparator();
        mainPage.addItem(submitLink);

        ConstantData mainConstantData = new ConstantData(mainPage);
        FormPageScreen mainScreen = new FormPageScreen(mainConstantData, mainConstantData, mainConstantData);

        manifest.addScreen(AppConstant.MAIN_SCREEN_ID, mainScreen);

        // List Screen
        RemoteData listScreenDataSource = new RemoteData(PAGE_GENERATOR_SERVLET_URL);
        listScreenDataSource.addParameter(AppConstant.PARAMETER_SCREENID_KEY, "listPage");
        CompositeListPageScreen listScreen = new CompositeListPageScreen(listScreenDataSource, listScreenDataSource,
                listScreenDataSource);
        TableConfiguration detailTableConfiguration = new TableConfigRelWidths(new TableColRelWidth(50,
                TableCellType.caption), new TableColRelWidth(10, TableCellType.caption), new TableColRelWidth(40,
                TableCellType.caption));
        listScreen.addTableConfiguration(detailTableConfiguration);
        manifest.addScreen(AppConstant.LIST_SCREEN_ID, listScreen);

        // Tracker Screen
        CompositeListPageScreen trackerScreen = new CompositeListPageScreen(remoteData, remoteData, remoteData);

        TableConfigRelWidths tableConfig = new TableConfigRelWidths(new TableColRelWidth(80, TableCellType.any),
                new TableColRelWidth(80, TableCellType.any), new TableColRelWidth(80, TableCellType.any));
        tableConfig.setRowConfig(new TableRowConfig(TableCellType.remoteImage), new TableRowConfig(
                TableCellType.caption));
        trackerScreen.addTableConfiguration(tableConfig);
        manifest.addScreen(AppConstant.TRACKER_SCREEN_ID, trackerScreen);

        try {
            manifest.writeFile(new File("./manifest"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}