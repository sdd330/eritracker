package com.bmw.lightapp.hello.page;


import com.bmw.developer.cloud.c1.data.component.LinkColumns;
import com.bmw.developer.cloud.c1.data.component.Table;
import com.bmw.developer.cloud.c1.data.component.TableRow;
import com.bmw.developer.cloud.c1.data.page.CompositeListPage;

public class PageGenerator {

	public static String generatePakringListPage() {
		CompositeListPage page = new CompositeListPage();
		Table headline = new Table();
		TableRow headlineRow = new TableRow();
		headlineRow.addCell("Column.Name.Text");
		headlineRow.addCell("Column.Service.Text");
		headlineRow.addCell("Column.Distance.Text");
		headlineRow.setHeader(true);
		headline.addRow(headlineRow);
		headline.setWidth("100%");
		page.addItem(headline);
		//mock data
		addMockRows(page);
		return page.toJson();
	}
	
	private static void addMockRows(CompositeListPage page) {
		//TODO: mock data here, replace with real data after backend is ready
		for(int i = 0; i < 3; i++) {
			LinkColumns poiLinks = new LinkColumns("Details");
			poiLinks.addReturnParam("xx", "xxxxxx" + i);
			poiLinks.addTextCell("name" + i);
			poiLinks.addTextCell("Service " + i);
			poiLinks.addTextCell("distance" + i);
			page.addItem(poiLinks);
		}
	}
	
}
