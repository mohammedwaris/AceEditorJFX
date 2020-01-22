package com.silverbrain.ace.events;

public class AceEvent {

	public static final String READY = "ready";
	public static final String CHANGE = "change";
	public static final String BLUR = "blur";
	public static final String COPY = "copy";
	public static final String PASTE = "paste";
	public static final String FOCUS = "focus";
	public static final String CHANGE_SELECTION_STYLE = "changeSelectionStyle";
	
	private String eventType;
	private Object eventData;
	
	public AceEvent(String eventType, Object eventData) {
		this.eventType  = eventType;
		this.eventData = eventData;
	}
	
	public String getType() {
		return this.eventType;
	}
	
	public Object getEventData() {
		return this.eventData;
	}
}