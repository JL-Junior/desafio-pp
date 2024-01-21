package org.desafio.infra.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    Long id;

    @Column(name ="tx_complete_name", length = 100)
    String completeName;

    @Column(name = "id_user_type")
    Short userTypeId;

    @Column(unique = true, name = "tx_email", length = 256)
    String email;

    @Column(name = "vl_amount")
    Double balance;
}
