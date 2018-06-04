package pizzaOrdering;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *  @author Karthika
 * @param filePath
 * @param fileName
 * @param sheetName
 * @param dataToWrite
 * @return void
 * @throws IOException
 */
public class UpdateValidMenuInXls {
	public void writeToExcel(String filePath,String fileName,String sheetName,String key,Set<String> dataToWrite)throws IOException{

		File file=new File(filePath+"\\"+fileName);

		FileInputStream inputStream=new FileInputStream(file);

		String fileExtension=fileName.substring(fileName.indexOf("."));

		
		Workbook kartzWorkBook=null;
		if(fileExtension.equals(".xlsx")){

			kartzWorkBook=new XSSFWorkbook(inputStream);
		}
		else if(fileExtension.equals(".xls")){
			kartzWorkBook=new HSSFWorkbook(inputStream);
		}
		Sheet kartzSheet=kartzWorkBook.getSheet(sheetName);

		int rowCount=kartzSheet.getLastRowNum();
		for(int rowIterator=0;rowIterator<rowCount;rowIterator++){
			System.out.println("Inside Remove Rows");
			kartzSheet.removeRow(kartzSheet.getRow(rowIterator));
		}
				inputStream.close();
		int inputs=dataToWrite.size();

		for(int rowNum=0;rowNum<=inputs;rowNum++){
			System.out.println("B4 creatttte row");
			Row kartzRow=kartzSheet.createRow(rowNum);
			System.out.println("Aftr creatttte row");
			Cell kartzCell=kartzRow.createCell(0);
			if(rowNum==0){

				System.out.println("0 row"+key);
				kartzCell.setCellValue(key);
			}

			else{	
				System.out.println(" row");
				Object[] value=dataToWrite.toArray();
				System.out.println("Inside update xls Key is " +value[rowNum-1].toString());
				kartzCell.setCellValue(value[rowNum-1].toString());

			}	
		}
		

		FileOutputStream outputStream=new FileOutputStream(file);
		kartzWorkBook.write(outputStream);
		outputStream.close();
	}

}


