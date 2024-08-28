CREATE TABLE SCHEDULE(
    id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    user_id BIGINT NOT NULL ,
    title VARCHAR(100) NOT NULL ,
    contents VARCHAR(500) NOT NULL ,
    create_at DATETIME NOT NULL ,
    modified_at DATETIME NOT NULL ,
    FOREIGN KEY (user_id) REFERENCES USER(id)
)