package com.afs.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";
    private static final String NO_AVAILABLE_POSITION = "No available position.";
    private Map<ParkingTicket,Car> parkingTicketCarMap = new HashMap<>();
    private int capacity;
    ParkingTicket park(Car car) {
        if(capacity <= 0) throw new RuntimeException(NO_AVAILABLE_POSITION);
        capacity--;
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicketCarMap.put(parkingTicket,car);
        return parkingTicket;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if(parkingTicketCarMap.containsKey(parkingTicket)){
            capacity++;
            Car car = parkingTicketCarMap.get(parkingTicket);
            parkingTicketCarMap.remove(parkingTicket);
            return car;
        }
        throw new RuntimeException(UNRECOGNIZED_PARKING_TICKET);
    }
}
