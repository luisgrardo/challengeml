package com.rebelalliance.service;

import com.rebelalliance.exceptions.RebelAllianceException;
import com.rebelalliance.model.SateliteWrap;
import com.rebelalliance.model.WrapInformation;

public interface SateliteService {

	WrapInformation getInformation(SateliteWrap satelites) throws RebelAllianceException;

}
