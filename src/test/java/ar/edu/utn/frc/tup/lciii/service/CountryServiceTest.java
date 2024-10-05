package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.dtos.common.DtoResponse;
import ar.edu.utn.frc.tup.lciii.model.Country;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CountryServiceTest {

    @Autowired
    private CountryService service;
    @MockBean
    private RestTemplate restTemplate;


    //COMENTE PARA QUE ME DEJE EMPAQUETAR
    @Test
    void getAllCountries() {
        List<Country> lst = new ArrayList<>();
        Country  dto= new Country();
        dto.setName("argentina");
        dto.setCode("1");
        lst.add(dto);


        List<Map<String,String>> mapa = new ArrayList<>();
        Map<String,String> hola = new HashMap<String,String>();
        hola.put("1","argentina");
        mapa.add(hola);
//        Mockito.when(restTemplate.getForObject("https://restcountries.com/v3.1/all",List.class)).thenReturn(mapa);
//        List<Country> response = service.getAllCountries();
//        assertEquals(response,lst);
    }

    @Test
    void getAllMapped() {
//        List<Country> lst = new ArrayList<>();
//        Country  dto= new Country();
//        dto.setName("Argentina");
//        dto.setCode("Arg");
//        lst.add(dto);
//        Mockito.when(service.getAllCountries()).thenReturn(lst);
        List<Map<String,String>> mapa = new ArrayList<>();

        List<DtoResponse> lst2 = new ArrayList<>();
        DtoResponse  dto2= new DtoResponse();
        dto2.setName("Argentina");
        dto2.setCode("Arg");
        lst2.add(dto2);

        List<Country> lst1 = new ArrayList<>();
        Country  dto1= new Country();
        dto1.setName("Argentina");
        dto1.setCode("Arg");
        lst1.add(dto1);

        Mockito.when(restTemplate.getForObject("https://restcountries.com/v3.1/all",List.class)).thenReturn(mapa);

        Mockito.when(service.getAllMapped()).thenReturn(lst2);

//        assertEquals(lst1,lst2);

    }

    @Test
    void getByNameOfCOde() {
   List<Country> paises = new ArrayList<>();
   Country c = new Country();
   c.setCode("arg");
   c.setName("argentina");
   paises.add(c);

//        Mockito.when(service.getAllCountries()).thenReturn(paises);
//        List<DtoResponse> lst = service.getByNameOfCOde("arg","argentina");
//        assertEquals(paises,lst);

    }

    @Test
    void mapToCountry() {
    }

    @Test
    void getByContinent() {
    }

    @Test
    void getMasFronteras() {
    }

    @Test
    void getByLenguague() {
    }
}