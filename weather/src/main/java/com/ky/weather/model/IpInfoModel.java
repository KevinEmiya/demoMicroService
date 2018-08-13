package com.ky.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpInfoModel {
    //{"code":0,"data":{"ip":"27.17.78.142","country":"中国","area":"","region":"湖北","city":"武汉","county":"XX","isp":"电信",
    // "country_id":"CN","area_id":"","region_id":"420000","city_id":"420100","county_id":"xx","isp_id":"100017"}}

    Integer code;
    Info data;

    @Data
    public static class Info{
        String ip;
        String country;
        String area;
        String region;
        String city;
        String county;
        String isp;
        String country_id;
        String area_id;
        String region_id;
        String city_id;
        String county_id;
        String isp_id;
    }
}
