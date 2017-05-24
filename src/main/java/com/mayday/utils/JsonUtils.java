package com.mayday.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;

/**
 * Created by EricAi on 2017/5/12.
 * Json工具类
 * ajax请求直接调用这个工具类响应到前端
 */
public class JsonUtils {


    private static ObjectMapper mapper = new ObjectMapper();

    private static Log log= LogFactory.getLog(JsonUtils.class);

    public static void PrintJson(Object object,HttpServletResponse response){
        OutputStream out=null;

        try {
            StringWriter stringWriter=new StringWriter();
            mapper.writeValue(stringWriter,object);
            response.setContentType("text/html;charset=utf-8");
            out=response.getOutputStream();
            out.write(stringWriter.toString().getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
        }finally {
            if(out!=null){
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public static void renderData(HttpServletResponse response, String data) {
        PrintWriter printWriter = null;
        try {
            response.setContentType("text/html;charset=utf-8");
            printWriter = response.getWriter();
            printWriter.print(data);
        } catch (IOException ex) {

        } finally {
            if (null != printWriter) {
                printWriter.flush();
                printWriter.close();
            }
        }
    }


}
