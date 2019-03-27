package com.zjc;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import javax.swing.filechooser.FileSystemView;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EasypoiController {
    public static void main(String[] args) throws Exception{
        String root = EasypoiController.class.getClassLoader().getResource("").getPath();
        // 读取源文件
        FileInputStream fis = new FileInputStream(root+"/全市技术人员工作绩效公示表.xls");
        HSSFWorkbook workBook = new HSSFWorkbook(fis);

        // 进行模板的克隆(接下来的操作都是针对克隆后的sheet)
        HSSFSheet sheet = workBook.cloneSheet(0);
//        workBook.setSheetName(0, sheet.getSheetName()); // 给sheet命名

        // 读取指定cell的内容
        HSSFCell nameCell = sheet.getRow(1).getCell(0);
        HSSFCell nameCell2 = sheet.getRow(2).getCell(0);
        System.out.println(nameCell.getStringCellValue());
        System.out.println(nameCell2.getStringCellValue());

        // 替换单元格内容(注意获取的cell的下标是合并之前的下标)
        replaceCellValue(sheet.getRow(1).getCell(0), "xxxxx时间");
        replaceCellValue(sheet.getRow(2).getCell(0), "xxxxx人");

        // 动态插入数据-增加行
        List<Map<String, Object>> datas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Map data = new HashMap<>();
            data.put("name", "name" + i);
            data.put("age", "age" + i);
            data.put("sex", "sex" + i);
            datas.add(data);
        }
        // 插入行
        sheet.shiftRows(8, 8 + datas.size(), datas.size(), true, false);// 第1个参数是指要开始插入的行，第2个参数是结尾行数,第三个参数表示动态添加的行数
        for (int i = 0; i < datas.size(); i++) {
            HSSFRow creRow = sheet.createRow(8 + i);
//            creRow.setRowStyle(sheet.getRow(4).getRowStyle());
            creRow.createCell(0).setCellValue(datas.get(i).get("name").toString());
            creRow.createCell(1).setCellValue(datas.get(i).get("age").toString());
            creRow.createCell(2).setCellValue(datas.get(i).get("sex").toString());
        }

        // 获取桌面路径
        FileSystemView fsv = FileSystemView.getFileSystemView();
        String desktop = fsv.getHomeDirectory().getPath();
        String filePath = desktop + "/template.xls";

        // 输出为一个新的Excel，也就是动态修改完之后的excel
        OutputStream out = new FileOutputStream(filePath);
        workBook.removeSheetAt(0); // 移除workbook中的模板sheet
        workBook.write(out);

        fis.close();
        out.flush();
        out.close();
    }

    /**
     * 替换单元格的内容，单元格的获取位置是合并单元格之前的位置，也就是下标都是合并之前的下表
     *
     * @param cell
     *            单元格
     * @param value
     *            需要设置的值
     */
    public static void replaceCellValue(Cell cell, Object value) {
        String val = value != null ? String.valueOf(value) : "";
        cell.setCellValue(val);
    }
}
