package com.lawcloud.lawper.security;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.lawcloud.lawper.dao.UrlResourcesMapper;

public class URLFilterInvocationSecurityMetadataSource implements
        FilterInvocationSecurityMetadataSource,InitializingBean {
  
    protected final Logger logger = LoggerFactory.getLogger(URLFilterInvocationSecurityMetadataSource.class);
      
    private final static List<ConfigAttribute> NULL_CONFIG_ATTRIBUTE = Collections.emptyList();

    //权限集合  
    private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;
      
    @Autowired
    private UrlResourcesMapper urlResourcesMapper;
      
    /* (non-Javadoc) 
     * @see org.springframework.security.access.SecurityMetadataSource#getAttributes(java.lang.Object) 
     */  
    @Override  
    public Collection<ConfigAttribute> getAttributes(Object object)  
            throws IllegalArgumentException {  
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
          
        Collection<ConfigAttribute> attrs = NULL_CONFIG_ATTRIBUTE;  
          
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {  
            if (entry.getKey().matches(request)) {  
                attrs =  entry.getValue();  
                break;  
            }  
        }  
        logger.info("URL资源："+request.getRequestURI()+ " -> " + attrs);  
        return attrs;  
    }  
  
    /* (non-Javadoc) 
     * @see org.springframework.security.access.SecurityMetadataSource#getAllConfigAttributes() 
     */  
    @Override  
    public Collection<ConfigAttribute> getAllConfigAttributes() {  
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
  
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {  
            allAttributes.addAll(entry.getValue());  
        }  
  
        return allAttributes;  
    }  
  
    /* (non-Javadoc) 
     * @see org.springframework.security.access.SecurityMetadataSource#supports(java.lang.Class) 
     */  
    @Override  
    public boolean supports(Class<?> clazz) {  
        return FilterInvocation.class.isAssignableFrom(clazz);  
    }  
      
    private Map<String,String> loadResource(){  
        Map<String,String> map = new LinkedHashMap<String,String>();  
          
        List<Map<String,String>> list = this.urlResourcesMapper.getURLResourceMapping();
        Iterator<Map<String,String>> it = list.iterator();  
        while(it.hasNext()){  
            Map<String,String> rs = it.next();  
            String resourcePath = rs.get("resourcePath");  
            String authorityMark = rs.get("authorityMark");  
              
            if(map.containsKey(resourcePath)){  
                String mark = map.get(resourcePath);
                map.put(resourcePath, mark+","+authorityMark);  
            }else{  
                map.put(resourcePath, authorityMark);  
            }  
        }  
        return map;  
    }  
      
    protected Map<RequestMatcher, Collection<ConfigAttribute>> bindRequestMap(){  
        Map<RequestMatcher, Collection<ConfigAttribute>> map =   
                new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();  
          
        Map<String,String> resMap = this.loadResource();  
        for(Map.Entry<String,String> entry:resMap.entrySet()){  
            String key = entry.getKey();  
            Collection<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();
            attributes = SecurityConfig.createListFromCommaDelimitedString(entry.getValue());
            map.put(new AntPathRequestMatcher(key), attributes);
        }  
          
        return map;  
    }  
  
    /* (non-Javadoc) 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet() 
     */  
    @Override  
    public void afterPropertiesSet() throws Exception {  
        this.requestMap = this.bindRequestMap();  
        logger.info("资源权限列表"+this.requestMap);  
    }  
      
    public void refreshResourceMap(){
        this.requestMap = this.bindRequestMap();  
    }  
  
} 