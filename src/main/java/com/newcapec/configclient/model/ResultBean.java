package com.newcapec.configclient.model;

import com.newcapec.configclient.constant.HttpEnum;

/**
 * @author jqq
 * @version 1.0
 * @description 新返回实体
 * @date 2019/6/6 14:54
 **/
public class ResultBean<T> {
    /**
     * 响应code
     */
    private int code;

    /**
     * code对应信息
     */
    private String msg;

    /**
     * 业务数据
     */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static final ResultBean RESULT = new ResultBean();

    public static ResultBean ok() {
        RESULT.setCode(HttpEnum.OK.code());
        RESULT.setMsg(HttpEnum.OK.msg());
        RESULT.setData(null);
        return RESULT;
    }

    /**
     * @author jqq
     * @description 返回成功实体(需要成功信息,业务数据)
     * @date 2019/6/6 19:32
     * @param msg
     * @param data
     * @return com.newcapec.yunpayservice.model.common.ResultBean
     */
    public static ResultBean ok(String msg, Object data) {
        RESULT.setCode(HttpEnum.OK.code());
        RESULT.setMsg(msg);
        RESULT.setData(data);
        return RESULT;
    }

    /**
     * @author jqq
     * @description 返回成功实体(使用默认的成功信息)
     * @date 2019/6/6 19:32
     * @param data
     * @return com.newcapec.yunpayservice.model.common.ResultBean
     */
    public static ResultBean ok(Object data) {
        RESULT.setCode(HttpEnum.OK.code());
        RESULT.setMsg(HttpEnum.OK.msg());
        RESULT.setData(data);
        return RESULT;
    }

    /**
     * @author jqq
     * @description 返回失败实体(需要失败状态码,失败信息)
     * @date 2019/6/6 19:33
     * @param code
     * @param msg
     * @return com.newcapec.yunpayservice.model.common.ResultBean
     */
    public static ResultBean fail(int code, String msg) {
        RESULT.setCode(code);
        RESULT.setMsg(msg);
        RESULT.setData(null);
        return RESULT;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
