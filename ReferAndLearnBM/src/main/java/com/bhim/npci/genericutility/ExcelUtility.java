package com.bhim.npci.genericutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import io.appium.java_client.android.AndroidDriver;

/**
 * @author Priyanka 
 * This class is used to perform all excel related operations
 */
public class ExcelUtility{
	
	/**
	 * This method is used to read data from excel workBook.
	 * @author Priyanka
	 * @param SheetName
	 * @param rowNum
	 * @param cellNum
	 * @return
	 * @throws Throwable
	 */
	public String readDataFromExcel(String SheetName, int rowNum, int cellNum) throws Throwable {
		FileInputStream fis = new FileInputStream(PathConstants.testDataFilePath);
		Workbook workBook = WorkbookFactory.create(fis);
		Sheet sheet = workBook.getSheet(SheetName);
		Row row = sheet.getRow(rowNum);
		Cell cell = row.getCell(cellNum);
		String cellValue = cell.getStringCellValue();
		return cellValue;
	}

	/**
	 * This method is used to fetch the data from the excel based on key
	 * @author Priyanka
	 * @param sheetName
	 * @param requiredKey
	 * @return
	 * @throws IOException
	 * @throws EncryptedDocumentException
	 */
	public String getDataFromExcel(String sheetName, String testcaseName, String requiredKey)
			 {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(PathConstants.testDataFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Workbook workBook = null;
		try {
			workBook = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheet = workBook.getSheet(sheetName);
		String value = "";
		String actualTestCaseName = "";
		String actualKey = "";
		boolean flag = false;
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			try {
				actualTestCaseName = sheet.getRow(i).getCell(0).getStringCellValue();
			} catch (Exception e) {
			}
			if (actualTestCaseName.equalsIgnoreCase(testcaseName)) {
				for (int j = 1; j <= sheet.getRow(i).getLastCellNum(); j++) {
					try {
						actualKey = sheet.getRow(i-1).getCell(j).getStringCellValue();
					} catch (Exception e) {
					}
					if (actualKey.equalsIgnoreCase(requiredKey)) {
						try {
							value = sheet.getRow(i).getCell(j).toString();
						} catch (Exception e) {
						}
						flag = true;
						break;
					}
				}
			}
			if (flag) {
				break;
			}
		}
		try {
			workBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * @author Priyanka
	 * This method is used to insert the data into the excel based on key
	 * @param sheetName
	 * @param testcaseName
	 * @param requiredKey
	 * @param value
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void setDataIntoExcel(String sheetName, String testcaseName, String requiredKey, String value)
		 {
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(PathConstants.testDataFilePath);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		Workbook workBook=null;
		try {
			workBook = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException | IOException e) {
			
			e.printStackTrace();
		}
		Sheet sheet = workBook.getSheet(sheetName);
		String actualTestCaseName = "";
		String actualKey = "";
		boolean flag = false;
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			try {
				actualTestCaseName = sheet.getRow(i).getCell(0).getStringCellValue();
			} catch (Exception e) {
			}
			if (actualTestCaseName.equalsIgnoreCase(testcaseName)) {
				for (int j = 1; j <= sheet.getRow(i).getLastCellNum(); j++) {
					try {
						actualKey = sheet.getRow(i - 1).getCell(j).getStringCellValue();
					} catch (Exception e) {
					}
					if (actualKey.equalsIgnoreCase(requiredKey)) {
						try {
							sheet.getRow(i).createCell(j).setCellValue(value);
						} catch (Exception e) {
						}
						flag = true;
						break;
					}
				}
			}
			if (flag) {
				break;
			}
		}
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(PathConstants.testDataFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			workBook.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			workBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
