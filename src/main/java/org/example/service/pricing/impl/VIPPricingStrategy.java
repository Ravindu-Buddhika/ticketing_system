package org.example.service.pricing.impl;

import org.example.service.pricing.PricingStrategy;

public class VIPPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice, boolean isHighDemand) {
        if (!isHighDemand) {
            return basePrice * 0.8; // 20% off
        }
        return basePrice;
    }

    @Override
    public boolean hasPriorityAccess() {
        return false;
    }
}
