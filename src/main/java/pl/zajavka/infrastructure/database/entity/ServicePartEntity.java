package pl.zajavka.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "servicePartId")
@ToString(of = {"servicePartId", "quantity"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "service_part")
public class ServicePartEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "service_part_id")
    private Long servicePartId;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_service_request_id")
    private CarServiceRequestEntity carServiceRequest;
}
