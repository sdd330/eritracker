/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2014
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package com.bmw.lightapp.hello.screen;

import com.bmw.developer.cloud.c1.data.component.Caption;
import com.bmw.developer.cloud.c1.data.component.Checkbox;
import com.bmw.developer.cloud.c1.data.component.SubmitLink;
import com.bmw.developer.cloud.c1.data.page.FormPage;

/**
 * MainSelectScreen
 */
public class MainScreen {

    private FormPage page;

    /**
     * Creates a new instance of <code>MainSelectScreen</code>.
     */
    public MainScreen() {
        page = new FormPage("tracker");
        Checkbox item1 = new Checkbox(new Caption("Parking Service"), "parking", "parking");
        page.addItem(item1);

        Checkbox item2 = new Checkbox(new Caption("Charging Service"), "charging", "charging");
        page.addItem(item2);

        Checkbox item3 = new Checkbox(new Caption("Coffee Service"), "coffee", "coffee");
        page.addItem(item3);

        Checkbox item4 = new Checkbox(new Caption("Free Wifi Service"), "freewifi", "freewifi");
        page.addItem(item4);

        SubmitLink submit = new SubmitLink(new Caption("Track"));
        page.addItem(submit);
    }

    /**
     * @return screen data
     */
    public String toJson() {
        return page.toJson();
    }
}
