package com.ccq.springbootkafka.exportExcel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/********************************
 *** 导出excel测试
 ***@Author chengchuanqiang
 ***@Date 2019/2/19 14:35
 ***@Version 1.0.0e.printStackTrace();
 ********************************/
public class ExportExcel {

    public static void main(String[] args) {
        try {
            ExportExcel.export();
        } catch (IOException e) {

        }
    }

    private static void export() throws IOException {
        long start = System.currentTimeMillis();
        SXSSFWorkbook workbook = new SXSSFWorkbook(2000);
        workbook.setCompressTempFiles(true);
        Sheet sheet = workbook.createSheet("工作表");
        for (int rowNum = 0; rowNum < 100000; rowNum++) {
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 200; cellNum++) {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(rowNum + ":" + cellNum);
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        FileOutputStream out = new FileOutputStream("d://excel/" + simpleDateFormat.format(new Date()) + ".xlsx");
        workbook.write(out);
        out.close();
        long end = System.currentTimeMillis();

        System.out.println("time:" + (end -start)/1000 + "s");
    }

}
