/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.wires.delegate;

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
import com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.wires.validator.*;

/**
 * NonlinearShuntCompensatorPoint business delegate class.
 * <p>
 * This class implements the Business Delegate design pattern for the purpose of:
 * <ol>
 * <li>Reducing coupling between the business tier and a client of the business tier by hiding all business-tier implementation details</li>
 * <li>Improving the available of NonlinearShuntCompensatorPoint related services in the case of a NonlinearShuntCompensatorPoint business related service failing.</li>
 * <li>Exposes a simpler, uniform NonlinearShuntCompensatorPoint interface to the business tier, making it easy for clients to consume a simple Java object.</li>
 * <li>Hides the communication protocol that may be required to fulfill NonlinearShuntCompensatorPoint business related services.</li>
 * </ol>
 * <p>
 * @author your_name_here
 */
public class NonlinearShuntCompensatorPointBusinessDelegate 
extends BaseBusinessDelegate {
//************************************************************************
// Public Methods
//************************************************************************
    /** 
     * Default Constructor 
     */
    public NonlinearShuntCompensatorPointBusinessDelegate()  {
    	queryGateway 		= applicationContext.getBean(QueryGateway.class);
    	commandGateway 		= applicationContext.getBean(CommandGateway.class);
    	queryUpdateEmitter  = applicationContext.getBean(QueryUpdateEmitter.class);
	}


   /**
	* NonlinearShuntCompensatorPoint Business Delegate Factory Method
	*
	* All methods are expected to be self-sufficient.
	*
	* @return 	NonlinearShuntCompensatorPointBusinessDelegate
	*/
	public static NonlinearShuntCompensatorPointBusinessDelegate getNonlinearShuntCompensatorPointInstance() {
		return( new NonlinearShuntCompensatorPointBusinessDelegate() );
	}
 
   /**
    * Creates the provided command.
    * 
    * @param		command ${class.getCreateCommandAlias()}
    * @exception    ProcessingException
    * @exception	IllegalArgumentException
    * @return		CompletableFuture<UUID>
    */
	public CompletableFuture<UUID> createNonlinearShuntCompensatorPoint( CreateNonlinearShuntCompensatorPointCommand command )
    throws ProcessingException, IllegalArgumentException {

		CompletableFuture<UUID> completableFuture = null;
				
		try {
			// --------------------------------------
        	// assign identity now if none
        	// -------------------------------------- 
			if ( command.getNonlinearShuntCompensatorPointId() == null )
				command.setNonlinearShuntCompensatorPointId( UUID.randomUUID() );
				
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	NonlinearShuntCompensatorPointValidator.getInstance().validate( command );    

    		// ---------------------------------------
    		// issue the CreateNonlinearShuntCompensatorPointCommand - by convention the future return value for a create command
        	// that is handled by the constructor of an aggregate will return the UUID 
    		// ---------------------------------------
        	completableFuture = commandGateway.send( command );
        	
			LOGGER.log( Level.INFO, "return from Command Gateway for CreateNonlinearShuntCompensatorPointCommand of NonlinearShuntCompensatorPoint is " + command );
			
        }
        catch (Exception exc) {
            final String errMsg = "Unable to create NonlinearShuntCompensatorPoint - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return completableFuture;
    }

   /**
    * Update the provided command.
    * @param		command UpdateNonlinearShuntCompensatorPointCommand
    * @return		CompletableFuture<Void>
    * @exception    ProcessingException
    * @exception  	IllegalArgumentException
    */
    public CompletableFuture<Void> updateNonlinearShuntCompensatorPoint( UpdateNonlinearShuntCompensatorPointCommand command ) 
    throws ProcessingException, IllegalArgumentException {
    	CompletableFuture<Void> completableFuture = null;
    	
    	try {       

			// --------------------------------------
        	// validate 
        	// --------------------------------------    	
        	NonlinearShuntCompensatorPointValidator.getInstance().validate( command );    

        	// --------------------------------------
        	// issue the UpdateNonlinearShuntCompensatorPointCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
    	}
        catch (Exception exc) {
            final String errMsg = "Unable to save NonlinearShuntCompensatorPoint - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        
    	return completableFuture;
    }
   
   /**
    * Deletes the associatied value object
    * @param		command DeleteNonlinearShuntCompensatorPointCommand
    * @return		CompletableFuture<Void>
    * @exception 	ProcessingException
    */
    public CompletableFuture<Void> delete( DeleteNonlinearShuntCompensatorPointCommand command ) 
    throws ProcessingException, IllegalArgumentException {	
    	
    	CompletableFuture<Void> completableFuture = null;
    	
        try {  
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	NonlinearShuntCompensatorPointValidator.getInstance().validate( command );    
        	
        	// --------------------------------------
        	// issue the DeleteNonlinearShuntCompensatorPointCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
        }
        catch (Exception exc) {
            final String errMsg = "Unable to delete NonlinearShuntCompensatorPoint using Id = "  + command.getNonlinearShuntCompensatorPointId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }
        
        return completableFuture;
    }

    /**
     * Method to retrieve the NonlinearShuntCompensatorPoint via NonlinearShuntCompensatorPointFetchOneSummary
     * @param 	summary NonlinearShuntCompensatorPointFetchOneSummary 
     * @return 	NonlinearShuntCompensatorPointFetchOneResponse
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    public NonlinearShuntCompensatorPoint getNonlinearShuntCompensatorPoint( NonlinearShuntCompensatorPointFetchOneSummary summary ) 
    throws ProcessingException, IllegalArgumentException {
    	
    	if( summary == null )
    		throw new IllegalArgumentException( "NonlinearShuntCompensatorPointFetchOneSummary arg cannot be null" );
    	
    	NonlinearShuntCompensatorPoint entity = null;
    	
        try {
        	// --------------------------------------
        	// validate the fetch one summary
        	// --------------------------------------    	
        	NonlinearShuntCompensatorPointValidator.getInstance().validate( summary );    
        	
        	// --------------------------------------
        	// use queryGateway to send request to Find a NonlinearShuntCompensatorPoint
        	// --------------------------------------
        	CompletableFuture<NonlinearShuntCompensatorPoint> futureEntity = queryGateway.query(new FindNonlinearShuntCompensatorPointQuery( new LoadNonlinearShuntCompensatorPointFilter( summary.getNonlinearShuntCompensatorPointId() ) ), ResponseTypes.instanceOf(NonlinearShuntCompensatorPoint.class));
        	
        	entity = futureEntity.get();
        }
        catch( Exception exc ) {
            final String errMsg = "Unable to locate NonlinearShuntCompensatorPoint with id " + summary.getNonlinearShuntCompensatorPointId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return entity;
    }


    /**
     * Method to retrieve a collection of all NonlinearShuntCompensatorPoints
     *
     * @return 	List<NonlinearShuntCompensatorPoint> 
     * @exception ProcessingException Thrown if any problems
     */
    public List<NonlinearShuntCompensatorPoint> getAllNonlinearShuntCompensatorPoint() 
    throws ProcessingException {
        List<NonlinearShuntCompensatorPoint> list = null;

        try {
        	CompletableFuture<List<NonlinearShuntCompensatorPoint>> futureList = queryGateway.query(new FindAllNonlinearShuntCompensatorPointQuery(), ResponseTypes.multipleInstancesOf(NonlinearShuntCompensatorPoint.class));
        	
        	list = futureList.get();
        }
        catch( Exception exc ) {
            String errMsg = "Failed to get all NonlinearShuntCompensatorPoint";
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return list;
    }

