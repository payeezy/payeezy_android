package com.example.payeezyclient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import android.content.Context;
import android.os.Environment;

//import au.com.bytecode.opencsv.*;


public class CustomFileReader2 
{
	 private String fileName = "";
	 
	 //private Workbook workbook = null;
	 private Sheet localSheet = null;
	 private int localRowCount = 0;
	 private int localColumnCount = 0;
	 private int currentRowNum = 0;
	 //private String[] row = new String[1];
	 private Context context = null;
	 
	 public CustomFileReader2(Context context) {
	 	// TODO Auto-generated constructor stub
	 	 this.context = context;
	 }
	 
	 public void LoadFile(String file)
	 {
		 fileName = file;
		 if(fileName == "" )
		 {
			 fileName = "testpayloads.xls";
		 }
		 String[][] resultSet = new String[1][1]; 
		 //File inputWorkbook = new File(fileName);
		 File inputWorkbook = null;//new File(fileName);
		 if(context != null)
		 {
			 //File localFileDir = Environment.getExternalStorageDirectory();
			 //File localFileDir2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
			 inputWorkbook =  new File(Environment.getExternalStorageDirectory(), fileName);
		 }
		 else
		 {
			 inputWorkbook = new File(fileName);
		 }
		 
		    if(inputWorkbook.exists()){
		        Workbook w;
		        try {
		            w = Workbook.getWorkbook(inputWorkbook);
		            // Get the first sheet
		            //Sheet sheet = w.getSheet(0);
		            Sheet sheet = w.getSheet(0);
		            localSheet = sheet;
		            int rowCount = sheet.getRows();
		            localRowCount = rowCount;
		            int colCount = sheet.getColumns();
		            localColumnCount = colCount;
		            //String[][] resultSet = new String[rowCount][colCount]; 
		            resultSet = new String[rowCount][colCount];
		            // Loop over column and lines
		            
		        } catch (BiffException e) {
		            e.printStackTrace();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		    else
		    {
		        resultSet[0][0] = "File not found..!";
		    }
		    if(resultSet.length == 0){
		        resultSet[0][0] = "Data not found..!";
		    }
		    //return resultSet;
	 }
	 
	 public String[] GetNextRow()
	 {
		 String[] row = null;
		 if(currentRowNum < localRowCount)
		 {
			 try
			 {
				 Cell[] rowcells = localSheet.getRow(currentRowNum);
				 row = new String[localColumnCount];
				 for(int i=0;i<rowcells.length;i++)
				 {
					 row[i] = rowcells[i].getContents();
				 }
				 currentRowNum++;
			 }
			 catch(Exception e)
			 {
				 System.out.println(e.getMessage());
			 }
		 }
		 return row;
	 }
	 
	 public Cell[] GetNextCellRow()
	 {
		 Cell[] row = null;
		 if(currentRowNum < localRowCount)
		 {
			 try
			 {
				 row = localSheet.getRow(currentRowNum);
				 currentRowNum++;
			 }
			 catch(Exception e)
			 {
				 System.out.println(e.getMessage());
			 }
		 }
		 return row;
	 }
	 
	 public void WriteToFile(String file, String result)
	 {
		 
		 try {
			 File targetFile = null;//new File(fileName);
			 if(context != null)
			 {
				 //File localFileDir = Environment.getExternalStorageDirectory();
				 //File localFileDir2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
				 targetFile =  new File(Environment.getExternalStorageDirectory(), file);
			 }
			 else
			 {
				 targetFile = new File(file);
			 }
			FileOutputStream stream = new FileOutputStream(targetFile,true);
			OutputStreamWriter bufferedWriter = new OutputStreamWriter( stream);
			bufferedWriter.append("\n");
			bufferedWriter.append(result);
			bufferedWriter.append("\n");
			stream.flush();
		   bufferedWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 
	 //public void LoadFile(String file)
	 public String[][] GetFileContents(String file)
	 {
		 fileName = file;
		 String[][] resultSet = new String[1][1]; 
		 //File inputWorkbook = new File(fileName);
		 File inputWorkbook = null;//new File(fileName);
		 if(context != null)
		 {
			 //File localFileDir = Environment.getExternalStorageDirectory();
			 //File localFileDir2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
			 inputWorkbook =  new File(Environment.getExternalStorageDirectory(), fileName);
		 }
		 else
		 {
			 inputWorkbook = new File(fileName);
		 }
		    if(inputWorkbook.exists()){
		        Workbook w;
		        try {
		            w = Workbook.getWorkbook(inputWorkbook);
		            // Get the first sheet
		            Sheet sheet = w.getSheet(0);
		            int rowCount = sheet.getRows();
		            int colCount = sheet.getColumns();
		            //String[][] resultSet = new String[rowCount][colCount]; 
		            resultSet = new String[rowCount][colCount];
		            // Loop over column and lines
		            for (int j = 0; j < sheet.getRows(); j++) {
		                //Cell cell = sheet.getCell(0, j);
		                //if(cell.getContents().equalsIgnoreCase(key)){
		                    for (int i = 0; i < sheet.getColumns(); i++) {
		                        Cell cel = sheet.getCell(i, j);
		                        //resultSet.add(cel.getContents());
		                        resultSet[j][i] = cel.getContents();
		                        
		                    }
		                //}
		                continue;
		            }
		        } catch (BiffException e) {
		            e.printStackTrace();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		    else
		    {
		        resultSet[0][0] = "File not found..!";
		    }
		    if(resultSet.length == 0){
		        resultSet[0][0] = "Data not found..!";
		    }
		    return resultSet;
	 }
	 
	 public void WriteToExcel(String file, String[] columndata)
	 {
		 try
		 {
			 fileName = file;
			 if(fileName == "" )
			 {
				 fileName = "resulttestpayloads.xls";
			 }
			 String[][] resultSet = new String[1][1]; 
			 //File inputWorkbook = new File(fileName);
			 File outputWorkbook = null;//new File(fileName);
			 Workbook inputworkbook = Workbook.getWorkbook(new File(Environment.getExternalStorageDirectory(), "newtestpayloads.xls")); 
			 WritableWorkbook workbook = null;
			 if(context != null)
			 {
				 //File localFileDir = Environment.getExternalStorageDirectory();
				 //File localFileDir2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
				 outputWorkbook =  new File(Environment.getExternalStorageDirectory(), fileName);
			 }
			 else
			 {
				 outputWorkbook = new File(Environment.getExternalStorageDirectory(), fileName);
			 }
			 
			 if(!outputWorkbook.exists())
			 {
				 //WorkbookSettings ws = new WorkbookSettings();
				 //ws.setWriteAccess("rw");
				 //Workbook.createWorkbook(inputWorkbook);
				 workbook = (WritableWorkbook )Workbook.createWorkbook(outputWorkbook, inputworkbook );
				 
			 }
			 else
			 {
				 workbook = (WritableWorkbook )Workbook.createWorkbook(outputWorkbook, inputworkbook );
			 }
			 
				 
		     if(outputWorkbook.exists())
		     {
			    	WritableWorkbook w;
	        
		            //w = (WritableWorkbook) Workbook.getWorkbook(outputWorkbook);
		        	w = workbook ;
		            // Get the first sheet
		            //Sheet sheet = w.getSheet(0);
		            WritableSheet sheet = (WritableSheet)w.getSheet(0);
		            localSheet = sheet;
		            int rowCount = sheet.getRows();
		            localRowCount = rowCount;
		            int colCount = sheet.getColumns();
		            localColumnCount = colCount;
		            //String[][] resultSet = new String[rowCount][colCount]; 
		            resultSet = new String[rowCount][colCount];
		            // Loop over column and lines
		            int currentRow = 1;
		            int currentCol = colCount ;
		            //sheet.insertRow(currentRow);
		            sheet.insertColumn(currentCol);
		            for(int i=0;i<columndata.length;i++)
		            {
		            	currentRow = i+1;
			            //WritableCell cell = sheet.getWritableCell(currentRow, currentCol);
			            Label label = new Label( currentCol, currentRow, columndata[i]);
			            sheet.addCell(label);
		            }
		            
		        	
			    }
			    else
			    {
			        resultSet[0][0] = "File not found..!";
			    }
			    if(resultSet.length == 0){
			        resultSet[0][0] = "Data not found..!";
			    }
			workbook.write();
			workbook.close();
		    //return resultSet;
			}	
		 	catch (BiffException e) {
	            e.printStackTrace();
	        }
		    catch (Exception e) {
	            e.printStackTrace();
	        }
	 }
	
	 public List<String> read(String key) throws IOException  {
		    List<String> resultSet = new ArrayList<String>();
		    String inputFile = key; //"payloads.xls";
		    File inputWorkbook = new File(inputFile);
		    if(inputWorkbook.exists()){
		        Workbook w;
		        try {
		            w = Workbook.getWorkbook(inputWorkbook);
		            // Get the first sheet
		            Sheet sheet = w.getSheet(0);
		            // Loop over column and lines
		            for (int j = 0; j < sheet.getRows(); j++) {
		                Cell cell = sheet.getCell(0, j);
		                if(cell.getContents().equalsIgnoreCase(key)){
		                    for (int i = 0; i < sheet.getColumns(); i++) {
		                        Cell cel = sheet.getCell(i, j);
		                        resultSet.add(cel.getContents());
		                    }
		                }
		                continue;
		            }
		        } catch (BiffException e) {
		            e.printStackTrace();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		    else
		    {
		        resultSet.add("File not found..!");
		    }
		    if(resultSet.size()==0){
		        resultSet.add("Data not found..!");
		    }
		    return resultSet;
		}
		
}

