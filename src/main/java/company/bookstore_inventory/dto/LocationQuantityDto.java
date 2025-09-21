package company.bookstore_inventory.dto;

public class LocationQuantityDto {
    private Long id;
    private String name;
    private Integer availableQty;

    // Constructors, getters, and setters
    public LocationQuantityDto() {
    }

    public LocationQuantityDto(Long id, String name, Integer availableQty) {
        this.id = id;
        this.name = name;
        this.availableQty = availableQty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(Integer availableQty) {
        this.availableQty = availableQty;
    }
}