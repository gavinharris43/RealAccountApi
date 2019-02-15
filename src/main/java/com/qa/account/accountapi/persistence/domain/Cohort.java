package com.qa.account.accountapi.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cohort {

	@Id
	@GeneratedValue
	private Long cohortId;
	
	private int trainerId;
	
    @Column(unique = true)
	private String name;
	
	public Cohort() {
		
	}
	
	public Cohort(Long cohortId, int trainerId, String name) {
		this.cohortId = cohortId;
		this.trainerId = trainerId;
		this.name = name;
	}
	
	public Long getCohortId() {
		return cohortId;
	}

	public void setCohortId(Long cohortId) {
		this.cohortId = cohortId;
	}

	public int getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name + this.trainerId;
	}
}
