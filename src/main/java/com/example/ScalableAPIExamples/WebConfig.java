package com.example.ScalableAPIExamples;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ThrottlingInterceptor throttlingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(throttlingInterceptor);
    }
}
