package org.eitan.comments;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Comment {

    @Id
    private String id;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String content;

    @LastModifiedDate
    private long timestamp;

}
