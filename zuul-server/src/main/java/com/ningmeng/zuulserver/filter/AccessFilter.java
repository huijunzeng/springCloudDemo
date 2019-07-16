package com.ningmeng.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 过滤器，实现ZuulFilter的四个方法，目的在请求被路由之前检查请求中是否携带有token参数，若有就进行路由，若没有就拒绝访问，返回错误信息
 */
@Component //注入filter
public class AccessFilter extends ZuulFilter {

    /**
     * 返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
     * pre：可以在请求被路由之前调用，可用来实现Authentication、选择源服务地址等
     * routing：在路由请求时候被调用，即返回Response后执行。用来实现对Response结果进行修改，收集统计数据以及把Response传输会客户端
     * post：在routing和error过滤器之后被调用
     * error：处理请求时发生错误时被调用，上面三个过程中任何一个出现错误都交由ERROR类型的filters进行处理
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 通过int值来定义过滤器的执行顺序  数值越小，执行顺序越前
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。在这里，我们直接返回true，所以该过滤器总是生效
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑。需要注意，这里我们通过ctx.setSendZuulResponse(false)令zuul过滤该请求，不对其进行路由，然后通过ctx.setResponseStatusCode(401)设置了其返回的错误码，当然我们也可以进一步优化我们的返回，比如，通过ctx.setResponseBody(body)对返回body内容进行编辑等。
     * @return
     */
    @Override
    public Object run() throws ZuulException {
        // 引用的是zuul包下的RequestContext
        RequestContext ctx = RequestContext.getCurrentContext();
        // 避免中文乱码
        ctx.getResponse().setContentType("text/json;charset=UTF-8");
        ctx.getResponse().setCharacterEncoding("UTF-8");
        HttpServletRequest request = ctx.getRequest();
        Object accessToken = request.getParameter("token");
        if(accessToken == null) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            try {
                ctx.getResponse().getWriter().write("没有权限，访问失败");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        return null;
    }
}
