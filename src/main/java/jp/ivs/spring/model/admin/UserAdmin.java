package jp.ivs.spring.model.admin;

public class UserAdmin
{
    String username, password, fullName;

    //region Hàm Tạo ()
    public UserAdmin() { }
    public UserAdmin(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    public UserAdmin(String username, String password, String fullName)
    {
        this(username, password);
        this.setFullName(fullName);
    }
    //endregion
    //region getter&setter
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    //endregion
	public static String uploadImages = System.getProperty("upload.Directory") + "/img";
}
