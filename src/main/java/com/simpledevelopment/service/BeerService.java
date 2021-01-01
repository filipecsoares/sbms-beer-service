package com.simpledevelopment.service;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;

import com.simpledevelopment.web.model.BeerDto;
import com.simpledevelopment.web.model.BeerPagedList;
import com.simpledevelopment.web.model.BeerStyleEnum;

public interface BeerService {

	public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest);

	public BeerDto getById(UUID beerId);

	public BeerDto saveNewBeer(BeerDto beerDto);

	public BeerDto updateBeer(UUID beerId, BeerDto beerDto);
}
