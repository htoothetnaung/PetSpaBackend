package com.spaService.ServiceBookingSystem.repository;

import com.spaService.ServiceBookingSystem.entity.Reservation;
import org.hibernate.query.criteria.JpaFetch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByCompanyId(Long companyId);

    List<Reservation> findAllByUserId(Long userId); // why not client as it makes more sense but in Reservaation entity we declear client as user and user_id
}
