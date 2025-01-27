package ru.aston;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    Выбери номер команды:
                    1. Все талоны
                    2. Добавить талон"
                    3. ----
                    4. Удалить талон по ID""");

            int command = scanner.nextInt();

            switch (command) {
                case 1 -> {
                    List<Ticket> ticketList = CreateReadUpdateDelete.getTicketData();
                    System.out.println(ticketList);
                    break;
                }
                case 2 -> {
                    System.out.println("userID: ");
                    int userId = scanner.nextInt();
                    System.out.println("carID: ");
                    int carId = scanner.nextInt();
                    System.out.println("parkSpotId: ");
                    int parkSpotId = scanner.nextInt();
                    System.out.println("Часов парковки: ");
                    int parkingTimeInHours = scanner.nextInt();


                    Ticket ticket = new Ticket();
                    ticket.setUserId(userId);
                    ticket.setCarId(carId);
                    ticket.setParkSpotId(parkSpotId);
                    ticket.setParkingTimeInHours(parkingTimeInHours);

                    System.out.println(CreateReadUpdateDelete.insertTicket(ticket));
                    break;
                }
                case 3 -> {

                }
                case 4 -> {
                    System.out.println("Введите ID:");
                    int id = scanner.nextInt();
                    System.out.println(CreateReadUpdateDelete.deleteTicket(id));
                    break;
                }
            }
        }
    }
}