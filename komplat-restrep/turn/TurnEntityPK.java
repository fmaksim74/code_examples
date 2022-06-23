package ru.uralprom.komplat.rest.jpa.turn;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * 
 * @author FedorchenkoMI
 * 
 * The primary key class for the "Turn" database table.
 * 
 */
@Embeddable
public class TurnEntityPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer accountId;

	public Integer getAccountId() {
		return this.accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	private Short serviceId; 

	public Short getServiceId() {
		return this.serviceId;
	}
	public void setServiceId(Short serviceId) {
		this.serviceId = serviceId;
	}

	private Short providerId;

	public Short getProviderId() {
		return this.providerId;
	}
	public void setProviderId(Short providerId) {
		this.providerId = providerId;
	}

	private Date month;

	public Date getMonth() {
		return this.month;
	}
	public void setMonth(Date month) {
		this.month = month;
	}
	
	public TurnEntityPK() {
	}
	
	public TurnEntityPK(Integer accountId, Short serviceId, Short providerId, Date month) {
		this.accountId = accountId;
		this.serviceId = serviceId;
		this.providerId = providerId;
		this.month = month;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TurnEntityPK)) {
			return false;
		}
		TurnEntityPK castOther = (TurnEntityPK)other;
		return 
			this.accountId.equals(castOther.accountId)
			&& this.serviceId.equals(castOther.serviceId)
			&& this.providerId.equals(castOther.providerId)
			&& this.month.equals(castOther.month);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.accountId.hashCode();
		hash = hash * prime + this.serviceId.hashCode();
		hash = hash * prime + this.providerId.hashCode();
		hash = hash * prime + this.month.hashCode();
		
		return hash;
	}
}