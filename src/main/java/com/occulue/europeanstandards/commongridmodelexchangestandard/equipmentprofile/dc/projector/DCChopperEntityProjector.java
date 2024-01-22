/**
 * ***************************************************************************** Turnstone Biologics
 * Confidential
 *
 * <p>2018 Turnstone Biologics All Rights Reserved.
 *
 * <p>This file is subject to the terms and conditions defined in file 'license.txt', which is part
 * of this source code package.
 *
 * <p>Contributors : Turnstone Biologics - General Release
 * ****************************************************************************
 */
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.dc.projector;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.dc.repository.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Projector for DCChopper as outlined for the CQRS pattern.
 *
 * <p>Commands are handled by DCChopperAggregate
 *
 * @author your_name_here
 */
@Component("dCChopper-entity-projector")
public class DCChopperEntityProjector {

  // core constructor
  public DCChopperEntityProjector(DCChopperRepository repository) {
    this.repository = repository;
  }

  /*
   * Insert a DCChopper
   *
   * @param	entity DCChopper
   */
  public DCChopper create(DCChopper entity) {
    LOGGER.info("creating " + entity.toString());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Update a DCChopper
   *
   * @param	entity DCChopper
   */
  public DCChopper update(DCChopper entity) {
    LOGGER.info("updating " + entity.toString());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Delete a DCChopper
   *
   * @param	id		UUID
   */
  public DCChopper delete(UUID id) {
    LOGGER.info("deleting " + id.toString());

    // ------------------------------------------
    // locate the entity by the provided id
    // ------------------------------------------
    DCChopper entity = repository.findById(id).get();

    // ------------------------------------------
    // delete what is discovered
    // ------------------------------------------
    repository.delete(entity);

    return entity;
  }

  /**
   * Method to retrieve the DCChopper via an FindDCChopperQuery
   *
   * @return query FindDCChopperQuery
   */
  @SuppressWarnings("unused")
  public DCChopper find(UUID id) {
    LOGGER.info("handling find using " + id.toString());
    try {
      return repository.findById(id).get();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find a DCChopper - {0}", exc.getMessage());
    }
    return null;
  }

  /**
   * Method to retrieve a collection of all DCChoppers
   *
   * @param query FindAllDCChopperQuery
   * @return List<DCChopper>
   */
  @SuppressWarnings("unused")
  public List<DCChopper> findAll(FindAllDCChopperQuery query) {
    LOGGER.info("handling findAll using " + query.toString());
    try {
      return repository.findAll();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find all DCChopper - {0}", exc.getMessage());
    }
    return null;
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired protected final DCChopperRepository repository;

  private static final Logger LOGGER = Logger.getLogger(DCChopperEntityProjector.class.getName());
}
