package com.dosideas.repository;

import com.dosideas.domain.Provincia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/** Este es una interfaz de acceso a datos, que utiliza Spring Data para
 * implementar el repositorio.
 * Contiene varios métodos heredados, que permiten buscar Paises por distintos
 * criterios, guardar un pais, borrarlo, etc.
 */
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

    List<Provincia> findByNombre(String nombre);

    List<Provincia> findByNombreContainingIgnoreCase(String palabraClave);
    
    @Query(value = "SELECT p.* FROM Provincia p " +
                   "JOIN Pais pa ON p.id_pais = pa.id " +
                   "WHERE pa.nombre = :nombrePais", nativeQuery = true)
    List<Provincia> findAllByNombrePais(@Param("nombrePais") String nombrePais);
    
}
