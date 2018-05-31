package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by miracle on 17-8-2.
 */
@Configuration
@ImportResource(locations = "classpath:spring/*xml")
public class ImportConfig {
}
