package com.silverbrain.ace;

import javafx.scene.web.WebEngine;

public class Document {

	private WebEngine webEngine;
	
	public Document(WebEngine webEngine) {
		this.webEngine = webEngine;
	}

	public Document(String text) {
		
	}
	
	public String getValue() {
		String js = "editor.getSession().getDocument().getValue();";
		String value = (String) webEngine.executeScript(js);
		return value;
	}
	
	public void setValue(String value) {
		String js = "editor.getSession().getDocument().setValue(\"" + value + "\");";
		webEngine.executeScript(js);
	}

}