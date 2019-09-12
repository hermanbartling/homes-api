package se.difo.hemnetapi.api.dto.common;

public class PaginationDto {

    int number;
    int size;
    int totalPages;
    long totalElements;

    public PaginationDto(int number, int size, int totalPages, long totalElements) {
        this.number = number;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public int getNumber() {
        return number;
    }

    public int getSize() {
        return size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }
}
