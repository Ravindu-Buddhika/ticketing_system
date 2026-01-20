package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double finalAmount;
    private LocalDateTime bookingTime;

    @ManyToOne
    private User user;

    @OneToOne
    private Seat seat;
}
