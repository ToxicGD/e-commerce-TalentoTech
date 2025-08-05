package co.edu.udea.talentotech.programacion.intermedio.api_rest.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.ProductDTO;
//import co.edu.udea.talentotech.programacion.intermedio.api_rest.dto.MateriaDTO;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.Product;
//import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.Materia;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.repositories.ProductRepository;
//import co.edu.udea.talentotech.programacion.intermedio.api_rest.repositories.MateriaRepository;
import co.edu.udea.talentotech.programacion.intermedio.api_rest.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    //@Autowired
    //private MateriaRepository materiaRepository;

    @Transactional(readOnly = true)  //begin - commit en sql
    @Override
    public List<ProductDTO> findAll() {
        List<Product> all = (List<Product>) productRepository.findAll();
        return all.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedAlumno = productRepository.save(product);
        return convertToDTO(savedAlumno);
    }

    @Transactional
    @Override
    public ProductDTO update(Integer id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno not found with id: " + id));
        // Update fields
        if (productDTO.getProductId() != null) {
            product.setProductId(productDTO.getProductId());
        }
        if (productDTO.getName() != null) {
            product.setName(productDTO.getName());
        }
        if (productDTO.getDescription() != null) {
            product.setDescription(productDTO.getDescription());
        }
        if (productDTO.getPrice() != 0) {
            product.setPrice(productDTO.getPrice());
        }
        Product updatedProduct = productRepository.save(product);
        return convertToDTO(updatedProduct);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    /*@Transactional
    @Override
    public void enrollMateria(Integer cedulaAlumno, Short codigoMateria) {
        Alumno alumno = alumnoRepository.findById(cedulaAlumno)
                .orElseThrow(() -> new RuntimeException("Alumno not found with cedula: " + cedulaAlumno));
        Materia materia = materiaRepository.findById(codigoMateria)
                .orElseThrow(() -> new RuntimeException("Materia not found with codigo: " + codigoMateria));
        alumno.getMaterias().add(materia);
        alumnoRepository.save(alumno);
    }

    @Transactional
    @Override
    public void unenrollMateria(Integer cedulaAlumno, Short codigoMateria) {
        Alumno alumno = alumnoRepository.findById(cedulaAlumno)
                .orElseThrow(() -> new RuntimeException("Alumno not found with cedula: " + cedulaAlumno));
        Materia materia = materiaRepository.findById(codigoMateria)
                .orElseThrow(() -> new RuntimeException("Materia not found with codigo: " + codigoMateria));
        alumno.getMaterias().remove(materia);
        alumnoRepository.save(alumno);
    }

    @Transactional(readOnly = true)
    @Override
    public List<MateriaDTO> findMateriasByAlumno(Integer cedulaAlumno) {
        Alumno alumno = alumnoRepository.findById(cedulaAlumno)
                .orElseThrow(() -> new RuntimeException("Alumno not found with cedula: " + cedulaAlumno));

        return alumno.getMaterias().stream()
                .map(materia -> new MateriaDTO(materia))
                .collect(Collectors.toList());
    }*/

    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(product);
    }

    private Product convertToEntity(ProductDTO productDTO) {
        Product product =  new Product();
        product.setProductId(productDTO.getProductId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        return product;
    }
}
