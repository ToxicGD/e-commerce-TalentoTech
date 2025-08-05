package co.edu.udea.talentotech.programacion.intermedio.api_rest.services;

import java.util.List;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.ProductDTO;
//import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.MateriaDTO;

public interface ProductService {
    List<ProductDTO> findAll();
    ProductDTO save(ProductDTO productDTO);
    ProductDTO update(Integer id, ProductDTO productDTO);
    void delete(Integer id);
    //void enrollMateria(Integer cedulaAlumno, Short codigoMateria);
    //void unenrollMateria(Integer cedulaAlumno, Short codigoMateria);
    //List<MateriaDTO> findMateriasByAlumno(Integer cedulaAlumno);
}
