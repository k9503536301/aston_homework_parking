package ru.aston.util;

import ru.aston.dao.*;
import ru.aston.model.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ParkingMenuUtil {
    public static void printMainMenu() {
        System.out.println("Выберите таблицу:");
        System.out.println("1. Пользователи");
        System.out.println("2. Парковочные талоны");
        System.out.println("3. Парковочные места");
        System.out.println("4. Машины");
        System.out.println("5. Выход");
    }

    public static int readChoice() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static <T extends ParkingObject> void displaySubMenu(BaseDao<T> dao) {
        while(true)  {
            System.out.println("\nВыберите действие: \n" +
                    "1. Добавить запись\n" +
                    "2. Удалить запись\n" +
                    "3. Найти запись по id\n" +
                    "4. Вывести таблицу\n"+
                    "5. Назад\n");
            Scanner scanner = new Scanner(System.in);
            int j = scanner.nextInt();

            switch (j){

                case 1:{
                    addRecord(dao);
                    break;
                }

                case 2:{
                    deleteRecordById(dao);
                    break;
                }

                case 3:{
                    findRecordById(dao);
                    break;
                }

                case 4:{
                    listRecords(dao);
                    break;
                }

                case 5:{
                    return;
                }
                default:{System.out.println("Wrong key");}
            }
        }
    }

    private static <T extends ParkingObject> void addRecord(BaseDao<T> dao){
        String daoName = dao.getClass().getSimpleName();

        switch (daoName){
            case "UserDao":{addNewUser((UserDao) dao); break;}
            case "TicketDao": {addNewTicket((TicketDao) dao);break;}
            case "ParkingSpotDao":{addNewParkingSpot((ParkingSpotDao) dao);break;}
            case "VehicleDao":{addNewVehicle((VehicleDao) dao);break;}
        }
    }

    private static void addNewUser(UserDao dao) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();

        System.out.println("Enter first name: ");
        user.setFirstName(scanner.next());

        System.out.println("Enter last name: ");
        user.setLastName(scanner.next());

        System.out.println("Enter email: ");
        user.setEmail(scanner.next());

        dao.save(user);
        System.out.println("User was added successfully!");
    }

    private static void addNewTicket(TicketDao dao) {
        Scanner scanner = new Scanner(System.in);
        Ticket ticket = new Ticket();

        System.out.println("carID: ");
        int vehicleId = scanner.nextInt();
        System.out.println("parkSpotId: ");
        int parkSpotId = scanner.nextInt();
        System.out.println("Часов парковки: ");
        int parkingTimeInHours = scanner.nextInt();

        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId);

        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setId(parkSpotId);

        ticket.setVehicle(vehicle);
        ticket.setParkingSpot(parkingSpot);
        ticket.setParkingTimeInHours(parkingTimeInHours);

        dao.save(ticket);
        System.out.println("Ticket was added successfully!");
    }

    private static void addNewVehicle(VehicleDao vehicleDao) {
        Vehicle vehicle = new Vehicle();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номерной знак: \n" );
        vehicle.setPlate(scanner.next());

        System.out.println("Введите модель машины: \n" );
        vehicle.setModel(scanner.next());
        System.out.println("Введите Id владельца: \n" );
        int ownerId = scanner.nextInt();

        User owner = new User();
        owner.setId(ownerId);

        vehicle.setOwner(owner);
        vehicleDao.save(vehicle);

        System.out.println("Vehicle was added successfully!");
    }

    private static void addNewParkingSpot(ParkingSpotDao dao) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter spot number: ");
        int spotNumber = scanner.nextInt();

        ParkingSpot spot = new ParkingSpot();
        spot.setSpotNumber(spotNumber);
        spot.setAvailable(true);

        dao.save(spot);
        System.out.println("Parking spot was added successfully!");
    }

    private static <T extends ParkingObject> void listRecords(BaseDao<T> dao) {
        List<T> records = dao.findAll();
        String objName = getObjectName(dao.getClass());

        if (records.isEmpty()) {
            System.out.println("No "+ objName +"s found.");
        } else {
            records.stream().map(T::toString).forEach(System.out::println);
        }
    }

    private static <T> void findRecordById(BaseDao<T> dao) {
        Scanner scanner = new Scanner(System.in);
        String objName = getObjectName(dao.getClass());
        System.out.print("Enter "+objName+" id: ");

        int recordId = scanner.nextInt();
        Optional<T> optRecord = dao.findById(recordId);

        optRecord.ifPresentOrElse(
                item -> System.out.println(item.toString()),
                () -> System.out.println("No record found.")
        );
    }

    private static <T> void deleteRecordById(BaseDao<T> dao) {
        Scanner scanner = new Scanner(System.in);
        String objName = getObjectName(dao.getClass());
        System.out.print("Enter "+objName+" id: ");

        int recordId = scanner.nextInt();
        dao.deleteById(recordId);

        System.out.print(objName+" record was successfully deleted");
    }

    private static String getObjectName(Class<?> cls) {
        String className = cls.getSimpleName();
        return className.substring(0,className.length()-3);
    }
}
