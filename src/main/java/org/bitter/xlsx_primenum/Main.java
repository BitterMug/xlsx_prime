package org.bitter.xlsx_primenum;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("No file input!");
            return;
        }
        try {
            FileInputStream fis=new FileInputStream(args[0]);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);

            for(Row row: sheet) {               //Iterating through rows
                Cell cell = row.getCell(1);  //Data will be only in second collum. If not, this will not work.
                String value = cell.getStringCellValue();
                if (isValid(value) && isPrime(Integer.parseInt(value))) {
                    System.out.println(value);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File can not be opened!");
        }
    }

    public static boolean isValid(String val) {

        try {
            int temp = Integer.parseInt(val);   //Check for error.
            if (temp > 0){                      //Positive number check. By definition "0" is not positive.
                //That is why it's excluded. But
                return true;
            }
            //System.out.println("Input is not positive: " + val);                      //Debug
        } catch (NumberFormatException e) {
            //System.out.println("Input String cannot be parsed to Integer: " + val);   //Debug
        }
        return false;
    }

    public static boolean isPrime(int val) {

        boolean isDivisible = false;
        for (int i = 2; i <= val / 2; ++i) {    //Checking if number has any divisor.
            //Must be less or equal to X/2. No point in checking more.
            if (val % i == 0) {
                isDivisible = true;
                break;
            }
        }
        return (!isDivisible);
    }
}

