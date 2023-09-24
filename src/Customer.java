import java.io.Serializable;

public class Customer implements Serializable {
    private  String name ;
    private  String  email ;
    Customer(String name,String email ){
        this.name = name;
        this.email = email;
    }
    public  String getName(){
        return  name;
    }


}
