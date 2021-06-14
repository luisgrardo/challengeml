package com.rebelalliance.service;

import com.rebelalliance.exceptions.RebelAllianceException;
import com.rebelalliance.model.Position;
import com.rebelalliance.model.SateliteWrap;
import com.rebelalliance.model.WrapInformation;

/**
 * @author Luis Gerardo Espinosa Sampayo
 *
 */
public interface SateliteService {

	/**
	 * @param satelites
	 * @return
	 * @throws RebelAllianceException
	 */
	WrapInformation getInformation(SateliteWrap satelites) throws RebelAllianceException;
	
	/**
	 * @param positions
	 * @param distances
	 * @return
	 */
	
	 Position getLocation(double[] distances);

}
