package hello.itemservice.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();
        request.setAttribute("logId",uuid);

        //@RequestMapping : HandlerMethod
        //정적 리소스 : ResourceHttpRequestHandler

        if(handler instanceof HandlerMethod){
            HandlerMethod hm = (HandlerMethod) handler;//호출할 컨트롤러 메소드의 모든정보가 호출되어있다.
        }

        log.info("REQUEST [{}][{}][{}][{}]",uuid, request.getDispatcherType(), requestURI, handler);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle=[{}]", modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String logId = (String)request.getAttribute("logId");
        log.info("REQUEST [{}][{}][{}][{}]",logId,request.getDispatcherType(),handler,requestURI);
        if(ex != null){
            log.error("afterCompletion error!! ", ex);
        }
    }
}
