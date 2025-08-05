/*package co.edu.udea.talentotech.programacion.intermedio.api_rest.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.MateriaDTO;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.services.MateriaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/materias")
@CrossOrigin(origins = "*")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<MateriaDTO>> getAllMaterias() {
        List<MateriaDTO> materias = materiaService.findAll();
        return ResponseEntity.ok(materias);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<MateriaDTO> getMateriaById(@PathVariable Short codigo) {
        Optional<MateriaDTO> materia = materiaService.findById(codigo);
        return materia.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MateriaDTO> createMateria(@Valid @RequestBody MateriaDTO materiaDTO) {
        MateriaDTO savedMateria = materiaService.save(materiaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMateria);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<MateriaDTO> updateMateria(
            @PathVariable Short codigo, 
            @Valid @RequestBody MateriaDTO materiaDTO) {
        try {
            MateriaDTO updatedMateria = materiaService.update(codigo, materiaDTO);
            return ResponseEntity.ok(updatedMateria);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> deleteMateria(@PathVariable Short codigo) {
        materiaService.deleteById(codigo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<MateriaDTO>> getMateriasByNombre(@RequestParam String nombre) {
        List<MateriaDTO> materias = materiaService.findByNombre(nombre);
        return ResponseEntity.ok(materias);
    }

    @GetMapping("/buscar/credito")
    public ResponseEntity<List<MateriaDTO>> getMateriasByCredito(@RequestParam Short credito) {
        List<MateriaDTO> materias = materiaService.findByCredito(credito);
        return ResponseEntity.ok(materias);
    }

    @GetMapping("/alumno/{cedulaAlumno}")
    public ResponseEntity<List<MateriaDTO>> getMateriasByAlumno(@PathVariable Integer cedulaAlumno) {
        List<MateriaDTO> materias = materiaService.findMateriasByAlumno(cedulaAlumno);
        return ResponseEntity.ok(materias);
    }

    @PostMapping("/{codigoMateria}/matricular/{cedulaAlumno}")
    public ResponseEntity<String> enrollAlumno(
            @PathVariable Short codigoMateria, 
            @PathVariable Integer cedulaAlumno) {
        try {
            materiaService.enrollAlumno(codigoMateria, cedulaAlumno);
            return ResponseEntity.ok("Alumno matriculado exitosamente en la materia");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al matricular alumno: " + e.getMessage());
        }
    }

    @DeleteMapping("/{codigoMateria}/matricular/{cedulaAlumno}")
    public ResponseEntity<String> unenrollAlumno(
            @PathVariable Short codigoMateria, 
            @PathVariable Integer cedulaAlumno) {
        try {
            materiaService.unenrollAlumno(codigoMateria, cedulaAlumno);
            return ResponseEntity.ok("Alumno desmatriculado exitosamente de la materia");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al desmatricular alumno: " + e.getMessage());
        }
    }

}
*/