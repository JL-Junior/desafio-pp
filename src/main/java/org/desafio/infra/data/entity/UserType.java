package org.desafio.infra.data.entity;

import io.quarkus.arc.All;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_user_type")
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_type")
    Long id;

    @Column(name = "tx_description", length = 255)
    String description;

    @Column(name = "bl_send")
    Boolean send;

    @Column(name = "bl_receive")
    Boolean receive;

    @OneToMany(mappedBy = "userType")
    List<User> user = new ArrayList<>();

}
