package org.example.service;

import org.example.dtos.ordersDto;
import org.example.dtos.reservationsDto;
import org.example.model.entity.Orders;
import org.example.model.entity.reservations;

import java.util.List;

public interface reservationService {

    List<reservations> getAllReservations(int pageNo, int pageSize);
    reservationsDto createReservation(reservationsDto bookingDto);
    reservations updateReservation(String reservationId, reservations bookings);

    boolean cancelReservation(String bookingId);


}
