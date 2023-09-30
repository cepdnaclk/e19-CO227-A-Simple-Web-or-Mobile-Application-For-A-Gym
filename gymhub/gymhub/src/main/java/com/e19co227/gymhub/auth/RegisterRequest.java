package com.e19co227.gymhub.auth;

import com.e19co227.gymhub.appuser.AppUserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String userName;
    private String fullName;
    private String email;
    private String password;
    private AppUserRole appUserRole;
    private String contactNumber;
    private String nic;

}
