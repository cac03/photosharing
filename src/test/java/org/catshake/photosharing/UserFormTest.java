package org.catshake.photosharing;

import org.catshake.photosharing.web.user.UserForm;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class UserFormTest {
    @SuppressWarnings("resource")
    private final Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();

    @Test
    void validationFailsWhenPasswordConfirmationIsNotEqualToPassword() {
        UserForm userForm = new UserForm();
        userForm.setPassword("abc");
        userForm.setConfirmPassword("ABCd");
        Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);

        assertThat(violations)
                .filteredOn(it -> it.getPropertyPath().toString().equals("passwordMatch"))
                .hasSize(1)
                .allSatisfy(it -> assertThat(it.getMessage()).isEqualTo("password confirmation must be equal to password"));
    }
}