package com.simpledevelopment.web.mappers;

import org.springframework.beans.factory.annotation.Autowired;

import com.simpledevelopment.domain.Beer;
import com.simpledevelopment.service.inventory.BeerInventoryService;
import com.simpledevelopment.web.model.BeerDto;

public abstract class BeerMapperDecorator implements BeerMapper {
	private BeerInventoryService beerInventoryService;
	private BeerMapper mapper;

	@Autowired
	public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
		this.beerInventoryService = beerInventoryService;
	}

	@Autowired
	public void setMapper(BeerMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public BeerDto beerToBeerDto(Beer beer) {
		BeerDto dto = mapper.beerToBeerDto(beer);
		dto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
		return dto;
	}

	@Override
	public Beer beerDtoToBeer(BeerDto beerDto) {
		return mapper.beerDtoToBeer(beerDto);
	}
}