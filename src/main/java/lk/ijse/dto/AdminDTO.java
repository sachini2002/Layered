package lk.ijse.dto;

public class AdminDTO {
    private String userid;
    private String username;
    private String email;
    private String pw;

    // Constructor
    public AdminDTO(String userid, String username, String email, String pw) {
        this.userid = userid;
        this.username = username;
        this.email = email;
        this.pw = pw;
    }

    // Getter methods
    public String getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPw() {
        return pw;
    }

    // Setter methods
    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    // toString method
    @Override
    public String toString() {
        return "Admin{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", pw=" + pw +
                '}';
    }
}
