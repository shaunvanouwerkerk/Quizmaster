package model;

public class User {

    private int idUser;
    private String password;
    private String username;
    private String roleName;

    public User(int idUser, String password, String username, String roleName) {
        this.idUser = idUser;
        this.password = password;
        this.username = username;
        this.roleName = roleName;
    }

    public User(String password, String username, String roleName) {
        this(0, password, username, roleName);
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder("");
        resultString.append(idUser + " ");
        resultString.append(username + " ");
        resultString.append(roleName);
        return resultString.toString();
    }

    //Getters and Setters
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
