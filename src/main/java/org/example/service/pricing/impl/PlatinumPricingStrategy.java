package org.example.service.pricing.impl;

import org.example.service.pricing.PricingStrategy;

public class PlatinumPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice, boolean isHighDemand) {
        return 0;
    }

    @Override
    public boolean hasPriorityAccess() {
        return false;
    }
}
