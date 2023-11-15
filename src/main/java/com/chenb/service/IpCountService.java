package com.chenb.service;

import com.chenb.properties.IpProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class IpCountService {

    private Map<String, Integer> ipCountMap = new HashMap<>();

    @Autowired
    private IpProperties ipProperties;

    @Autowired
    private HttpServletRequest httpServletRequest;

    public void count(){
        // 获取当前请求的ip地址
        String ip = httpServletRequest.getRemoteAddr();
        // 累加此ip地址的请求次数
        Integer count = ipCountMap.get(ip);
        if(count != null) {
            ipCountMap.put(ip, count+1);
        } else{
            ipCountMap.put(ip, 1);
        }
    }

    @Scheduled(cron = "0/#{ipProperties.cycle} * * * * ?")
    public void print(){
        System.out.println("IP访问监控");
        if (ipProperties.getModel().equals(IpProperties.LogModel.DETAIL.getValue())){
            // 详细显示模式
            System.out.println("+-----ip-address-----+--num--+");
            for (Map.Entry<String, Integer> entry : ipCountMap.entrySet()) {
                System.out.println(String.format("|%18s  |%5d  |", entry.getKey(), entry.getValue()));
            }
            System.out.println("+--------------------+-------+");
        } else if (ipProperties.getModel().equals(IpProperties.LogModel.SIMPLE.getValue())){
            // 极简显示模式
            System.out.println("+-----ip-address-----+");
            for (String key : ipCountMap.keySet()) {
                System.out.println(String.format("|%18s  |", key));
            }
            System.out.println("+--------------------+");
        }

        // 是否周期内清除数据
        if (ipProperties.getCycleReset()) {
            ipCountMap.clear();
        }
    }

    public static void main(String[] args) {
        System.out.println("+-----ip-address-----+--num--+");
        System.out.println(String.format("|%18s  |%5d  |", "123", 12));
        System.out.println(String.format("|%18s  |%5d  |", "asdfgbn", 1234));
        System.out.println("+--------------------+-------+");
    }
}
