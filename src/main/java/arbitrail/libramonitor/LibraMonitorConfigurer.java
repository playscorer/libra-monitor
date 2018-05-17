/*package arbitrail.libramonitor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LibraMonitorConfigurer implements WebMvcConfigurer {

	// file:/Users/Filipe/Documents/Work-Trader/cryptobot.libra/logdir/
	@Value("${logdirpath}")
	private String LOGDIRPATH;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations(LOGDIRPATH);
	}

	
	
}
*/