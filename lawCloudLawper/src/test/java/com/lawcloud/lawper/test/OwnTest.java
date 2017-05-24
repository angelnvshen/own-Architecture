package com.lawcloud.lawper.test;

import org.junit.Test;

/**
 * Created by dell on 2017/5/23.
 */
public class OwnTest {

    @Test
    public void test(){
        String mobileNo = "23323268415";
//        System.out.println(xx.substring(xx.length()-6, xx.length()));
        String rawPassWord = mobileNo.length() > 6 ? mobileNo.substring(mobileNo.length() - 6, mobileNo.length()) : mobileNo;
        System.out.println(rawPassWord);
    }
}
