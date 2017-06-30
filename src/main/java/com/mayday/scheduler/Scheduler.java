package com.mayday.scheduler;

import com.mayday.utils.HttpClientHelper;
import com.mayday.utils.HttpClientResponseModel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {


    @Scheduled(cron = "0/30 * * * * ?")
    public void schedule() {

        System.out.println("开始抓取数据..");
      //  HttpClientResponseModel model = HttpClientHelper.get("http://api.1680210.com/CQShiCai/getBaseCQShiCaiList.do?lotCode=10003", "");

   //     System.out.println("抓取到的数据为:" + model.getResponseContent());

    }
  @Scheduled(cron="0/10 * * * * ?")
    public  void schedule2(){
        System.out.println("测试第二个定时器");

    }

}