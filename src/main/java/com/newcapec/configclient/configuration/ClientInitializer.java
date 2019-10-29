package com.newcapec.configclient.configuration;

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import com.newcapec.configclient.Cache;
import com.newcapec.configclient.constant.GlobalConstant;
import com.newcapec.configclient.constant.MessageConstant;
import com.newcapec.configclient.model.MessageBody;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @author jqq
 * @version 1.0
 * @description
 * @date 2019/6/24 11:48
 **/
@Component
public class ClientInitializer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${rocketmq.consumer-id}")
    private String consumerId;

    @Value("${rocketmq.access-key}")
    private String accessKey;

    @Value("${rocketmq.secret-key}")
    private String secretKey;

    @Value("${rocketmq.ons-addr}")
    private String onsAddr;

    @Value("${rocketmq.topic}")
    private String topic;

    @Value("${configserver.appid}")
    private String appid;

    @Value("${configserver.envid}")
    private Integer envid;

    @Value("${configserver.item.query.url}")
    private String queryItemUrl;

    private static Consumer consumer;

    @PostConstruct
    public void init() {
        checkValues();
        //项目初始化时将该项目，在环境中的配置项加载到缓存
        Cache.loadItems(appid, envid, queryItemUrl);
        logger.info("初始化配置项:{}",Cache.localItem);

        logger.info("初始化消费者！");
        Properties consumerProperties = new Properties();
        consumerProperties.setProperty(PropertyKeyConst.AccessKey, accessKey);
        consumerProperties.setProperty(PropertyKeyConst.SecretKey, secretKey);
        consumerProperties.setProperty(PropertyKeyConst.ONSAddr, onsAddr);
        consumerProperties.setProperty(PropertyKeyConst.GROUP_ID, consumerId);
        //将消费者设置为广播消费模式
        consumerProperties.setProperty(PropertyKeyConst.MessageModel, MessageModel.BROADCASTING.getModeCN());
        consumer = ONSFactory.createConsumer(consumerProperties);
        String tag = appid + MessageConstant.TAG_SPLITER + envid;
        consumer.subscribe(topic, tag, new MessageListener() {
            @Override
            public Action consume(Message message, ConsumeContext consumeContext) {
                String body = new String(message.getBody(), Charset.forName(GlobalConstant.CHARSET));
                MessageBody messageBody = JSON.parseObject(body, MessageBody.class);
                switch (messageBody.getOperation()) {
                    case MessageConstant.OPERATION_ADD:
                        Cache.localItem.put(messageBody.getKey(), messageBody.getValue());
                        break;
                    case MessageConstant.OPERATION_DELETE:
                        Cache.localItem.remove(messageBody.getKey());
                        break;
                    case MessageConstant.OPERATION_UPDATE:
                        Cache.localItem.put(messageBody.getKey(), messageBody.getValue());
                        break;
                    default:
                        break;
                }
                logger.info("本地缓存:{}", Cache.localItem);
                return Action.CommitMessage;
            }
        });
        consumer.start();
    }

    private void checkValues() {
        if (StringUtils.isEmpty(consumerId)) {
            throw new RuntimeException("consumerId不能为空");
        }
        if (StringUtils.isEmpty(accessKey)) {
            throw new RuntimeException("accessKey不能为空");
        }
        if (StringUtils.isEmpty(secretKey)) {
            throw new RuntimeException("secretKey不能为空");
        }
        if (StringUtils.isEmpty(onsAddr)) {
            throw new RuntimeException("onsAddr不能为空");
        }
        if (StringUtils.isEmpty(topic)) {
            throw new RuntimeException("topic不能为空");
        }
        if (StringUtils.isEmpty(appid)) {
            throw new RuntimeException("appid不能为空");
        }
        if (StringUtils.isEmpty(queryItemUrl)) {
            throw new RuntimeException("queryItemUrl不能为空");
        }
        if (envid == null) {
            throw new RuntimeException("envid不能为空");
        }
    }

    public static Consumer getConsumer() {
        return consumer;
    }
}
