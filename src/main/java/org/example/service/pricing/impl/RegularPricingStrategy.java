package org.example.service.pricing.impl;

import org.example.service.pricing.PricingStrategy;

public class RegularPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice, boolean isHighDemand) {
        return basePrice;
    }

    @Override
    public boolean hasPriorityAccess() {
        return false;
    }
}
