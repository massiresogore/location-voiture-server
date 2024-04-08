package com.msr.agenceloc.system.exception;

public class GreaterException extends RuntimeException{
    public GreaterException (int prixJournalier, String objectName){
        super("Le prix :"+ prixJournalier + "du "+objectName+" est supérieur aux maximum 45");
    }
}
