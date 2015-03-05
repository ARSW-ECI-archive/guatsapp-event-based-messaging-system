/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.guassap.messaging;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.apache.activemq.command.ActiveMQObjectMessage;

/**
 *
 * @author hcadavid
 */
public class IncomingMessagesListener extends Observable implements MessageListener {

	/**
         * Este método permite inyectarle varios observadores al suscriptor de 
         * mensajes.
         * @param obs 
         */
        public void setObservers(List<Observer> obs){
            for (Observer o:obs){
                this.addObserver(o);
            }
        }
	
	public IncomingMessagesListener() {
		super();
		System.out.println("Waiting for messages...");
	}

	@Override
	public void onMessage(Message m) {
		ActiveMQObjectMessage om=(ActiveMQObjectMessage)m; 
		try {
                        
			String receivedObject=(String)om.getObject();
                        
                        //notificar al observable que se recibió un mensaje
                        setChanged();                        
                        notifyObservers(receivedObject);
                        System.out.println("Got:"+m);
		} catch (JMSException e) {
			throw new RuntimeException("An exception occured while trying to get a JMS object:"+e.getMessage(),e);
		}

	}
        
        

}
