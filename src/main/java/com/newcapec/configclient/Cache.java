package com.newcapec.configclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.newcapec.configclient.constant.HttpEnum;
import com.newcapec.configclient.model.Item;
import com.newcapec.configclient.model.ResultBean;
import com.newcapec.configclient.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jqq
 * @version 1.0
 * @description
 * @date 2019/6/26 15:27
 **/
public class Cache {

    private static final Logger LOGGER = LoggerFactory.getLogger(Cache.class);

    public static final Map<String, String> localItem = new ConcurrentHashMap<>();

    /**
     * @author jqq
     * @description 应用初始化时加载相应的配置项
     * @date 2019/7/9 14:39
     * @param
     * @return void
     */
    public static void loadItems(String appid, Integer envid, String queryItemUrl) {
        JSONObject json = new JSONObject();
        json.put("appid", appid);
        json.put("envid", envid);
        String result = HttpUtil.post(queryItemUrl, json.toString());
        if (StringUtils.isEmpty(result)) {
            throw new RuntimeException("请求配置中心服务失败");
        }
        ResultBean resultBean = JSON.parseObject(result, ResultBean.class);
        if (resultBean != null && resultBean.getCode() == HttpEnum.OK.code()
                && resultBean.getData() != null) {
            List<Item> items = JSON.parseArray(resultBean.getData().toString(), Item.class);
            for (Item item : items) {
                Cache.localItem.put(item.getKey(), item.getValue());
            }
        }
    }

    /**
     * @author jqq
     * @description 重新加载配置项,用于刷新配置项数据
     * @date 2019/7/9 14:41
     * @param
     * @return void
     */
    public static void reloadItems(String appid, Integer envid, String queryItemUrl) {
        localItem.clear();
        loadItems(appid, envid, queryItemUrl);
    }
}
