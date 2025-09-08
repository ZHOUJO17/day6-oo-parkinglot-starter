package com.afs.parkinglot;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ParkingLot {
    private static final String NO_VALID_PARKING_SPACE_ERROR_MSG = "No available position";
    private Map<ParkingTicket,Car> parkingTicketCarMap = new HashMap<>();
    private int capacity;
    ParkingTicket park(Car car) {
        if(capacity <= 0) throw new RuntimeException(NO_VALID_PARKING_SPACE_ERROR_MSG);
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
            return parkingTicketCarMap.get(parkingTicket);
        }
        return null;
    }
}
