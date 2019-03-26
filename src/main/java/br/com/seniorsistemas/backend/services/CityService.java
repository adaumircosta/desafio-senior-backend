package br.com.seniorsistemas.backend.services;

import br.com.seniorsistemas.backend.domains.City;
import br.com.seniorsistemas.backend.dtos.StateAndQuantity;
import br.com.seniorsistemas.backend.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService extends GenericService<City, Long>{

    private final CityRepository repository;

    @Autowired
    public CityService(CityRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<String> getCityIsCapital() {
        return repository.findCitiesByCapitalTrueOrderByName().stream().map(City::getName).collect(Collectors.toList());
    }

    public List<StateAndQuantity> getMaxMinState() {
        List<Object[]> objects = repository.getMaxAndMinState();
        return objects.stream().map(o -> new StateAndQuantity(o[0].toString(), Integer.parseInt(o[1].toString()))).collect(Collectors.toList());
    }

    public List<StateAndQuantity> getCitysForState() {
        List<Object[]> objects = repository.getCitysForState();
        return objects.stream().map(o -> new StateAndQuantity(o[0].toString(), Integer.parseInt(o[1].toString()))).collect(Collectors.toList());
    }

    public List<String> getCityByState(String uf) {
        return repository.findCitiesByUf(uf).stream().map(City::getName).collect(Collectors.toList());
    }

    public Long getCountAll() {
        return repository.countAll();
    }


}
