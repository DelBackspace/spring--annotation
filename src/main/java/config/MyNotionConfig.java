package config;

import myAnotion.MyRequestMappingProcess;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: pengjianzhou
 * @Description:
 * @Date: Created in 上午9:06 18-5-31
 */
@Configuration
public class MyNotionConfig {


    @Bean
    public MyRequestMappingProcess myRequestMappingProcess(){
        return new MyRequestMappingProcess();
    }
}
