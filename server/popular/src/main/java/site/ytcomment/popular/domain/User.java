package site.ytcomment.popular.domain;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String email;
}