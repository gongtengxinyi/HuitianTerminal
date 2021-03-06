package com.huitian.util;

import com.huitian.dto.IndentDto;
import com.huitian.dto.Param;

import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class AppendToFile {
    /**
     * 订单内容写到文件中
     * @param fileName
     * @param indent
     */
    public static void appendMethodC(String fileName, IndentDto indent) {
        try {

            StringBuilder sb =new StringBuilder();
            List<Param> params = indent.getParam();
            for (Param param : params) {
                sb.append(param.getPicName()).append(".DXF").append(",").append(param.getxLongToZero()).append(",")
                        .append(param.getyLongToZero()).append(",")
                        .append(param.getzSpin()).append(",").append(param.getZoom()).append("\r\n");
            }


            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            //将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(sb.toString());
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A方法追加文件：使用RandomAccessFile
     */
    public static void appendMethodA(String fileName, String content) {
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            //将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * B方法追加文件：使用FileWriter
     */
    public static void appendMethodB(String fileName, String content) {
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}