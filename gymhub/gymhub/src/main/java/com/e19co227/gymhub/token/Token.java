package com.e19co227.gymhub.token;


import com.e19co227.gymhub.appuser.AppUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a token entity associated with an application user for authentication and authorization.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {

    // The unique identifier for the token.
    @Id
    @GeneratedValue
    public Integer id;

    // The token value, which is typically a JWT (JSON Web Token).
    @Column(unique = true)
    public String token;

    // The type of token (e.g., Bearer token).
    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    // Indicates whether the token has been revoked (true) or not (false).
    public boolean revoked;

    // Indicates whether the token has expired (true) or is still valid (false).
    public boolean expired;

    // The application user associated with the token.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    public AppUser appUser;
}