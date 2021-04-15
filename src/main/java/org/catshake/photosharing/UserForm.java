package org.catshake.photosharing;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserForm {
    @NotNull
    @Pattern(regexp = "[A-Z ]{6,30}", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String name;
    @NotNull
    @Pattern(regexp = "[A-Z]{6,30}", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String username;
    @NotNull
    @Pattern(regexp = "[A-Z0-9]{6,30}", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String password;
    @NotNull
    @Pattern(regexp = "[A-Z0-9]{6,30}", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String confirmPassword;

    @AssertTrue
    public boolean arePasswordsEqual() {
        return password.equals(confirmPassword);
    }
}
