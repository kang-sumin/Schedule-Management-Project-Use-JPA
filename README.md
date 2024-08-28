# Schedule-Management-Project-Use-JPA
JPA를 활용한 upgrade 일정 관리 앱 서버 만들기

&nbsp;
&nbsp;

## **요구사항**

### 1단계

기능: 일정 CRU 학습목표: JPA 입문

### 조건

1.  일정을 저장, 단건 조회, 수정할 수 있습니다.
2.  일정은 작성 유저명, 할일 제목, 할일 내용, 작성일, 수정일 필드를 갖고 있습니다.

### 2단계

기능: 댓글 CRUD 학습목표: 연관관계 기본

### 조건

1.  일정에 댓글을 달 수 있습니다.
    1.  댓글과 일정은 연관관계를 가집니다.
2.  댓글을 저장, 단건 조회, 전체 조회, 수정, 삭제할 수 있습니다.
3.  댓글은 댓글 내용, 작성일, 수정일, 작성 유저명 필드를 갖고 있습니다.

### 3단계

기능: 일정 페이징 조회 학습목표: 페이징/정렬

### 조건

1.  일정을 Spring Data JPA의 Pageable과 Page 인터페이스를 활용하여 페이지네이션을 구현해주세요.
    1.  페이지 번호와 페이지 크기를 쿼리 파라미터로 전달하여 요청하는 항목을 나타냅니다.
    2.  할일 제목, 할일 내용, 댓글 개수, 일정 작성일, 일정 수정일, 일정 작성 유저명 필드를 조회합니다.
    3.  디폴트 페이지 크기는 10으로 적용합니다.
2.  일정의 수정일을 기준으로 내림차순 정렬합니다.

### 4단계

기능: 일정 삭제 학습목표: 영속성 전이

### 조건

1.  일정을 삭제할 때 일정의 댓글도 함께 삭제됩니다.
    1.  JPA의 영속성 전이 기능을 활용합니다.

### 5단계

기능: 유저 CRUD 학습목표: 연관관계 심화

### 조건

1.  유저를 저장, 단건 조회, 전체 조회, 삭제할 수 있습니다.
    1.  유저는 유저명, 이메일, 작성일, 수정일 필드를 갖고 있습니다.
2.  일정은 이제 작성 유저명 필드 대신 유저 고유 식별자 필드를 가집니다.
3.  일정을 작성한 유저는 추가로 일정 담당 유저들을 배치할 수 있습니다.
    1.  유저와 일정은 N:M 관계입니다. (@ManyToMany 사용 금지!)

### 6단계

기능: 일정 조회 개선 학습목표: 지연 로딩

### 조건

1.  일정 단건 조회 시 담당 유저들의 고유 식별자, 유저명, 이메일이 추가로 포함됩니다.
2.  일정 전체 조회 시 담당 유저 정보가 포함되지 않습니다.
    1.  JPA의 지연 로딩 기능을 활용합니다.

---

## **API 명세서**
![scheduleAPI](https://github.com/user-attachments/assets/a0657707-b1fa-4c68-90db-27629708edc3)
![commentAPI](https://github.com/user-attachments/assets/f1af627a-fb70-4d86-8e95-98e11df6094d)
![userAPI](https://github.com/user-attachments/assets/82729234-205b-4ba4-9108-d9a0b1c40ebd)


---

## **ERD**
![Blank diagram - Page 1 (1)](https://github.com/user-attachments/assets/91c1d06c-6440-42a2-9b0a-68ef8e50eb18)
![comment](https://github.com/user-attachments/assets/2bf2f089-24ef-451f-9846-bd7156d9dac3)

---

## **SQL**

```
CREATE TABLE USER(
    id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    user VARCHAR(20) NOT NULL ,
    email VARCHAR(100) NOT NULL ,
    create_at DATETIME NOT NULL ,
    modified_at DATETIME NOT NULL
)
```

```
CREATE TABLE SCHEDULE(
    id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    user_id BIGINT NOT NULL ,
    title VARCHAR(100) NOT NULL ,
    contents VARCHAR(500) NOT NULL ,
    create_at DATETIME NOT NULL ,
    modified_at DATETIME NOT NULL ,
    FOREIGN KEY (user_id) REFERENCES USER(id)
)
```

```
CREATE TABLE COMMENT(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    contents VARCHAR(500) NOT NULL ,
    create_at DATETIME NOT NULL ,
    modified_at DATETIME NOT NULL ,
    user_id BIGINT NOT NULL ,
    schedule_id BIGINT NOT NULL ,
    FOREIGN KEY (user_id) REFERENCES USER(id),
    FOREIGN KEY (schedule_id) REFERENCES SCHEDULE(id)
)
```

```
CREATE TABLE USER_SCHEDULE(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL ,
    schedule_id BIGINT NOT NULL ,
    FOREIGN KEY (user_id) REFERENCES USER(id),
    FOREIGN KEY (schedule_id) REFERENCES SCHEDULE(id)
)
```
