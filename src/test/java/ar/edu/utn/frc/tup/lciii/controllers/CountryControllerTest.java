package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.DtoResponse;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CountryController.class)
@AutoConfigureMockMvc
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CountryService service;
    @Test
    void getAllCountries() throws Exception{

        List<DtoResponse> lst = new ArrayList<>();
        DtoResponse  dto= new DtoResponse("ARG","argentina");
        lst.add(dto);
        Mockito.when(service.getAllMapped()).thenReturn(lst);
        //mockMvc.perform(get(("/countries").
//        mockMvc.perform(get("api/countries").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/countries").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void getByContinent() throws Exception{
        List<DtoResponse> lst = new ArrayList<>();
        DtoResponse  dto= new DtoResponse("ARG","argentina");
        lst.add(dto);
        Mockito.when(service.getByContinent("Americas")).thenReturn(lst);
        //mockMvc.perform(get(("/countries").
//        mockMvc.perform(get("api/countries").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/countries/{continent}/continent","Americas").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getByLenguague() throws Exception{
        List<DtoResponse> lst = new ArrayList<>();
        DtoResponse  dto= new DtoResponse("ARG","argentina");
        lst.add(dto);
        Mockito.when(service.getByLenguague("ENGLISH")).thenReturn(lst);
        //mockMvc.perform(get(("/countries").
//        mockMvc.perform(get("api/countries").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/countries/{language}/languague","ENGLISH").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getMasFronteras() throws Exception {
//        DtoResponse lst = new ArrayList<>();
//        DtoResponse  dto= new DtoResponse("ARG","argentina");
//       // lst.add(dto);
//        Mockito.when(service.getMasFronteras()).thenReturn(lst);
//        //mockMvc.perform(get(("/countries").
////        mockMvc.perform(get("api/countries").accept(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk())
////                .andDo(print());
//        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/countries/most-borders").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
    }}