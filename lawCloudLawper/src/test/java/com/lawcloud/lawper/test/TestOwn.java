package com.lawcloud.lawper.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by dell on 2017/5/19.
 */
public class TestOwn {

    @Test
    public void test(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin"));

        System.out.println("$2a$10$FUDYL2X8Ng9WS38jRIi86uZ4gHJgSXhA3s0izPVvXiLZaJu/R4njK".length());
    }
}
