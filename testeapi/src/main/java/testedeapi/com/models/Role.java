package testedeapi.com.models;

public enum Role {
    USER("user"),
    ADMIN("admin");
    
    private String role;
    
    Role(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }

}
