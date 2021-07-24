package com.bpn.assignment.model.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class CommonEntityModel implements Serializable {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "entry_date", nullable = true, updatable = false, insertable = false)
	private Date entryDate;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "entry_date", nullable = true)
	private Date lastModifiedDate;

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

}
