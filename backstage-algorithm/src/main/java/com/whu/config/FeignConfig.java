//package com.whu.config;
//
//import feign.Logger;
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import feign.codec.Decoder;
//import feign.codec.Encoder;
//import org.hibernate.validator.internal.util.logging.LoggerFactory;
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
//import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
//import org.springframework.cloud.openfeign.support.SpringDecoder;
//import org.springframework.cloud.openfeign.support.SpringEncoder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.List;
//
///**
// * @author Hedon Wang
// * @create 2020-07-14 14:53
// */
//
//@Configuration
//public class FeignConfig {
//
//
//    @Bean
//    Logger.Level feignLoggerLevel(){
//        return Logger.Level.FULL;
//    }
//
//
//    @Bean
//    public Decoder feignDecoder() {
//        return new ResponseEntityDecoder(new SpringDecoder(feignHttpMessageConverter()));
//    }
//
//    @Bean
//    public Encoder feignEncoder() {
//        return new SpringEncoder(feignHttpMessageConverter());
//    }
//
//    public ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
//        final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(new GateWayMappingJackson2HttpMessageConverter());
//        return () -> httpMessageConverters;
//    }
//
//    public class GateWayMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
//        GateWayMappingJackson2HttpMessageConverter(){
//            List<MediaType> mediaTypes = new ArrayList<>();
////            mediaTypes.add(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
////            mediaTypes.add(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
//            mediaTypes.add(MediaType.valueOf(MediaType.ALL_VALUE));
//            setSupportedMediaTypes(mediaTypes);
//        }
//    }
//
//    @Bean
//    public RequestInterceptor requestInterceptor() {
//        return new RequestInterceptor() {
//            @Override
//            public void apply(RequestTemplate template) {
//                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
//                        .getRequestAttributes();
//                HttpServletRequest request = attributes.getRequest();
//                Enumeration<String> headerNames = request.getHeaderNames();
//                if (headerNames != null) {
//                    while (headerNames.hasMoreElements()) {
//                        String name = headerNames.nextElement();
//                        String values = request.getHeader(name);
//                        template.header(name, values);
//                    }
//                }
//                Enumeration<String> bodyNames = request.getParameterNames();
//                StringBuffer body =new StringBuffer();
//                if (bodyNames != null) {
//                    while (bodyNames.hasMoreElements()) {
//                        String name = bodyNames.nextElement();
//                        String values = request.getParameter(name);
//                        body.append(name).append("=").append(values).append("&");
//                    }
//                }
//                if(body.length()!=0) {
//                    body.deleteCharAt(body.length()-1);
//                    template.body(body.toString());
//                    System.out.println("feign interceptor body:{}"+body.toString());
//                }
//            }
//        };
//    }
//
//}
