package org.catshake.photosharing.web.user;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Data
@ToString(exclude = {
        "password",
        "confirmPassword"
})
public class UserForm {
    @NotNull
    @Pattern(regexp = "[A-Z ]{3,30}", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String name;
    @NotNull
    @Pattern(regexp = "[A-Z0-9]{4,30}", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String username;
    @NotNull
    @Pattern(regexp = "[A-Z0-9]{6,30}", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String password;
    @NotNull
    @Pattern(regexp = "[A-Z0-9]{6,30}", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String confirmPassword;

    @AssertTrue(message = "password confirmation must be equal to password")
    public boolean getPasswordMatch() {
        return Objects.equals(password, confirmPassword);
    }
}
