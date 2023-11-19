package org.alfr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String name;
    private String lastName;
    private Integer age;
    private String email;
}
