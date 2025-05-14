package com.carlos.curso.springboot.app.interceptor.springboot_interceptor.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Component("timeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = ((HandlerMethod) handler);
        log.info("LoadingTimeInterceptor: preHandle() entrando..." + method.getMethod().getName());

        long startTime = System.currentTimeMillis();
        request.setAttribute("start", startTime);

        Random random = new Random();
        int delay = random.nextInt(500);
        Thread.sleep(delay);

        /* Personalizar para cuando sea False */
        //        Map<String, String> json = new HashMap<>();
        //        json.put("error", "no tienes acceso a esta página!");
        //        json.put("date", new Date().toString());
        //
        //        ObjectMapper mapper = new ObjectMapper();
        //        String jsonString = mapper.writeValueAsString(json);
        //
        //        response.setContentType("application/json");
        //        response.setStatus(401);
        //        response.getWriter().write(jsonString);
        //
        //        return false;
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long end = System.currentTimeMillis();
        long start = (long) request.getAttribute("start");
        long result = end - start;
        log.info("Tiempo transcurrido: " + result + " milisegundos");
        log.info("LoadingTimeInterceptor: postHandle() entrando..." + ((HandlerMethod) handler).getMethod().getName());

    }
}
