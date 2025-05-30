package mx.unam.aragon.ico.te.DeportesMVC.servicios;


import mx.unam.aragon.ico.te.DeportesMVC.modelos.Equipo;
import mx.unam.aragon.ico.te.DeportesMVC.repositorios.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipoService {
    @Autowired
    private EquipoRepository equipoRepository;
    public boolean guardarEquipo(Equipo equipo) {

         Equipo resultado = equipoRepository.save(equipo);
         return resultado != null;
    }

    public Equipo getEquipo(Integer id) {
        return equipoRepository.getById(id);
    }

    public boolean eliminarEquipo(Integer id) {
        if (equipoRepository.existsById(id)) {
            equipoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean actualizarEquipo(Equipo equipo) {
        if (equipoRepository.existsById(equipo.getId())) {
            equipoRepository.save(equipo);  // JPA actualiza si el ID existe
            return true;
        }
        return false;
    }
}
