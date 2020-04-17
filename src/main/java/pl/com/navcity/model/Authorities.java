package pl.com.navcity.model;

public enum Authorities {

    ROLE_DRIVER("Driver"),
    ROLE_ADMIN("Admin"),
    ROLE_MANAGER("Manager");

    final String value;

    Authorities(String value) {
        this.value = value;
    }

    public String getName(){
        return value;
    }
}


