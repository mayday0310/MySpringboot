package com.mayday.timer;

import java.util.Date;
import java.util.TimerTask;

/**
 * Created by Eric on 2017/6/26.
 */
public class JobTask  extends TimerTask{

    @Override
    public void run() {

     System.out.println("当前时间为:"+new Date());

    }
}
