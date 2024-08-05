package com.articos.dto;

import java.util.UUID;

public record ArticleIdDto(UUID id, String title, String content, UUID userRef) {
}
