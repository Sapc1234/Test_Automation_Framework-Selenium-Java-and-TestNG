package com.DataDrivenTestingFromExcel;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelDataProvider

{
	// multiple sets of data to our tests
	// Each and every data will be represented as array
	// 5 sets of data as 5 array frm data provider to your tests
	// the test will run 5 times separate sets of data(Arrays)

	DataFormatter formatter = new DataFormatter();

	@Test(dataProvider = "driveTest")

	public void testCaseData(String greeting, String communication, String id)

	{
		// when run this test ,this test should run the number of times is equals to the
		// number arrays defined in the DataProvider because we are linking this test.

		// when it recives data from the data provider first it will check how many
		// arrays are defined for example if 3 array defined this test will run three
		// different times.

		// For each time it will capture an array and it will map the values to its
		// arguments
		System.out.println(greeting + communication + id);
	}

	@DataProvider(name = "driveTest")

	public Object[][] getData() throws IOException

	{
		// Object[][] data = { { "Hello", "text", 1 }, { "bye", "message", 123 }, {
		// "solo", "call", 453 } };
		// return data;
		// every row of excel should be sent to one array
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\ExcelDatDemo.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);// This has a power to read ur excel sheet and get the data
		XSSFSheet sheet = wb.getSheetAt(0);

		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int colCount = row.getLastCellNum();
		Object[][] data = new Object[rowCount - 1][colCount];// we don't want  title

		for (int i = 0; i < rowCount - 1; i++)

		{

			row = sheet.getRow(i + 1);
			for (int j = 0; j < colCount; j++)

			{
				// System.out.println((row.getCell(j));
				XSSFCell cell = row.getCell(j);
				data[i][j] = formatter.formatCellValue(cell);

			}
		}

		return data;

	}

}
