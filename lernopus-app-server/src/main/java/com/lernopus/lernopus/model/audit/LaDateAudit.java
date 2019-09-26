package com.lernopus.lernopus.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"laCreatedAt", "laUpdatedAt"},
        allowGetters = true
)
public abstract class LaDateAudit implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2681695807045411826L;

	@CreatedDate
    private Instant laCreatedAt;

    @LastModifiedDate
    private Instant laUpdatedAt;

    public Instant getLaCreatedAt() {
        return laCreatedAt;
    }

    public void setLaCreatedAt(Instant laCreatedAt) {
        this.laCreatedAt = laCreatedAt;
    }

    public Instant getLaUpdatedAt() {
        return laUpdatedAt;
    }

    public void setLaUpdatedAt(Instant laUpdatedAt) {
        this.laUpdatedAt = laUpdatedAt;
    }

}
