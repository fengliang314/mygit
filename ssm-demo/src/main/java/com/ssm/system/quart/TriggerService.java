package com.ssm.system.quart;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

import org.apache.log4j.Logger;

//定时任务
public class TriggerService {

	private static final Logger log = Logger.getLogger(TriggerService.class.getName());
	
	public void doIt() {
		Random random = new Random();
		log.info(random.nextInt());
		long currentTimeMillis = System.currentTimeMillis();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String string = simpleDateFormat.format(new Date(currentTimeMillis));
		System.out.println(string);
	}

}
