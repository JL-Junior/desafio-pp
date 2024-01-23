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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_user_document")
public class UserDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_document")
    Long id;

    @Column(name = "id_user")
    Long userId;

    @Column(name = "nm_document", unique = true)
    Long documentNumber;

    @Column(name = "id_document_type")
    Long documentTypeId;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", updatable = false, insertable = false)
    User user;

    @ManyToOne
    @JoinColumn(name = "id_document_type", referencedColumnName = "id_document_type", updatable = false, insertable = false)
    DocumentType documentType;
}
