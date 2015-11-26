package org.think2.cms.interceptor;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Repository
public class SecurityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 用户是否登录
        String uri = request.getRequestURI();
        if ("/sys/index.do".equals(uri) || "/sys/login.do".equals(uri)) {
            return true;
        } else {
//            UserSession userSession = (UserSession) request.getSession().getAttribute("session_user");
//            if (null != userSession) {
//                if (!"/sys/getMenu.do".equals(uri)) { // 获取菜单不记录日志
//                    CmsLog.save(IpUtils.getRequestIpAddress(request), userSession.getId(), uri);
//                }
//                return true;
//            } else {
//                response.sendRedirect("/sys/index.do");
//                return false;
//            }
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}