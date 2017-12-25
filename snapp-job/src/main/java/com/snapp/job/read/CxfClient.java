package com.snapp.job.read;

import com.google.common.collect.Lists;
import com.snapp.job.constants.ReadConstants;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author zw(汤泽辰吃得多喝得多)
 * @date 2017-12-19  20:40
 */
public class CxfClient {

    //    public static void main(String[] args) {
    //        getRes(ReadConstants.BRAND, Lists.newArrayList("A"));
    //        //        cl3();
    //    }

    /**
     * 动态调用方式
     */
    public static String getRes(String method, List<String> params) {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(ReadConstants.WSDL_URL);
        Object[] objects;
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke(method, getParam(params));
            return String.valueOf(objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static Object[] getParam(List<String> params) {
        List<Object> objects = Lists.newArrayList(ReadConstants.WSDL_USERNAME, ReadConstants.WSDL_PASSWORD);
        objects.addAll(params);
        return objects.toArray();
    }

    //    /**
    //     * 方式1.代理类工厂的方式,需要拿到对方的接口
    //     */
    //    public static void cl1() {
    //        try {
    //            // 接口地址
    //            String address = "http://123.127.164.42:8080/GodNian/services/godnian?wsdl";
    //            // 代理工厂
    //            JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
    //            // 设置代理地址
    //            jaxWsProxyFactoryBean.setAddress(address);
    //            // 设置接口类型
    //            jaxWsProxyFactoryBean.setServiceClass(CommonService.class);
    //            // 创建一个代理接口实现
    //            CommonService cs = (CommonService) jaxWsProxyFactoryBean.create();
    //            // 数据准备
    //            String userName = "Leftso";
    //            // 调用代理接口的方法调用并返回结果
    //            String result = cs.helloWorld(userName);
    //            System.out.println("返回结果:" + result);
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    }

    /**
     * 动态调用方式
     */
    public static void cl2() {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://123.127.164.42:8080/GodNian/services/godnian?wsdl");
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,
        // PASS_WORD));
        Object[] objects;
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("helloWorld", "1111");
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 动态调用方式
     */
    public static void cl3() {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://123.127.164.42:8080/GodNian/services/godnian?wsdl");
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,
        // PASS_WORD));
        Object[] objects;
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("getVecNameByAutomakerAndVecModeAndModelYear", "admin", "123456", "一汽丰田", "卡罗拉", "2014");
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}