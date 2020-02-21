package kr.gudi.config;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import kr.gudi.config.filter.UTF8Filter;
import kr.gudi.config.mybatis.MybatisConfig;
import kr.gudi.config.spring.AOPConfig;
import kr.gudi.config.spring.SecurityConfig;
import kr.gudi.config.spring.SpringConfig;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {SecurityConfig.class, MybatisConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {SpringConfig.class, AOPConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	@Override
    protected Filter[] getServletFilters() {
        return new Filter[] {new UTF8Filter()};
    }
	
}
