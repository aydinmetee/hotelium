package com.hotelium.mainservice.domain;

import com.hotelium.mainservice.util.IdGenerator;
import com.hotelium.mainservice.util.SessionContext;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Mete Aydin
 * @date 17.10.2021
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {
    @Id
    @Column(name = "ID", nullable = false, length = 36)
    private String id;
    @Column(name = "ORG_ID", nullable = false, length = 36)
    private String orgId;
    @Column(name = "CRE_USER", length = 36)
    private String creUser;
    @Column(name = "cre_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creDate;
    @Column(name = "UPD_USER", length = 36)
    private String updUser;
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updDate;
    @Column(name = "valid")
    @Type(type = "yes_no")
    private Boolean valid;


    @PrePersist
    public void onPrePersist() {
        setId(IdGenerator.getUUID());
        setOrgId(SessionContext.getSessionData().getOrgId());
        setCreUser(SessionContext.getSessionData().getUserId());
        setValid(Boolean.TRUE);
        this.creDate = new Date();
    }

    @PreUpdate
    public void onPreUpdate() {
        setUpdUser(SessionContext.getSessionData().getUserId());
        this.updDate = new Date();
    }

}
