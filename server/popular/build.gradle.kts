plugins {
	java
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "site.ytcomment"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.4")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	//spring mail
	implementation ("org.springframework.boot:spring-boot-starter-mail")
	//redis
	implementation ("org.springframework.boot:spring-boot-starter-data-redis")
	testImplementation("io.projectreactor:reactor-test")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.4")
	runtimeOnly("com.mysql:mysql-connector-j")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	// Junit, Mockito 추가
	testImplementation ("org.junit.jupiter:junit-jupiter-api:5.8.2")
	testImplementation ("org.junit.jupiter:junit-jupiter-engine:5.8.2")
	testImplementation ("org.mockito:mockito-core:4.5.1")
	testImplementation ("org.mockito:mockito-junit-jupiter:4.5.1")
	testImplementation ("org.assertj:assertj-core:3.22.0")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
