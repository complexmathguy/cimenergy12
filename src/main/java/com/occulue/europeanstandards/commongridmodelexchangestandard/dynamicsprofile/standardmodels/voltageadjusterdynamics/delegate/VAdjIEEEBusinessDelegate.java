/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardmodels.voltageadjusterdynamics.delegate;

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
import com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardmodels.voltageadjusterdynamics.validator.*;

/**
 * VAdjIEEE business delegate class.
 * <p>
 * This class implements the Business Delegate design pattern for the purpose of:
 * <ol>
 * <li>Reducing coupling between the business tier and a client of the business tier by hiding all business-tier implementation details</li>
 * <li>Improving the available of VAdjIEEE related services in the case of a VAdjIEEE business related service failing.</li>
 * <li>Exposes a simpler, uniform VAdjIEEE interface to the business tier, making it easy for clients to consume a simple Java object.</li>
 * <li>Hides the communication protocol that may be required to fulfill VAdjIEEE business related services.</li>
 * </ol>
 * <p>
 * @author your_name_here
 */
public class VAdjIEEEBusinessDelegate 
extends BaseBusinessDelegate {
//************************************************************************
// Public Methods
//************************************************************************
    /** 
     * Default Constructor 
     */
    public VAdjIEEEBusinessDelegate()  {
    	queryGateway 		= applicationContext.getBean(QueryGateway.class);
    	commandGateway 		= applicationContext.getBean(CommandGateway.class);
    	queryUpdateEmitter  = applicationContext.getBean(QueryUpdateEmitter.class);
	}


   /**
	* VAdjIEEE Business Delegate Factory Method
	*
	* All methods are expected to be self-sufficient.
	*
	* @return 	VAdjIEEEBusinessDelegate
	*/
	public static VAdjIEEEBusinessDelegate getVAdjIEEEInstance() {
		return( new VAdjIEEEBusinessDelegate() );
	}
 
   /**
    * Creates the provided command.
    * 
    * @param		command ${class.getCreateCommandAlias()}
    * @exception    ProcessingException
    * @exception	IllegalArgumentException
    * @return		CompletableFuture<UUID>
    */
	public CompletableFuture<UUID> createVAdjIEEE( CreateVAdjIEEECommand command )
    throws ProcessingException, IllegalArgumentException {

		CompletableFuture<UUID> completableFuture = null;
				
		try {
			// --------------------------------------
        	// assign identity now if none
        	// -------------------------------------- 
			if ( command.getVAdjIEEEId() == null )
				command.setVAdjIEEEId( UUID.randomUUID() );
				
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	VAdjIEEEValidator.getInstance().validate( command );    

    		// ---------------------------------------
    		// issue the CreateVAdjIEEECommand - by convention the future return value for a create command
        	// that is handled by the constructor of an aggregate will return the UUID 
    		// ---------------------------------------
        	completableFuture = commandGateway.send( command );
        	
			LOGGER.log( Level.INFO, "return from Command Gateway for CreateVAdjIEEECommand of VAdjIEEE is " + command );
			
        }
        catch (Exception exc) {
            final String errMsg = "Unable to create VAdjIEEE - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return completableFuture;
    }

   /**
    * Update the provided command.
    * @param		command UpdateVAdjIEEECommand
    * @return		CompletableFuture<Void>
    * @exception    ProcessingException
    * @exception  	IllegalArgumentException
    */
    public CompletableFuture<Void> updateVAdjIEEE( UpdateVAdjIEEECommand command ) 
    throws ProcessingException, IllegalArgumentException {
    	CompletableFuture<Void> completableFuture = null;
    	
    	try {       

			// --------------------------------------
        	// validate 
        	// --------------------------------------    	
        	VAdjIEEEValidator.getInstance().validate( command );    

        	// --------------------------------------
        	// issue the UpdateVAdjIEEECommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
    	}
        catch (Exception exc) {
            final String errMsg = "Unable to save VAdjIEEE - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        
    	return completableFuture;
    }
   
   /**
    * Deletes the associatied value object
    * @param		command DeleteVAdjIEEECommand
    * @return		CompletableFuture<Void>
    * @exception 	ProcessingException
    */
    public CompletableFuture<Void> delete( DeleteVAdjIEEECommand command ) 
    throws ProcessingException, IllegalArgumentException {	
    	
    	CompletableFuture<Void> completableFuture = null;
    	
        try {  
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	VAdjIEEEValidator.getInstance().validate( command );    
        	
        	// --------------------------------------
        	// issue the DeleteVAdjIEEECommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
        }
        catch (Exception exc) {
            final String errMsg = "Unable to delete VAdjIEEE using Id = "  + command.getVAdjIEEEId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }
        
        return completableFuture;
    }

    /**
     * Method to retrieve the VAdjIEEE via VAdjIEEEFetchOneSummary
     * @param 	summary VAdjIEEEFetchOneSummary 
     * @return 	VAdjIEEEFetchOneResponse
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    public VAdjIEEE getVAdjIEEE( VAdjIEEEFetchOneSummary summary ) 
    throws ProcessingException, IllegalArgumentException {
    	
    	if( summary == null )
    		throw new IllegalArgumentException( "VAdjIEEEFetchOneSummary arg cannot be null" );
    	
    	VAdjIEEE entity = null;
    	
        try {
        	// --------------------------------------
        	// validate the fetch one summary
        	// --------------------------------------    	
        	VAdjIEEEValidator.getInstance().validate( summary );    
        	
        	// --------------------------------------
        	// use queryGateway to send request to Find a VAdjIEEE
        	// --------------------------------------
        	CompletableFuture<VAdjIEEE> futureEntity = queryGateway.query(new FindVAdjIEEEQuery( new LoadVAdjIEEEFilter( summary.getVAdjIEEEId() ) ), ResponseTypes.instanceOf(VAdjIEEE.class));
        	
        	entity = futureEntity.get();
        }
        catch( Exception exc ) {
            final String errMsg = "Unable to locate VAdjIEEE with id " + summary.getVAdjIEEEId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return entity;
    }


    /**
     * Method to retrieve a collection of all VAdjIEEEs
     *
     * @return 	List<VAdjIEEE> 
     * @exception ProcessingException Thrown if any problems
     */
    public List<VAdjIEEE> getAllVAdjIEEE() 
    throws ProcessingException {
        List<VAdjIEEE> list = null;

        try {
        	CompletableFuture<List<VAdjIEEE>> futureList = queryGateway.query(new FindAllVAdjIEEEQuery(), ResponseTypes.multipleInstancesOf(VAdjIEEE.class));
        	
        	list = futureList.get();
        }
        catch( Exception exc ) {
            String errMsg = "Failed to get all VAdjIEEE";
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return list;
    }

