/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardmodels.voltagecompensatordynamics.delegate;

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
import com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardmodels.voltagecompensatordynamics.validator.*;

/**
 * GenICompensationForGenJ business delegate class.
 * <p>
 * This class implements the Business Delegate design pattern for the purpose of:
 * <ol>
 * <li>Reducing coupling between the business tier and a client of the business tier by hiding all business-tier implementation details</li>
 * <li>Improving the available of GenICompensationForGenJ related services in the case of a GenICompensationForGenJ business related service failing.</li>
 * <li>Exposes a simpler, uniform GenICompensationForGenJ interface to the business tier, making it easy for clients to consume a simple Java object.</li>
 * <li>Hides the communication protocol that may be required to fulfill GenICompensationForGenJ business related services.</li>
 * </ol>
 * <p>
 * @author your_name_here
 */
public class GenICompensationForGenJBusinessDelegate 
extends BaseBusinessDelegate {
//************************************************************************
// Public Methods
//************************************************************************
    /** 
     * Default Constructor 
     */
    public GenICompensationForGenJBusinessDelegate()  {
    	queryGateway 		= applicationContext.getBean(QueryGateway.class);
    	commandGateway 		= applicationContext.getBean(CommandGateway.class);
    	queryUpdateEmitter  = applicationContext.getBean(QueryUpdateEmitter.class);
	}


   /**
	* GenICompensationForGenJ Business Delegate Factory Method
	*
	* All methods are expected to be self-sufficient.
	*
	* @return 	GenICompensationForGenJBusinessDelegate
	*/
	public static GenICompensationForGenJBusinessDelegate getGenICompensationForGenJInstance() {
		return( new GenICompensationForGenJBusinessDelegate() );
	}
 
   /**
    * Creates the provided command.
    * 
    * @param		command ${class.getCreateCommandAlias()}
    * @exception    ProcessingException
    * @exception	IllegalArgumentException
    * @return		CompletableFuture<UUID>
    */
	public CompletableFuture<UUID> createGenICompensationForGenJ( CreateGenICompensationForGenJCommand command )
    throws ProcessingException, IllegalArgumentException {

		CompletableFuture<UUID> completableFuture = null;
				
		try {
			// --------------------------------------
        	// assign identity now if none
        	// -------------------------------------- 
			if ( command.getGenICompensationForGenJId() == null )
				command.setGenICompensationForGenJId( UUID.randomUUID() );
				
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	GenICompensationForGenJValidator.getInstance().validate( command );    

    		// ---------------------------------------
    		// issue the CreateGenICompensationForGenJCommand - by convention the future return value for a create command
        	// that is handled by the constructor of an aggregate will return the UUID 
    		// ---------------------------------------
        	completableFuture = commandGateway.send( command );
        	
			LOGGER.log( Level.INFO, "return from Command Gateway for CreateGenICompensationForGenJCommand of GenICompensationForGenJ is " + command );
			
        }
        catch (Exception exc) {
            final String errMsg = "Unable to create GenICompensationForGenJ - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return completableFuture;
    }

   /**
    * Update the provided command.
    * @param		command UpdateGenICompensationForGenJCommand
    * @return		CompletableFuture<Void>
    * @exception    ProcessingException
    * @exception  	IllegalArgumentException
    */
    public CompletableFuture<Void> updateGenICompensationForGenJ( UpdateGenICompensationForGenJCommand command ) 
    throws ProcessingException, IllegalArgumentException {
    	CompletableFuture<Void> completableFuture = null;
    	
    	try {       

			// --------------------------------------
        	// validate 
        	// --------------------------------------    	
        	GenICompensationForGenJValidator.getInstance().validate( command );    

        	// --------------------------------------
        	// issue the UpdateGenICompensationForGenJCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
    	}
        catch (Exception exc) {
            final String errMsg = "Unable to save GenICompensationForGenJ - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        
    	return completableFuture;
    }
   
   /**
    * Deletes the associatied value object
    * @param		command DeleteGenICompensationForGenJCommand
    * @return		CompletableFuture<Void>
    * @exception 	ProcessingException
    */
    public CompletableFuture<Void> delete( DeleteGenICompensationForGenJCommand command ) 
    throws ProcessingException, IllegalArgumentException {	
    	
    	CompletableFuture<Void> completableFuture = null;
    	
        try {  
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	GenICompensationForGenJValidator.getInstance().validate( command );    
        	
        	// --------------------------------------
        	// issue the DeleteGenICompensationForGenJCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
        }
        catch (Exception exc) {
            final String errMsg = "Unable to delete GenICompensationForGenJ using Id = "  + command.getGenICompensationForGenJId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }
        
        return completableFuture;
    }

    /**
     * Method to retrieve the GenICompensationForGenJ via GenICompensationForGenJFetchOneSummary
     * @param 	summary GenICompensationForGenJFetchOneSummary 
     * @return 	GenICompensationForGenJFetchOneResponse
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    public GenICompensationForGenJ getGenICompensationForGenJ( GenICompensationForGenJFetchOneSummary summary ) 
    throws ProcessingException, IllegalArgumentException {
    	
    	if( summary == null )
    		throw new IllegalArgumentException( "GenICompensationForGenJFetchOneSummary arg cannot be null" );
    	
    	GenICompensationForGenJ entity = null;
    	
        try {
        	// --------------------------------------
        	// validate the fetch one summary
        	// --------------------------------------    	
        	GenICompensationForGenJValidator.getInstance().validate( summary );    
        	
        	// --------------------------------------
        	// use queryGateway to send request to Find a GenICompensationForGenJ
        	// --------------------------------------
        	CompletableFuture<GenICompensationForGenJ> futureEntity = queryGateway.query(new FindGenICompensationForGenJQuery( new LoadGenICompensationForGenJFilter( summary.getGenICompensationForGenJId() ) ), ResponseTypes.instanceOf(GenICompensationForGenJ.class));
        	
        	entity = futureEntity.get();
        }
        catch( Exception exc ) {
            final String errMsg = "Unable to locate GenICompensationForGenJ with id " + summary.getGenICompensationForGenJId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return entity;
    }


    /**
     * Method to retrieve a collection of all GenICompensationForGenJs
     *
     * @return 	List<GenICompensationForGenJ> 
     * @exception ProcessingException Thrown if any problems
     */
    public List<GenICompensationForGenJ> getAllGenICompensationForGenJ() 
    throws ProcessingException {
        List<GenICompensationForGenJ> list = null;

        try {
        	CompletableFuture<List<GenICompensationForGenJ>> futureList = queryGateway.query(new FindAllGenICompensationForGenJQuery(), ResponseTypes.multipleInstancesOf(GenICompensationForGenJ.class));
        	
        	list = futureList.get();
        }
        catch( Exception exc ) {
            String errMsg = "Failed to get all GenICompensationForGenJ";
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return list;
    }

