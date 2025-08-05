/*package co.edu.udea.talentotech.programacion.intermedio.api_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.talentotech.programacion.intermedio.api_rest.entities.Materia;

import java.util.List;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Short> {
    
    List<Materia> findByNombreContainingIgnoreCase(String nombre);
    
    List<Materia> findByCredito(Short credito);
    
    @Query("SELECT m FROM Materia m JOIN m.alumnos a WHERE a.cedula = :cedulaAlumno")
    List<Materia> findMateriasByAlumno(@Param("cedulaAlumno") Integer cedulaAlumno);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO alumno_materia (codigo_materia, cc_alumno) VALUES (:codigoMateria, :cedulaAlumno)", nativeQuery = true)
    void enrollAlumno(@Param("codigoMateria") Short codigoMateria, @Param("cedulaAlumno") Integer cedulaAlumno);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM alumno_materia WHERE codigo_materia = :codigoMateria AND cc_alumno = :cedulaAlumno", nativeQuery = true)
    void unenrollAlumno(@Param("codigoMateria") Short codigoMateria, @Param("cedulaAlumno") Integer cedulaAlumno);
}
*/