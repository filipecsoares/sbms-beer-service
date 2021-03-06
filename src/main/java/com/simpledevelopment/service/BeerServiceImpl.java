package com.simpledevelopment.service;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.simpledevelopment.domain.Beer;
import com.simpledevelopment.repository.BeerRepository;
import com.simpledevelopment.web.controller.NotFoundException;
import com.simpledevelopment.web.mappers.BeerMapper;
import com.simpledevelopment.web.model.BeerDto;
import com.simpledevelopment.web.model.BeerPagedList;
import com.simpledevelopment.web.model.BeerStyleEnum;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {
	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper;

	@Override
	public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest,
			Boolean showInventoryOnHand) {

		BeerPagedList beerPagedList;
		Page<Beer> beerPage;

		if (StringUtils.hasText(beerName) && !ObjectUtils.isEmpty(beerStyle)) {
			// search both
			beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
		} else if (StringUtils.hasText(beerName) && ObjectUtils.isEmpty(beerStyle)) {
			// search beer_service name
			beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
		} else if (!StringUtils.hasText(beerName) && !ObjectUtils.isEmpty(beerStyle)) {
			// search beer_service style
			beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
		} else {
			beerPage = beerRepository.findAll(pageRequest);
		}

		if (showInventoryOnHand) {
			beerPagedList = new BeerPagedList(
					beerPage.getContent().stream().map(beerMapper::beerToBeerDtoWithInventory)
							.collect(Collectors.toList()),
					PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
					beerPage.getTotalElements());
		} else {
			beerPagedList = new BeerPagedList(
					beerPage.getContent().stream().map(beerMapper::beerToBeerDto).collect(Collectors.toList()),
					PageRequest.of(beerPage.getPageable().getPageNumber(), beerPage.getPageable().getPageSize()),
					beerPage.getTotalElements());
		}

		return beerPagedList;
	}

	@Override
	public BeerDto getById(UUID beerId, Boolean showInventoryOnHand) {
		if (showInventoryOnHand) {
			return beerMapper
					.beerToBeerDtoWithInventory(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
		} else {
			return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
		}
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {
		return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
	}

	@Override
	public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
		Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

		beer.setBeerName(beerDto.getBeerName());
		beer.setBeerStyle(beerDto.getBeerStyle().name());
		beer.setPrice(beerDto.getPrice());
		beer.setUpc(beerDto.getUpc());

		return beerMapper.beerToBeerDto(beerRepository.save(beer));
	}
}