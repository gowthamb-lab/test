package org.example.service.impl;

import org.example.Repository.reservationsRepository;
import org.example.dtos.reservationsDto;
import org.example.exceptions.AdminNotFoundException;
import org.example.exceptions.locationNotFoundException;
import org.example.exceptions.reservationServiceException;
import org.example.model.entity.locations;
import org.example.model.entity.reservations;
import org.example.service.reservationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class reservationServiceImpl implements reservationService {

    @Autowired
    private reservationsRepository reservationsRepo;

    private reservationsDto convertEntitytoDTO(reservations booking) {
        reservationsDto bookingDto = new reservationsDto();
        BeanUtils.copyProperties(booking, bookingDto);
        return bookingDto;
    }
    private reservations convertDTOtoEntity(reservationsDto bookingDto){
        reservations booking = new reservations();
        BeanUtils.copyProperties(bookingDto,booking);
        return booking;
    }


    @Override
    public List<reservations> getAllReservations(int pageNo, int size) {

        Pageable paging = PageRequest.of(pageNo, size);

        try{
            List<reservations> adm= (List<reservations>) reservationsRepo.findAll(paging);
            return adm;
        }
        catch(Exception e)
        {
            throw new reservationServiceException("bookings not found",e);
        }
    }

    @Override
    public reservationsDto createReservation(reservationsDto bookingDto) {
        try{
            reservations booking =  convertDTOtoEntity(bookingDto);
            reservations responseEntity = reservationsRepo.save(booking);
            bookingDto= convertEntitytoDTO(responseEntity);
        }
        catch(Exception e)
        {
            System.out.println("order not created");
            throw new reservationServiceException("Requested reservation cannot be made",e);
        }
        return bookingDto;
    }

    @Override
    public boolean cancelReservation(String bookingId) {

        Optional<reservations> loc = reservationsRepo.findById(bookingId);
        if(!loc.isPresent())
        {
            throw new locationNotFoundException("Requested location cannot be created");
        }
        reservationsRepo.delete(loc.get());
        return true;
    }

    @Override
    public reservations updateReservation(String reservationId, reservations bookings) {
        Optional<reservations> loc = reservationsRepo.findById(reservationId);
        if(!loc.isPresent())
        {
            throw new locationNotFoundException("Requested reservation is not found");
        }
        return reservationsRepo.save(loc.get());
    }
}
