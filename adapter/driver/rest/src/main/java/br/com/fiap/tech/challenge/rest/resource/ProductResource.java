package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.port.driver.QueryProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductResource {

    private QueryProductService queryProductService;

    @GetMapping
    public  now(){
        return LocalDateTime.now().toString();
    }

}
