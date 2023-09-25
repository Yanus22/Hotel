import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;

public class FileRead {
    public Map<String, Customer> readCustomerData(String path) {
        Map<String, Customer> customerData = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))) {

            Object object = objectInputStream.readObject();

            if (object instanceof Map) {
                customerData = (Map<String, Customer>) object;
            } else {
                System.err.println("File does not contain a valid map of customer data.");
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return customerData;
    }




    public List<Room> ReadInRoom(String path) {
        List<Room> listResult = null;

        try (FileInputStream fileInputStream = new FileInputStream(path);
             ObjectInputStream reader = new ObjectInputStream(fileInputStream)) {

            Object object = reader.readObject();

            if (object instanceof List) {
                listResult = (List<Room>) object;
            } else {
                System.out.println("File does not contain a list of Room objects.");
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading from the file: " + e.getMessage());
        }

        return listResult;
    }


}
