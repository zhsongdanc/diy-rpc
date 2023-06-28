package com.demus.common.enums;

/**
 * @description:
 * @author: zhsong
 * @time: 2023/6/28 21:16
 */
public enum  PackageTypeEnum {
    REQ_PACKAGE(1), RESP_PACKAGE(2);

    PackageTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    private int code;
}
