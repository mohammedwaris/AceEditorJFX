package com.silverbrain.ace.events;

public class Cell {
		private int row;
		private int column;
		
		public Cell(int row, int column) {
			this.row = row;
			this.column = column;
		}
		
		public int getRow() {
			return this.row;
		}
		
		public int getColumn() {
			return this.column;
		}
		
		public String toString() {
			String text = "Cell[row: " + row + ", column: " + column + "]";
			return text;
		}
}