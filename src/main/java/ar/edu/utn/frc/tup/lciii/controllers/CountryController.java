package ar.edu.utn.frc.tup.lciii.controllers;
import ar.edu.utn.frc.tup.lciii.dtos.common.DtoResponse;
import ar.edu.utn.frc.tup.lciii.model.Country;
import ar.edu.utn.frc.tup.lciii.service.CountryService;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping ("api")
public class CountryController {

    @Autowired
    private CountryService countryService;
    @GetMapping("/countries")
    public ResponseEntity<?> getAllCountries(@RequestParam(required = false) String name , @RequestParam(required = false) String code){
        List<DtoResponse> lst= new ArrayList<>();
        if(Objects.equals(name, null) && Objects.equals(code, null)){
           lst= this.countryService.getAllMapped();
        }
        else {
            lst = this.countryService.getByNameOfCOde(name,code);
        }
        if(lst.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else{
          return ResponseEntity.ok(lst);
        }
    }
    @GetMapping("/countries/{continent}/continent")
    public ResponseEntity<?> getByContinent(@PathVariable String continent){
        List<DtoResponse> lst = this.countryService.getByContinent(continent);
        if(lst.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(lst);
        }
    }
    @GetMapping("/countries/{language}/languague")
    public ResponseEntity<?> getByLenguague(@PathVariable String language){
        List<DtoResponse> lst = this.countryService.getByLenguague(language);
        if(lst.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(lst);
        }
    }
    @GetMapping("/countries/most-borders")
    public ResponseEntity<?> getMasFronteras(){
        List<DtoResponse> lst = this.countryService.getMasFronteras();
        if(lst.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.ok(lst);
        }
    }

}