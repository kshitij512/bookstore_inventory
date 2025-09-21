package company.bookstore_inventory.dto;

public class BookRequest {
    private String bookName;
    private Long locationId;
    private Integer quantity;

    // Constructors, getters, and setters
    public BookRequest() {
    }

    public BookRequest(String bookName, Long locationId, Integer quantity) {
        this.bookName = bookName;
        this.locationId = locationId;
        this.quantity = quantity;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}