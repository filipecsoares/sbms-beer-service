package com.simpledevelopment.service;

import java.util.UUID;

import com.simpledevelopment.web.model.BeerDto;

public interface BeerService {

	public BeerDto getById(UUID beerId);

	public BeerDto saveNewBeer(BeerDto beerDto);

	public BeerDto updateBeer(UUID beerId, BeerDto beerDto);
}
