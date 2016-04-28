package com.zhxjz.jobs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zhxjz.common.CustomizedPropertyPlaceholderConfigurer;
import com.zhxjz.framework.session.zookeeper.ZooKeeperSessionHelper;
import com.zhxjz.framework.util.ApplicationContextUtil;

/**
 * 当分布式session使用zookeeper实现时，定时清过期session中的数据
 * 
 * @author caozj
 *
 */
@Component
public class ClearZookeeperSession {

	private static final Log logger = LogFactory.getLog(ClearZookeeperSession.class);

	@Scheduled(cron = "0 0/8 * * * *")
	private void initJobs() {
		if ("true".equalsIgnoreCase(CustomizedPropertyPlaceholderConfigurer.getContextProperty("session.isEnableDistributedSession"))
				&& "zookeeper".equalsIgnoreCase(CustomizedPropertyPlaceholderConfigurer.getContextProperty("session.distribute.type"))) {
			logger.info("开始清过期zookeeper的session数据");
			((ZooKeeperSessionHelper) ApplicationContextUtil.getBean("zooKeeperSessionHelper")).clearTimeoutData();
			logger.info("完成清过期zookeeper的session数据");
		}
	}

}
