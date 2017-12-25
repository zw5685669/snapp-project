//package com.snapp.job.read;
//
//import org.apache.cxf.Bus;
//import org.apache.cxf.jaxws.EndpointImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.xml.ws.Endpoint;
//
///**
// * <p>
// * </p>
// *
// * @author zw(汤泽辰吃得多喝得多)
// * @date 2017-12-19  20:38
// */
//@Configuration
//public class CxfConfig {
//    @Autowired
//    private Bus bus;
//
//    @Autowired
//    CommonService commonService;
//
//    /** JAX-WS **/
//    @Bean
//    public Endpoint endpoint() {
//        EndpointImpl endpoint = new EndpointImpl(bus, commonService);
//        endpoint.publish("/CommonService");
//        return endpoint;
//    }
//}
