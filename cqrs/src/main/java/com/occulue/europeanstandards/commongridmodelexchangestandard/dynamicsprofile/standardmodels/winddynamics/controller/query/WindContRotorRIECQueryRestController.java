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
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.standardmodels.winddynamics.controller.query;

import com.occulue.api.*;
import com.occulue.controller.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.*;

/**
 * Implements Spring Controller query CQRS processing for entity WindContRotorRIEC.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/WindContRotorRIEC")
public class WindContRotorRIECQueryRestController extends BaseSpringRestController {

  /**
   * Handles loading a WindContRotorRIEC using a UUID
   *
   * @param UUID windContRotorRIECId
   * @return WindContRotorRIEC
   */
  @GetMapping("/load")
  public WindContRotorRIEC load(@RequestParam(required = true) UUID windContRotorRIECId) {
    WindContRotorRIEC entity = null;

    try {
      entity =
          WindContRotorRIECBusinessDelegate.getWindContRotorRIECInstance()
              .getWindContRotorRIEC(new WindContRotorRIECFetchOneSummary(windContRotorRIECId));
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load WindContRotorRIEC using Id " + windContRotorRIECId);
      return null;
    }

    return entity;
  }

  /**
   * Handles loading all WindContRotorRIEC business objects
   *
   * @return Set<WindContRotorRIEC>
   */
  @GetMapping("/")
  public List<WindContRotorRIEC> loadAll() {
    List<WindContRotorRIEC> windContRotorRIECList = null;

    try {
      // load the WindContRotorRIEC
      windContRotorRIECList =
          WindContRotorRIECBusinessDelegate.getWindContRotorRIECInstance()
              .getAllWindContRotorRIEC();

      if (windContRotorRIECList != null)
        LOGGER.log(Level.INFO, "successfully loaded all WindContRotorRIECs");
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "failed to load all WindContRotorRIECs ", exc);
      return null;
    }

    return windContRotorRIECList;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected WindContRotorRIEC windContRotorRIEC = null;
  private static final Logger LOGGER =
      Logger.getLogger(WindContRotorRIECQueryRestController.class.getName());
}
