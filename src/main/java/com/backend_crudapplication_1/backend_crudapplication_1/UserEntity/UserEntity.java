package com.backend_crudapplication_1.backend_crudapplication_1.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    String firstName;
    String lastName;
    @Id
    Long userId;
    String emailId;
    String password;
    String confirmPassword;

}
