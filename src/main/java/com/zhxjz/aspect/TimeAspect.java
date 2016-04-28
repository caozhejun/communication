package com.zhxjz.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.zhxjz.annotation.TimeAnnotation;

/**
 * 计算方法消耗时间的切面实现
 * 
 * @author caozj
 *
 */
@Aspect
@Component
public class TimeAspect {

	private static final Log logger = LogFactory.getLog(TimeAspect.class);

	@Around(value = "@annotation(time)")
	public Object logTime(ProceedingJoinPoint pjd, TimeAnnotation time) throws Throwable {
		// 获取目标方法签名
		String signature = pjd.getSignature().toString();
		long now = System.currentTimeMillis();
		Object result = pjd.proceed();
		long consumeTime = System.currentTimeMillis() - now;
		if (consumeTime >= time.overtime()) {
			logger.info("执行" + signature + "消耗的时间为" + consumeTime + "毫秒");
		}
		return result;
	}
}
