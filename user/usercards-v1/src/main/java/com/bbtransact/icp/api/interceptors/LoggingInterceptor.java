/*
 * package com.bbtransact.icp.api.interceptors; import
 * javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse; import
 * org.codehaus.jackson.map.ObjectMapper; import
 * org.springframework.web.servlet.HandlerInterceptor; import
 * org.springframework.web.servlet.ModelAndView; public class LoggingInterceptor
 * implements HandlerInterceptor {
 * @Override public boolean preHandle(HttpServletRequest request,
 * HttpServletResponse response, Object handler) throws Exception { String url =
 * request.getRequestURI(); System.out.println(url); String split[] =
 * url.split("/"); System.out.println(url); if(split[1].equals("v1") &&
 * split[2].equals("accountholders") && split[3].length()==16 &&
 * split[4].equals("cards")){ System.out.println("pattern match"); return true;
 * }else{ System.out.println("pattern doesn't match"); ErrorMessage eMSG = new
 * ErrorMessage(); ObjectMapper om = new ObjectMapper();
 * System.out.println(url); String jsonObj =
 * om.writeValueAsString(eMSG.getErrorMsg("url doesn't exist", "404", url));
 * //return jsonObj; response.getWriter().write(jsonObj); return false; }
 * System.out.println("---Before Method Execution---"); return true; }
 * @Override public void postHandle( HttpServletRequest request,
 * HttpServletResponse response, Object handler, ModelAndView modelAndView)
 * throws Exception { System.out.println("---method executed---"); }
 * @Override public void afterCompletion(HttpServletRequest request,
 * HttpServletResponse response, Object handler, Exception ex) throws Exception
 * { System.out.println("---Request Completed---"); } }
 */