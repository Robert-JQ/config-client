package com.newcapec.configclient.constant;

/**
 * @author jqq
 * @description http状态码枚举类
 * @date 2019/5/24 9:59
 * @copyright 2016-2019 新开普 - Powered By 研发中心
 * @version 1.0
 * @see org.springframework.http.HttpStatus
 */
public enum HttpEnum {
    /**
     * http状态码及状态描述
     */
    CONTINUE(100, "继续请求"),
    SWITCHING_PROTOCOLS(101, "切换协议"),
    PROCESSING(102, "处理将被继续执行"),
    OK(200, "成功"),
    CREATED(201, "成功,新资源已被创建"),
    ACCEPTED(202, "接受请求,尚未处理"),
    NON_AUTHORITATIVE_INFORMATION(203, "成功,但不是权威信息"),
    NO_CONTENT(204, "成功,无内容"),
    RESET_CONTENT(205, "成功,重置内容"),
    PARTIAL_CONTENT(206, "部分内容"),
    MULTI_STATUS(207, "多状态码"),
    MULTIPLE_CHOICES(300, "多种选择"),
    MOVED_PERMANENTLY(301, "永久移动"),
    FOUND(302, "临时移动"),
    SEE_OTHER(303, "查看其他地址"),
    NOT_MODIFIED(304, "未修改"),
    TEMPORARY_REDIRECT(307, "临时重定向"),
    PERMANENT_REDIRECT(308, "永久重定向"),
    BAD_REQUEST(400, "客户端错误"),
    UNAUTHORIZED(401, "需要认证"),
    FORBIDDEN(403, "拒绝执行"),
    NOT_FOUND(404, "无法找到资源"),
    METHOD_NOT_ALLOWED(405, "请求方法不支持"),
    NOT_ACCEPTABLE(406, "无法提供与Accept头相匹配的响应"),
    PROXY_AUTHENTICATION_REQUIRED(407, "需要代理的身份认证"),
    REQUEST_TIMEOUT(408, "请求超时"),
    CONFLICT(409, "处理请求发生冲突"),
    GONE(410, "请求的资源已经不存在"),
    LENGTH_REQUIRED(411, "需要Content-Length请求信息"),
    PRECONDITION_FAILED(412, "请求的先决条件错误"),
    PAYLOAD_TOO_LARGE(413, "请求实体过大"),
    URI_TOO_LONG(414, "请求URI过长"),
    UNSUPPORTED_MEDIA_TYPE(415, "无法处理请求的媒体类型"),
    REQUESTED_RANGE_NOT_SATISFIABLE(416, "请求范围无效"),
    EXPECTATION_FAILED(417, "无法满足期望"),
    UNPROCESSABLE_ENTITY(422, "无法处理实体"),
    UPGRADE_REQUIRED(426, "需要升级"),
    PRECONDITION_REQUIRED(428, "需要先决条件"),
    TOO_MANY_REQUESTS(429, "请求太多"),
    REQUEST_HEADER_FIELDS_TOO_LARGE(431, "请求头字段过大"),
    UNAVAILABLE_FOR_LEGAL_REASONS(451, "因法律原因不可用"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    NOT_IMPLEMENTED(501, "无法完成请求"),
    BAD_GATEWAY(502, "网关错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    GATEWAY_TIMEOUT(504, "网关超时"),
    HTTP_VERSION_NOT_SUPPORTED(505, "不支持的HTTP版本"),
    VARIANT_ALSO_NEGOTIATES(506, "服务器配置错误"),
    INSUFFICIENT_STORAGE(507, "服务器存储空间不足"),
    LOOP_DETECTED(508, "处理请求陷入死循环"),
    BANDWIDTH_LIMIT_EXCEEDED(509, "服务器超出指定带宽"),
    NOT_EXTENDED(510, "服务器需要扩展"),
    NETWORK_AUTHENTICATION_REQUIRED(511, "需要网络认证");

    private final int code;
    private final String msg;

    HttpEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }

    /**
     * @desc 根据状态码获取枚举实例
     * @author Robert-JQ
     * @date 2018/11/13 23:01
     * @param code
     */
    public static HttpEnum valueOf(int code) {
        for (HttpEnum httpEnum : HttpEnum.values()) {
            if (httpEnum.code == code) {
                return httpEnum;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + code + "]");
    }
}
