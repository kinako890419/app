package com.example.app.entity;

import com.example.app.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "OIDC_CONFIG")
@SQLDelete(sql = "UPDATE SAML_CONFIG SET IS_DELETED = '1' WHERE ID = ?")
@SQLRestriction("IS_DELETED = false")
public class SamlConfigEntity extends BaseEntity {
    String idpName;
    String domainFilter;
}
