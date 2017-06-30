package com.mayday.timer;

import java.util.Timer;

/**
 * Created by Eric on 2017/6/26.
 * java Timer定时器实现
 */
public class MyTimer {

   public static void main(String [] args){
       Timer  timer=new Timer();
       long  starTime=2000;  //从什么时候开始执行
       long  interval=20000; //间隔时间 ，多级执行一次任务


       timer.schedule(new JobTask(),starTime,interval);


   }

}
