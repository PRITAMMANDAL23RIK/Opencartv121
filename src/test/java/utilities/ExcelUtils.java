package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public FileInputStream fileInput;
	public FileOutputStream fileOutput;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFCell cell;
	public XSSFRow row;
	public String Path;
	
	public ExcelUtils(String Path) {
		this.Path=Path;
	}
	
	public int getTotalRow(String sheetname) throws IOException {
		fileInput=new FileInputStream(Path);
		workbook=new XSSFWorkbook(fileInput);
		sheet=workbook.getSheet(sheetname);
		int row=sheet.getLastRowNum();
		workbook.close();
		fileInput.close();
		return row;
	}
	
	public int getTotalCell(String sheetname,int rownum) throws IOException {
		fileInput=new FileInputStream(Path);
		workbook=new XSSFWorkbook(fileInput);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(rownum);
		int cell=row.getLastCellNum();
		workbook.close();
		fileInput.close();
		return cell;
	}
	
	public String getCellValue(String sheetname,int rownum,int cellnum) throws IOException {
		fileInput=new FileInputStream(Path);
		workbook=new XSSFWorkbook(fileInput);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(rownum);
		cell=row.getCell(cellnum);
		String data;
		try {
			data=cell.toString();
		}
		catch(Exception e) {
			data="";
		}
		workbook.close();
		fileInput.close();
		return data;
	}

}
