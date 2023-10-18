
package com.e19co227.gymhub.registration.token;

import com.e19co227.gymhub.appuser.AppUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

// Entity class representing a confirmation token used during user registration.
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ConfirmationToken {

    // Database sequence generator for generating unique token IDs.
    @SequenceGenerator(
            name = "confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1
    )

    // Unique identifier for the confirmation token.
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_sequence"
    )
    private Integer id;

    // Token value used for email verification.
    @Column(nullable = false)
    private String token;

    // Timestamp indicating when the token was created.
    private LocalDateTime createdAt;

    // Timestamp indicating when the token will expire.
    private LocalDateTime expiresAt;

    // Timestamp indicating when the token was confirmed (used for email verification).
    private LocalDateTime confirmedAt;

    // Many-to-One relationship with the associated AppUser entity.
    @ManyToOne
    @JoinColumn(nullable = false,
            name = "app_user_id"
    )

    private AppUser appUser;

    // Constructor for creating a confirmation token with specified values.
    public ConfirmationToken(
            String token,
            LocalDateTime createdAt,
            LocalDateTime expiresAt,
            AppUser appUser ) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }
}

