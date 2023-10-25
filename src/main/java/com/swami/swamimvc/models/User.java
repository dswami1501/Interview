package com.swami.swamimvc.models;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(schema = "Interview")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @NotBlank(message = "User name cannot be blank")
    @Size(min = 1, max = 255, message = "User name must be between 1 and 255 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "User name can only contain alphabetic characters")
    private String userName;
    @OneToMany(mappedBy = "user")
    private List<Comments> comments;
}
