package com.spaService.ServiceBookingSystem.entity;

import com.spaService.ServiceBookingSystem.dto.ReviewDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date reviewDate;

    private String review;

    private Long rating;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ad_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ad ad;

    public ReviewDTO getDTO(){
        ReviewDTO dto = new ReviewDTO();

        dto.setId(id);
        dto.setReviewDate(reviewDate);
        dto.setReview(review);
        dto.setRating(rating);
        dto.setUserId(user.getId());
        dto.setAdId(ad.getId());
        dto.setUserName(user.getName());
        dto.setServiceName(ad.getServiceName());

        return dto;
    }


}
