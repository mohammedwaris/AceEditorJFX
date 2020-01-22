package com.silverbrain.ace;

public class Range {

	private int startRow;
	private int startColumn;
	private int endRow;
	private int endColumn;
	
	public Range(int startRow, int startColumn, int endRow, int endColum) {
		this.startRow = startRow;
		this.startColumn = startColumn;
		this.endRow = endRow;
		this.endColumn = endColumn;
	}
	
	public int getStartRow() {
		return this.startRow;
	}
	
	public int getStartColumn() {
		return this.startColumn;
	}
	
	public int getEndRow() {
		return this.endRow;
	}
	
	public int getEndColumn() {
		return this.endColumn;
	}

}