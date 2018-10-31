package com.folksam.selenium.infrastructure.handler;


import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

/**
 * Data handler contains methods to get the data and set the data from/to test data sheet
 *
 * @author KSGA03
 */
public class ExcelDataHandler {

    private   XSSFSheet ExcelWSheet;
    private   XSSFWorkbook ExcelWBook;
    private    XSSFWorkbook ExcelWBookResult;
    private   XSSFCell Cell;
    private   XSSFRow Row;
    private   String filePath = null;
    private  String resultFilePath = null;
    private FileInputStream inputStream;
    private FileInputStream resultInputStream;

    private  int currentRow;
    private  int currentColumn;
    
    /**
     * Constructor to load Excel File
     * @param filePath
     * @throws Exception
     */
    public ExcelDataHandler(String filePath, String resultFilePath) throws Exception {
        try {

            this.filePath = filePath;
            this.resultFilePath = resultFilePath;

            // Open the Excel file
            inputStream = new FileInputStream(new File(filePath));


            // Access the required test data excel file
            ExcelWBook = new XSSFWorkbook(inputStream);


        } catch (FileNotFoundException e){
            throw (e);
        }


    }

    public ExcelDataHandler() {
		// TODO Auto-generated constructor stub
	}

	/**
     * Set what file would be used for data input
     * @param filePath
     * @throws Exception
     */
    public void setDataSourceFile(String filePath) throws Exception {
        try {

            this.filePath = filePath;
 
            // Open the Excel file
            inputStream = new FileInputStream(new File(filePath));

            // Access the required test data excel file
            ExcelWBook = new XSSFWorkbook(inputStream);


        } catch (FileNotFoundException e){
            throw (e);
        }

    }
    
    /**
     * Set what file would be used for data input
     * @param filePath
     * @throws Exception
     */
    public void setTestResultsFile(String filePath) throws Exception {
        try {

            this.resultFilePath = filePath;
 
            // Open the Excel file
            resultInputStream = new FileInputStream(new File(filePath));

            // Access the required test data excel file
            ExcelWBookResult = new XSSFWorkbook(resultInputStream);


        } catch (FileNotFoundException e){
            throw (e);
        }

    }
    
    
    
    
    /**
     * Method to get the data from excel
     * @param sheetName
     * @param rowNo
     * @param ColumnHeader
     * @return
     */
    public  String getCellData(String sheetName, int rowNo,String  ColumnHeader) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        DataFormatter formatter= new DataFormatter();
        FormulaEvaluator formulaEvaluator= ExcelWBook.getCreationHelper().createFormulaEvaluator();

