package enums;

public enum LoginInfo {

    CORRECT_MAIL("deneme123kadir@hotmail.com"),
    WRONG_MAIL("kadir12345@gmail.com"),
    CORRECT_PASSWORD("1Q2w3e4r5t"),
    WRONG_PASSWORD("123asd");

    private String loginInfoValue;

    LoginInfo(String loginInfoValue) {
        this.loginInfoValue = loginInfoValue;
    }

    public String getLoginInfo() {
        return loginInfoValue;
    }


}
