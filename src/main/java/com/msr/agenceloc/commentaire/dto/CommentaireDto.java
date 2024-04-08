package com.msr.agenceloc.commentaire.dto;

import jakarta.validation.constraints.NotNull;

public record CommentaireDto(
        Long commentaireId,
        @NotNull
        String description) {
}
