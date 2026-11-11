package td7.domain.cruise.booking.traveler;

import jakarta.persistence.*;

@Entity
public class Traveler {

    @Id
    private TravelerId id;
    private TravelerName name;

    @Enumerated(EnumType.STRING)
    private TravelerCategory category;

    public Traveler(TravelerId id, TravelerName name, TravelerCategory category)
    {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Traveler() {

    }

    public TravelerId getId() {
        return id;
    }

    public TravelerName getName() {
        return name;
    }

    public TravelerCategory getCategory() {
        return category;
    }
}
