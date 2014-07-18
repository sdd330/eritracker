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

        Header header = new Header("EriTracker", "1.0", AppConstant.MAIN_SCREEN_ID, true, "icon-eritracker.jpg");
        Manifest manifest = new Manifest(header);

        // Main Screen
        FormPage mainPage = new FormPage("Label.ChooseServices.Text", AppConstant.TRACKER_SCREEN_ID);

        // Checkbox List
        Checkbox itemParking = new Checkbox(new Caption("Label.Parking.Text"), AppConstant.TAG_PARKING,
                AppConstant.TAG_PARKING);
        Checkbox itemRefuelling = new Checkbox(new Caption("Label.Refuelling.Text"), AppConstant.TAG_REFUELLING,
                AppConstant.TAG_REFUELLING);
        Checkbox itemCharing = new Checkbox(new Caption("Label.Charging.Text"), AppConstant.TAG_CHARGING,
                AppConstant.TAG_CHARGING);
        Checkbox itemCoffee = new Checkbox(new Caption("Label.Coffee.Text"), AppConstant.TAG_COFFEE,
                AppConstant.TAG_COFFEE);
        Checkbox itemFood = new Checkbox(new Caption("Label.Food.Text"), AppConstant.TAG_FOOD, AppConstant.TAG_FOOD);

        mainPage.addItem(itemParking);
        mainPage.addItem(itemCharing);
        mainPage.addItem(itemRefuelling);
        mainPage.addItem(itemCoffee);
        mainPage.addItem(itemFood);

        // Submit Link
        SubmitLink submitLink = new SubmitLink(new Caption("Label.Save.Text"));
        submitLink.target(new LinkTarget(AppConstant.LIST_SCREEN_ID));
        submitLink.setTopSeparator();
        mainPage.addItem(submitLink);

        ConstantData mainConstantData = new ConstantData(mainPage);
        FormPageScreen mainScreen = new FormPageScreen(mainConstantData, mainConstantData, mainConstantData);

        manifest.addScreen(AppConstant.MAIN_SCREEN_ID, mainScreen);

        // List Screen
        RemoteData listScreenDataSource = new RemoteData(PAGE_GENERATOR_SERVLET_URL);
        listScreenDataSource.addParameter(AppConstant.PARAMETER_SCREENID_KEY, AppConstant.LIST_SCREEN_ID);
        CompositeListPageScreen listScreen = new CompositeListPageScreen(listScreenDataSource, listScreenDataSource,
                listScreenDataSource);
        TableConfiguration detailTableConfiguration = new TableConfigRelWidths(new TableColRelWidth(50,
                TableCellType.caption), new TableColRelWidth(10, TableCellType.caption), new TableColRelWidth(40,
                TableCellType.caption));
        listScreen.addTableConfiguration(detailTableConfiguration);
        manifest.addScreen(AppConstant.LIST_SCREEN_ID, listScreen);

        RemoteData remoteData = new RemoteData(PAGE_GENERATOR_SERVLET_URL);
        remoteData.addParameter(AppConstant.PARAMETER_SCREENID_KEY, AppConstant.TRACKER_SCREEN_ID);

        // Tracker Screen
        RemoteData trackerScreenDataSource = new RemoteData(PAGE_GENERATOR_SERVLET_URL);
        trackerScreenDataSource.addParameter(AppConstant.PARAMETER_SCREENID_KEY, AppConstant.TRACKER_SCREEN_ID);
        CompositeListPageScreen trackerScreen = new CompositeListPageScreen(trackerScreenDataSource,
                trackerScreenDataSource, trackerScreenDataSource);

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