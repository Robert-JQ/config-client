package com.newcapec.configclient.model;

/**
 * @author jqq
 * @version 1.0
 * @description
 * @date 2019/6/26 15:33
 **/
public class MessageBody {

    private String operation;

    private String key;

    private String value;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private static final MessageBody MESSAGE_BODY = new MessageBody();

    public static MessageBody newBody(String operation, String key, String value) {
        MESSAGE_BODY.setOperation(operation);
        MESSAGE_BODY.setKey(key);
        MESSAGE_BODY.setValue(value);
        return MESSAGE_BODY;
    }

    @Override
    public String toString() {
        return "MessageBody{" +
                "operation='" + operation + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
