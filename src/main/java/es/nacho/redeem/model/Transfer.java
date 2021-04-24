package es.nacho.redeem.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfer")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tr_id", nullable = false)
    private Long id;

    @Column(name = "tr_datetime", nullable = false)
    private LocalDateTime datetime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Employee employee;

    public Transfer(LocalDateTime datetime) {
        super();
        this.datetime = datetime;
    }

    public Transfer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }
}
