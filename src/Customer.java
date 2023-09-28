import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Customer implements Serializable {
    private String name;
    private String email;

    Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
