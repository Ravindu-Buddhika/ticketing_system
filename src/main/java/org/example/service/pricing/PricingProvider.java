package org.example.service.pricing;

import org.example.service.pricing.impl.PlatinumPricingStrategy;
import org.example.service.pricing.impl.RegularPricingStrategy;
import org.example.service.pricing.impl.VIPPricingStrategy;
import org.springframework.stereotype.Component;

@Component
public class PricingProvider {
    public PricingStrategy getStrategy(String tier) {
        return switch (tier.toUpperCase()) {
            case "VIP" -> new VIPPricingStrategy();
            case "PLATINUM" -> new PlatinumPricingStrategy();
            default -> new RegularPricingStrategy();
        };
    }
}
