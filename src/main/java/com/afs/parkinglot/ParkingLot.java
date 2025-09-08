package com.afs.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    private final Map<ParkingTicket, Car> parkedCars;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkedCars = new HashMap<>();
    }

    public ParkingTicket park(Car car) {
        if (parkedCars.size() >= capacity) {
            throw new RuntimeException("No available position.");
        }
        ParkingTicket ticket = new ParkingTicket();
        parkedCars.put(ticket, car);
        return ticket;
    }

    public Car fetch(ParkingTicket ticket) {
        if (ticket == null || !parkedCars.containsKey(ticket)) {
            throw new RuntimeException("Unrecognized parking ticket.");
        }
        return parkedCars.remove(ticket);
    }

    public boolean hasAvailablePosition() {
        return parkedCars.size() < capacity;
    }
}
