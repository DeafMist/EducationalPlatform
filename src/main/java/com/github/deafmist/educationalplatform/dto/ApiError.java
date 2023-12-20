package com.github.deafmist.educationalplatform.dto;

import java.time.OffsetDateTime;

public record ApiError(OffsetDateTime dateOccurred, String message) {
}
