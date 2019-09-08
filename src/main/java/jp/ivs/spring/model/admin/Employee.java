package jp.ivs.spring.model.admin;

public class Employee
{
    int id, departId, level;
    String name;
    String birthday;
    String photo;
    String email;
    String phone;
    String notes;
    String departName;
    boolean gender;
    double salary;

    //region Hàm Tạo()
    public Employee() { }
    public Employee(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
    public Employee( int departId
            , String departName
            , String name
            , String birthday
            , String photo
            , String email
            , String phone
            , String notes
            , boolean gender
            , int level
            , double salary
    )   {
        this.departId = departId;
        this.departName = departName;
        this.name = name;
        this.birthday = birthday;
        this.photo = photo;
        this.email = email;
        this.phone = phone;
        this.notes = notes;
        this.gender = gender;
        this.level = level;
        this.salary = salary;
    }
    //endregion

    //region setter&getter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getDepartId() { return departId; }
    public void setDepartId(int departId) { this.departId = departId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBirthday() { return birthday; }
    public void setBirthday(String Birthday) { this.birthday = Birthday; }

    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public boolean getGender() { return gender; }
    public void setGender(boolean gender) { this.gender = gender; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getDepartName() { return departName; }
    public void setDepartName(String departName) { this.departName = departName; }
    //endregion
}
