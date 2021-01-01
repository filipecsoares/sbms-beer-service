package com.simpledevelopment.web.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import com.simpledevelopment.domain.Beer;
import com.simpledevelopment.web.model.BeerDto;

@Mapper(uses = { DateMapper.class })
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

	BeerDto beerToBeerDto(Beer beer);

	Beer beerDtoToBeer(BeerDto beerDto);
}
