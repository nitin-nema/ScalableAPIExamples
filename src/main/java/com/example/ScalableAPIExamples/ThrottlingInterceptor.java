package com.example.ScalableAPIExamples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class ThrottlingInterceptor implements HandlerInterceptor {

    private final Map<String, Integer> requestCounts = new ConcurrentHashMap<>();// track of the number of request made by each client ip address
    private final int MAX_REQUESTS = 5;
    private final long TIME_WINDOW = 60000; // 1 minute
    private final Map<String, Long> requestTimes = new ConcurrentHashMap<>();  // to store the timestamp of the first request made by each client IP address

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String clientIp = request.getRemoteAddr();
        long currentTime = System.currentTimeMillis();

        requestCounts.putIfAbsent(clientIp, 0);
        requestTimes.putIfAbsent(clientIp, currentTime);

        long timeElapsed = currentTime - requestTimes.get(clientIp);

        if (timeElapsed > TIME_WINDOW) {
            requestCounts.put(clientIp, 1);
            requestTimes.put(clientIp, currentTime);
        } else if (requestCounts.get(clientIp) >= MAX_REQUESTS) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value()); // 429 Too many request
            return false;
        } else {
            requestCounts.put(clientIp, requestCounts.get(clientIp) + 1);
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String clientIp = request.getRemoteAddr();
        long currentTime = System.currentTimeMillis();

        if ((currentTime - requestTimes.get(clientIp)) > TIME_WINDOW) {
            requestCounts.put(clientIp, 0);
        }
    }
}

// -------------------------//

@Configuration
public class WebConfig implements WebMvcConfigurer{
    @Autowired
    private ThrottlingInterceptor throttlingInterceptor;

    @Override
    public  void addInterceptor(InterceptorRegistry registry){
        registry.addInterceptor(throttlingInterceptor);
    }
}
