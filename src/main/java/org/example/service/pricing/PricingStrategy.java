package org.example.service.pricing;

public interface PricingStrategy {
    double calculatePrice(double basePrice, boolean isHighDemand);
    boolean hasPriorityAccess();
}
