package kr.gudi.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import kr.gudi.aspect.WorkFlowAOP;

@Configuration
@EnableAspectJAutoProxy
public class AOPConfig {

	@Bean
	public WorkFlowAOP homeAOP() {
		return new WorkFlowAOP();
	}
	
}
