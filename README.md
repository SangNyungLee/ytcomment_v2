
# 📺 Youtube Comment 📺

> 🎯 인기 영상의 최고 인기 댓글을 알고리즘 없이 한 눈에! 👉 https://ytcomment.kr/

![image](https://github.com/user-attachments/assets/053ff231-e756-4797-9291-df8eba35a9df)

## 🔗 Service Link

- **Live Service**: [https://ytcomment.kr](https://ytcomment.kr)
### 🧪 Test Account
```
ID: test
PW: test!
```

## 📖 Description

**Youtube Comment**는 유튜브 급상승 영상들을 댓글과 함께 한눈에 보여주는 서비스입니다.  
알고리즘 없이 정제된 인기 댓글을 기반으로, **댓글 중심의 소비 경험**을 제공합니다.

## 🖥 Screenshots
|메인 페이지|로그인페이지|회원가입페이지|
|-|-|-|
|![image](https://github.com/user-attachments/assets/2fde38a0-63d9-4097-84a3-a9079d818ca3)|![image](https://github.com/user-attachments/assets/6e87a9c1-5280-460c-a6ef-a9cf214d1bd6)|![image](https://github.com/user-attachments/assets/9cc55ec2-d952-4f7e-95db-99683b81f049)|
|마이 페이지|상세 페이지|검색 페이지|
|![image](https://github.com/user-attachments/assets/4a4e9785-3cb9-4783-adf8-51a7a1df5ee2)|![image](https://github.com/user-attachments/assets/d9945827-c58a-4146-ab60-4bd1158eb214)|![image](https://github.com/user-attachments/assets/0a07fd34-46a5-4a03-882c-ae95f727f508)|


## ⭐ Main Features

### 🔥 Trending Video Comments
- 실시간 급상승 영상 & 인기 댓글 보기
- 카테고리별 영상 필터링
- 댓글 정렬 (좋아요순 / 최신순)
- 영상 상세 페이지에서 설명, 태그, 댓글 확인
- 영상 스크랩(저장) 기능
- 영상 사이트에서 직접 실행(Embed)
- 모바일UI 반응형 서비스
  
### 🧑‍💼 User Features
- 마이페이지에서 닉네임/비밀번호 수정, 회원 탈퇴
- 스크랩 영상 모아보기
- 키워드 기반 영상 검색 (검색 결과도 스크랩/상세 가능)

### 🔐 Auth & Security
- 이메일 회원가입 (JWT 기반 인증)
- 이메일 인증 (Google SMTP)
- 카카오 소셜 로그인


## 💻 Getting Started

### Frontend

```bash
cd /ytcomment_v2/client

# Install
npm install

# Dev mode
npm run dev

# Production build
npm run build
```

### BE server
```bash
cd /ytcomment_v2/server/popular

# Run Spring Boot Application
# PopularApplication 실행
```

## 🛠 Tech Stack

| Category  | Tech                              |
|-----------|-----------------------------------|
| Language  | Java, JavaScript                  |
| Framework | Spring Boot, React (Vite)         |
| DB        | AWS RDS (MySQL)                   |
| Caching   | Redis                              |
| Deploy    | AWS EC2                           |
| Auth      | JWT, Google SMTP, Kakao Login     |


## 🔨 Server Architecture
![image](https://github.com/user-attachments/assets/c6a3dd81-1386-4dd5-b8a3-e24f10c4fa82)
