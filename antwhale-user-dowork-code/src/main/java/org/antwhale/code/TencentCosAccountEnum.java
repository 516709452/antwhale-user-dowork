package org.antwhale.code;

public enum TencentCosAccountEnum {
    getSecretId("AKIDAC3hz2qRq3ILK5cuye6IFxRhjfOuCLI4"),
    getSecretKey("c1EG5jBPPH0XquoV1MZLBsRxIRaeXCdz"),
    getCourseBucketName("antwhale-course-1313415017"),
    BasciCoursePath("Course/Basic/");

    private String value;

    TencentCosAccountEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
