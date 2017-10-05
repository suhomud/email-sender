package io.smacc.esender;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RestControllerAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Before("execution(public * io.smacc.esender.rest.*Controller.*(..))")
	public void logBeforeRestCall(JoinPoint pjp) throws Throwable {
		log.info(":::::Before REST call:::::" + pjp);
	}

}
