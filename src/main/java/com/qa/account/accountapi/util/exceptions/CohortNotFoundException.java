package com.qa.account.accountapi.util.exceptions;

public class CohortNotFoundException extends RuntimeException{

    public CohortNotFoundException(String exception){
        super("Id supplied does not have a cohort: " + exception);
    }

}
