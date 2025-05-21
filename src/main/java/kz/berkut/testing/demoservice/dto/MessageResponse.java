package kz.berkut.testing.demoservice.dto;

import java.time.Instant;

public record MessageResponse(Instant createdAt, String body) { }
