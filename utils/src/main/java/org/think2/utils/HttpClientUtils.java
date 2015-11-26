package org.think2.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.List;
import java.util.Map;

public class HttpClientUtils {

    private static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * http get请求，UTF-8编码格式，返回字符串
     *
     * @param url 请求url
     * @return 返回值字符串
     */
    public static String get(String url) {
        return get(url, null, DEFAULT_ENCODING);
    }

    /**
     * http get请求，UTF-8编码格式，返回字符串
     *
     * @param url    请求url
     * @param params 请求参数
     * @return 返回值字符串
     */
    public static String get(String url, Map<String, Object> params) {
        return get(url, params, DEFAULT_ENCODING);
    }

    /**
     * http get请求，返回字符串
     *
     * @param url      请求url
     * @param encoding 编码格式
     * @return 返回值字符串
     */
    public static String get(String url, String encoding) {
        return get(url, null, encoding);
    }

    /**
     * http get请求，返回字符串
     *
     * @param url      请求url
     * @param params   请求参数
     * @param encoding 编码格式
     * @return 返回值字符串
     */
    public static String get(String url, Map<String, Object> params, String encoding) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            if (null != params && params.size() > 0) {
                List<NameValuePair> nameValuePairs = ConvertUtils.mapToNameValuePairs(params);
                String p = new UrlEncodedFormEntity(nameValuePairs).toString();
                if (null != p) {
                    if (url.contains("?")) {
                        url += url + "&" + p;
                    } else {
                        url += url + "?" + p;
                    }
                }
            }
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            try {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new Exception("http post response status line " + response.getStatusLine());
                }
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, encoding);
                EntityUtils.consume(entity);
                return result;
            } finally {
                response.close();
            }
        } catch (Exception e) {
            throw new Think2UtilsException(e);
        }
    }

    /**
     * http get请求，返回byte数组
     *
     * @param url 请求url
     * @return 返回byte数组
     */
    public static byte[] getByteArray(String url) {
        return getByteArray(url, null);
    }

    /**
     * http get请求，返回byte数组
     *
     * @param url    请求url
     * @param params 请求参数
     * @return 返回byte数组
     */
    public static byte[] getByteArray(String url, Map<String, Object> params) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            if (null != params && params.size() > 0) {
                List<NameValuePair> nameValuePairs = ConvertUtils.mapToNameValuePairs(params);
                String p = new UrlEncodedFormEntity(nameValuePairs).toString();
                if (null != p) {
                    if (url.contains("?")) {
                        url += url + "&" + p;
                    } else {
                        url += url + "?" + p;
                    }
                }
            }
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            try {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new Exception("http post response status line " + response.getStatusLine());
                }
                HttpEntity entity = response.getEntity();
                byte[] result = EntityUtils.toByteArray(entity);
                EntityUtils.consume(entity);
                return result;
            } finally {
                response.close();
            }
        } catch (Exception e) {
            throw new Think2UtilsException(e);
        }
    }

    /***
     * http post请求，UTF-8编码格式，返回字符串
     *
     * @param url 请求url
     * @return 返回值字符串
     */
    public static String post(String url) {
        return post(url, null, DEFAULT_ENCODING);
    }

    /***
     * http post请求，UTF-8编码格式，返回字符串
     *
     * @param url    请求url
     * @param params 请求参数
     * @return 返回值字符串
     */
    public static String post(String url, Map<String, Object> params) {
        return post(url, params, DEFAULT_ENCODING);
    }

    /**
     * http post请求，返回字符串
     *
     * @param url      请求url
     * @param params   请求参数
     * @param encoding 编码格式
     * @return 返回值字符串
     */
    public static String post(String url, Map<String, Object> params, String encoding) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            if (null != params && params.size() > 0) {
                List<NameValuePair> nameValuePairs = ConvertUtils.mapToNameValuePairs(params);
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new Exception("http post response status line " + response.getStatusLine());
                }
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, encoding);
                EntityUtils.consume(entity);
                return result;
            } finally {
                response.close();
            }
        } catch (Exception e) {
            throw new Think2UtilsException(e);
        }
    }

}
