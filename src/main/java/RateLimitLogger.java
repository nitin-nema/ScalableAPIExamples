@Component
public class RateLimitLogger {

    private static final Logger logger = LoggerFactory.getLogger(RateLimitLogger.class);

    public void logExceededRequest(String clientIp) {
        logger.warn("Rate limit exceeded for IP: {}", clientIp);
    }
}
//-------------------//

@Autowired
private RateLimitLogger rateLimitLogger;

@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String clientIp = request.getRemoteAddr();
    if (requestCounts.get(clientIp) >= MAX_REQUESTS) {
        rateLimitLogger.logExceededRequest(clientIp);
        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        return false;
    }
    // existing logic...
}
