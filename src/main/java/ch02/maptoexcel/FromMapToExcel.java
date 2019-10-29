package ch02.maptoexcel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class FromMapToExcel {

    public static void main(String [] args) {
        Map<String, Integer> map = new TreeMap();
        load(map, "data/Countries.dat");
        print(map);
        storeXL(map, "data/Countries.xls", "XXX");
    }

    public static void load(Map map, String fileSpec){
        File file = new File(fileSpec);
        try {
            Scanner input = new Scanner(file);
            while(input.hasNext()) {
                String country = input.next();
                int pop = input.nextInt();
                map.put(country, pop);
            }
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void print(Map map) {
        Set countries = map.keySet();
        for(Object country : countries) {
            Object pop = map.get(country);
            System.out.printf("%-10s%,12d%n", country, pop);
        }
    }

    public static void storeXL(Map map, String fileSpec, String sheet) {
        try {
            FileOutputStream out = new FileOutputStream(fileSpec);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet(sheet);
            Set countries = map.keySet();
            short rowNum = 0;
            for(Object country : countries) {
                Object pop = map.get(country);
                HSSFRow row = worksheet.createRow(rowNum);
                row.createCell(0).setCellValue((String)country);
                row.createCell(1).setCellValue((Integer)pop);
                ++rowNum;
            }
            workbook.write(out);
            out.flush();
            out.close();
        } catch(Exception e) {
            System.err.println(e);
        }
    }
}
