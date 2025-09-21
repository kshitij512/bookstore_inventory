package company.bookstore_inventory.dto;

import java.util.List;

public class BookResponse {
    private String name;
    private List<LocationQuantityDto> locations;

    // Constructors, getters, and setters
    public BookResponse() {
    }

    public BookResponse(String name, List<LocationQuantityDto> locations) {
        this.name = name;
        this.locations = locations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LocationQuantityDto> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationQuantityDto> locations) {
        this.locations = locations;
    }
}