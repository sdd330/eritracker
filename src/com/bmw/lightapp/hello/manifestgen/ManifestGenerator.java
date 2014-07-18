package com.bmw.lightapp.hello.manifestgen;

import java.io.File;
import java.io.FileNotFoundException;

import com.bmw.developer.cloud.c1.data.TableCellType;
import com.bmw.developer.cloud.c1.data.TableColRelWidth;
import com.bmw.developer.cloud.c1.data.TableConfigRelWidths;
import com.bmw.developer.cloud.c1.data.TableConfiguration;
import com.bmw.developer.cloud.c1.data.TableRowConfig;
import com.bmw.developer.cloud.c1.data.manifest.CompositeListPageScreen;
import com.bmw.developer.cloud.c1.data.manifest.Header;
import com.bmw.developer.cloud.c1.data.manifest.Manifest;
import com.bmw.developer.cloud.c1.data.manifest.RemoteData;
import com.bmw.lightapp.hello.servlet.HelloWorldServlet;

/**
 * 该类用于生成manifest.json文件
 */
public class ManifestGenerator {
    //请注意首页的id必须是"main".
    private static String MAIN_SCREEN_ID = "main";
    private static String LISTMORE_SCREEN_ID = "listmore";
    private static String PAGE_GENERATOR_SERVLET_URL = "http://eripark.duapp.com/eritracker/hello";
    
    public static void main(String[] args) {

        Header header = new Header("EriTracker", "1.0", MAIN_SCREEN_ID, false, "icon-eritracker.png");
        Manifest manifest = new Manifest(header);

        RemoteData remoteData = new RemoteData(PAGE_GENERATOR_SERVLET_URL);
        //三个参数分别对应线上、集成、测试环境的url，我们这里不做区分，用同一个
        CompositeListPageScreen screen = new CompositeListPageScreen(remoteData, remoteData, remoteData);
        TableConfigRelWidths tableConfig = new TableConfigRelWidths(new TableColRelWidth(80, TableCellType.any),
                new TableColRelWidth(80, TableCellType.any), new TableColRelWidth(80, TableCellType.any));
        tableConfig.setRowConfig(new TableRowConfig(TableCellType.remoteImage), new TableRowConfig(
                TableCellType.caption));
        screen.addTableConfiguration(tableConfig);
        manifest.addScreen(MAIN_SCREEN_ID, screen);

        RemoteData listScreenDataSource = new RemoteData(PAGE_GENERATOR_SERVLET_URL);
        listScreenDataSource.addParameter("page", "listPage");
        CompositeListPageScreen listScreen = new CompositeListPageScreen(listScreenDataSource, listScreenDataSource, listScreenDataSource);
        TableConfiguration detailTableConfiguration = new TableConfigRelWidths(
                new TableColRelWidth(50, TableCellType.caption),
                new TableColRelWidth(10, TableCellType.caption),
                new TableColRelWidth(40, TableCellType.caption));
        listScreen.addTableConfiguration(detailTableConfiguration);
        manifest.addScreen(LISTMORE_SCREEN_ID, listScreen);
        //将manifest写到文件 manifest/manifest.json
        try {
            manifest.writeFile(new File("./manifest"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}