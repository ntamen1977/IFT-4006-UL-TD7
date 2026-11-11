package td7.domain.cruise.dateTime;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class CruiseDateTime {
    private LocalDateTime localDateTime;

    public CruiseDateTime() {

    }

    public CruiseDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public boolean isBefore(CruiseDateTime otherCruiseDateTime) {
        return localDateTime.isBefore(otherCruiseDateTime.localDateTime);
    }

    @Override
    public String toString() {
        return localDateTime.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        CruiseDateTime that = (CruiseDateTime) obj;
        return localDateTime.equals(that.localDateTime);
    }
}
