package com.selenium.reports;

import java.io.File;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Border;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class DataSheet {

	public static Workbook wbook;
	public static WritableWorkbook wwbCopy;
	public static String ExecutedTestCasesSheet;
	public static WritableSheet shSheet;
//	static WritableWorkbook workbook;
	public static WritableCellFormat times;
	public static WritableCellFormat timesBoldUnderline;

	public DataSheet() {
		{
			try {
				//String fileName = "C:/Users/pawankumar_p/Desktop/Book3.xls";
			   //  wwbCopy = Workbook.createWorkbook(new File(fileName));
			  //   shSheet = wwbCopy.createSheet("Sheet1", 0);
				String filepath = "src/test/resources/Book1.xls";
				filepath = System.getProperty("user.dir") + "/" + filepath;
							
				wbook = Workbook.getWorkbook(new File(filepath));
				wwbCopy = Workbook.createWorkbook(new File(filepath), wbook);
				shSheet = wwbCopy.getSheet(0);
				createLabel();
				
				 
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}

	}

	private static void createLabel() throws WriteException {
		// Lets create a times font
			WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
		// Define the cell format
		times = new WritableCellFormat(times10pt);
		// Lets automatically wrap the cells
		times.setWrap(true);

		// create create a bold font with unterlines
		WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD, false,
				UnderlineStyle.SINGLE);
		timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
		// Lets automatically wrap the cells
		//timesBoldUnderline.setWrap(true);

		CellView cv = new CellView();
		cv.setFormat(times);
		cv.setFormat(timesBoldUnderline);
		//cv.setAutosize(true);

		// Write a few headers
		addCaption( 0, 0, "Test case No");
		addCaption( 1, 0, "Test case Name");
		addCaption( 2, 0, "Test case Desc");
		addCaption( 3, 0, "Test case Result");

	}

	@SuppressWarnings("deprecation")
	public void setValueIntoCell(String strSheetName, int iColumnNumber, int iRowNumber, String strData)
			throws WriteException {
		shSheet	= wwbCopy.getSheet(0);
		Label labTemp=null;
		 // Create cell font and format
	    WritableFont cellFont=null;
	    WritableCellFormat cellFormat =null;		
		try {
		    if(iColumnNumber==3) {
			    if(strData.equalsIgnoreCase("Fail")||strData.equalsIgnoreCase("Skip")) {
			    	cellFont = new WritableFont(WritableFont.TIMES,12,WritableFont.BOLD);
			    	cellFont.setColour(Colour.RED);	
			    	cellFormat = new WritableCellFormat(cellFont);	
			    	cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
				    // Create the label, specifying content and format
			    	labTemp = new Label(iColumnNumber, iRowNumber, strData,cellFormat);
			    }else if(strData.equalsIgnoreCase("Pass")) {
			    	cellFont = new WritableFont(WritableFont.TIMES, 12,WritableFont.BOLD);			    	
				    cellFont.setColour(Colour.GREEN);
				    cellFormat = new WritableCellFormat(cellFont);
				    cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
				    // Create the label, specifying content and format
			    	labTemp = new Label(iColumnNumber, iRowNumber,strData,cellFormat);
			    }
			    
		    }else{
		    	cellFont = new WritableFont(WritableFont.TIMES, 12);
		    	cellFont.setColour(Colour.BLACK);	
		    	cellFormat = new WritableCellFormat(cellFont);	
		    	cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		    	labTemp = new Label(iColumnNumber, iRowNumber, strData,cellFormat);
		    }
		    //add cell data with format to the sheet
			shSheet.addCell(labTemp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void addCaption(int column, int row, String s) throws RowsExceededException,	WriteException {
		Label label;
		label = new Label(column, row, s, timesBoldUnderline);
		shSheet.addCell(label);
	}
	
	
  // @AfterSuite(alwaysRun=true)
	public void closeFile() {
		try {
			System.out.println("after");
			// Closing the writable work book
			wwbCopy.write();
			wwbCopy.close();

			// Closing the original work book
			wbook.close();
		} catch (Exception e)

		{
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) 
	
}