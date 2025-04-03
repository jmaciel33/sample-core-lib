package io.jmaciel.rest.pagination;

import java.util.List;

public record PaginatedResponse<T>(List<T> data, Meta meta) {
}