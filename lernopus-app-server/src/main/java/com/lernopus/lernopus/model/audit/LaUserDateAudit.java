package com.lernopus.lernopus.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.MappedSuperclass;

/**
 * Created by amernath v on 2019-09-04.
 */

@MappedSuperclass
@JsonIgnoreProperties(
        value = {"laCreatedUser", "laUpdatedUser"},
        allowGetters = true
)
public abstract class LaUserDateAudit extends LaDateAudit {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1400917156085404189L;

	@CreatedBy
    private Long laCreatedUser;

    @LastModifiedBy
    private Long laUpdatedUser;

    public Long getLaCreatedUser() {
        return laCreatedUser;
    }

    public void setLaCreatedUser(Long laCreatedUser) {
        this.laCreatedUser = laCreatedUser;
    }

    public Long getLaUpdatedUser() {
        return laUpdatedUser;
    }

    public void setLaUpdatedUser(Long laUpdatedUser) {
        this.laUpdatedUser = laUpdatedUser;
    }
}
