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

@Service
public class SateliteServiceImpl implements SateliteService {
	


	
	
	double[][] positions = new double[][] { { -500.0, -200.0 }, { 100.0, -100.0 }, { 500.0, 100.0 } };

	@Override
	public WrapInformation getInformation(SateliteWrap satelites)throws  RebelAllianceException{
		WrapInformation information = new WrapInformation();
		double[] distances= new double[satelites.getSatelite().size()];
			if( satelites.getSatelite().size() < 2) {
            throw new  RebelAllianceException("Para determinar posicion o mensajes se requieren 3 elementos");
		}   
		int i = 0;
		List<List<String>> msgList = new ArrayList<List<String>>();
		//List<String> li = new ArrayList<String>();
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

	public String[] getMessageValid(String[] messages) {
		String[] messageFinal = new String[messages.length];
		int index = 0;
		for (String w : messages) {
			if (true) {
				messageFinal[index] = "";

			} else {
				messageFinal[index] = w;
			}

			index++;
		}

		return messageFinal;

	}

	

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

    public void removeSpace(List<List<String>> msgList, int size){

        int s = 0;
        for(int i = 0; i < msgList.size(); i++){
            s = msgList.get(i).size();
            msgList.set(i, msgList.get(i).subList(s-size, s));
        }

       
    }

    public String organizarMensaje(List<List<String>> msgList){

        String phrase = "";
        for(List<String> m : msgList){

            if(m.size()>0 && !m.get(0).equals("")){
                phrase = (m.size() == 1) ? m.get(0) : m.get(0) + " ";
                msgList.stream().forEach( s -> s.remove(0));
                return  phrase + organizarMensaje(msgList);
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
