package com.mayday.utils;

import com.alibaba.fastjson.JSONObject;
import com.lottery.commons.global.GlobalProperty;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Spring on 2017/6/12 0012.
 */
public class ExportTxt {
    /**
     * 功能：Java读取txt文件的内容
     * <p>
     * 步骤：1：先获得文件句柄
     * <p>
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * <p>
     * 3：读取到输入流后，需要读取生成字节流
     * <p>
     * 4：一行一行的输出。readline()。
     * <p>
     * 备注：需要考虑的是异常情况
     *
     * @param filePath
     */
    public static List<JSONObject> readTxtFile(String filePath) {
        List<JSONObject> numberResult  = new ArrayList<>();
        Set<String> numberSet = new LinkedHashSet<>();
        InputStreamReader read=null;
        try {
            String encoding= GlobalProperty.getPropSetting("app.import.number.encoding");
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                read = new InputStreamReader( new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String  lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    if (lineTxt.trim().matches("[\\d]{11}")){
                        numberSet.add(lineTxt);
                    }
                }

                numberSet.forEach(s->{
                    JSONObject number = new JSONObject();
                    number.put("number_head",Integer.valueOf(s.substring(0,3)));
                    number.put("number_body",s.substring(3));
                    numberResult.add(number);
                });
            }else{
                System.out.println("找不到指定的文件");
            }
        }catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }finally {
            if (read!=null) try {
                read.close();
            } catch (IOException ignored) {
            }
        }
        return numberResult;
    }


    public static void main(String args[]){
        String  filePath = "L:\\Apache\\htdocs\\res\\20121012.txt";
        readTxtFile(filePath);
    }



}
