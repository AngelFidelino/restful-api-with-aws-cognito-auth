package org.alfr.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.alfr.dto.AwsSecret;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends AwsSecret{
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private Integer age;
    private String email;
}
