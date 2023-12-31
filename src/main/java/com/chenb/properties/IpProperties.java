package com.chenb.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("ipProperties")
@ConfigurationProperties(prefix = "iptools")
public class IpProperties {

    /**
     * 日志显示周期
     */
    private Long cycle = 5L;

    /**
     * 是否周期内重置数据
     */
    private Boolean cycleReset = false;

    /**
     *  日志显示模式
     */
    private String model = LogModel.DETAIL.value;

    public enum LogModel{
        DETAIL("detail"),
        SIMPLE("simple");
        private String value;

        LogModel(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    public Long getCycle() {
        return cycle;
    }

    public void setCycle(Long cycle) {
        this.cycle = cycle;
    }

    public Boolean getCycleReset() {
        return cycleReset;
    }

    public void setCycleReset(Boolean cycleReset) {
        this.cycleReset = cycleReset;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
