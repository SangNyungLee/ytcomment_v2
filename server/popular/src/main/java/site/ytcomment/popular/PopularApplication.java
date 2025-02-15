package site.ytcomment.popular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // 스케줄링을 사용하려면 Application에 사용한다고 명시해줘야됨
public class PopularApplication {

	public static void main(String[] args) {
		SpringApplication.run(PopularApplication.class, args);
	}

}
