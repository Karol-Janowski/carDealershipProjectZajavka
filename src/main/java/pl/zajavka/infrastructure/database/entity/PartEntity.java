package pl.zajavka.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(of = "partId")
@ToString(of = {"partId", "serialNumber", "description", "price"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "part")
public class PartEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "part_id")
    private Long partId;

    @Column(name = "serial_number", unique = true)
    private String serialNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;
}
