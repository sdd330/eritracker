package com.bmw.lightapp.hello.screen;

import com.bmw.developer.cloud.c1.data.component.Link;
import com.bmw.developer.cloud.c1.data.component.Table;
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