        try{

            ExcelWSheet = ExcelWBook.getSheet(sheetName);
            currentRow = rowNo;

            currentColumn=getColumnNoFromHeader(ExcelWSheet,sheetName,ColumnHeader);
            Cell = ExcelWSheet.getRow(currentRow).getCell(currentColumn);
            String formatstring = Cell.getCellStyle().getDataFormatString();

            switch(Cell.getCellType()) {
                case XSSFCell.CELL_TYPE_BLANK : return "";
                case XSSFCell.CELL_TYPE_NUMERIC :
                    if (DateUtil.isCellDateFormatted(Cell) && formatstring.equals("m/d/yy")) {
                        return (sdf.format(Cell.getDateCellValue()));
                    }
                    else if(DateUtil.isCellDateFormatted(Cell) && formatstring.equals("h:mm")){
                        return (time.format(Cell.getDateCellValue()));
                    }
                    else
                    {

                        String val = formatter.formatCellValue(Cell);
                        return val;

                    }
                case XSSFCell.CELL_TYPE_BOOLEAN:
                    return Boolean.toString(Cell.getBooleanCellValue());
                case XSSFCell.CELL_TYPE_ERROR:
                    return "";
                case XSSFCell.CELL_TYPE_FORMULA:
                    return formatter.formatCellValue(Cell,formulaEvaluator);
                case XSSFCell.CELL_TYPE_STRING:
                    if (Cell.getStringCellValue().length() >= 1) {
                        return (Cell.getStringCellValue());
                    } else {
                        return "";
                    }
                default:
                    return "";

            }

        }catch (Exception e){
            return"";

        }
    }

    /**
     * Method to get the data from excel by providing row and column number
     * @param sheetName
     * @param rowNo
     * @param ColumnNo
     * @return
     */
    public  String getCellData(String sheetName, int rowNo,int  ColumnNo) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        DataFormatter formatter= new DataFormatter();
        FormulaEvaluator formulaEvaluator= ExcelWBook.getCreationHelper().createFormulaEvaluator();

        try{

            ExcelWSheet = ExcelWBook.getSheet(sheetName);
            currentRow = rowNo;

            currentColumn=ColumnNo;
            Cell = ExcelWSheet.getRow(currentRow).getCell(currentColumn);
            String formatstring = Cell.getCellStyle().getDataFormatString();

            switch(Cell.getCellType()) {
                case XSSFCell.CELL_TYPE_BLANK : return "";
                case XSSFCell.CELL_TYPE_NUMERIC :
                    if (DateUtil.isCellDateFormatted(Cell) && formatstring.equals("m/d/yy")) {
                        return (sdf.format(Cell.getDateCellValue()));
                    }
                    else if(DateUtil.isCellDateFormatted(Cell) && formatstring.equals("h:mm")){
                        return (time.format(Cell.getDateCellValue()));
                    }
                    else
                    {
                        String val = formatter.formatCellValue(Cell);
                        return val;

                    }
                case XSSFCell.CELL_TYPE_BOOLEAN:
                    return Boolean.toString(Cell.getBooleanCellValue());
                case XSSFCell.CELL_TYPE_ERROR:
                    return "";
                case XSSFCell.CELL_TYPE_FORMULA:
                    return formatter.formatCellValue(Cell,formulaEvaluator);
                case XSSFCell.CELL_TYPE_STRING:
                    if (Cell.getStringCellValue().length() >= 1) {
                        return (Cell.getStringCellValue());
                    } else {
                        return "";
                    }
                default:
                    return "";

            }

        }catch (Exception e){
            return"";

        }
    }


    /**
     * Method to set the values to Cell in excel
     * @param sheetName
     * @param RowNum
     * @param ColHeader
     * @param Result
     * @throws Exception
     */
    public  void setCellData(String sheetName,int RowNum, String ColHeader, String Result) throws Exception	{

        try{
            resultInputStream = new FileInputStream(new File(resultFilePath));
            ExcelWBookResult = new XSSFWorkbook(resultInputStream);

            ExcelWSheet = ExcelWBookResult.getSheet(sheetName);
            if ( ExcelWSheet.getRow(RowNum) == null) {
                Row = ExcelWSheet.createRow(RowNum);
            }else {
                Row  = ExcelWSheet.getRow(RowNum);
            }
            int ColumnNo = getColumnNoFromHeader(ExcelWSheet,sheetName,ColHeader);
            Cell = Row.getCell(ColumnNo, Row.RETURN_BLANK_AS_NULL);
            if (Cell == null) {
                Cell = Row.createCell(ColumnNo);
                Cell.setCellValue(Result);
            } else {
                Cell.setCellValue(Result);
            }

            FileOutputStream fileOut = new FileOutputStream(new File(resultFilePath));

            ExcelWBookResult.write(fileOut);
            fileOut.flush();
            fileOut.close();

        }catch(Exception e){
            throw (e);

        }
    }

    /**
     * Method to set the values to Cell in excel by providing row number and cloumn number
     * @param sheetName
     * @param RowNum
     * @param ColumnNo
     * @param Result
     * @throws Exception
     */
    public  void setCellData(String sheetName,int RowNum, int ColumnNo, String Result) throws Exception	{

        try{
            resultInputStream = new FileInputStream(new File(resultFilePath));
            ExcelWBookResult = new XSSFWorkbook(resultInputStream);

            ExcelWSheet = ExcelWBookResult.getSheet(sheetName);
            if ( ExcelWSheet.getRow(RowNum) == null) {
                Row = ExcelWSheet.createRow(RowNum);
            }else {
                Row  = ExcelWSheet.getRow(RowNum);
            }
            Cell = Row.getCell(ColumnNo, Row.RETURN_BLANK_AS_NULL);
            if (Cell == null) {
                Cell = Row.createCell(ColumnNo);
                Cell.setCellValue(Result);
            } else {
                Cell.setCellValue(Result);
            }

            FileOutputStream fileOut = new FileOutputStream(new File(resultFilePath));

            ExcelWBookResult.write(fileOut);
            fileOut.flush();
            fileOut.close();

        }catch(Exception e){
            throw (e);

        }
    }



    /**
     * Method to get the column number from column header
     * @param SheetName
     * @param ColumnHeader
     * @return
     */

    public  int getColumnNoFromHeader(XSSFSheet ExcelWSheet,String SheetName,String ColumnHeader){


        int columnsCount = ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
        for (int i=0; i<columnsCount;i++)
        {
            if(ExcelWSheet.getRow(0).getCell(i).getStringCellValue().equalsIgnoreCase(ColumnHeader))
            {
                currentColumn = i;
                return currentColumn;
            }
        }

        return columnsCount;
    }

    /**
     * Method to get the Number of rows in a sheet
     * @param SheetName
     * @return
     */
    public int getRowCount(String SheetName){
        ExcelWSheet = ExcelWBook.getSheet(SheetName);

        for (int i=0; i <ExcelWSheet.getLastRowNum()+1; i++ )
        {
            if (ExcelWSheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase("ENDOFDATA"))
            {
                return i;
            }
        }
        return (ExcelWSheet.getLastRowNum()+1);
    }



}
