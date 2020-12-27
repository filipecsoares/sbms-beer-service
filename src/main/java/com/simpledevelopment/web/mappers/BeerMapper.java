package com.simpledevelopment.web.mappers;

import org.mapstruct.Mapper;

import com.simpledevelopment.domain.Beer;
import com.simpledevelopment.web.model.BeerDto;

@Mapper(uses = { DateMapper.class })
public interface BeerMapper {

	BeerDto beerToBeerDto(Beer beer);

	Beer beerDtoToBeer(BeerDto beerDto);
}
