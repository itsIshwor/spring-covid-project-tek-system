package com.hackerrank.api.service;

import com.hackerrank.api.model.Covid;
import java.util.List;

public interface CovidService {

  List<Covid> getAllCovid();

  Covid createNewCovid(Covid covid);

  Covid getCovidById(Long id);

  List<Covid> top5By(String by);

  Integer totalBy(String by);
}
