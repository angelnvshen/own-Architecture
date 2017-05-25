package com.lawcloud.lawper.security;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 安全资源白名单持有者类
 */
public class SecurityMetadataSourceTrustListHolder {
    private static final List<RequestMatcher> smsTrustList = new ArrayList<RequestMatcher>();

    public void setTrustList(List<String> trustList) {
        smsTrustList.clear();
        for (String s : trustList) {
            RequestMatcher requestMatcher = new AntPathRequestMatcher(s);
            smsTrustList.add(requestMatcher);
        }
    }

    public static boolean isTrustSecurityMetadataSource(HttpServletRequest httpRequest) {
        for (RequestMatcher requestMatcher : smsTrustList) {
            if (requestMatcher.matches(httpRequest)) {
                return true;
            }
        }
        return false;
    }
}