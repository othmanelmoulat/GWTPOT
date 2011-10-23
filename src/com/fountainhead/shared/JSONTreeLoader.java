package com.fountainhead.shared;
import java.util.Iterator;
import java.util.Set;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

/**
 * Class that acts as a client to a JSONTreeLoader service. Currently, this client just
 * requests a text which contains a JSONTreeLoader encoding of a search result set from
 * yahoo. We use a text file to demonstrate how the pieces work without tripping
 * on cross-site scripting issues.
 * 
 * If you would like to make this a more dynamic example, you can associate a
 * servlet with this example and simply have it hit the yahoo service and return
 * the results.
 */
public class JSONTreeLoader {

	private final Tree jsonTree;

	/**
	 * @param jsonTree
	 * @param jsonValue
	 */
	public JSONTreeLoader(Tree jsonTree) {
		super();
		this.jsonTree = jsonTree;

	}

	/*
	 * Add the object presented by the JSONValue as a children to the requested
	 * TreeItem.
	 */
	private void addChildren(TreeItem treeItem, JSONValue jsonValue) {
		JSONArray jsonArray;
		JSONObject jsonObject;
		TreeItem item = null;

		if ((jsonArray = jsonValue.isArray()) != null) {
			for (int i = 0; i < jsonArray.size(); ++i) {

				addChildren(treeItem, jsonArray.get(i));
			}
		} else if ((jsonObject = jsonValue.isObject()) != null) {

			Set keys = jsonObject.keySet();
			for (Iterator iter = keys.iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				if (key.equals("text")) {
					String text = getChildText(jsonObject.get(key).isString()
							.stringValue());
					item = treeItem.addItem(text);
					addChildren(item, jsonObject.get(key));
				} else if (key.equals("children")) {

					addChildren(item, jsonObject.get(key));
				}
			}
		}

	}

	public void populateTreeError(String responseText) {

		jsonTree.removeItems();
		jsonTree.setVisible(true);
		TreeItem treeItem = jsonTree.addItem("Failed to parse JSONTreeLoader response");
		treeItem.addItem(responseText);
		// treeItem.setStyleName("JSONTreeLoader-JSONResponseObject");
		treeItem.setState(true);
	}

	/*
	 * Update the treeview of a JSONTreeLoader object.
	 */
	public void populateTree(JSONValue jsonValue) {

		jsonTree.removeItems();
		jsonTree.setVisible(true);
		TreeItem treeItem = jsonTree.addItem("Tree Items Root");
		addChildren(treeItem, jsonValue);
		// treeItem.setStyleName("JSONTreeLoader-JSONResponseObject");
		treeItem.setState(true);
	}
	/*
	 * Causes the text of child elements to wrap.
	 */
	private String getChildText(String text) {
		return "<span style='white-space:normal'>" + text + "</span>";
	}
}

