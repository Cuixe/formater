package org.cuixe.formater.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.cuixe.formater.excel.vo.Field;
import org.cuixe.formater.excel.vo.Format;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class LayoutParser {

    private final int INDEX = 1;
    private final int POSITION = 2;
    private final int FIELD_NAME = 3;
    private final int FORMAT_ROW = 4;

    private List<Format> formats = new ArrayList<>();
    private Map<String, Format> mapFormats = new HashMap<>();

    public LayoutParser(String excelFile) throws Exception {
        FileInputStream inputStream = new FileInputStream(new File(excelFile));
        Workbook workbook = new XSSFWorkbook(inputStream);
        workbook.sheetIterator().forEachRemaining(this::processSheet);
        workbook.close();
        inputStream.close();
    }

    private void processSheet(Sheet sheet) {
        sheet.getSheetName();
        Iterator<Row> iterator = sheet.iterator();
        int index = 0;
        Format format = new Format();
        format.setType(sheet.getSheetName());
        formats.add(format);
        mapFormats.put(format.getType(), format);
        while (iterator.hasNext()) {
            Row row = iterator.next();
            if (index == 0) {
                index++;
                continue;
            }
            parseRow(row, format);
        }
    }

    private void  parseRow(Row row, Format format) {
        Field field = new Field();
        int index = 1;
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            switch (index) {
                case INDEX:
                    int value = ((Double)cell.getNumericCellValue()).intValue();
                    field.setIndex(value);
                    break;
                case POSITION:
                    int position = ((Double)cell.getNumericCellValue()).intValue();
                    field.setInit(position);
                    break;
                case FIELD_NAME:
                    String value2 = cell.getStringCellValue();
                    field.setName(value2);
                    break;
                case FORMAT_ROW:
                    String value3 = cell.getStringCellValue();
                    String part = value3.split(",")[0];
                    part = part.replace("S", "");
                    field.setSize(Integer.valueOf(part));
                    break;
                default:
                    break;
            }
            index++;
        }
        format.getFields().add(field);
    }

    public List<Format> getFormats() {
        return formats;
    }

    public Map<String, Format> getMapFormats() {
        return mapFormats;
    }
}
