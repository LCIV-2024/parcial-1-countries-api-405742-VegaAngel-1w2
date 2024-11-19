package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.common.DtoResponse;
import ar.edu.utn.frc.tup.lciii.entities.CountryEntity;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {



        @Autowired
        private final RestTemplate restTemplate;
        @Autowired
        private final CountryRepository repository;

        public List<Country> getAllCountries() {
                String url = "https://restcountries.com/v3.1/all";
                List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);
                List<Country> lst =  response.stream().map(this::mapToCountry).collect(Collectors.toList());
                return response.stream().map(this::mapToCountry).collect(Collectors.toList());

        }
        public List<DtoResponse> getAllMapped(){
                String url = "https://restcountries.com/v3.1/all";
                List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);
                List<Country> lst =  response.stream().map(this::mapToCountry).collect(Collectors.toList());
                //return response.stream().map(this::mapToCountry).collect(Collectors.toList());
                List<DtoResponse> respuesta = new ArrayList<>();
                for (Country c : lst){
                        DtoResponse dto = new DtoResponse(c.getCode(),c.getName());
                        respuesta.add(dto);
                }
                return respuesta;
        }
        public List<Country> getAllNoMapped(){
//                String url = "https://restcountries.com/v3.1/all";
//                List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);
//                List<Country> lst =  response.stream().map(this::mapToCountry).collect(Collectors.toList());
                //return response.stream().map(this::mapToCountry).collect(Collectors.toList());
                List<Country> respuesta ;
                respuesta = this.getAllCountries();
                return respuesta;
        }
        public List<DtoResponse> getByNameOfCOde(String name,String code){
                List<Country> countries = this.getAllCountries();
                List<Country> filtrados = new ArrayList<>();

                for (Country c:countries){
                        if(c.getName().equals(name) ){
                                filtrados.add(c);
                        }
                        if(c.getCode().equals(code)){
                                filtrados.add(c);
                        }
                }

                List<DtoResponse> respuesta = new ArrayList<>();
                for (Country c : filtrados){
                        DtoResponse dto = new DtoResponse(c.getCode(),c.getName());
                        respuesta.add(dto);
                }
                return respuesta;
        }

        /**
         * Agregar mapeo de campo cca3 (String)
         * Agregar mapeo campos borders ((List<String>))
         */
        public Country mapToCountry(Map<String, Object> countryData) {
                Map<String, Object> nameData = (Map<String, Object>) countryData.get("name");
                return Country.builder()
                        .name((String) nameData.get("common"))
                        .population(((Number) countryData.get("population")).longValue())
                        .area(((Number) countryData.get("area")).doubleValue())
                        .region((String) countryData.get("region"))
                        .code((String) countryData.get("cca3"))
                        .borders((List<String>) countryData.get("borders"))
                        .languages((Map<String, String>) countryData.get("languages"))
                        .build();
        }
        public List<DtoResponse> getByContinent(String name){
                List<Country> countries = this.getAllCountries();
                List<Country> filtrados = new ArrayList<>();

                for (Country c:countries){
                        if(Objects.equals(c.getRegion(), name)){
                                filtrados.add(c);
                        }
                }
                List<DtoResponse> respuesta = new ArrayList<>();
                for (Country c : filtrados){
                        DtoResponse dto = new DtoResponse(c.getCode(),c.getName());
                        respuesta.add(dto);
                }
                return respuesta;
        }
        public DtoResponse getMasFronteras(){
                Country masFronteras = new Country();
                List<Country> paises = this.getAllCountries();
                for (Country c : paises){
                        if(c.getBorders() != null) {
                        if(Objects.equals(masFronteras.getName(),null)){
                                masFronteras = c;
                        }

                                if (c.getBorders().size() > masFronteras.getBorders().size()) {
                                        masFronteras = c;
                                }
                        }
                }
                DtoResponse respose = new DtoResponse(masFronteras.getCode(),masFronteras.getName());
                return respose;
        }
        public List<DtoResponse> getByLenguague(String idioma){
                List<Country> countries = this.getAllCountries();
                List<Country> filtrados = new ArrayList<>();

                for (Country c:countries){
                        if(c.getLanguages() != null){
                      for (Map.Entry<String,String> lenguaje :c.getLanguages().entrySet()){
                                        if(Objects.equals(lenguaje.getValue(), idioma)){
                                                filtrados.add(c);
                                        }
                      }}
                }
                List<DtoResponse> respuesta = new ArrayList<>();
                for (Country c : filtrados){
                        DtoResponse dto = new DtoResponse(c.getCode(),c.getName());
                        respuesta.add(dto);
                }
                return respuesta;
        }
        public List<DtoResponse> postCountries(int number){
                Random random = new Random();
                List<Country> paises = this.getAllCountries();
               // random.nextInt(paises.size());
                List<Country> paisesFiltrado = new ArrayList<>();
                for (int i = 0; i < number; i++) {
                      Integer r =  random.nextInt(paises.size());
                        paisesFiltrado.add(paises.get(r));
                }
                for(Country c:paisesFiltrado){
                        CountryEntity ce = new CountryEntity();
                        ce.setArea(c.getArea());
                        ce.setName(c.getName());
                        ce.setRegion(c.getRegion());
                        ce.setCode(c.getCode());
                        ce.setPopulation(c.getPopulation());
                        repository.save(ce);
                }



                List<CountryEntity> respuesta = repository.findAll();
                List<DtoResponse> response = new ArrayList<>();
                for(CountryEntity c : respuesta){
                        DtoResponse dto = new DtoResponse(c.getCode(),c.getName());
                        response.add(dto);
                }
                return response;
        }


       // private CountryDTO mapToDTO(Country country) {
           //     return new CountryDTO(country.getCode(), country.getName());
      //  }
}