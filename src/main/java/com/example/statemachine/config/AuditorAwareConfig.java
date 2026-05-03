package com.example.statemachine.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AuditorAwareConfig implements AuditorAware<UUID> {

    @Value("${system.default.staffId}")
    private UUID systemDefaultStaffId;

    @NonNull
    @Override
    public Optional<UUID> getCurrentAuditor() {
        return Optional.of(systemDefaultStaffId);
    }

//    private Optional<UUID> extractUserUuid() {
//        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
//                .map(Authentication::getName)
//                .filter(name -> !name.equals("anonymousUser"))
//                .map(UUID::fromString);
//    }
}
