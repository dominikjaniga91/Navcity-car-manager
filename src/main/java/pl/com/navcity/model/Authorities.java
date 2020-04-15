package pl.com.navcity.model;

public enum Authorities {

    ROLE_DRIVER("Driver"),
    ROLE_ADMIN("Admin"),
    ROLE_MANAGER("Manager");

    String value;

    Authorities(String value) {
        this.value = value;
    }

    String getName(){
        return value;
    }
}
