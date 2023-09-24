import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Booking  implements Serializable {
    Map<String, Customer> mapCustomer = new HashMap<>();
    List<Room> listRoom = Arrays.asList(new SingleRoom(), new SingleRoom(), new SingleRoom(), new SingleRoom(), new SingleRoom(), new SingleRoom(),
            new DeLyux(), new DeLyux(), new DeLyux(), new DeLyux(), new DoubleRoom(), new DoubleRoom(), new DoubleRoom(), new DoubleRoom(), new DoubleRoom(), new DoubleRoom());
    Booking(){
        WriteFileList();
        WriteFileCostumer();
    }
    private  void WriteFileList(){
        try {
            ObjectOutputStream write= new ObjectOutputStream(new FileOutputStream("/home/yanus/IdeaProjects/ExamJava/RoomFile.txt"));
            write.writeObject(listRoom);
            write.flush();
            write.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }  private  void WriteFileCostumer(){
        try {
            ObjectOutputStream write= new ObjectOutputStream(new FileOutputStream("/home/yanus/IdeaProjects/ExamJava/src/Costumer"));
            write.writeObject(mapCustomer);
            write.flush();
            write.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
