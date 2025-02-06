package com.spaService.ServiceBookingSystem.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewDTO {
    private Long id;

    private Date reviewDate;

    private String review;

    private Long rating;

    private Long userId;

    private Long adId;

    private String userName;

    private String serviceName;

    private Long bookId; // to change the review status of booking [also because if you can review, that also means you have already book]
}
