package com.qa.account.accountapi.persistence.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long accountId;

    private String firstName;

    private String lastName;
    
    @Column(unique = true)
    private String email;
    
    private String password;

    private String accountNumber;
    
    @OneToOne(cascade=CascadeType.ALL)
    private Cohort cohort;
    
    @OneToOne(cascade=CascadeType.ALL)
    private Prize prize;

    public Account() {
    }

    public Account(Long accountId, String firstName, String lastName,String email, String password, String accountNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.email=email;
        this.password=password;
        this.accountId = accountId;
    }

    public Long getId() {
        return accountId;
    }

    public void setId(Long id) {
        this.accountId = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public Prize getPrize() {
    	return prize;
    }
    
    public void setPrize(Prize prize) {
    	this.prize = prize;
    }
    
    
    public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public Cohort getCohort() {
		return cohort;
	}

	public void setCohort(Cohort cohort) {
		this.cohort = cohort;
	}

	public String toString() {
    	return this.accountId + this.firstName + this.lastName +this.email +this.accountNumber;
    }
}
