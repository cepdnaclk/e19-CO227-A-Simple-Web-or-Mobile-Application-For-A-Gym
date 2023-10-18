
package com.e19co227.gymhub.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
           // TODO: RegeX to validate email
        return true;
    }
}

