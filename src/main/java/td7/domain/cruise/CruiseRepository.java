package td7.domain.cruise;

public interface CruiseRepository {
    Cruise findById(CruiseId cruiseId);
    void save(Cruise cruise);
}
