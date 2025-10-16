package com.DataDrivenTestingFromExcel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelDataDriven

{
	@Test(invocationCount = 1)

	public void exceltestDataLoader() throws IOException

	{
		// Create object for XSSFWorkbook class
		// Get access of sheet
		// Get access of all the rows of sheet
		// Access to specific row from all rows
		// Get Access to all cells of Row
		// Access the Data from excel into Arrays

		// Identify the testcase column by scanning the entire 1st row
		// Once column identified then scan entire testcase column to identify purchase
		// testCase row
		// After you grab purchase testCase row = pull all the data of that row and feed
		// into test

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\DataDemo.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);// it will accept file input stream object
		int sheets = workbook.getNumberOfSheets();
		System.out.println("Toatal Number of sheets :" + sheets);

		for (int i = 0; i < sheets; i++)

		{
			if (workbook.getSheetName(i).equalsIgnoreCase("TestData"))

			{

				XSSFSheet sheet = workbook.getSheetAt(i);
				// Identify the testcase column by scanning the entire 1st row
				Iterator<Row> rows = sheet.iterator();// sheet is collection of rows
				Row firstRow = rows.next();
				Iterator<Cell> ce = firstRow.cellIterator();// row is a collection of cells

				int k = 0;
				int column = 0;
				while (ce.hasNext())

				{
					Cell value = ce.next();

					if (value.getStringCellValue().equalsIgnoreCase("TestCase"))

					{
						// desired column
						column = k;

					}

					k++;
				}

				System.out.println(column);

				// Once column identified then scan entire testcase column to identify purchase
				// testCase row

				while (rows.hasNext())

				{
					Row r = rows.next();

					if (r.getCell(column).getStringCellValue().equalsIgnoreCase("Add Profile"))

					{
						// After you grab purchase testCase row = pull all the data of that row and feed
						// into test

						Iterator<Cell> cv = r.cellIterator();

						while (cv.hasNext())

						{
							// System.out.println(cv.next().getStringCellValue());

							Cell c = cv.next();

							if (c.getCellType() == CellType.STRING)

							{
								System.out.println(c.getStringCellValue());
							}

							else

							{
								System.out.println(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
						}
					}
				}

			}
		}
	}

	@Test

	public void excelDataReader() throws IOException

	{

		DataDriven d = new DataDriven();
		ArrayList data = d.getData("Add Profile");

		System.out.println(data.get(0));
		System.out.println(data.get(1));
		System.out.println(data.get(2));
		System.out.println(data.get(3));

		// driver.findElement(By.xpath("locator")).sendkeys(data.get(1));
	}

}
