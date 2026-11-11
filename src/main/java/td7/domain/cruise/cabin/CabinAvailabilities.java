package td7.domain.cruise.cabin;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import td7.domain.cruise.cabin.exceptions.CabinTypeNotAvailableException;

import java.util.Map;

@Entity
public class CabinAvailabilities {
    @Id
    private CabinAvailabilitiesId id;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<CabinType, Integer> availibilitiesByCabinType;

    public CabinAvailabilities(Map<CabinType, Integer> availibilitiesByCabinType) {
        this.id = new CabinAvailabilitiesId();
        this.availibilitiesByCabinType = availibilitiesByCabinType;
    }

    public CabinAvailabilities() {

    }

    public void bookCabin(CabinType cabinType) {
        if (availibilitiesByCabinType.getOrDefault(cabinType, 0) <= 0) {
            throw new CabinTypeNotAvailableException();
        }
        availibilitiesByCabinType.put(cabinType, availibilitiesByCabinType.get(cabinType) - 1);
    }
}
