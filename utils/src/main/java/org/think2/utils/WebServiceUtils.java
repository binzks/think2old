package org.think2.utils;

import org.apache.axis.client.Service;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoubin on 15/11/11.
 */
public class WebServiceUtils {

    /***
     * 调用wsdl webservice，并返回字符串类型的结果
     *
     * @param address   wsdl地址
     * @param namespace 命名空间
     * @param method    方法名
     * @param params    参数
     * @return 返回结果字符串
     */
    public static String wsdlInvoke(String address, String namespace, String method, Map<String, Object> params) {
        return (String) wsdlInvokeForObject(address, namespace, method, params);
    }

    /***
     * 调用wsdl webservice，并返回对象
     *
     * @param address   wsdl地址
     * @param namespace 命名空间
     * @param method    方法名
     * @param params    参数
     * @return 返回结果字符串
     */
    public static Object wsdlInvokeForObject(String address, String namespace, String method, Map<String, Object> params) {
        try {
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(address);
            call.setOperationName(new QName(namespace, method));
            // 设置入参
            List<Object> values = new ArrayList<>();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                call.addParameter(entry.getKey(), XMLType.XSD_STRING, ParameterMode.IN);
                values.add(entry.getValue());
            }
            // 设置返回值格式
            call.setReturnType(XMLType.XSD_STRING);
            // 接口调用
            return call.invoke(values.toArray());
        } catch (Exception e) {
            throw new Think2UtilsException(e);
        }
    }
}
