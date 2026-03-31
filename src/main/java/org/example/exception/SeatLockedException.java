package org.example.exception;

import lombok.Getter;

@Getter
public class SeatLockedException extends RuntimeException {
    private final long remainingSeconds;

    public SeatLockedException(long remainingSeconds) {
        super("Seat is currently locked. Please try again in " + remainingSeconds + " seconds.");
        this.remainingSeconds = remainingSeconds;
    }
}
