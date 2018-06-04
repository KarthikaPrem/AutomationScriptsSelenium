package pizzaOrdering;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class GetMenuListFromXLS {
	/**
	 * This function is used to read data from xls.Here we are taking the items ordered in the menu xls
	 * @param args Xls filepath and filename
	 * @throws IOException
	 * @returnType  map(key(Item types),value(ItemName ArrayList))
	 */
	public static void main(String[] args) throws IOException	{
		Map<String, ArrayList<String>> itemsOrderedMain=new LinkedHashMap<String, ArrayList<String>>();
		GetMenuListFromXLS objReader=new GetMenuListFromXLS();
		String filePath="C:\\Users\\Sreejith\\Selenium";
		itemsOrderedMain=objReader.readExcel(filePath,"Menu.xlsx");
		for(String items:itemsOrderedMain.keySet()){System.out.println("Keys sets "+items);}


	}	


	public Map<String, ArrayList<String>> readExcel(String filePath,String fileName) throws IOException{
		Map<String, ArrayList<String>> itemsOrdered=new LinkedHashMap<String, ArrayList<String>>();

		//List<String> keys=null;
		ArrayList<ArrayList<String>> menuItems=new ArrayList<ArrayList<String>>();

		File file=new File(filePath+"\\"+fileName);

		FileInputStream inputStream=new FileInputStream(file);

		Workbook kartzWorkbook=null;
		String fileExtensionName=fileName.substring(fileName.indexOf("."));
		if( fileExtensionName.equals(".xlsx")){

			kartzWorkbook=new XSSFWorkbook(inputStream);
		}
		else if (fileExtensionName.equals(".xls")){
			kartzWorkbook=new HSSFWorkbook(inputStream);
		}
		//Sheet kartzSheet=kartzWorkbook.getSheet(sheetName);

		int sheetNumbers=kartzWorkbook.getNumberOfSheets();
		for(int sheetNameIterator=0;sheetNameIterator<sheetNumbers;sheetNameIterator++){
			menuItems.add(new ArrayList<String>());
			String sheetsName=kartzWorkbook.getSheetName(sheetNameIterator);
			//keys.add(sheetNameIterator, sheetsName);
			Sheet kartzNewSheet=kartzWorkbook.getSheet(sheetsName);
			int totalRowCount=kartzNewSheet.getLastRowNum()-kartzNewSheet.getFirstRowNum();
			for(int rowCountIterator=1;rowCountIterator<=totalRowCount;rowCountIterator++){
				Row row=kartzNewSheet.getRow(rowCountIterator);
				if(!(row.getZeroHeight())){	
					String ItemName=row.getCell(0).toString();

					if(ItemName!=null){
						menuItems.get(sheetNameIterator).add(ItemName);
					}
					itemsOrdered.put(sheetsName, menuItems.get(sheetNameIterator));
				}
			}

		}
		return itemsOrdered;

	}
}
