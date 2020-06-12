package com.selenium.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ReadExcelRows {

	public void abc(String sheet, String env, String columnName) {

		File src = new File("C:\\Users\\PawanPadma\\Desktop\\Book1.xlsx");

		XSSFWorkbook wb = null;
		try {

			wb = new XSSFWorkbook(src);
			XSSFSheet sh1 = null;
			sh1 = wb.getSheet(sheet);

			Row r = sh1.getRow(0);
			int patchColumn = -1;
			for (int cn = 0; cn < r.getLastCellNum(); cn++) {
				Cell c = r.getCell(cn);
				if (c.getStringCellValue().equals(columnName)) {
					if (env.equals("qa")) {
						patchColumn = cn;
						System.out.println("value is  " + patchColumn);
						String cellvalue = sh1.getRow(c.getRowIndex() + 1).getCell(c.getColumnIndex())
								.getStringCellValue();
						System.out.println("cell value is   " + cellvalue);
						break;
					} else if (env.equals("dev")) {
						patchColumn = cn;
						System.out.println("value is  " + patchColumn);
						String cellvalue = sh1.getRow(c.getRowIndex() + 2).getCell(c.getColumnIndex())
								.getStringCellValue();
						System.out.println("cell value is   " + cellvalue);
						break;
					}
				}

			}
			if (patchColumn == -1) {
				try {
					throw new Exception("None of the cells in the first row were found");
				} catch (Exception e) {

					e.printStackTrace();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void vvv() {
		abc("test", "dev", "variable");
	}
}
