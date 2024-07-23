package data;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {

	public static String[][] readexcelData() throws IOException
	{
		
		FileInputStream file = new FileInputStream("C:\\Users\\ADMIN\\NavaDhitiWorkSpace\\AppiumFrameWork\\src\\main\\java\\utils\\DatasetExcel.xlsx");
		
		XSSFWorkbook book = new XSSFWorkbook(file);
		
		XSSFSheet sheet = book.getSheetAt(0);
		
		int rownum= sheet.getLastRowNum();
		
		int cellnum = sheet.getRow(0).getLastCellNum();
		
		System.out.println(rownum);
		
		System.out.println(cellnum);
		
		String data [][]  = new String[rownum][cellnum];

		for(int i=1;i<=rownum;i++)
		{
			XSSFRow row = sheet.getRow(i);
			
			for(int j=0;j<cellnum;j++)
			{
			String fulldata = row.getCell(j).getStringCellValue();
			
			data [i-1][j]  = fulldata;
			}
			
		}
		
		book.close();
		
		return data;

		
	}

}
