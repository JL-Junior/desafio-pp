package org.desafio.infra.data.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    Long id;

    @Column(name ="tx_complete_name", length = 100)
    String completeName;

    @Column(name = "id_user_type")
    Long userTypeId;

    @Column( name = "tx_email", unique = true, length = 256)
    String email;

    @Column(name = "vl_balance")
    Double balance;

    @ManyToOne
    @JoinColumn(name = "id_user_type", referencedColumnName = "id_user_type", insertable = false, updatable = false)
    UserType userType;

    @OneToMany(mappedBy = "user")
    List<UserDocument> userDocument = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    List<Transaction> transactionsSend = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    List<Transaction> transactionsReceived = new ArrayList<>();


}
