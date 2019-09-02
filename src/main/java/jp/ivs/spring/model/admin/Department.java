package jp.ivs.spring.model.admin;

public class Department
{
    int id; String name;
    //region Hàm Tạo()
    public Department() { }
    public Department(String name) { this.name = name; }
    //endregion

    //region getter&setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    //endregion
}
