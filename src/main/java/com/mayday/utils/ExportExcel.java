package com.mayday.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rio-Lee on 2016/7/3.
 * 导出Excel工具
 */
public class ExportExcel {

    private static final String JSON_STR = "\\{(\\w+:.+)(,\\w+:.+)*\\}";

    private Workbook workbook;

    private String filePath;


    private Map<String, String> dataHeadMap = new HashMap<>();

    private Map<String, Integer> headIndexMap = new HashMap<>();

    private List<Map<String, Object>> currentData = new ArrayList<>();



    public List<Map<String, Object>> getCurrentData() {
        return currentData;
    }


    private String searchKeyByValue(String val) {
        for (Map.Entry<String, String> data : dataHeadMap.entrySet()) {
            if (data.getValue().equals(val)) return data.getKey();
        }
        return null;
    }

    private String searchKeyByIndex(int i){
        for(Map.Entry<String,Integer> entry:headIndexMap.entrySet()){
            if(i==entry.getValue())return entry.getKey();
        }
        return null;
    }



    public List<Map<String, Object>> readExcel() throws IOException {
        List<Map<String, Object>> result = new ArrayList<>();
        Workbook read;
        if (filePath.toLowerCase().endsWith(".xls")) {
            read = new HSSFWorkbook(new FileInputStream(filePath));
//            read = new XSSFWorkbook(new FileInputStream(filePath));
        } else if (filePath.toLowerCase().endsWith("xlsx")) {
            read = new XSSFWorkbook(new FileInputStream(filePath));
        } else {
            throw new IOException();
        }
        Sheet sheet = read.getSheetAt(0);
        Row headRow = sheet.getRow(0);
        String[] heads = new String[headRow.getLastCellNum()];
        for (int i = 0, l = headRow.getLastCellNum(); i < l; i++) {
            String ch = headRow.getCell(i).getStringCellValue();
//            String en = searchKeyByValue(ch);
            String en = searchKeyByIndex(i);
            heads[i] = en == null ? String.valueOf(i) : en;
        }
        for (int row = 1, len = sheet.getLastRowNum(); row <= len; row++) {
            Map<String, Object> item = new HashMap<>();
            Row currentRow = sheet.getRow(row);
            if(currentRow==null)continue;
            Cell tempCell = currentRow.getCell(1);
            //if (tempCell == null || "".equals(tempCell.getStringCellValue().trim())) break;
            for (int col = 0, last = heads.length; col < last; col++) {
                Cell currentCell = currentRow.getCell(col);
                if (currentCell == null) {
                    String currKey = heads[col];
                    item.put(currKey, null);
                    continue;
                }
//                currentRow.getCell(col).setCellType(XSSFCell.CELL_TYPE_STRING);
                String currValue = castDateString(currentRow.getCell(col));
                String currKey = heads[col];
                item.put(currKey, currValue);
            }
            result.add(item);
        }
        return result;
    }

    private String castDateString(Cell cell){
        DecimalFormat df = new DecimalFormat("#");
        if(cell == null){
            return "";
        }
        switch (cell.getCellType()){
            case HSSFCell.CELL_TYPE_NUMERIC:
                if(HSSFDateUtil.isCellDateFormatted(cell)){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                }
                return df.format(cell.getNumericCellValue());
            case HSSFCell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case HSSFCell.CELL_TYPE_FORMULA:
                return cell.getCellFormula();
            case HSSFCell.CELL_TYPE_BLANK:
                return "";

        }
        return "";

    }


    public void writeCurrentData() throws IOException {
        flushCurrentDataToWorkbook();
        int index = filePath.lastIndexOf("/");
        File dir = new File(filePath.substring(0, index));
        if (!dir.exists()) dir.mkdirs();
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
    }




    private void flushCurrentDataToWorkbook() {
        Sheet sheet = workbook.createSheet();
        Row headRow = sheet.createRow(0);
        for (Map.Entry<String, String> head : dataHeadMap.entrySet()) {
            Integer cellIndex = headIndexMap.get(head.getKey());
            if (cellIndex != null) {
                Cell headCell = headRow.createCell(cellIndex);
                headCell.setCellType(Cell.CELL_TYPE_STRING);
                headCell.setCellValue(head.getValue());
            }
        }
        for (int row = 0, len = currentData.size(); row < len; row++) {
            Row contentRow = sheet.createRow(row + 1);
            for (Map.Entry<String, Object> curr : currentData.get(row).entrySet()) {
                int cellIndex = Integer.valueOf(curr.getKey());
                String val = curr.getValue() == null ? "" : curr.getValue().toString().trim();
                Cell contentCell = contentRow.createCell(cellIndex);
                contentCell.setCellValue(val);
            }
        }
    }




    public void setCurrentData(List<Map<String, Object>> datas) {
        currentData.clear();
        for (Map<String, Object> data : datas) {
            Map<String, Object> tempCurrentItem = new HashMap<>();
            for (Map.Entry<String, Integer> headIndex : headIndexMap.entrySet()) {
                String key = headIndex.getKey();
                Integer idx = headIndex.getValue();
                Object value = data.get(key);
                tempCurrentItem.put(idx.toString(),value==null?"":value);
            }
            currentData.add(tempCurrentItem);
        }
    }









    private ExportExcel(String filePath, String headConfig) throws IOException {
        if (filePath != null && filePath.toLowerCase().endsWith("xlsx")) {
            this.workbook = new XSSFWorkbook();
        } else if (filePath != null && filePath.toLowerCase().endsWith(".xlsx")) {
//            this.workbook = new HSSFWorkbook();
            this.workbook = new XSSFWorkbook();
        } else {
            throw new IOException();
        }
        this.filePath = filePath;
        if (headConfig != null && headConfig.matches(JSON_STR)) {
            headConfig = headConfig.substring(1, headConfig.length() - 1);
            String[] items = headConfig.split(",");
            for (int i = 0, len = items.length; i < len; i++) {
                String ch = items[i].split(":")[1];
                String en = items[i].split(":")[0];
                headIndexMap.put(en, i);
                dataHeadMap.put(en, ch);
            }
        }
    }

    public static ExportExcel createExportExcel(String filePath, String headConfig) throws IOException {
        return new ExportExcel(filePath, headConfig);
    }


    public void createExportExcel(String filePath) throws IOException {
        if (filePath != null && filePath.toLowerCase().endsWith("xlsx")) {
            this.workbook = new XSSFWorkbook();
        } else if (filePath != null && filePath.toLowerCase().endsWith(".xlsx")) {
//            this.workbook = new HSSFWorkbook();
            this.workbook = new XSSFWorkbook();
        } else {
            throw new IOException();
        }
        this.filePath = filePath;
    }

}
