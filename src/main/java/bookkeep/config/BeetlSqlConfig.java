package bookkeep.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
public class BeetlSqlConfig {
    
	@Bean("dataSource")
	@Primary
	@ConfigurationProperties("spring.datasource.druid")
	public DataSource dataSource(){
		return DruidDataSourceBuilder.create().build();
	}

}