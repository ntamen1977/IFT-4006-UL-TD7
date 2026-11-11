package td7.application.cruise.booking.dtos;

public class BookingTravelerDto {
    public String id;

    public String name;
    public String category;

    public BookingTravelerDto() {
    }

    public BookingTravelerDto(String id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }
}
