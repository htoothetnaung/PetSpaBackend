package com.spaService.ServiceBookingSystem.services.client;


import com.spaService.ServiceBookingSystem.dto.AdDTO;
import com.spaService.ServiceBookingSystem.dto.AdDetailsForClientDTO;
import com.spaService.ServiceBookingSystem.dto.ReservationDTO;
import com.spaService.ServiceBookingSystem.dto.ReviewDTO;
import com.spaService.ServiceBookingSystem.entity.Ad;
import com.spaService.ServiceBookingSystem.entity.Reservation;
import com.spaService.ServiceBookingSystem.entity.Review;
import com.spaService.ServiceBookingSystem.entity.User;
import com.spaService.ServiceBookingSystem.enums.ReservationStatus;
import com.spaService.ServiceBookingSystem.enums.ReviewStatus;
import com.spaService.ServiceBookingSystem.repository.AdRepository;
import com.spaService.ServiceBookingSystem.repository.ReservationRepository;
import com.spaService.ServiceBookingSystem.repository.ReviewRepository;
import com.spaService.ServiceBookingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService
{
    @Autowired
    private AdRepository adRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<AdDTO> getAllAds(){
        return adRepository.findAll().stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

    public List<AdDTO> searchAdByService(String name){
        return adRepository.findAllByServiceNameContaining(name).stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

    public Boolean bookService(ReservationDTO reservationDTO){
        Optional<Ad> optionalAd = adRepository.findById(reservationDTO.getAdId());
        Optional<User> optionalUser = userRepository.findById(reservationDTO.getUserId());
        if(optionalAd.isPresent() && optionalUser.isPresent()){
            Reservation reservation = new Reservation();

            reservation.setBookDate(reservationDTO.getBookDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);
            reservation.setUser(optionalUser.get());

            reservation.setAd(optionalAd.get());
            reservation.setCompany(optionalAd.get().getUser());
            reservation.setReviewStatus(ReviewStatus.FALSE);

            reservationRepository.save(reservation);
            return true;
        }
    return false;
    }

    public AdDetailsForClientDTO getAdDetailsByAdId(Long adId){
        Optional<Ad> optionalAd = adRepository.findById(adId);
        AdDetailsForClientDTO adDetailsForClientDTO = new AdDetailsForClientDTO();
        if(optionalAd.isPresent()){
            adDetailsForClientDTO.setAdDTO(optionalAd.get().getAdDto());

            List<Review> reviews = reviewRepository.findAllByAdId(adId);
            adDetailsForClientDTO.setReviewDTOList(reviews.stream().map(Review::getDTO).collect(Collectors.toList()));
        }
        return adDetailsForClientDTO;
    }

    public List<ReservationDTO> getAllBookingsByUserId(Long userId){
        return reservationRepository.findAllByUserId(userId).stream().map(Reservation::getReservationDTO).collect(Collectors.toList());
    }

    public Boolean giveReview(ReviewDTO reviewDTO){
        Optional<User> optionalUser = userRepository.findById(reviewDTO.getUserId());
        Optional<Reservation> optionalBooking = reservationRepository.findById(reviewDTO.getBookId());

        if(optionalUser.isPresent() && optionalBooking.isPresent()){
            Review review = new Review();

            review.setReviewDate(new Date());
            review.setReview(reviewDTO.getReview());
            review.setRating(reviewDTO.getRating());

            review.setUser(optionalUser.get());
            review.setAd(optionalBooking.get().getAd());

            reviewRepository.save(review);

            Reservation booking = optionalBooking.get();
            booking.setReviewStatus(ReviewStatus.TRUE);

            reservationRepository.save(booking);

            return true;
        }

        return false;
    }




}
