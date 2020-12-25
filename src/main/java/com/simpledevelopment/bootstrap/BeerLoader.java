package com.simpledevelopment.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.simpledevelopment.domain.Beer;
import com.simpledevelopment.repository.BeerRepository;

@Component
public class BeerLoader implements CommandLineRunner {

	private final BeerRepository beerRepository;

	public BeerLoader(BeerRepository beerRepository) {
		super();
		this.beerRepository = beerRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		loadBeerObjects();
	}

	private void loadBeerObjects() {
		if (beerRepository.count() == 0) {
			beerRepository.save(Beer.builder().beerName("Mango Bob").beerStyle("IPA").quantityToBrew(200).minOnHand(12)
					.upc(337010000001L).price(new BigDecimal(12.95)).build());

			beerRepository.save(Beer.builder().beerName("Galaxi Cat").beerStyle("PALE_ALE").quantityToBrew(200)
					.minOnHand(12).upc(337010000002L).price(new BigDecimal(11.95)).build());
		}
	}
}
