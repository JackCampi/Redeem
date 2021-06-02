package es.nacho.redeem.web.dto.employee;

public class ChangePasswordDto {

    private String oldPassword;
    private String newPassord;
    private String confirmPassword;

    public ChangePasswordDto() {
    }

    public ChangePasswordDto(String oldPassword, String newPassord, String confirmPassword) {
        this.oldPassword = oldPassword;
        this.newPassord = newPassord;
        this.confirmPassword = confirmPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassord() {
        return newPassord;
    }

    public void setNewPassord(String newPassord) {
        this.newPassord = newPassord;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
