package com.rebelalliance.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.rebelalliance.exceptions.RebelAllianceException;
import com.rebelalliance.model.Position;
import com.rebelalliance.model.Satelite;
import com.rebelalliance.model.SateliteWrap;
import com.rebelalliance.model.WrapInformation;
import com.rebelalliance.service.SateliteService;

/**
 * Representa la implementacion de el servicio satelite
 * 
 * @author Luis Gerardo Espinosa Sampayo
 *
 */
@Service
public class SateliteServiceImpl implements SateliteService {
	


	
	
	double[][] positions = new double[][] { { -500.0, -200.0 }, { 100.0, -100.0 }, { 500.0, 100.0 } };

	/**
	 * 
	 * Obtiene información mensaje descifrado y posición
	 * @param satelites
	 * @return
	 * @throws RebelAllianceException
	 */
	@Override
	public WrapInformation getInformation(SateliteWrap satelites)throws  RebelAllianceException{
		WrapInformation information = new WrapInformation();
		double[] distances= new double[satelites.getSatelite().size()];
			if( satelites.getSatelite().size() < 2) {
            throw new  RebelAllianceException("Para determinar posicion o mensajes se requieren 3 elementos");
		}   
		int i = 0;
		List<List<String>> msgList = new ArrayList<List<String>>();
		List<String> li =null;
		for (Satelite satelite: satelites.getSatelite()) {
			li = new ArrayList<String>();
			for(String s : satelite.getMessage()) {
				li.add(s);
			}
			
			msgList.add(li);
			distances[i]=satelite.getDistance();
			i++;
		}
		information.setMessage(getMessage(msgList));
		information.setPosition(getLocation(distances));
		return information;
	}

	/**
	 * @param messages
	 * @return
	 */
	public String[] getMessageValid(String[] messages) {
		String[] messageFinal = new String[messages.length];
		int index = 0;
		for (String w : messages) {
			if (true) {
				messageFinal[index] = "";

			}

			index++;
		}

		return messageFinal;

	}

	

	/**
	 * 
	 * obitene la posicion  X e Y calculando con trilateracion y acercandose por arpoximacion Minimos cuadrados no lineales
	 * utilizando el algoritmo de Levenberg-Marquardt
	 * @param distances
	 * @return  Position 
	 */
	
	
	public Position getLocation(double[] distances) {
		NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
				new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
		Optimum optimum = solver.solve();
		double[] calculatedPosition = optimum.getPoint().toArray();

		Position position = new Position();
		position.setX(calculatedPosition[0]);
		position.setY(calculatedPosition[1]);
		
		return position;

	}
	
	
	
	
	
	/**
	 * forma el mensaje completo y remueve los caracteres en blanco 
	 * @param msgList
	 * @return String
	 */
	public List<String> getMsgWord(List<List<String>> msgList){

        List<String> listWords = new ArrayList<String>();
        for( List<String> msg : msgList){
            listWords = Stream.concat(listWords.stream(), msg.stream())
                    .distinct()
                    .collect(Collectors.toList());
        }
        listWords.remove("");
        return listWords;
    }

    /**
     * borra el token 
     * @param msgList
     * @param size
     */
    public void removeSpace(List<List<String>> msgList, int size){

        int sl = 0;
        for(int i = 0; i < msgList.size(); i++){
            sl = msgList.get(i).size();
            msgList.set(i, msgList.get(i).subList(sl-size, sl));
        }

       
    }

    /**
     * analiza la lista y va racorriendo el token hasta no entontrar espacios en blanco 
     * @param msgList
     * @return
     */
    public String organizarMensaje(List<List<String>> msgList){

        String token = "";
        for(List<String> msl : msgList){

            if(msl.size()>0 && !msl.get(0).equals("")){
                token = (msl.size() == 1) ? msl.get(0) : msl.get(0) + " ";
                msgList.stream().forEach( sl -> sl.remove(0));
                return  token + organizarMensaje(msgList);
            }
        }
       
        return "";
    }

    public String getMessage(List<List<String>> msgList) throws RebelAllianceException {

        List<String> msgWord = getMsgWord(msgList);
        if(!validateMessagesSize(msgList, msgWord.size()))
            throw new RebelAllianceException("no hay suficientes tokens");
        
        removeSpace(msgList,msgWord.size());
        String message = organizarMensaje(msgList);
        if(!validateMessageWord(msgWord,message))
            throw new RebelAllianceException("No se puede determinar el mensaje");

        return message;
    }

    public boolean validateMessagesSize(List<List<String>> messages, int size){
        for(List<String> m : messages){
            if(m.size() < size){
                return false;
            }
        }
        return true;
    }

    public boolean validateMessageWord(List<String> phrases, String message){
        List<String> msg = Arrays.stream(message.split(" ")).collect(Collectors.toList());
        Collections.sort(phrases);
        Collections.sort(msg);
        return Arrays.equals(phrases.toArray(), msg.toArray());
    }

	

}
