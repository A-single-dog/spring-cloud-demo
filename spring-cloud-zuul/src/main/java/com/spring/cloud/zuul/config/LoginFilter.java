package com.spring.cloud.zuul.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.spring.cloud.zuul.util.FilterUtil;

/**
 *  编辑ZuulFilter自定义过滤器，用于校验登录
 *  重写zuulFilter类，有四个重要的方法
 *  1.- `shouldFilter`：返回一个`Boolean`值，判断该过滤器是否需要执行。返回true执行，返回false不执行。
 *  2.- `run`：过滤器的具体业务逻辑。
 *  3.- `filterType`：返回字符串，代表过滤器的类型。包含以下4种：
 *      - `pre`：请求在被路由之前执行
 *      - `routing`：在路由请求时调用
 *      - `post`：在routing和errror过滤器之后调用
 *      - `error`：处理请求时发生错误调用
 *  4.- `filterOrder`：通过返回的int值来定义过滤器的执行顺序，数字越小优先级越高
 * 
 * 登录认证
 * @user zds
 * @date 2020年1月10日下午5:43:57
 **/
@Component
public class LoginFilter extends ZuulFilter{

	/**
	 * 登录校验过滤器，执行逻辑的方法
	 */
	@Override
	public Object run() throws ZuulException {

		//1）获取zuul提供的请求上下文对象
		RequestContext currentContext = RequestContext.getCurrentContext();
		//2）从上下文中获取request对象
		HttpServletRequest request = currentContext.getRequest();
		if (request.getMethod().equals("OPTIONS")){
            corsFilterOptions();
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(HttpStatus.SC_OK);
            return null;
        }else{
            corsFilter();
        }
		//3）从请求中获取token
		String token = request.getHeader("Authorization");
		//4）判断（没有token， 认为用户没有登录，返回401状态码）
		if (token==null || "".equals(token)) {
			//没有token，认为登录校验失败， 进行拦截
			currentContext.setSendZuulResponse(false);
			// 返回401， 也可以考虑重定向到登录页
			currentContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
			currentContext.setResponseBody("请先登录");
		}
		
		//如果校验通过， 可以考虑把用户信息放入上下文，继续向后执行
		return null;
	}

	/**
     * true需要过滤，false不需要过滤直接放行
     * 返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。
	 */
	@Override
	public boolean shouldFilter() {
		
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestURI = request.getRequestURI();
        //判断白名单
        boolean isAllow = !isAllowPath(requestURI);
        if (!isAllow){
            if (request.getMethod().equals("OPTIONS")){
                corsFilterOptions();
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(200);
            }else{
                corsFilter();
            }
            return false;
        }
        //return isAllow;
        return true;
	}

	/**
	 * 执行顺序， 值越小执行顺序越靠前
	 */
	@Override
	public int filterOrder() {

		return 1;
	}

	/**
	 * 登录校验的过滤级别， 肯定是第一层过滤
	 */
	@Override
	public String filterType() {

		return "pre";
	}
	
	/**
     * 判断请求URI是不是白名单中的URI
     * @param requestURI
     * @return
     */
    private Boolean isAllowPath(String requestURI) {
        if (FilterUtil.isValidUrl(requestURI))
            return true;
        return false;
    }
    
	 /**
     * GET POST请求的跨域设置
     */
    private void corsFilter(){
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.getResponse().setCharacterEncoding("UTF-8");
        ctx.addZuulResponseHeader("Access-Control-Allow-Origin", "*");
        ctx.addZuulResponseHeader("Access-Control-Allow-Credentials","true");
        ctx.addZuulResponseHeader("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS");
        ctx.addZuulResponseHeader("Access-Control-Allow-Headers","DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Authorization");
        ctx.addZuulResponseHeader("Access-Control-Allow-Credentials", "true");
        ctx.addZuulResponseHeader("Content-Type", "text/html;charset=UTF-8");
    }
	
	/**
     * Options请求的跨域设置
     */
    private void corsFilterOptions(){
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.getResponse().setCharacterEncoding("UTF-8");
        ctx.addZuulResponseHeader("Access-Control-Allow-Origin", "*");
        ctx.addZuulResponseHeader("Access-Control-Allow-Credentials","true");
        ctx.addZuulResponseHeader("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS");
        ctx.addZuulResponseHeader("Access-Control-Allow-Headers","DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Authorization");
        ctx.addZuulResponseHeader("Access-Control-Allow-Credentials", "true");
        ctx.addZuulResponseHeader("Access-Control-Max-Age", "3600");
        ctx.addZuulResponseHeader("Content-Type", "text/html;charset=UTF-8");
        ctx.addZuulResponseHeader("Content-Length", "0");
    }

}

