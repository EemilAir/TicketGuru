package bugivelhot.ticketguru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bugivelhot.ticketguru.entity.Kaupunki;

@Repository
public interface KaupunkiRepository extends JpaRepository<Kaupunki, Integer> {}
