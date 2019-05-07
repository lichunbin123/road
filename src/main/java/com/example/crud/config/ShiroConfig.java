//package com.example.crud.config;
//
//import org.apache.shiro.cache.CacheManager;
//import org.apache.shiro.cache.MemoryConstrainedCacheManager;
//import org.apache.shiro.codec.Base64;
//import org.apache.shiro.session.mgt.SessionManager;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.CookieRememberMeManager;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.servlet.SimpleCookie;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//
//import javax.servlet.Filter;
//import java.util.LinkedHashMap;
//import java.util.Map;
//@Configuration
//public class ShiroConfig {
//    /**
//     * 创建ShiroFilterFactoryBean
//     */
//    @Bean
//    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//
//        //设置安全管理器
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        /**
//         * anon:无需认证访问
//         * authc：必须认证才可以访问
//         * user：如果使用Remeberme可以直接访问
//         * perms:该资源必须得到资源权限才可以访问
//         * role: 该资源必须得到角色权限才可以访问
//         */
//        Map<String,String> filterMap = new LinkedHashMap<>();
//        System.out.println("shiro资源拦截");
//        filterMap.put("/login","anon");
////        filterMap.put("/getAllBlogs","authc");
//
////        filterMap.put("/getAllBlogs","perms[user:add]");
//        //authc:所有url必须通过认证才能访问，anon:所有url都可以匿名访问
////        filterMap.put("/getAllBlogs","authc");
//        filterMap.put("/**", "corsAuthenticationFilter");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
//        //自定义过滤器
//        Map<String, Filter> filter = new LinkedHashMap<>();
//        filter.put("corsAuthenticationFilter", corsAuthenticationFilter());
//        shiroFilterFactoryBean.setFilters(filter);
//        return shiroFilterFactoryBean;
//    }
//
//    /**
//     * 创建DefaultWebSecurityManager
//     */
//    @Bean
//    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserReaml userReaml){
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        //关联Reaml
//        securityManager.setRealm(userReaml);
//        securityManager.setCacheManager(cacheManager());
//        securityManager.setRememberMeManager(rememberMeManager());
//        return securityManager;
//    }
//    /**
//     * 创建Reaml
//     */
//    @Bean
//    public UserReaml getReaml(){
//        return new UserReaml();
//    }
//
//    /**
//     * cookie对象
//     * @return
//     */
//    @Bean
//    public SimpleCookie rememberMeCookie() {
//        // 设置cookie名称，对应login.html页面的<input type="checkbox" name="rememberMe"/>
//        SimpleCookie cookie = new SimpleCookie("rememberMe");
//        // 设置cookie的过期时间，单位为秒，这里为一天
//        cookie.setMaxAge(86400);
//        return cookie;
//    }
//
//    /**
//     * cookie管理对象
//     * @return
//     */
//    @Bean
//    public CookieRememberMeManager rememberMeManager() {
//        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//        cookieRememberMeManager.setCookie(rememberMeCookie());
//        // rememberMe cookie加密的密钥
//        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
//        return cookieRememberMeManager;
//    }
//
//    public CORSAuthenticationFilter corsAuthenticationFilter(){
//        return new CORSAuthenticationFilter();
//    }
//
//    @Bean
//    public CacheManager cacheManager() {
//        return new MemoryConstrainedCacheManager();
//    }
//
//    /**
//     * Shiro生命周期处理器 * @return
//     */
//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//    /**
//     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能 * @return
//     */
//    @Bean
//    @DependsOn({"lifecycleBeanPostProcessor"})
//    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        advisorAutoProxyCreator.setProxyTargetClass(true);
//        return advisorAutoProxyCreator;
//    }
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
//        return authorizationAttributeSourceAdvisor;
//    }
//
//    //自定义sessionManager
//    @Bean
//    public SessionManager sessionManager() {
//        return new CustomSessionManager();
//    }
//}
