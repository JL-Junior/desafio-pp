package org.desafio.infra.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_transaction")
public class Transaction {

    @Id
    @Column(name = "id_transaction")
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "vl_amount", scale = 2)
    Double amount;

    @Column(name = "id_sender")
    Long senderId;

    @Column(name = "id_receiver")
    Long receiverId;

    @Column(name = "dt_timestamp")
    LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "id_sender", referencedColumnName = "id_user", insertable = false, updatable = false)
    User sender;

    @ManyToOne
    @JoinColumn(name = "id_receiver", referencedColumnName = "id_user", insertable = false, updatable = false)
    User receiver;
}
