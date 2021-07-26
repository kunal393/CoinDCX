package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Datadriven {
	
	public static Workbook workbook;
	public static Sheet sheet;
	public static Row row;
	public static Cell cell;
	public String path=System.getProperty("user.dir")+"//TestData//testdata.xlsx";
	public String sheetname="password";
	public FileInputStream fis;
	public Cell getdata(String file,String sheetname,int Row, int Cell) throws IOException {
		FileInputStream fis=new FileInputStream(file);
		workbook= new XSSFWorkbook(fis);
//		sheet=workbook.getSheet(sheetname);
//		row=sheet.getRow(Row);
//		cell=row.getCell(Cell);
		return workbook.getSheet(sheetname).getRow(Row).getCell(Cell);
			
	}
	
		
	public int getRowCount(String file,String sheetname) throws IOException  {
		FileInputStream fis=new FileInputStream(file);
		workbook= new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetname);
		return sheet.getLastRowNum();
	}
	
	public int getCellCount(String file,String sheetname,int Row) throws IOException  {
		FileInputStream fis=new FileInputStream(file);
		workbook= new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(Row);
		return row.getLastCellNum();
	}
	
	
	
	
	
	public Object[][] datas(String sheetname) {
		
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		}
		try {
			workbook= new XSSFWorkbook(fis);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		sheet=workbook.getSheet(sheetname);

		
		Object[][] obj=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0;i<sheet.getLastRowNum();i++) {
			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
				
				if(!sheet.getRow(i+1).getCell(j).toString().isEmpty()) {
				obj[i][j]=sheet.getRow(i+1).getCell(j).toString();
				}
			}
		}
		return obj;
	}
	
}
