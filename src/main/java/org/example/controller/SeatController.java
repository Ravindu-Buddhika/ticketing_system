package org.example.controller;

import org.example.entity.Seat;
import org.example.service.SeatService;
import org.example.exception.SeatLockedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping("/{id}/hold")
    public ResponseEntity<?> holdSeat(
            @PathVariable("id") Long seatId,
            @RequestParam Long userId) {
        try {
            Seat updatedSeat = seatService.holdSeat(seatId, userId);
            return ResponseEntity.ok(updatedSeat);

        } catch (SeatLockedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of(
                            "status", "Locked",
                            "message", e.getMessage(),
                            "retryAfterSeconds", e.getRemainingSeconds()
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
