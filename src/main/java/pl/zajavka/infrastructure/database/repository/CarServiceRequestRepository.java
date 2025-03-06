package pl.zajavka.infrastructure.database.repository;

import org.hibernate.Session;
import pl.zajavka.business.dao.CarServiceRequestDAO;
import pl.zajavka.infrastructure.configuration.HibernateUtil;
import pl.zajavka.infrastructure.database.entity.CarServiceRequestEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CarServiceRequestRepository implements CarServiceRequestDAO {
    @Override
    public Set<CarServiceRequestEntity> findActiveServiceRequestsByCarVin(String carVin) {
        try (Session session = HibernateUtil.getSession()) {
            if (Objects.isNull(session)) {
                throw new RuntimeException("Session is null");
            }
            session.beginTransaction();

            String query = """
                    SELECT sr from CarServiceRequestEntity sr
                    WHERE sr.car.vin = :carVin
                    AND sr.completedDateTime IS NULL
                    """;

            List<CarServiceRequestEntity> result = session.createQuery(query, CarServiceRequestEntity.class)
                    .setParameter("carVin", carVin)
                    .list();

            session.getTransaction().commit();
            return new HashSet<>(result);
        }
    }
}
