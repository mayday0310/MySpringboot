package com.mayday.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by riolee on 2017/5/1.
 * Pojo转换工具
 */
public class PojoCaster {

    /**
     * 将POJO转换成MAP对象
     * @param pojo 需要转换的POJO
     * @return 对应的MAP对象数据
     */
    public static Map<String, Object> pojoToMap(Object pojo) {
        Class clz = pojo.getClass();
        Map<String, Object> ret = new ConcurrentHashMap<>();
        Method[] methods = clz.getDeclaredMethods();
        Arrays.stream(methods).forEach(md -> {
            String name = md.getName();
            if (name.startsWith("get") && !name.equalsIgnoreCase("getClass") && md.getParameterCount()==0) {
                try {
                    Object val = md.invoke(pojo);
                    ret.put(beanPropNameToColumn(name.substring(3)),val);
                } catch (IllegalAccessException | InvocationTargetException ignored) {
                    // 方法执行失败则忽略
                }
            }
        });
        return ret;
    }

    /**
     * 将驼峰式属性名转换成下划线字段名
     *
     * @param propName 需要被转换的属性名称
     * @return 返回下划线的字段名
     */
    public static String beanPropNameToColumn(String propName) {
        StringBuffer ret = new StringBuffer();
        for (int i = 0, len = propName.length(); i < len; i++) {
            String ch = propName.substring(i, i + 1);
            if (i > 0 && ch.matches("[A-Z]")) {
                ret.append("_");
            }
            ret.append(ch.toLowerCase());
        }
        return ret.toString();
    }

}
