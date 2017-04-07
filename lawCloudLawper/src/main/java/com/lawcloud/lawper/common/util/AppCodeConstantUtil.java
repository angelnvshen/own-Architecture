package com.lawcloud.lawper.common.util;

import org.springframework.core.env.Environment;

/**
 * Created by dell on 2016/12/23.
 */
public class AppCodeConstantUtil {

    private Environment env;

    public void setEnv(Environment env) {
        this.env = env;
    }

    public String isBlank(String key) {
        String _tmp = env.getProperty(key) + env.getProperty("isBlank");
        String str2 = null;
        try {
            str2 = new String(_tmp.getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2;
    }

    public String isLegal(String key) {
        String _tmp = env.getProperty(key) + env.getProperty("not_legal");
        String str2 = null;
        try {
            str2 = new String(_tmp.getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2;
    }
  
    public String getProperty(String key) {
        String _tmp = env.getProperty(key);
        String str2 = null;
        try {
            str2 = new String(_tmp.getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2;
    }
}
