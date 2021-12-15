package com.example.stocktrackerapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "user_table", uniqueConstraints = @UniqueConstraint(columnNames = {"email", "username"}))
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstname;
    private String lastname;

    @Column(nullable = false)
    private String email;
    private String password;

    @Column(nullable = false)
    private String username;

    private String date_of_birth;
    private String number;
}
