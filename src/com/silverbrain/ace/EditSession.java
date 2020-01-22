package com.silverbrain.ace;

import javafx.scene.web.WebEngine;

public class EditSession {

	private WebEngine webEngine;
	
	private Document document;
	
	public EditSession(WebEngine webEngine) {
		this.webEngine = webEngine;
		
		this.document = new Document(webEngine);
	}
	
	public Document getDocument() {
		return this.document;
	}
}