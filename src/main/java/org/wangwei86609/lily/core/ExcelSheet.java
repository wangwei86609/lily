package org.wangwei86609.lily.core;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Image;
import jxl.Range;
import jxl.Sheet;

public class ExcelSheet{
	private int cols;
	private int[] cols_Width;
	private int rows;
	private int[] rows_Height;
	private List<Image> images=new ArrayList<Image>();
	private Sheet sheet;
	private Range[] ranges;
	private String sheetName;
	private Map<String,Cell> mergeCell;
	private int numberCells;
	
	public int getNumberCells() {
		return numberCells;
	}
	public void setNumberCells(int numberCells) {
		this.numberCells = numberCells;
	}
	public Map<String, Cell> getMergeCell() {
		return mergeCell;
	}
	public void setMergeCell(Map<String, Cell> mergeCell) {
		this.mergeCell = mergeCell;
	}
	public String getSheetName() {
		return sheetName;
	}
	public int getCols() {
		return cols;
	}
	private void setCols(int cols) {
		this.cols = cols;
	}
	public int[] getCols_Width() {
		return cols_Width;
	}
	private void setCols_Width(int[] cols_Width) {
		this.cols_Width = cols_Width;
	}
	public List<Image> getImages() {
		return images;
	}
	private void setImages(List<Image> images) {
		this.images = images;
	}
	public Range[] getRanges() {
		return ranges;
	}
	private void setRanges(Range[] ranges) {
		this.ranges = ranges;
	}
	public int getRows() {
		return rows;
	}
	private void setRows(int rows) {
		this.rows = rows;
	}
	public int[] getRows_Height() {
		return rows_Height;
	}
	private void setRows_Height(int[] rows_Height) {
		this.rows_Height = rows_Height;
	}
	public Sheet getSheet() {
		return sheet;
	}
	private ExcelSheet(){
		
	}
	public ExcelSheet(Sheet sheet){
		this.sheet=sheet;
		readSheet();
	}

	private void readSheet(){
		this.setCols(sheet.getColumns());
		this.setRows(sheet.getRows());
		this.setCols_Width(colsWidth());
		this.setRows_Height(rowsHeight());
		this.setImages(find_Image());
		this.setRanges(sheet.getMergedCells());
		this.sheetName=sheet.getName();
		this.mergeCell=getMCell();
		this.setNumberCells(getCells());
	}

	private int[] colsWidth(){
		cols_Width=new int[this.getCols()];
		for(int i=0;i<sheet.getColumns();i++){
			cols_Width[i]= sheet.getColumnView(i).getSize()*2;
		}
		return cols_Width;
	}

	private int[] rowsHeight(){
		rows_Height=new int[this.getRows()];
		for(int i=0;i<sheet.getRows();i++){
			rows_Height[i]=sheet.getRowView(i).getSize();
		}
		return rows_Height;
	}

	private List<Image> find_Image(){
		for(int i=0;i<sheet.getNumberOfImages();i++){
			jxl.Image jxlImage=sheet.getDrawing(i);
			Image image;
			try {
				image =null;//Image.getInstance(jxlImage.getImageData());
				images.add(image);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return images;
	}
	private Map<String,Cell> getMCell(){
		Map<String,Cell> map=new HashMap<String,Cell>();
		if(sheet.getMergedCells()==null){
			return null;
		}
		Range range[]=sheet.getMergedCells();
		for(int i=0;i<range.length;i++){
			int row=range[i].getTopLeft().getRow();
			int col=range[i].getTopLeft().getColumn();
			Cell bcell= range[i].getBottomRight();
			map.put(row+","+col,bcell);
		}
		return map;
	}
	private int getCells(){
		int number=0;
		if(sheet.getMergedCells()==null){
			return 0;
		}
		Range range[]=sheet.getMergedCells();
		for(int i=0;i<range.length;i++){
			int rows=range[i].getBottomRight().getRow()-range[i].getTopLeft().getRow()+1;
			int cols=range[i].getBottomRight().getColumn()-range[i].getTopLeft().getColumn()+1;
			number+=rows*cols;
		}
		return number;
	}
}
