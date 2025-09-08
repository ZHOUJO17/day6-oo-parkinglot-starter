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

    @Test
    void should_get_exception_when_park_given_standard_parking_boy_and_no_available_position() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            StandardParkingBoy standardParkingBoy = new StandardParkingBoy(new ParkingLot(0));
            standardParkingBoy.park(new Car());
        });
        assertEquals("No available position.", runtimeException.getMessage());
    }

    @Test
    void should_park_to_first_parking_lot_when_park_given_standard_parking_boy_with_two_available_parking_lots_and_a_car() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(firstParkingLot, secondParkingLot);
        Car car = new Car();
        
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        
        assertNotNull(parkingTicket);
        Car resultCar = firstParkingLot.fetch(parkingTicket);
        assertEquals(car, resultCar);
    }

    @Test
    void should_park_to_second_parking_lot_when_park_given_standard_parking_boy_with_first_full_and_second_available_and_a_car() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(firstParkingLot, secondParkingLot);

        // Fill first parking lot
        firstParkingLot.park(new Car());

        Car car = new Car();
        ParkingTicket parkingTicket = standardParkingBoy.park(car);

        assertNotNull(parkingTicket);
        Car resultCar = secondParkingLot.fetch(parkingTicket);
        assertEquals(car, resultCar);
    }

    @Test
    void should_get_right_cars_when_fetch_twice_given_standard_parking_boy_with_two_parking_lots_and_two_parking_tickets() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(firstParkingLot, secondParkingLot);

        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingTicket firstTicket = standardParkingBoy.park(firstCar);
        ParkingTicket secondTicket = standardParkingBoy.park(secondCar);

        Car resultFirstCar = standardParkingBoy.fetch(firstTicket);
        Car resultSecondCar = standardParkingBoy.fetch(secondTicket);

        assertEquals(firstCar, resultFirstCar);
        assertEquals(secondCar, resultSecondCar);
    }

    @Test
    void should_get_exception_when_fetch_given_standard_parking_boy_with_two_parking_lots_and_unrecognized_ticket() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            StandardParkingBoy standardParkingBoy = new StandardParkingBoy(new ParkingLot(1), new ParkingLot(1));
            standardParkingBoy.fetch(new ParkingTicket());
        });
        assertEquals("Unrecognized parking ticket.", runtimeException.getMessage());
    }

    @Test
    void should_get_exception_when_fetch_given_standard_parking_boy_with_two_parking_lots_and_used_ticket() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            StandardParkingBoy standardParkingBoy = new StandardParkingBoy(new ParkingLot(1), new ParkingLot(1));
            ParkingTicket parkingTicket = standardParkingBoy.park(new Car());
            standardParkingBoy.fetch(parkingTicket);
            standardParkingBoy.fetch(parkingTicket);
        });
        assertEquals("Unrecognized parking ticket.", runtimeException.getMessage());
    }

    @Test
    void should_get_exception_when_park_given_standard_parking_boy_with_two_full_parking_lots_and_a_car() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            StandardParkingBoy standardParkingBoy = new StandardParkingBoy(new ParkingLot(0), new ParkingLot(0));
            standardParkingBoy.park(new Car());
        });
        assertEquals("No available position.", runtimeException.getMessage());
    }

    @Test
    void should_get_a_parking_ticket_when_park_given_smart_parking_boy_and_a_parking_lot_and_a_car() {
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(10));
        ParkingTicket parkingTicket = smartParkingBoy.park(new Car());
        assertNotNull(parkingTicket);
    }

    @Test
    void should_get_a_car_when_fetch_given_smart_parking_boy_and_valid_parking_ticket() {
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(10));
        Car originalCar = new Car();
        ParkingTicket parkingTicket = smartParkingBoy.park(originalCar);
        Car resultCar = smartParkingBoy.fetch(parkingTicket);
        assertNotNull(resultCar);
        assertEquals(originalCar, resultCar);
    }

    @Test
    void should_get_right_cars_when_fetch_twice_given_smart_parking_boy_and_two_parking_tickets() {
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(10));
        Car originalCar = new Car();
        Car originalCar1 = new Car();
        ParkingTicket parkingTicket = smartParkingBoy.park(originalCar);
        ParkingTicket parkingTicket1 = smartParkingBoy.park(originalCar1);
        Car resultCar = smartParkingBoy.fetch(parkingTicket);
        Car resultCar1 = smartParkingBoy.fetch(parkingTicket1);
        assertNotNull(resultCar);
        assertEquals(originalCar, resultCar);
        assertNotNull(resultCar1);
        assertEquals(originalCar1, resultCar1);
    }

    @Test
    void should_get_exception_when_fetch_given_smart_parking_boy_and_wrong_parking_ticket() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(10));
            smartParkingBoy.fetch(new ParkingTicket());
        });
        assertEquals("Unrecognized parking ticket.", runtimeException.getMessage());
    }

    @Test
    void should_get_exception_when_fetch_given_smart_parking_boy_and_used_parking_ticket() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(10));
            ParkingTicket parkingTicket = smartParkingBoy.park(new Car());
            smartParkingBoy.fetch(parkingTicket);
            smartParkingBoy.fetch(parkingTicket);
        });
        assertEquals("Unrecognized parking ticket.", runtimeException.getMessage());
    }

    @Test
    void should_get_exception_when_park_given_smart_parking_boy_and_no_available_position() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(0));
            smartParkingBoy.park(new Car());
        });
        assertEquals("No available position.", runtimeException.getMessage());
    }

    @Test
    void should_park_to_parking_lot_with_most_available_positions_when_park_given_smart_parking_boy_with_two_parking_lots_and_a_car() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(firstParkingLot, secondParkingLot);
        Car car = new Car();

        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        assertNotNull(parkingTicket);
        Car resultCar = secondParkingLot.fetch(parkingTicket);
        assertEquals(car, resultCar);
    }

    @Test
    void should_park_to_first_parking_lot_when_park_given_smart_parking_boy_with_two_parking_lots_same_available_positions_and_a_car() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(firstParkingLot, secondParkingLot);
        Car car = new Car();

        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        assertNotNull(parkingTicket);
        Car resultCar = firstParkingLot.fetch(parkingTicket);
        assertEquals(car, resultCar);
    }

    @Test
    void should_get_right_cars_when_fetch_twice_given_smart_parking_boy_with_two_parking_lots_and_two_parking_tickets() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(firstParkingLot, secondParkingLot);

        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingTicket firstTicket = smartParkingBoy.park(firstCar);
        ParkingTicket secondTicket = smartParkingBoy.park(secondCar);

        Car resultFirstCar = smartParkingBoy.fetch(firstTicket);
        Car resultSecondCar = smartParkingBoy.fetch(secondTicket);

        assertEquals(firstCar, resultFirstCar);
        assertEquals(secondCar, resultSecondCar);
    }

    @Test
    void should_get_exception_when_fetch_given_smart_parking_boy_with_two_parking_lots_and_unrecognized_ticket() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(1), new ParkingLot(1));
            smartParkingBoy.fetch(new ParkingTicket());
        });
        assertEquals("Unrecognized parking ticket.", runtimeException.getMessage());
    }

    @Test
    void should_get_exception_when_fetch_given_smart_parking_boy_with_two_parking_lots_and_used_ticket() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(1), new ParkingLot(1));
            ParkingTicket parkingTicket = smartParkingBoy.park(new Car());
            smartParkingBoy.fetch(parkingTicket);
            smartParkingBoy.fetch(parkingTicket);
        });
        assertEquals("Unrecognized parking ticket.", runtimeException.getMessage());
    }

    @Test
    void should_get_exception_when_park_given_smart_parking_boy_with_two_full_parking_lots_and_a_car() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(0), new ParkingLot(0));
            smartParkingBoy.park(new Car());
        });
        assertEquals("No available position.", runtimeException.getMessage());
    }

    @Test
    void should_get_a_parking_ticket_when_park_given_super_parking_boy_and_a_parking_lot_and_a_car() {
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(new ParkingLot(10));
        ParkingTicket parkingTicket = superParkingBoy.park(new Car());
        assertNotNull(parkingTicket);
    }

    @Test
    void should_get_a_car_when_fetch_given_super_parking_boy_and_valid_parking_ticket() {
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(new ParkingLot(10));
        Car originalCar = new Car();
        ParkingTicket parkingTicket = superParkingBoy.park(originalCar);
        Car resultCar = superParkingBoy.fetch(parkingTicket);
        assertNotNull(resultCar);
        assertEquals(originalCar, resultCar);
    }

    @Test
    void should_get_right_cars_when_fetch_twice_given_super_parking_boy_and_two_parking_tickets() {
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(new ParkingLot(10));
        Car originalCar = new Car();
        Car originalCar1 = new Car();
        ParkingTicket parkingTicket = superParkingBoy.park(originalCar);
        ParkingTicket parkingTicket1 = superParkingBoy.park(originalCar1);
        Car resultCar = superParkingBoy.fetch(parkingTicket);
        Car resultCar1 = superParkingBoy.fetch(parkingTicket1);
        assertNotNull(resultCar);
        assertEquals(originalCar, resultCar);
        assertNotNull(resultCar1);
        assertEquals(originalCar1, resultCar1);
    }

    @Test
    void should_get_exception_when_fetch_given_super_parking_boy_and_wrong_parking_ticket() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            SuperParkingBoy superParkingBoy = new SuperParkingBoy(new ParkingLot(10));
            superParkingBoy.fetch(new ParkingTicket());
        });
        assertEquals("Unrecognized parking ticket.", runtimeException.getMessage());
    }

    @Test
    void should_get_exception_when_fetch_given_super_parking_boy_and_used_parking_ticket() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            SuperParkingBoy superParkingBoy = new SuperParkingBoy(new ParkingLot(10));
            ParkingTicket parkingTicket = superParkingBoy.park(new Car());
            superParkingBoy.fetch(parkingTicket);
            superParkingBoy.fetch(parkingTicket);
        });
        assertEquals("Unrecognized parking ticket.", runtimeException.getMessage());
    }

    @Test
    void should_get_exception_when_park_given_super_parking_boy_and_no_available_position() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            SuperParkingBoy superParkingBoy = new SuperParkingBoy(new ParkingLot(0));
            superParkingBoy.park(new Car());
        });
        assertEquals("No available position.", runtimeException.getMessage());
    }

    @Test
    void should_park_to_parking_lot_with_highest_available_rate_when_park_given_super_parking_boy_with_two_parking_lots_and_a_car() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(5);
        // Fill first parking lot with 1 car (rate = 1/2 = 0.5)
        firstParkingLot.park(new Car());
        // Fill second parking lot with 1 car (rate = 4/5 = 0.8)
        secondParkingLot.park(new Car());

        SuperParkingBoy superParkingBoy = new SuperParkingBoy(firstParkingLot, secondParkingLot);
        Car car = new Car();

        ParkingTicket parkingTicket = superParkingBoy.park(car);

        assertNotNull(parkingTicket);
        Car resultCar = secondParkingLot.fetch(parkingTicket);
        assertEquals(car, resultCar);
    }

    @Test
    void should_park_to_first_parking_lot_when_park_given_super_parking_boy_with_two_parking_lots_same_available_rate_and_a_car() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(firstParkingLot, secondParkingLot);
        Car car = new Car();

        ParkingTicket parkingTicket = superParkingBoy.park(car);

        assertNotNull(parkingTicket);
        Car resultCar = firstParkingLot.fetch(parkingTicket);
        assertEquals(car, resultCar);
    }

    @Test
    void should_get_right_cars_when_fetch_twice_given_super_parking_boy_with_two_parking_lots_and_two_parking_tickets() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        SuperParkingBoy superParkingBoy = new SuperParkingBoy(firstParkingLot, secondParkingLot);

        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingTicket firstTicket = superParkingBoy.park(firstCar);
        ParkingTicket secondTicket = superParkingBoy.park(secondCar);

        Car resultFirstCar = superParkingBoy.fetch(firstTicket);
        Car resultSecondCar = superParkingBoy.fetch(secondTicket);

        assertEquals(firstCar, resultFirstCar);
        assertEquals(secondCar, resultSecondCar);
    }

    @Test
    void should_get_exception_when_fetch_given_super_parking_boy_with_two_parking_lots_and_unrecognized_ticket() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            SuperParkingBoy superParkingBoy = new SuperParkingBoy(new ParkingLot(1), new ParkingLot(1));
            superParkingBoy.fetch(new ParkingTicket());
        });
        assertEquals("Unrecognized parking ticket.", runtimeException.getMessage());
    }


}
