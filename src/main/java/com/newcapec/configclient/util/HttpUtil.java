package com.newcapec.configclient.util;

import com.newcapec.configclient.constant.HttpEnum;
import com.newcapec.configclient.constant.GlobalConstant;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author jqq
 * @version 1.0
 * @description
 * @date 2019/6/25 16:44
 **/
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * @author jqq
     * @description 发送post请求,主要针对使用@requestbody注解接收参数的方法
     * @date 2019/6/25 17:26
     * @param url
     * @param jsonStr
     * @return java.lang.String
     */
    public static String post(String url, String jsonStr) {
        String result = null;
        HttpPost post = new HttpPost(url);
        StringEntity param = new StringEntity(jsonStr, ContentType.APPLICATION_JSON);
        post.setEntity(param);
        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(post)) {
            if (response.getStatusLine().getStatusCode() == HttpEnum.OK.code()) {
                result = EntityUtils.toString(response.getEntity(), GlobalConstant.CHARSET);
            }
        } catch (IOException e) {
            logger.error("发送post请求失败,地址:{},参数：{}", url, jsonStr, e);
        }
        return result;
    }
}
