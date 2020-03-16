package pl.com.navcity.exception;

public class DriverNotFoundException extends RuntimeException {

    public DriverNotFoundException(Integer id){

        super("ID: " + id + " doesn't exits");
    }
}
