public class User
{
    int ID;
    int password;
    String role;


    public User()
    {
        ID = 000;
        password = 111;
        role = "Default";
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(int i , int p , String r)
    {
        ID = i;
        password = p;
        role = r;   
    }

    public String toString()
    {
        return "ID: " + ID + "Password: " + password + "Role: " + role;
    }

}