/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.domainprofile.delegate;

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
import com.occulue.europeanstandards.commongridmodelexchangestandard.domainprofile.validator.*;

/**
 * DomainVersion business delegate class.
 * <p>
 * This class implements the Business Delegate design pattern for the purpose of:
 * <ol>
 * <li>Reducing coupling between the business tier and a client of the business tier by hiding all business-tier implementation details</li>
 * <li>Improving the available of DomainVersion related services in the case of a DomainVersion business related service failing.</li>
 * <li>Exposes a simpler, uniform DomainVersion interface to the business tier, making it easy for clients to consume a simple Java object.</li>
 * <li>Hides the communication protocol that may be required to fulfill DomainVersion business related services.</li>
 * </ol>
 * <p>
 * @author your_name_here
 */
public class DomainVersionBusinessDelegate 
extends BaseBusinessDelegate {
//************************************************************************
// Public Methods
//************************************************************************
    /** 
     * Default Constructor 
     */
    public DomainVersionBusinessDelegate()  {
    	queryGateway 		= applicationContext.getBean(QueryGateway.class);
    	commandGateway 		= applicationContext.getBean(CommandGateway.class);
    	queryUpdateEmitter  = applicationContext.getBean(QueryUpdateEmitter.class);
	}


   /**
	* DomainVersion Business Delegate Factory Method
	*
	* All methods are expected to be self-sufficient.
	*
	* @return 	DomainVersionBusinessDelegate
	*/
	public static DomainVersionBusinessDelegate getDomainVersionInstance() {
		return( new DomainVersionBusinessDelegate() );
	}
 
   /**
    * Creates the provided command.
    * 
    * @param		command ${class.getCreateCommandAlias()}
    * @exception    ProcessingException
    * @exception	IllegalArgumentException
    * @return		CompletableFuture<UUID>
    */
	public CompletableFuture<UUID> createDomainVersion( CreateDomainVersionCommand command )
    throws ProcessingException, IllegalArgumentException {

		CompletableFuture<UUID> completableFuture = null;
				
		try {
			// --------------------------------------
        	// assign identity now if none
        	// -------------------------------------- 
			if ( command.getDomainVersionId() == null )
				command.setDomainVersionId( UUID.randomUUID() );
				
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	DomainVersionValidator.getInstance().validate( command );    

    		// ---------------------------------------
    		// issue the CreateDomainVersionCommand - by convention the future return value for a create command
        	// that is handled by the constructor of an aggregate will return the UUID 
    		// ---------------------------------------
        	completableFuture = commandGateway.send( command );
        	
			LOGGER.log( Level.INFO, "return from Command Gateway for CreateDomainVersionCommand of DomainVersion is " + command );
			
        }
        catch (Exception exc) {
            final String errMsg = "Unable to create DomainVersion - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return completableFuture;
    }

   /**
    * Update the provided command.
    * @param		command UpdateDomainVersionCommand
    * @return		CompletableFuture<Void>
    * @exception    ProcessingException
    * @exception  	IllegalArgumentException
    */
    public CompletableFuture<Void> updateDomainVersion( UpdateDomainVersionCommand command ) 
    throws ProcessingException, IllegalArgumentException {
    	CompletableFuture<Void> completableFuture = null;
    	
    	try {       

			// --------------------------------------
        	// validate 
        	// --------------------------------------    	
        	DomainVersionValidator.getInstance().validate( command );    

        	// --------------------------------------
        	// issue the UpdateDomainVersionCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
    	}
        catch (Exception exc) {
            final String errMsg = "Unable to save DomainVersion - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        
    	return completableFuture;
    }
   
   /**
    * Deletes the associatied value object
    * @param		command DeleteDomainVersionCommand
    * @return		CompletableFuture<Void>
    * @exception 	ProcessingException
    */
    public CompletableFuture<Void> delete( DeleteDomainVersionCommand command ) 
    throws ProcessingException, IllegalArgumentException {	
    	
    	CompletableFuture<Void> completableFuture = null;
    	
        try {  
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	DomainVersionValidator.getInstance().validate( command );    
        	
        	// --------------------------------------
        	// issue the DeleteDomainVersionCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
        }
        catch (Exception exc) {
            final String errMsg = "Unable to delete DomainVersion using Id = "  + command.getDomainVersionId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }
        
        return completableFuture;
    }

    /**
     * Method to retrieve the DomainVersion via DomainVersionFetchOneSummary
     * @param 	summary DomainVersionFetchOneSummary 
     * @return 	DomainVersionFetchOneResponse
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    public DomainVersion getDomainVersion( DomainVersionFetchOneSummary summary ) 
    throws ProcessingException, IllegalArgumentException {
    	
    	if( summary == null )
    		throw new IllegalArgumentException( "DomainVersionFetchOneSummary arg cannot be null" );
    	
    	DomainVersion entity = null;
    	
        try {
        	// --------------------------------------
        	// validate the fetch one summary
        	// --------------------------------------    	
        	DomainVersionValidator.getInstance().validate( summary );    
        	
        	// --------------------------------------
        	// use queryGateway to send request to Find a DomainVersion
        	// --------------------------------------
        	CompletableFuture<DomainVersion> futureEntity = queryGateway.query(new FindDomainVersionQuery( new LoadDomainVersionFilter( summary.getDomainVersionId() ) ), ResponseTypes.instanceOf(DomainVersion.class));
        	
        	entity = futureEntity.get();
        }
        catch( Exception exc ) {
            final String errMsg = "Unable to locate DomainVersion with id " + summary.getDomainVersionId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return entity;
    }


    /**
     * Method to retrieve a collection of all DomainVersions
     *
     * @return 	List<DomainVersion> 
     * @exception ProcessingException Thrown if any problems
     */
    public List<DomainVersion> getAllDomainVersion() 
    throws ProcessingException {
        List<DomainVersion> list = null;

        try {
        	CompletableFuture<List<DomainVersion>> futureList = queryGateway.query(new FindAllDomainVersionQuery(), ResponseTypes.multipleInstancesOf(DomainVersion.class));
        	
        	list = futureList.get();
        }
        catch( Exception exc ) {
            String errMsg = "Failed to get all DomainVersion";
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return list;
    }

