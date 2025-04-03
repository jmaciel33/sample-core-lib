package io.jmaciel.rest.pagination;

public record Meta(int page, boolean hasNext, long totalRecords, long totalPages) {
}
