package com.silverbrain.ace.events;

public class ChangeEventData extends AceEventData {

	private String action;
	private int id;
	private String[] lines;
	private Cell start;
	private Cell end;
	
	public ChangeEventData(int id, String action, String[] lines, Cell start, Cell end) {
		this.id = id;
		this.action = action;
		this.lines = lines;
		this.start = start;
		this.end = end;
	}
	
	public String toString() {
		String text = "ChangeEventData[id: " + id + ",action: " + action + ", lines: " + lines + ", start: " + start + ", end: " + end + "]";
		return text;
	}
	
	
}