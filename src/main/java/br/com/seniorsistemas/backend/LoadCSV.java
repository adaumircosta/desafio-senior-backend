package br.com.seniorsistemas.backend;

import br.com.seniorsistemas.backend.domains.City;
import br.com.seniorsistemas.backend.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
public class LoadCSV {

    private final CityRepository repository;

    @Autowired
    public LoadCSV(CityRepository repository) {
        this.repository = repository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {

        String file = "./file.csv";
        BufferedReader bufferedReader = null;
        String line = "";
        String divisor = ",";

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(divisor);

                City city = new City();
                city.setIbge_id(Long.parseLong(values[0]));
                city.setUf(values[1]);
                city.setName(values[2]);
                city.setCapital(Boolean.parseBoolean(values[3]));
                city.setLon(values[4]);
                city.setLat(values[5]);
                city.setNo_accents(values[6]);
                city.setAlternative_names(values[7]);
                city.setMicroregion(values[8]);
                city.setMesoregion(values[9]);

                repository.save(city);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
