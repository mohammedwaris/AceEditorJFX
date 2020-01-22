package com.silverbrain.ace;


import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.geometry.Insets;

import java.io.File;
import java.util.ArrayList;
import java.util.function.Consumer;

import netscape.javascript.JSObject;

import com.silverbrain.ace.events.*;



public class Editor extends EventDispatcher {

	private EditSession editSession;
	private WebEngine webEngine;
	private String js = "";
	
	public Editor(WebEngine webEngine) {
		this.webEngine = webEngine;
		this.editSession = new EditSession(this.webEngine);
	}
	
	public void invoke(String eventType, JSObject eventData) {
		
		AceEvent aceEvent = null;
		AceEventData aceEventData = null;
		if(eventType.equalsIgnoreCase(AceEvent.CHANGE)) {
			int id = (int)eventData.getMember("id");
			String action = (String)eventData.getMember("action");
			JSObject start = (JSObject)eventData.getMember("start");
			Cell startCell = new Cell((int)start.getMember("row"), (int)start.getMember("column"));
			JSObject end = (JSObject)eventData.getMember("end");
			Cell endCell = new Cell((int)end.getMember("row"),(int)end.getMember("column"));
			JSObject lines = (JSObject)eventData.getMember("lines");
			int numLines = (int)lines.getMember("length");
			String[] linesStr = new String[numLines];
			for(int i=0;i<numLines;i++) 
				linesStr[i] = (String)lines.getSlot(i);
			aceEventData = new ChangeEventData(id, action, linesStr, startCell, endCell);
			aceEvent = new AceEvent(eventType, eventData);
		}else if(eventType.equalsIgnoreCase(AceEvent.FOCUS)) {
			aceEvent = new AceEvent(eventType, null);
		}else if(eventType.equalsIgnoreCase(AceEvent.BLUR)) {
			aceEvent = new AceEvent(eventType, null);
		}else if(eventType.equalsIgnoreCase(AceEvent.COPY)) {
			String text = (String)eventData.getMember("text");
			aceEvent = new AceEvent(eventType, text);
		}else if(eventType.equalsIgnoreCase(AceEvent.PASTE)) {
			String text = (String)eventData.getMember("text");
			aceEvent = new AceEvent(eventType, text);
		}
		
		
		dispatchEvent(aceEvent);
		
	}
	
	public void setTheme(String themePath) {
		js = "editor.setTheme(" + themePath + ");";
		this.webEngine.executeScript(js);
	}
	
	public void addSelectionMarker(Range orientedRange) {
		int startRow = orientedRange.getStartRow();
		int startColumn = orientedRange.getStartColumn();
		int endRow = orientedRange.getEndRow();
		int endColumn = orientedRange.getEndColumn();
		js = "var orientedRange = new ace.Range(" + startRow + ", " + startColumn + "," + endRow + "," + endColumn + ");";
		this.webEngine.executeScript(js);
		js = "addSelectionMarker(orientedRange);";
		JSObject range = (JSObject)this.webEngine.executeScript(js);
	}
	
	public void alignCursors() {
		js = "editor.alignCursors();";
		this.webEngine.executeScript(js);
	}
	
	public void blockOutdent() {
		js = "editor.blockOutdent();";
		this.webEngine.executeScript(js);
	}
	
	public void blur() {
		js = "blur();";
		this.webEngine.executeScript(js);
	}
	
	public void centerSelection() {
		js = "editor.centerSelection();";
		this.webEngine.executeScript(js);
	}
	
	public void setSession(EditSession editSession) {
		this.editSession = editSession;
	}
	
	public EditSession getSession() {
		return this.editSession;
	}
}