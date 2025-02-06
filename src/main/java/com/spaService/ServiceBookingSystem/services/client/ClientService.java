package com.spaService.ServiceBookingSystem.services.client;

import com.spaService.ServiceBookingSystem.dto.AdDTO;
import com.spaService.ServiceBookingSystem.dto.AdDetailsForClientDTO;
import com.spaService.ServiceBookingSystem.dto.ReservationDTO;
import com.spaService.ServiceBookingSystem.dto.ReviewDTO;

import java.util.List;

public interface ClientService {

    List<AdDTO> getAllAds();

    List<AdDTO> searchAdByService(String name);

    Boolean bookService(ReservationDTO reservationDTO);

    AdDetailsForClientDTO getAdDetailsByAdId(Long adId);

    List<ReservationDTO> getAllBookingsByUserId(Long userId);

    Boolean giveReview(ReviewDTO reviewDTO);
}
