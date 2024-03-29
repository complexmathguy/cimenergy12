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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.meas.projector;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.meas.repository.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Projector for AnalogLimitSet as outlined for the CQRS pattern.
 *
 * <p>Commands are handled by AnalogLimitSetAggregate
 *
 * @author your_name_here
 */
@Component("analogLimitSet-entity-projector")
public class AnalogLimitSetEntityProjector {

  // core constructor
  public AnalogLimitSetEntityProjector(AnalogLimitSetRepository repository) {
    this.repository = repository;
  }

  /*
   * Insert a AnalogLimitSet
   *
   * @param	entity AnalogLimitSet
   */
  public AnalogLimitSet create(AnalogLimitSet entity) {
    LOGGER.info("creating " + entity.toString());

    // ------------------------------------------
    // persist a new one
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Update a AnalogLimitSet
   *
   * @param	entity AnalogLimitSet
   */
  public AnalogLimitSet update(AnalogLimitSet entity) {
    LOGGER.info("updating " + entity.toString());

    // ------------------------------------------
    // save with an existing instance
    // ------------------------------------------
    return repository.save(entity);
  }

  /*
   * Delete a AnalogLimitSet
   *
   * @param	id		UUID
   */
  public AnalogLimitSet delete(UUID id) {
    LOGGER.info("deleting " + id.toString());

    // ------------------------------------------
    // locate the entity by the provided id
    // ------------------------------------------
    AnalogLimitSet entity = repository.findById(id).get();

    // ------------------------------------------
    // delete what is discovered
    // ------------------------------------------
    repository.delete(entity);

    return entity;
  }

  /*
   * Add to the LimitSets
   *
   * @param	parentID	UUID
   * @param	addTo		childType
   * @return	AnalogLimitSet
   */
  public AnalogLimitSet addToLimitSets(UUID parentId, AnalogLimitSet addTo) {
    LOGGER.info("handling AssignLimitSetsToAnalogLimitSetEvent - ");

    AnalogLimitSet parentEntity = repository.findById(parentId).get();
    AnalogLimitSet child = AnalogLimitSetProjector.find(addTo.getAnalogLimitSetId());

    parentEntity.getLimitSets().add(child);

    // ------------------------------------------
    // save
    // ------------------------------------------
    repository.save(parentEntity);

    return parentEntity;
  }

  /*
   * Remove from the LimitSets
   *
   * @param	parentID	UUID
   * @param	removeFrom	childType
   * @return	AnalogLimitSet
   */
  public AnalogLimitSet removeFromLimitSets(UUID parentId, AnalogLimitSet removeFrom) {
    LOGGER.info("handling RemoveLimitSetsFromAnalogLimitSetEvent ");

    AnalogLimitSet parentEntity = repository.findById(parentId).get();
    AnalogLimitSet child = AnalogLimitSetProjector.find(removeFrom.getAnalogLimitSetId());

    parentEntity.getLimitSets().remove(child);

    // ------------------------------------------
    // save
    // ------------------------------------------
    update(parentEntity);

    return parentEntity;
  }

  /**
   * Method to retrieve the AnalogLimitSet via an FindAnalogLimitSetQuery
   *
   * @return query FindAnalogLimitSetQuery
   */
  @SuppressWarnings("unused")
  public AnalogLimitSet find(UUID id) {
    LOGGER.info("handling find using " + id.toString());
    try {
      return repository.findById(id).get();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find a AnalogLimitSet - {0}", exc.getMessage());
    }
    return null;
  }

  /**
   * Method to retrieve a collection of all AnalogLimitSets
   *
   * @param query FindAllAnalogLimitSetQuery
   * @return List<AnalogLimitSet>
   */
  @SuppressWarnings("unused")
  public List<AnalogLimitSet> findAll(FindAllAnalogLimitSetQuery query) {
    LOGGER.info("handling findAll using " + query.toString());
    try {
      return repository.findAll();
    } catch (IllegalArgumentException exc) {
      LOGGER.log(Level.WARNING, "Failed to find all AnalogLimitSet - {0}", exc.getMessage());
    }
    return null;
  }

  // --------------------------------------------------
  // attributes
  // --------------------------------------------------
  @Autowired protected final AnalogLimitSetRepository repository;

  @Autowired
  @Qualifier(value = "analogLimitSet-entity-projector")
  com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.meas.projector
          .AnalogLimitSetEntityProjector
      AnalogLimitSetProjector;

  private static final Logger LOGGER =
      Logger.getLogger(AnalogLimitSetEntityProjector.class.getName());
}
