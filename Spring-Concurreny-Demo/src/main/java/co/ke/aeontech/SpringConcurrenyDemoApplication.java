package co.ke.aeontech;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class SpringConcurrenyDemoApplication implements AsyncConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(SpringConcurrenyDemoApplication.class, args);
	}

	@Override
	public Executor getAsyncExecutor() {
	  ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
      executor.setCorePoolSize(4);
      executor.setMaxPoolSize(4);
      executor.setQueueCapacity(500);
      executor.setThreadNamePrefix("MyExecutor_");
      executor.initialize();
      return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new SimpleAsyncUncaughtExceptionHandler();
	}
	
}
