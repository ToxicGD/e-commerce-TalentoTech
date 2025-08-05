package co.edu.udea.talentotech.programacion.intermedio.api_rest.controllers;

import org.springframework.web.bind.annotation.RestController;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.ProductDTO;
//import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.MateriaDTO;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.services.ProductService;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.services.impl.ProductServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> getProducts(@RequestParam(required = false) String param) {
        // Hacer validaciones de la peticion HTTP
        return productService.findAll();
    }

    /*@GetMapping("/{cedula}/materias")
    public ResponseEntity<List<MateriaDTO>> getMateriasByAlumno(@PathVariable Integer cedula) {
        return ResponseEntity.ok(alumnoService.findMateriasByAlumno(cedula));
    }*/

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.save(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    /*@PostMapping("/{cedula}/materias/{codigoMateria}")
    public ResponseEntity<Void> matricularMateria(@PathVariable Integer cedula, @PathVariable Short codigoMateria) {
        alumnoService.enrollMateria(cedula, codigoMateria);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }*/

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer productId, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.update(productId, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        productService.delete(productId);
        return ResponseEntity.noContent().build();
    }

    /*@DeleteMapping("/{cedula}/materias/{codigoMateria}")
    public ResponseEntity<Void> eliminarMateria(@PathVariable Integer cedula, @PathVariable Short codigoMateria) {
        alumnoService.unenrollMateria(cedula, codigoMateria);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }*/
}
