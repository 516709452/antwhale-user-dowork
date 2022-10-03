package org.antwhale.code;

public enum AliyunCosAccountEnum {
    getAccessKeyID("LTAI5tQB6vbp5tJGt6RoX9Gq"),
    getAccessKeySecret("WUSoKjNFOlIbwaqYW0xokMSoG8owi2"),
    getEndpoint("oss-cn-beijing.aliyuncs.com");

    private String value;

    AliyunCosAccountEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
