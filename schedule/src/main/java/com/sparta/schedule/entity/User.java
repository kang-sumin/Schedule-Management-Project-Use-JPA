package com.sparta.schedule.entity;

import com.sparta.schedule.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="user")
public class User extends Timestamped{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user", nullable = false, length = 20)
    private String user;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    public User(UserRequestDto requestDto) {
        this.user = requestDto.getUser();
        this.email = requestDto.getEmail();
    }

}
