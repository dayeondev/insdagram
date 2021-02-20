## java files

---



#### [controller](https://github.com/dayeondev/insdagram/tree/main/src/main/java/com/dayeon/insdagram/controller)

* 각 URL별 요청(GET, POST 등)을 받아들이는 역할



#### [domain](https://github.com/dayeondev/insdagram/tree/main/src/main/java/com/dayeon/insdagram/domain)

* MVC 중 model 역할을 담당하며 각 model의 상세가 담겨있음.



#### [handler](https://github.com/dayeondev/insdagram/tree/main/src/main/java/com/dayeon/insdagram/handler)

* 기본 handler를 대체하는 역할
* spring security에서 로그인 시 실행되는 계정 관련 핸들러를 대체하는 파일을 포함하고 있음.



#### [repository](https://github.com/dayeondev/insdagram/tree/main/src/main/java/com/dayeon/insdagram/repository)

* DB에 접근하고 테이블의 내용을 select, insert, update, delete하는 역할
* JPA를 사용해서 처리



#### [service](https://github.com/dayeondev/insdagram/tree/main/src/main/java/com/dayeon/insdagram/service)

* 비즈니스 로직을 담당(현재는 대부분의 비즈니스 로직이 controller에서 처리되지만, 추후에 옮길 계획)



#### [SecurityConfig.java](https://github.com/dayeondev/insdagram/blob/main/src/main/java/com/dayeon/insdagram/SecurityConfig.java)

* spring security 설정



#### [SpringConfig.java](https://github.com/dayeondev/insdagram/blob/main/src/main/java/com/dayeon/insdagram/SpringConfig.java)

* ~~삭제 예정~~

