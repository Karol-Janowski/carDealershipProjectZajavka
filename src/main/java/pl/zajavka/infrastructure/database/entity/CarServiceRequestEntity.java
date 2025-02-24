package pl.zajavka.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "carServiceRequestId")
@ToString(of = {"serviceRequestId", "carServiceRequestNumber", "receivedDateTime", "completedDateTime"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car_service_request")
public class CarServiceRequestEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "car_service_request_id")
    private Long carServiceRequestId;

    @Column(name = "car_service_request_number", unique = true)
    private String carServiceRequestNumber;

    @Column(name = "received_date_time")
    private OffsetDateTime receivedDateTime;

    @Column(name = "completed_date_time")
    private OffsetDateTime completedDateTime;

}
