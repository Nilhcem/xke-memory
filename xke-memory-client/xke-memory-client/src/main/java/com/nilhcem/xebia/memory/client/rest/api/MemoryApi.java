package com.nilhcem.xebia.memory.client.rest.api;

import com.nilhcem.xebia.memory.client.model.PlayResponse;
import retrofit.http.Body;
import retrofit.http.POST;

public interface MemoryApi {

    @POST("/play")
    PlayResponse play(@Body int[][] cardsPositions);
}
