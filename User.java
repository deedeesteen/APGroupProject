public class User {
    int ID;
    int password;
    String role;
    String name;

    public User() {
        ID = 000;
        password = 111;
        role = "Default";
        name = "Default";
    }

    public User(int i, int p, String r) {
        ID = i;
        password = p;
        role = r;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "ID: " + ID + "Password: " + password + "Name: " + name + "Role: " + role;
    }

}