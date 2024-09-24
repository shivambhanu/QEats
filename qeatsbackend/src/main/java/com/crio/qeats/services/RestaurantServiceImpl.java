
/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.services;

import com.crio.qeats.dto.Restaurant;
import com.crio.qeats.exchanges.GetRestaurantsRequest;
import com.crio.qeats.exchanges.GetRestaurantsResponse;
import com.crio.qeats.repositoryservices.RestaurantRepositoryService;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RestaurantServiceImpl implements RestaurantService {
  private final Double peakHoursServingRadiusInKms = 3.0;
  private final Double normalHoursServingRadiusInKms = 5.0;
  @Autowired
  private RestaurantRepositoryService restaurantRepositoryService;
  
  @Override
  public GetRestaurantsResponse findAllRestaurantsCloseBy(GetRestaurantsRequest getRestaurantsRequest, LocalTime currentTime) {
    LocalTime peakHourStartA = LocalTime.of(8, 0);
    LocalTime peakHourEndA = LocalTime.of(10, 0);

    LocalTime peakHourStartB = LocalTime.of(13, 0);
    LocalTime peakHourEndB = LocalTime.of(14, 0);

    LocalTime peakHourStartC = LocalTime.of(19, 0);
    LocalTime peakHourEndC = LocalTime.of(21, 0);

    double servingRadius = normalHoursServingRadiusInKms;
    if((!currentTime.isBefore(peakHourStartA) && !currentTime.isAfter(peakHourEndA)) ||
       (!currentTime.isBefore(peakHourStartB) && !currentTime.isAfter(peakHourEndB)) ||
       (!currentTime.isBefore(peakHourStartC) && !currentTime.isAfter(peakHourEndC)) ){
        servingRadius = peakHoursServingRadiusInKms;
    }


    GetRestaurantsResponse getRestaurantsResponse = new GetRestaurantsResponse();
    List<Restaurant> restaurantList = restaurantRepositoryService.findAllRestaurantsCloseBy(getRestaurantsRequest.getLatitude(), getRestaurantsRequest.getLongitude(), currentTime, servingRadius);
    
    getRestaurantsResponse.setRestaurants(restaurantList);
    return getRestaurantsResponse;
  }


}

