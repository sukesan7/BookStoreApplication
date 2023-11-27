package bookstore;

public class User {
  private String username;
  private String password;
  private final UserTypeEnum userType;

  public User(String username, String password, UserTypeEnum userType){
    this.username = username;
    this.password = password;
    this.userType = userType;
  }

  public String getUsername(){
    return this.username;
  }

  public String getPassword(){
    return this.password;
  }

  public UserTypeEnum getUserType(){
    return this.userType;
  }
}
