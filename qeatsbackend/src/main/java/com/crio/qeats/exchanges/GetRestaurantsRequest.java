/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.exchanges;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: CRIO_TASK_MODULE_RESTAURANTSAPI
//  Implement GetRestaurantsRequest.
//  Complete the class such that it is able to deserialize the incoming query params from
//  REST API clients.
//  For instance, if a REST client calls API
//  /qeats/v1/restaurants?latitude=28.4900591&longitude=77.536386&searchFor=tamil,
//  this class should be able to deserialize lat/long and optional searchFor from that.
@Data
public class GetRestaurantsRequest {

    @NotNull(message = "Latitude cannot be null")
    @Min(value = -90, message = "Minimum Latitude must be -90")
    @Max(value = 90, message = "Maximum Latitude must be 90")
    private Double latitude;


    @NotNull(message = "Longitude cannot be null")
    @Min(value = -180, message = "Minimum Longitude must be -180")
    @Max(value = 180, message = "Maximum Longitude must be 180")
    private Double longitude;
    // private String searchFor;

    public GetRestaurantsRequest(Double latitude, Double longitude){
        //Add validations for latitude and longitude
        this.latitude = latitude;
        this.longitude = longitude;
    }

}

