/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardmodels.synchronousmachinedynamics.delegate;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.util.concurrent.*;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryUpdateEmitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.occulue.api.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardmodels.synchronousmachinedynamics.validator.*;

/**
 * SynchronousMachineEquivalentCircuit business delegate class.
 * <p>
 * This class implements the Business Delegate design pattern for the purpose of:
 * <ol>
 * <li>Reducing coupling between the business tier and a client of the business tier by hiding all business-tier implementation details</li>
 * <li>Improving the available of SynchronousMachineEquivalentCircuit related services in the case of a SynchronousMachineEquivalentCircuit business related service failing.</li>
 * <li>Exposes a simpler, uniform SynchronousMachineEquivalentCircuit interface to the business tier, making it easy for clients to consume a simple Java object.</li>
 * <li>Hides the communication protocol that may be required to fulfill SynchronousMachineEquivalentCircuit business related services.</li>
 * </ol>
 * <p>
 * @author your_name_here
 */
public class SynchronousMachineEquivalentCircuitBusinessDelegate 
extends BaseBusinessDelegate {
//************************************************************************
// Public Methods
//************************************************************************
    /** 
     * Default Constructor 
     */
    public SynchronousMachineEquivalentCircuitBusinessDelegate()  {
    	queryGateway 		= applicationContext.getBean(QueryGateway.class);
    	commandGateway 		= applicationContext.getBean(CommandGateway.class);
    	queryUpdateEmitter  = applicationContext.getBean(QueryUpdateEmitter.class);
	}


   /**
	* SynchronousMachineEquivalentCircuit Business Delegate Factory Method
	*
	* All methods are expected to be self-sufficient.
	*
	* @return 	SynchronousMachineEquivalentCircuitBusinessDelegate
	*/
	public static SynchronousMachineEquivalentCircuitBusinessDelegate getSynchronousMachineEquivalentCircuitInstance() {
		return( new SynchronousMachineEquivalentCircuitBusinessDelegate() );
	}
 
   /**
    * Creates the provided command.
    * 
    * @param		command ${class.getCreateCommandAlias()}
    * @exception    ProcessingException
    * @exception	IllegalArgumentException
    * @return		CompletableFuture<UUID>
    */
	public CompletableFuture<UUID> createSynchronousMachineEquivalentCircuit( CreateSynchronousMachineEquivalentCircuitCommand command )
    throws ProcessingException, IllegalArgumentException {

		CompletableFuture<UUID> completableFuture = null;
				
		try {
			// --------------------------------------
        	// assign identity now if none
        	// -------------------------------------- 
			if ( command.getSynchronousMachineEquivalentCircuitId() == null )
				command.setSynchronousMachineEquivalentCircuitId( UUID.randomUUID() );
				
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	SynchronousMachineEquivalentCircuitValidator.getInstance().validate( command );    

    		// ---------------------------------------
    		// issue the CreateSynchronousMachineEquivalentCircuitCommand - by convention the future return value for a create command
        	// that is handled by the constructor of an aggregate will return the UUID 
    		// ---------------------------------------
        	completableFuture = commandGateway.send( command );
        	
			LOGGER.log( Level.INFO, "return from Command Gateway for CreateSynchronousMachineEquivalentCircuitCommand of SynchronousMachineEquivalentCircuit is " + command );
			
        }
        catch (Exception exc) {
            final String errMsg = "Unable to create SynchronousMachineEquivalentCircuit - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return completableFuture;
    }

   /**
    * Update the provided command.
    * @param		command UpdateSynchronousMachineEquivalentCircuitCommand
    * @return		CompletableFuture<Void>
    * @exception    ProcessingException
    * @exception  	IllegalArgumentException
    */
    public CompletableFuture<Void> updateSynchronousMachineEquivalentCircuit( UpdateSynchronousMachineEquivalentCircuitCommand command ) 
    throws ProcessingException, IllegalArgumentException {
    	CompletableFuture<Void> completableFuture = null;
    	
    	try {       

			// --------------------------------------
        	// validate 
        	// --------------------------------------    	
        	SynchronousMachineEquivalentCircuitValidator.getInstance().validate( command );    

        	// --------------------------------------
        	// issue the UpdateSynchronousMachineEquivalentCircuitCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
    	}
        catch (Exception exc) {
            final String errMsg = "Unable to save SynchronousMachineEquivalentCircuit - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        
    	return completableFuture;
    }
   
   /**
    * Deletes the associatied value object
    * @param		command DeleteSynchronousMachineEquivalentCircuitCommand
    * @return		CompletableFuture<Void>
    * @exception 	ProcessingException
    */
    public CompletableFuture<Void> delete( DeleteSynchronousMachineEquivalentCircuitCommand command ) 
    throws ProcessingException, IllegalArgumentException {	
    	
    	CompletableFuture<Void> completableFuture = null;
    	
        try {  
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	SynchronousMachineEquivalentCircuitValidator.getInstance().validate( command );    
        	
        	// --------------------------------------
        	// issue the DeleteSynchronousMachineEquivalentCircuitCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
        }
        catch (Exception exc) {
            final String errMsg = "Unable to delete SynchronousMachineEquivalentCircuit using Id = "  + command.getSynchronousMachineEquivalentCircuitId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }
        
        return completableFuture;
    }

    /**
     * Method to retrieve the SynchronousMachineEquivalentCircuit via SynchronousMachineEquivalentCircuitFetchOneSummary
     * @param 	summary SynchronousMachineEquivalentCircuitFetchOneSummary 
     * @return 	SynchronousMachineEquivalentCircuitFetchOneResponse
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    public SynchronousMachineEquivalentCircuit getSynchronousMachineEquivalentCircuit( SynchronousMachineEquivalentCircuitFetchOneSummary summary ) 
    throws ProcessingException, IllegalArgumentException {
    	
    	if( summary == null )
    		throw new IllegalArgumentException( "SynchronousMachineEquivalentCircuitFetchOneSummary arg cannot be null" );
    	
    	SynchronousMachineEquivalentCircuit entity = null;
    	
        try {
        	// --------------------------------------
        	// validate the fetch one summary
        	// --------------------------------------    	
        	SynchronousMachineEquivalentCircuitValidator.getInstance().validate( summary );    
        	
        	// --------------------------------------
        	// use queryGateway to send request to Find a SynchronousMachineEquivalentCircuit
        	// --------------------------------------
        	CompletableFuture<SynchronousMachineEquivalentCircuit> futureEntity = queryGateway.query(new FindSynchronousMachineEquivalentCircuitQuery( new LoadSynchronousMachineEquivalentCircuitFilter( summary.getSynchronousMachineEquivalentCircuitId() ) ), ResponseTypes.instanceOf(SynchronousMachineEquivalentCircuit.class));
        	
        	entity = futureEntity.get();
        }
        catch( Exception exc ) {
            final String errMsg = "Unable to locate SynchronousMachineEquivalentCircuit with id " + summary.getSynchronousMachineEquivalentCircuitId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return entity;
    }


    /**
     * Method to retrieve a collection of all SynchronousMachineEquivalentCircuits
     *
     * @return 	List<SynchronousMachineEquivalentCircuit> 
     * @exception ProcessingException Thrown if any problems
     */
    public List<SynchronousMachineEquivalentCircuit> getAllSynchronousMachineEquivalentCircuit() 
    throws ProcessingException {
        List<SynchronousMachineEquivalentCircuit> list = null;

        try {
        	CompletableFuture<List<SynchronousMachineEquivalentCircuit>> futureList = queryGateway.query(new FindAllSynchronousMachineEquivalentCircuitQuery(), ResponseTypes.multipleInstancesOf(SynchronousMachineEquivalentCircuit.class));
        	
        	list = futureList.get();
        }
        catch( Exception exc ) {
            String errMsg = "Failed to get all SynchronousMachineEquivalentCircuit";
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return list;
    }

