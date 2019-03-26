package br.com.seniorsistemas.backend.controllers;

import br.com.seniorsistemas.backend.domains.City;
import br.com.seniorsistemas.backend.dtos.StateAndQuantity;
import br.com.seniorsistemas.backend.services.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "City Controller", description = "IBGE end points")
@RequestMapping(value = "/city")
@RestController
public class CityController {

    private final CityService service;

    @Autowired
    public CityController(CityService service) {
        this.service= service;
    }

    @GetMapping(value = "/capitals")
    @ApiOperation(value = "Buscar cidades que sao capitais ordenadas por nome")
    private List<String> getCapitalCitys() {
        return this.service.getCityIsCapital();
    }

    @GetMapping(value = "/limits/state")
    @ApiOperation(value = "Buscar estados com maior e menor quantidade de cidades")
    private List<StateAndQuantity> getStateMaxAndMin() {
        return this.service.getMaxMinState();
    }

    @GetMapping(value = "/count/state")
    @ApiOperation(value = "Quantidade de cidades por estado")
    private List<StateAndQuantity> getCitysForState() {
        return this.service.getCitysForState();
    }

    @GetMapping(value = "/{ibge}")
    @ApiOperation(value = "Retornando cidade por IBGE")
    private City getForIbge(
            @PathVariable Long ibge
    ) {
        return this.service.getById(ibge);
    }

    @GetMapping(value = "/state/{state}")
    @ApiOperation(value = "Retornando cidades com base no estado")
    private List<String> getForIbge(
            @PathVariable String state
    ) {
        return this.service.getCityByState(state);
    }

    @PostMapping
    @ApiOperation(value = "Adicionar City")
    private City createCity(
            @RequestBody City city
    ) {
        return this.service.create(city);
    }

    @DeleteMapping(value = "{ibge}")
    @ApiOperation(value = "Remover City")
    private void deleteCity(
            @PathVariable Long ibge
    ) {
        this.service.remove(ibge);
    }

    @GetMapping(value = "/count")
    @ApiOperation(value = "Contar todos")
    private Long getBYColumn() {
        return this.service.getCountAll();
    }

}
