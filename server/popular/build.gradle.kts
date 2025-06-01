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
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.4")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	//spring mail
	implementation ("org.springframework.boot:spring-boot-starter-mail")
	//redis
	implementation ("org.springframework.boot:spring-boot-starter-data-redis")
	//swagger
	implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
	implementation("org.springframework.boot:spring-boot-starter-security")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.springframework.security:spring-security-test")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.4")
	runtimeOnly("com.mysql:mysql-connector-j")
	// Junit, Mockito 추가
	testImplementation ("org.assertj:assertj-core:3.22.0")
	// validation 오류
	implementation ("org.springframework.boot:spring-boot-starter-validation")
	//jwt 의존성
	implementation ("io.jsonwebtoken:jjwt-api:0.11.5") // JWT 생성을 위한 기본 api
	runtimeOnly ("io.jsonwebtoken:jjwt-impl:0.11.5") // jwt 처리에 필요한 구현체
	runtimeOnly ("io.jsonwebtoken:jjwt-jackson:0.11.5") // JSON 처리 라이브러리
	// s3 Bucket
	implementation("io.awspring.cloud:spring-cloud-aws-starter-s3:3.3.0")
	// AWS SDK BOM을 사용하여 버전 관리를 일관되게 함
	implementation(platform("software.amazon.awssdk:bom:2.31.53"))
	// SES 모듈 추가
	implementation ("software.amazon.awssdk:ses")
	// 인증 및 지역 설정을 위한 모듈
	implementation ("software.amazon.awssdk:auth")
	implementation ("software.amazon.awssdk:regions")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
