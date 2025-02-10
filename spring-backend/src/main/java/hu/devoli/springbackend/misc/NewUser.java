package hu.devoli.springbackend.misc;

public class NewUser {
    private String name;
    private String email;
    private String password;

    public NewUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
