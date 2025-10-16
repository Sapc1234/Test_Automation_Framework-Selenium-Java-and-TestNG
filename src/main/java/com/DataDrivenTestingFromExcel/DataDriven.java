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

public class DataDriven

{

	public ArrayList<String> getData(String testCaseName) throws IOException

	{
		ArrayList<String> a = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\DataDemo.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		int sheets = wb.getNumberOfSheets();
		System.out.println("Total Number of sheets :" + sheets);

		// Identify the testcase column by scanning entire 1st row
		// Once column is identified then scan the entire testcase column to identify
		// the purchase testcase row
		// After grab the purchase test case row = pull all the data of that row and
		// feed into test

		for (int i = 0; i < sheets; i++)

		{
			if (wb.getSheetAt(i).getSheetName().equalsIgnoreCase("TestData"))

			{
				XSSFSheet sheet = wb.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();// sheet is collection of rows
				Row firstRow = rows.next();
				Iterator<Cell> ce = firstRow.cellIterator();// Row is collection of cells

				int k = 0;
				int column = 0;
				while (ce.hasNext())

				{
					Cell value = ce.next();
					if (value.getStringCellValue().equalsIgnoreCase("TestCase"))

					{
						column = k;// desired column
					}
					k++;
				}

				System.out.println(column);

				// once column id identified then scan the entire testcase column to identify
				// the purchase testcase row

				while (rows.hasNext())

				{
					Row r = rows.next();

					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName))

					{
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext())

						{

							// System.out.println(cv.next().getStringCellValue());
							// a.add(cv.next().getStringCellValue());
							Cell c = cv.next();

							if (c.getCellType() == CellType.STRING)

							{
								a.add(c.getStringCellValue());
							}

							else

							{
								// a.add(c.getNumericCellValue());
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));

							}

						}
					}

				}
				return a;

			}
		}
		return a;
	}

}
