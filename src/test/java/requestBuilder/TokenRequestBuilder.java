package requestBuilder;

public class TokenRequestBuilder {

    private String usernameOrEmailAddress;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsernameOrEmailAddress(String usernameOrEmailAddress) {
        this.usernameOrEmailAddress = usernameOrEmailAddress;
    }

    public String getUsernameOrEmailAddress() {
        return usernameOrEmailAddress;
    }
}
