package com.articos.dto;

import java.util.List;
import java.util.UUID;

public record UserDto(UUID id, String username, List<ArticleDto> articles) {
}
