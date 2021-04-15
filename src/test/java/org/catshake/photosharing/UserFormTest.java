package org.catshake.photosharing;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserFormTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

    @Test
    void test1() {
        UserForm userForm = new UserForm();
        userForm.setPassword("abc");
        userForm.setConfirmPassword("ABCd");
        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

        violations.forEach(it -> System.out.println(it));
    }
}