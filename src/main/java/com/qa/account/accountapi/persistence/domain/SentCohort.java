package com.qa.account.accountapi.persistence.domain;

public class SentCohort {


    private Long cohortId;

    private int trainerId;

    private String name;

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

	public SentCohort(){}

    public SentCohort(Cohort cohort){

        this.cohortId = cohort.getCohortId();
        this.trainerId = cohort.getTrainerId();
        this.name = cohort.getName();
    }


}
