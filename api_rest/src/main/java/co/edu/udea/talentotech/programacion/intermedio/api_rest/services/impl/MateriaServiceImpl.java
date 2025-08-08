/*package co.edu.udea.talentotech.programacion.intermedio.api_rest.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.MateriaDTO;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.Materia;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.repositories.MateriaRepository;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.services.MateriaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MateriaServiceImpl implements MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Override
    public List<MateriaDTO> findAll() {
        return materiaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MateriaDTO> findById(Short codigo) {
        return materiaRepository.findById(codigo)
                .map(this::convertToDTO);
    }

    @Override
    public MateriaDTO save(MateriaDTO materiaDTO) {
        Materia materia = convertToEntity(materiaDTO);
        Materia savedMateria = materiaRepository.save(materia);
        return convertToDTO(savedMateria);
    }

    @Override
    public MateriaDTO update(Short codigo, MateriaDTO materiaDTO) {
        return materiaRepository.findById(codigo)
                .map(existingMateria -> {
                    existingMateria.setNombre(materiaDTO.getNombre());
                    existingMateria.setCredito(materiaDTO.getCredito());
                    return convertToDTO(materiaRepository.save(existingMateria));
                })
                .orElseThrow(() -> new RuntimeException("Materia no encontrada con código: " + codigo));
    }

    @Override
    public void deleteById(Short codigo) {
        materiaRepository.deleteById(codigo);
    }

    @Override
    public List<MateriaDTO> findByNombre(String nombre) {
        return materiaRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MateriaDTO> findByCredito(Short credito) {
        return materiaRepository.findByCredito(credito).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MateriaDTO> findMateriasByAlumno(Integer cedulaAlumno) {
        return materiaRepository.findMateriasByAlumno(cedulaAlumno).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MateriaDTO convertToDTO(Materia materia) {
        MateriaDTO dto = new MateriaDTO();
        dto.setCodigo(materia.getCodigo());
        dto.setNombre(materia.getNombre());
        dto.setCredito(materia.getCredito());
        return dto;
    }

    private Materia convertToEntity(MateriaDTO dto) {
        Materia materia = new Materia();
        if (dto.getCodigo() != null) {
            materia.setCodigo(dto.getCodigo());
        }
        materia.setNombre(dto.getNombre());
        materia.setCredito(dto.getCredito());
        return materia;
    }
    @Override
    public void enrollAlumno(Short codigoMateria, Integer cedulaAlumno) {
        materiaRepository.enrollAlumno(codigoMateria, cedulaAlumno);       
    }

    @Override
    public void unenrollAlumno(Short codigoMateria, Integer cedulaAlumno) {
        materiaRepository.unenrollAlumno(codigoMateria, cedulaAlumno);
    }
}
    */