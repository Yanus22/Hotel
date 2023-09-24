import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Hotel implements Serializable {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    Scanner scanner = new Scanner(System.in);
    Scanner scanner1 = new Scanner(System.in);
    Map<String, Customer> mapCustomer;
    List<Room> listRoom;
    List<Long> IdForSingle = new ArrayList<>();
    List<Long> IdForSDouble = new ArrayList<>();
    List<Long> IdForDelyux = new ArrayList<>();

    Hotel() {
        ReadFileList();
        ReadFileCustomer();
        if (mapCustomer.isEmpty()) {
            System.out.println("costumer is empty");
        }
        System.out.println("Do you want to make a reservation?");
        String answer = scanner.nextLine().replaceAll(" ", "");
        if (answer.contains("yes")) {
            HelloApplication();
        }
        System.out.println("Number of customers: " + mapCustomer.size());
    }


    private void HelloApplication() {
        Customer customer = null;
        String name;
        String email;
        while (true) {
            System.out.println("Please enter your name:");
            name = scanner.nextLine();
            System.out.println("Please enter your email:");
            email = scanner.nextLine();
            if (mapCustomer.get(name) == null) {
                System.out.println("need registartion try again");
                continue;
            } else {
                customer = new Customer(name, email);
                break;
            }
        }
        System.out.println("Which type of room do you want to reserve?");
        System.out.println(new SingleRoom().toString() + " (Type 1)");
        System.out.println(new DoubleRoom().toString() + " (Type 2)");
        System.out.println(new DeLyux().toString() + " (Type 3)");
        int answer = scanner.nextInt();
        Reserve(answer, customer);
    }

    private void Reserve(int answer, Customer customer) {
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.now();
        boolean isReserved = false;
        int days = 0;
        while (true) {
            try {
                System.out.println("When do you want to reserve (yyyy-MM-dd)?");
                String dateString = scanner1.nextLine();
                date2 = LocalDate.parse(dateString, formatter);
                if (date2.isBefore(date1)) {
                    System.out.println("invalud Date  ");
                    throw new RuntimeException();
                }
                System.out.println("For how many days?");
                days = scanner.nextInt();
                int idAnswer = scanner.nextInt();

                for (Room element : listRoom) {
                    if ((answer == 1 && listRoom instanceof SingleRoom) ||
                            (answer == 2 && listRoom instanceof DoubleRoom) ||
                            (answer == 1 && listRoom instanceof SingleRoom)) {
                        if (idAnswer == element.id) {
                            if (element.ReserveRoom(date2, days, customer)) {
                                System.out.println("room is reserved");
                            }
                        }
                    }


                }

                break; // Exit the loop if parsing is successful
            } catch (DateTimeException e) {
                System.out.println("Invalid date format. Please enter in yyyy-MM-dd format.");
                // Clear the scanner buffer
                scanner1.nextLine();
            }
        }
        mapCustomer.put(customer.getName(), customer);
        WriteFileList();
        WriteFileCustomer();

    }

    public void ReadFileList() {
        try (ObjectInputStream read = new ObjectInputStream(new FileInputStream("/home/yanus/IdeaProjects/ExamJava/RoomFile.txt"))) {
            listRoom = (List<Room>) read.readObject();
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    public void ReadFileCustomer() {
        try (ObjectInputStream read = new ObjectInputStream(new FileInputStream("/home/yanus/IdeaProjects/ExamJava/src/Costumer"))) {
            mapCustomer = (Map<String, Customer>) read.readObject();
            System.out.println(mapCustomer);
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    private void WriteFileList() {
        try (ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream("/home/yanus/IdeaProjects/ExamJava/RoomFile.txt"))) {
            write.writeObject(listRoom);
            write.flush();
        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    private void WriteFileCustomer() {
        try (ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream("/home/yanus/IdeaProjects/ExamJava/src/Costumer"))) {
            write.writeObject(mapCustomer);
        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
}

