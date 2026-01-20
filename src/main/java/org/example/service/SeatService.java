package org.example.service;

import org.example.entity.Seat;
import org.example.entity.User;
import org.example.exception.SeatLockedException;
import org.example.repository.SeatRepository;
import org.example.repository.UserRepository;
import org.example.service.pricing.PricingProvider;
import org.example.service.pricing.PricingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SeatService {
    @Autowired
    SeatRepository seatRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PricingProvider pricingProvider;

    public Seat holdSeat(Long seatId,Long userId){
        Seat seat =seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));
        User user=userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDateTime now= LocalDateTime.now();

        if(seat.getStatus().equals("HELD")&& seat.getHoldExpiry().isAfter(now)){
            long remaining = Duration.between(now,seat.getHoldExpiry()).toSeconds();
            throw new SeatLockedException(remaining);
        }

        seat.setStatus("HELD");
        seat.setUser(user);
        seat.setHoldExpiry(now.plusMinutes(10));

        PricingStrategy strategy= pricingProvider.getStrategy(user.getTier());
        double price= strategy.calculatePrice(seat.getEvent().getBasePrice(), seat.getEvent().isHighDemand());

        System.out.println("Calculated Price for " + user.getName() + " : " + price);
        if(strategy.hasPriorityAccess()){
            System.out.println("Priority Access Granted!");
        }

        return seatRepository.save(seat);
    }
}