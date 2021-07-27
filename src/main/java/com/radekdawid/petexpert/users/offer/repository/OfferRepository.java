package com.radekdawid.petexpert.users.offer.repository;

import com.radekdawid.petexpert.users.offer.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long>, PagingAndSortingRepository<Offer, Long> {
}
