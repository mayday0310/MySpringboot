package com.mayday.scheduler;

import com.mayday.utils.HttpClientHelper;
import com.mayday.utils.HttpClientResponseModel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {



    @Scheduled(cron = "0/30 * * * * ?")
    public void schedule (){

        System.out.println("开始抓取数据..");
        HttpClientResponseModel model= HttpClientHelper.get("http://cp.eastday.com/data/kaijiang/04/2017-06-24.xml?_=1498220180408","");

        System.out.println("抓取到的数据为:"+model.getResponseContent());
    }

}

