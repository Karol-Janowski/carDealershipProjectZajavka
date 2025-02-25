package pl.zajavka.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = "invoiceId")
@ToString(of = {"invoiceId", "invoiceNumber", "invoiceDate"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoice")
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Long invoiceId;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "date_time")
    private OffsetDateTime invoiceDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_to_buy_id")
    private CarToBuyEntity car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salesman_id")
    private SalesmanEntity salesman;
}
