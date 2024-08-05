package com.articos.dto;

import java.util.UUID;

public record ArticleDto(String title, String content, UUID userRef) {
}
