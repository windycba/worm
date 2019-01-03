package com.wei.worm.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ImportExcel {
    private Sheet sheet;
    private Row row;

    /**
     * 读取Excel表格表头的内容
     */
    public String[] readExcelTitle(Workbook workbook) {
        sheet = workbook.getSheetAt(0);
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            title[i] = getCellFormatValue(row.getCell((short) i)).toString();
        }
        return title;
    }

    /**
     * 读取Excel数据内容
     * @return Map 包含单元格数据内容的Map对象
     */
    public List<Map<String, Object>> readExcelContent(Workbook workbook) {
        List<Map<String, Object>> content=new ArrayList<>();
        sheet = workbook.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
        String[] title=this.readExcelTitle(workbook);
        row = sheet.getRow(2);
        int colNum = row.getPhysicalNumberOfCells();
        for (int i = 2; i <= rowNum; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            row = sheet.getRow(i);
            for(int j = 0;j<colNum;j++){
                map.put(title[j],getCellFormatValue(row.getCell(j)));
            }
            content.add(map);
        }
        return content;
    }

    /**
     * 获取单元格数据内容为字符串类型的数据
     *
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:{
                System.out.println("string");strCell = cell.getStringCellValue();break;}
            case Cell.CELL_TYPE_NUMERIC: {
                System.out.println("num");
                strCell = String.valueOf(cell.getNumericCellValue());
                break;
            }
            case Cell.CELL_TYPE_BOOLEAN: {
                System.out.println("boo");
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            }
            case Cell.CELL_TYPE_BLANK:{ strCell = ""; break;}
            default: {System.out.println("kong");strCell = "";  break;}
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }

    /**
     * 根据HSSFCell类型设置数据
     * @param cell
     * @return
     */
    private Object getCellFormatValue(Cell cell) {
        Object cellvalue =null;
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: {
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellvalue = cell.getDateCellValue();

                    }else {
                        cellvalue=(int)cell.getNumericCellValue();
                    }

                    break;
                }
                case Cell.CELL_TYPE_FORMULA: {
                    if (DateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellvalue = sdf.format(date);
                    }else {
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                // 默认的Cell值
                default:
                    cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }
    public Workbook getWorkBook(String filePath) {
        FileInputStream fis =null;
        Workbook wookbook = null;
        try {
            fis = new FileInputStream(filePath);
            if(filePath.endsWith(".xls")) {
                wookbook = new HSSFWorkbook(fis);//2003版本的excel，用.xls结尾
            }else if(filePath.endsWith(".xlsx")){
                wookbook = new XSSFWorkbook(fis);//2007版本的excel，用.xlsx结尾
            }else {
                System.out.println("文件不是excel类型");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return wookbook;

    }

}
