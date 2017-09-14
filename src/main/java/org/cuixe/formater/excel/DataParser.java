package org.cuixe.formater.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cuixe.formater.excel.vo.Field;
import org.cuixe.formater.excel.vo.Format;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataParser {

    private LayoutParser layoutParser;
    private XSSFWorkbook workbook = new XSSFWorkbook();
    private Map<String, XSSFSheet> sheets = new HashMap<>();
    private String fileName;
    private String destination;

    public DataParser(String fileName, String destination) {
        this.fileName = fileName;
        this.destination = destination;
    }

    public void parse() throws Exception {
        FileReader reader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(reader);
        bufferedReader.lines().forEach(this::parseLine);
        try (FileOutputStream outputStream = new FileOutputStream(destination)) {
            workbook.write(outputStream);
        }
    }

    private void parseLine(String line) {
        String format = line.substring(0, 4);
        System.out.println("FORMAT: "  + format);
        if(!layoutParser.getMapFormats().containsKey(format)) {
            System.out.println("NO ENCONTRADO");
            return;
        }
        Format format1 = layoutParser.getMapFormats().get(format);
        XSSFSheet sheet;
        if(!sheets.containsKey(format)) {
            createNewSheet(format, format1);
        }
        sheet = sheets.get(format);

        List<Field> fieldList = format1.getFields();
        int index = 1;
        XSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
        for(Field field : fieldList) {
            int init = field.getInit() - 1;
            int end = (field.getInit()-1) + field.getSize();
            if (fieldList.size() == index) {
                if (line.length() < end) {
                    end = line.length()-1;
                }
            }
            String data = line.substring(init, end);
            XSSFCell cell = row.createCell(index-1);
            cell.setCellValue(data);
            index++;
        }
    }

    private void createNewSheet(String format, Format format1) {
        XSSFSheet sheet;
        sheet = workbook.createSheet(format);
        sheets.put(format, sheet);

        XSSFRow row = sheet.createRow(0);
        int cellIndex = 0;
        for(Field field : format1.getFields()) {
            Cell cell = row.createCell(cellIndex++);
            cell.setCellValue(field.getName());
        }
    }

    public void setLayoutParser(LayoutParser layoutParser) {
        this.layoutParser = layoutParser;
    }
}
