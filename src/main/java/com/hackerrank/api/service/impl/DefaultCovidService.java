package com.hackerrank.api.service.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import com.hackerrank.api.util.Utils;
import com.hackerrank.api.exception.BadRequestException;
import com.hackerrank.api.exception.ElementNotFoundException;
import com.hackerrank.api.model.Covid;
import com.hackerrank.api.repository.CovidRepository;
import com.hackerrank.api.service.CovidService;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DefaultCovidService implements CovidService {
    private final CovidRepository covidRepository;
    private static final String INVALID_MESSAGE = "invalid field %s for aggregation";
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    DefaultCovidService(CovidRepository covidRepository) {
        this.covidRepository = covidRepository;
    }

    @Override
    public List<Covid> getAllCovid() {
        return covidRepository.findAll();
    }

    @Override
    public Covid createNewCovid(Covid covid) {
        if (covid.getId() != null) {
            throw new BadRequestException("The ID must not be provided when creating a new Covid");
        }
        return covidRepository.save(covid);
    }

    @Override
    public Covid getCovidById(Long id) {
        return covidRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Covid data with " + id + " Not Found"));
    }

    @Override
    public List<Covid> top5By(String by) {
        // System.out.println("top5::::" + covidRepository.findTop5(by));
        if (!Utils.isValidPreCheckBy(by)) {
            throw new BadRequestException(by + " is not valid");
        }
        Sort sort = Sort.by(Sort.Direction.DESC, by);
        Pageable page = PageRequest.of(0, 5, sort);
        return covidRepository.findAll(page).toList();
    }


    public Integer getSumByField(String field) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> query = cb.createQuery(Integer.class);
        Root<Covid> root = query.from(Covid.class);
        query.select(cb.sum(root.get(field)));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public Integer totalBy(String by) {
        if (!Utils.isValidPreCheckBy(by))
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), String.format(INVALID_MESSAGE, by));
        System.out.println();
        return this.getSumByField(by);
    }
}
