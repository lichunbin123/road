//package com.example.crud.config;
//
//import net.minidev.json.JSONObject;
//import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
//
//public class CORSAuthenticationFilter extends FormAuthenticationFilter {
//
//    public CORSAuthenticationFilter() {
//        super();
//    }
//
//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
//            return true;
//        }
//        return super.isAccessAllowed(request, response, mappedValue);
//    }
//
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        HttpServletResponse res = (HttpServletResponse)response;
//        res.setHeader("Access-Control-Allow-Origin", "*");
//        res.setStatus(HttpServletResponse.SC_OK);
//        res.setCharacterEncoding("UTF-8");
//        PrintWriter writer = res.getWriter();
//        Map<String, Object> map= new HashMap<>();
//        map.put("code", 702);
//        map.put("msg", "未登录");
//        JSONObject json=new JSONObject(map);
//        writer.write(json.toJSONString(map));
//        writer.close();
//        return false;
//    }
//}
