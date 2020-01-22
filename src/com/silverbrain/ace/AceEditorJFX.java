package com.silverbrain.ace;

import java.io.File;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.scene.layout.AnchorPane;
import netscape.javascript.JSObject;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;

import com.silverbrain.ace.events.*;

public class AceEditorJFX extends AnchorPane {

	private Editor editor;
	private WebView webView;
	private WebEngine webEngine;
	
	public AceEditorJFX() {

		this.webView = new WebView();
		this.webEngine = this.webView.getEngine();
		this.editor = new Editor(this.webEngine);
		
		setTopAnchor(this.webView, 0.0);
		setLeftAnchor(this.webView, 0.0);
		setRightAnchor(this.webView, 0.0);
		setBottomAnchor(this.webView, 0.0);
		getChildren().add(this.webView);
		
		this.webEngine.getLoadWorker().stateProperty().addListener(new javafx.beans.value.ChangeListener() {
			@Override
			public void changed(javafx.beans.value.ObservableValue observable, Object oldValue, Object newValue)  {
				if (newValue == javafx.concurrent.Worker.State.SUCCEEDED) {	
					JSObject window = (JSObject) webEngine.executeScript("window");
					window.setMember("extEditor", editor);
					editor.dispatchEvent(new AceEvent(AceEvent.READY, null));
					//System.out.println(getEditor().getSession().getDocument().getValue());
				}
				//System.out.println(newValue);
			}
		});
		
		com.sun.javafx.webkit.WebConsoleListener.setDefaultListener(new com.sun.javafx.webkit.WebConsoleListener(){
			@Override
			public void messageAdded(javafx.scene.web.WebView webView, String message, int lineNumber, String sourceId) {
				String msg = "Console: [" + sourceId + ":" + lineNumber + "] " + message;
				System.out.println(msg);
			}
		});
		
		String url = "";
		try {
			url = getClass().getClassLoader().getResource("editor.html").toString();
			webEngine.load(url);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public EditSession createNewEditSession() {
		EditSession editSession = new EditSession(this.webEngine);
		return editSession;
	}
	
	public Editor getEditor() {
		return this.editor;
	}
	
}