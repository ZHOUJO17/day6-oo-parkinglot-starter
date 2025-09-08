package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    void should_get_ticket_when_park_given_enough_paring_space_and_a_car() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(10);
        ParkingTicket parkingTicket = parkingLot.park(car);
        assertNotNull(parkingTicket);
    }

    @Test
    void should_get_a_car_when_fetch_given_valid_parking_ticket() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car originalCar = new Car();
        ParkingTicket parkingTicket = parkingLot.park(originalCar);
        Car resultCar = parkingLot.fetch(parkingTicket);
        assertNotNull(resultCar);
        assertEquals(originalCar,resultCar);
    }

    @Test
    void should_get_a_car_when_multi_fetch_given_multi_valid_parking_tickets() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car originalCar = new Car();
        Car originalCar1 = new Car();
        ParkingTicket parkingTicket = parkingLot.park(originalCar);
        ParkingTicket parkingTicket1 = parkingLot.park(originalCar1);
        Car resultCar = parkingLot.fetch(parkingTicket);
        Car resultCar1 = parkingLot.fetch(parkingTicket1);
        assertNotNull(resultCar);
        assertEquals(originalCar,resultCar);
        assertNotNull(resultCar1);
        assertEquals(originalCar1,resultCar1);
    }

    @Test
    void should_get_exception_when_fetch_given_invalid_ticket() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            new ParkingLot(10).fetch(null);
        });
        RuntimeException runtimeException1 = assertThrows(RuntimeException.class, () -> {
            new ParkingLot(10).fetch(new ParkingTicket());
        });
        RuntimeException runtimeException2 = assertThrows(RuntimeException.class, () -> {
            ParkingLot parkingLot = new ParkingLot(10);
            ParkingTicket parkingTicket = parkingLot.park(new Car());
            parkingLot.fetch(parkingTicket);
            parkingLot.fetch(parkingTicket);
        });
        assertEquals("Unrecognized parking ticket.",runtimeException.getMessage());
        assertEquals("Unrecognized parking ticket.",runtimeException1.getMessage());
        assertEquals("Unrecognized parking ticket.",runtimeException2.getMessage());
    }

    @Test
    void should_get_exception_when_park_given_unavailable_position() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            new ParkingLot(0).park(new Car());
        });
        assertEquals("No available position.",runtimeException.getMessage());
    }

    @Test
    void should_get_a_parking_ticket_when_park_given_standard_paring_boy_and_a_parking_lot_and_a_car() {
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(new ParkingLot(10));
        ParkingTicket parkingTicket = standardParkingBoy.park(new Car());
        assertNotNull(parkingTicket);
    }

    @Test
    void should_get_a_car_when_fetch_given_standard_parking_boy_and_valid_parking_ticket() {
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(new ParkingLot(10));
        Car originalCar = new Car();
        ParkingTicket parkingTicket = standardParkingBoy.park(originalCar);
        Car resultCar = standardParkingBoy.fetch(parkingTicket);
        assertNotNull(resultCar);
        assertEquals(originalCar, resultCar);
    }

    @Test
    void should_get_right_cars_when_fetch_twice_given_standard_parking_boy_and_two_parking_tickets() {
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(new ParkingLot(10));
        Car originalCar = new Car();
        Car originalCar1 = new Car();
        ParkingTicket parkingTicket = standardParkingBoy.park(originalCar);
        ParkingTicket parkingTicket1 = standardParkingBoy.park(originalCar1);
        Car resultCar = standardParkingBoy.fetch(parkingTicket);
        Car resultCar1 = standardParkingBoy.fetch(parkingTicket1);
        assertNotNull(resultCar);
        assertEquals(originalCar, resultCar);
        assertNotNull(resultCar1);
        assertEquals(originalCar1, resultCar1);
    }

    @Test
    void should_get_exception_when_fetch_given_standard_parking_boy_and_wrong_parking_ticket() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            StandardParkingBoy standardParkingBoy = new StandardParkingBoy(new ParkingLot(10));
            standardParkingBoy.fetch(new ParkingTicket());
        });
        assertEquals("Unrecognized parking ticket.", runtimeException.getMessage());
    }

    @Test
    void should_get_exception_when_fetch_given_standard_parking_boy_and_used_parking_ticket() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            StandardParkingBoy standardParkingBoy = new StandardParkingBoy(new ParkingLot(10));
            ParkingTicket parkingTicket = standardParkingBoy.park(new Car());
            standardParkingBoy.fetch(parkingTicket);
            standardParkingBoy.fetch(parkingTicket);
        });
        assertEquals("Unrecognized parking ticket.", runtimeException.getMessage());
    }

}
