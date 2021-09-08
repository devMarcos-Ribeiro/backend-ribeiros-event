package com.marcosgrs.ribeirosevents.domain.repository;

import com.marcosgrs.ribeirosevents.domain.entity.Address;
import com.marcosgrs.ribeirosevents.domain.entity.Event;
import com.marcosgrs.ribeirosevents.domain.model.EventStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, String> {

}
