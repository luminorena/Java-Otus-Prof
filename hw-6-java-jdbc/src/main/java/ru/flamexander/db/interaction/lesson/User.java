package ru.flamexander.db.interaction.lesson;

@RepositoryTable(title = "users")
public class User {
    @RepositoryIdField
    private Long id;
    @RepositoryField("login")
    // loginParam not works
    private String loginParam;
    @RepositoryField
    private String password;
    @RepositoryField
    private String nickname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginParam() {
        return loginParam;
    }

    public void setLoginParam(String loginParam) {
        this.loginParam = loginParam;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public User() {
    }

    public User(Long id, String loginParam, String password, String nickname) {
        this.id = id;
        this.loginParam = loginParam;
        this.password = password;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + loginParam + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
