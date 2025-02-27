package com.spaService.ServiceBookingSystem.services.company;


import com.spaService.ServiceBookingSystem.dto.AdDTO;
import com.spaService.ServiceBookingSystem.dto.ReservationDTO;

import java.io.IOException;
import java.util.List;

public interface CompanyService {

    boolean postAd(Long userId, AdDTO adDTO ) throws IOException;

    List<AdDTO> getAllAds(Long userId);

    AdDTO getAdById(Long adId);

    boolean updateAd(Long adId, AdDTO adDTO) throws IOException;


    boolean deleteAd(Long adId);

    List<ReservationDTO> getAllReservations(Long companyId);

    boolean changeBookingStatus(Long bookingId, String status);
}
