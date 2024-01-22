/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentboundaryprofile.core.delegate;

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
import com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentboundaryprofile.core.validator.*;

/**
 * SubGeographicalRegion business delegate class.
 * <p>
 * This class implements the Business Delegate design pattern for the purpose of:
 * <ol>
 * <li>Reducing coupling between the business tier and a client of the business tier by hiding all business-tier implementation details</li>
 * <li>Improving the available of SubGeographicalRegion related services in the case of a SubGeographicalRegion business related service failing.</li>
 * <li>Exposes a simpler, uniform SubGeographicalRegion interface to the business tier, making it easy for clients to consume a simple Java object.</li>
 * <li>Hides the communication protocol that may be required to fulfill SubGeographicalRegion business related services.</li>
 * </ol>
 * <p>
 * @author your_name_here
 */
public class SubGeographicalRegionBusinessDelegate 
extends BaseBusinessDelegate {
//************************************************************************
// Public Methods
//************************************************************************
    /** 
     * Default Constructor 
     */
    public SubGeographicalRegionBusinessDelegate()  {
    	queryGateway 		= applicationContext.getBean(QueryGateway.class);
    	commandGateway 		= applicationContext.getBean(CommandGateway.class);
    	queryUpdateEmitter  = applicationContext.getBean(QueryUpdateEmitter.class);
	}


   /**
	* SubGeographicalRegion Business Delegate Factory Method
	*
	* All methods are expected to be self-sufficient.
	*
	* @return 	SubGeographicalRegionBusinessDelegate
	*/
	public static SubGeographicalRegionBusinessDelegate getSubGeographicalRegionInstance() {
		return( new SubGeographicalRegionBusinessDelegate() );
	}
 
   /**
    * Creates the provided command.
    * 
    * @param		command ${class.getCreateCommandAlias()}
    * @exception    ProcessingException
    * @exception	IllegalArgumentException
    * @return		CompletableFuture<UUID>
    */
	public CompletableFuture<UUID> createSubGeographicalRegion( CreateSubGeographicalRegionCommand command )
    throws ProcessingException, IllegalArgumentException {

		CompletableFuture<UUID> completableFuture = null;
				
		try {
			// --------------------------------------
        	// assign identity now if none
        	// -------------------------------------- 
			if ( command.getSubGeographicalRegionId() == null )
				command.setSubGeographicalRegionId( UUID.randomUUID() );
				
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	SubGeographicalRegionValidator.getInstance().validate( command );    

    		// ---------------------------------------
    		// issue the CreateSubGeographicalRegionCommand - by convention the future return value for a create command
        	// that is handled by the constructor of an aggregate will return the UUID 
    		// ---------------------------------------
        	completableFuture = commandGateway.send( command );
        	
			LOGGER.log( Level.INFO, "return from Command Gateway for CreateSubGeographicalRegionCommand of SubGeographicalRegion is " + command );
			
        }
        catch (Exception exc) {
            final String errMsg = "Unable to create SubGeographicalRegion - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return completableFuture;
    }

   /**
    * Update the provided command.
    * @param		command UpdateSubGeographicalRegionCommand
    * @return		CompletableFuture<Void>
    * @exception    ProcessingException
    * @exception  	IllegalArgumentException
    */
    public CompletableFuture<Void> updateSubGeographicalRegion( UpdateSubGeographicalRegionCommand command ) 
    throws ProcessingException, IllegalArgumentException {
    	CompletableFuture<Void> completableFuture = null;
    	
    	try {       

			// --------------------------------------
        	// validate 
        	// --------------------------------------    	
        	SubGeographicalRegionValidator.getInstance().validate( command );    

        	// --------------------------------------
        	// issue the UpdateSubGeographicalRegionCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
    	}
        catch (Exception exc) {
            final String errMsg = "Unable to save SubGeographicalRegion - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        
    	return completableFuture;
    }
   
   /**
    * Deletes the associatied value object
    * @param		command DeleteSubGeographicalRegionCommand
    * @return		CompletableFuture<Void>
    * @exception 	ProcessingException
    */
    public CompletableFuture<Void> delete( DeleteSubGeographicalRegionCommand command ) 
    throws ProcessingException, IllegalArgumentException {	
    	
    	CompletableFuture<Void> completableFuture = null;
    	
        try {  
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	SubGeographicalRegionValidator.getInstance().validate( command );    
        	
        	// --------------------------------------
        	// issue the DeleteSubGeographicalRegionCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
        }
        catch (Exception exc) {
            final String errMsg = "Unable to delete SubGeographicalRegion using Id = "  + command.getSubGeographicalRegionId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }
        
        return completableFuture;
    }

    /**
     * Method to retrieve the SubGeographicalRegion via SubGeographicalRegionFetchOneSummary
     * @param 	summary SubGeographicalRegionFetchOneSummary 
     * @return 	SubGeographicalRegionFetchOneResponse
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    public SubGeographicalRegion getSubGeographicalRegion( SubGeographicalRegionFetchOneSummary summary ) 
    throws ProcessingException, IllegalArgumentException {
    	
    	if( summary == null )
    		throw new IllegalArgumentException( "SubGeographicalRegionFetchOneSummary arg cannot be null" );
    	
    	SubGeographicalRegion entity = null;
    	
        try {
        	// --------------------------------------
        	// validate the fetch one summary
        	// --------------------------------------    	
        	SubGeographicalRegionValidator.getInstance().validate( summary );    
        	
        	// --------------------------------------
        	// use queryGateway to send request to Find a SubGeographicalRegion
        	// --------------------------------------
        	CompletableFuture<SubGeographicalRegion> futureEntity = queryGateway.query(new FindSubGeographicalRegionQuery( new LoadSubGeographicalRegionFilter( summary.getSubGeographicalRegionId() ) ), ResponseTypes.instanceOf(SubGeographicalRegion.class));
        	
        	entity = futureEntity.get();
        }
        catch( Exception exc ) {
            final String errMsg = "Unable to locate SubGeographicalRegion with id " + summary.getSubGeographicalRegionId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return entity;
    }


    /**
     * Method to retrieve a collection of all SubGeographicalRegions
     *
     * @return 	List<SubGeographicalRegion> 
     * @exception ProcessingException Thrown if any problems
     */
    public List<SubGeographicalRegion> getAllSubGeographicalRegion() 
    throws ProcessingException {
        List<SubGeographicalRegion> list = null;

        try {
        	CompletableFuture<List<SubGeographicalRegion>> futureList = queryGateway.query(new FindAllSubGeographicalRegionQuery(), ResponseTypes.multipleInstancesOf(SubGeographicalRegion.class));
        	
        	list = futureList.get();
        }
        catch( Exception exc ) {
            String errMsg = "Failed to get all SubGeographicalRegion";
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return list;
    }


