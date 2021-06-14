package com.rebelalliance.exceptions;

/**
 * 
 * Represta la Clase para exception RebelAlliance
 * @author Luis Gerardo Espinosa Sampayo
 *
 */
public class RebelAllianceException extends Exception  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param errorMessage
	 */
	public  RebelAllianceException(String errorMessage){
        super(errorMessage);
    }

}
