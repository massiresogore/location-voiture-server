package com.msr.agenceloc.date.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record DataReservationDto(
        @Future //include present
        @NotNull
        LocalDate dataReservationDto
) {
}
