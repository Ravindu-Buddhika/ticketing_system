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
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String seatNo;
    private String status = "AVAILABLE";
    private LocalDateTime holdExpiry;
    @Version
    private int version;

    @OneToOne
    private User user;
    @ManyToOne
    private Events event;
}
