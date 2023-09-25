import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

public class FileWrite {
    FileWrite(){}
    public  void WritInRoom(List<Room> list, String  path) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream writer = new ObjectOutputStream(fileOutputStream);
          writer.writeObject(list);
          writer.flush();
          writer.close();
          fileOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }catch (IOException e ) {
            throw new RuntimeException();
        }
    }
    public  void WriteInCoustumer(Map<String,Customer> map, String  path) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream writer = new ObjectOutputStream(fileOutputStream);
            writer.writeObject(map);
            writer.flush();
            writer.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }catch (IOException e ) {
            throw new RuntimeException();
        }
    }
}
