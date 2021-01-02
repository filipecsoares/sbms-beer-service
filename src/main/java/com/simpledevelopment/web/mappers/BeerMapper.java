package com.simpledevelopment.web.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import com.simpledevelopment.domain.Beer;
import com.simpledevelopment.web.model.BeerDto;

@Mapper(uses = { DateMapper.class })
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

	public BeerDto beerToBeerDto(Beer beer);

	public BeerDto beerToBeerDtoWithInventory(Beer beer);

	public Beer beerDtoToBeer(BeerDto beerDto);
}
