package mx.unam.aragon.ico.te.DeportesMVC.controladores;
import mx.unam.aragon.ico.te.DeportesMVC.servicios.EquipoService;
import mx.unam.aragon.ico.te.DeportesMVC.modelos.Equipo;
import mx.unam.aragon.ico.te.DeportesMVC.servicios.EquipoService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import java.util.List;

@Controller
@RequestMapping("/tienda")
public class DeporteController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping("/home/")
    public String home() {
        return "home";
    }

    @GetMapping("/buscar")
    public String buscarEquipo(Model model) {
        model.addAttribute("equipo", new Equipo());
        return "buscarEquipo";
    }

    @GetMapping("/equipo/")
    public String equipo(Model model) {
        Equipo equipo = new Equipo(0, "Club Deportivo Guadalajara", "Rebaño Sagrado", "Akron", 12,
                "https://i.pinimg.com/1200x/51/69/20/5169201c1d7c1aa468eaa4ba79cc72a3.jpg");
        model.addAttribute("equipo", equipo);
        return "equipo";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("equipo", new Equipo(1, "Club America", "Aguilas", "Azteca", 15,
                "https://i.etsystatic.com/32229139/r/il/b5d5ff/4004851799/il_fullxfull.4004851799_f3zq.jpg"));
        return "formEquipo";
    }

    @PostMapping("/guardar") // Corregido
    public String guardar(@ModelAttribute Equipo equipo) {
        LoggerFactory.getLogger(getClass()).info("Guardando equipo: " + equipo);
        equipoService.guardarEquipo(equipo);
        return "redirect:/tienda/nuevo?exito";
    }

    @GetMapping("/equipo/{id}")
    public String equipo(Model model, @PathVariable Integer id) {
        model.addAttribute("equipo", equipoService.getEquipo(id));
        return "equipo";
    }

    @GetMapping("/eliminar")
    public String mostrarFormularioEliminar() {
        return "eliminarEquipo";
    }

    @PostMapping("/eliminar")
    public String procesarEliminacion(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
        boolean eliminado = equipoService.eliminarEquipo(id);
        if (eliminado) {
            redirectAttributes.addFlashAttribute("mensajeExito", "Equipo con ID " + id + " eliminado exitosamente");
        } else {
            redirectAttributes.addFlashAttribute("mensajeError", "No se encontró el equipo con ID: " + id);
        }
        return "redirect:/tienda/eliminar";
    }

    // Mostrar formulario vacío para actualización
    @GetMapping("/seleccion")
        public String mostrarFormularioVacio(Model model) {
            model.addAttribute("equipo", new Equipo());
            return "seleccionActualizar";
    }

    // Mostrar formulario de actualización con equipo específico
    @GetMapping("/actualizar/{id}")
    public String mostrarFormularioActualizar(@PathVariable Integer id, Model model) {
        Equipo equipo = equipoService.getEquipo(id);
        if (equipo == null) {
            return "redirect:/tienda/actualizar?error=not_found";
        }
        model.addAttribute("equipo", equipo);
        return "actualizarEquipo";
    }


    // Procesar la actualización
    @PostMapping("/actualizar")
    public String procesarActualizacion(@ModelAttribute Equipo equipo, RedirectAttributes redirectAttributes) {
        boolean exito = equipoService.actualizarEquipo(equipo);
        if (exito) {
            redirectAttributes.addFlashAttribute("mensajeExito", "Equipo con ID " + equipo.getId() + " actualizado correctamente");
        } else {
            redirectAttributes.addFlashAttribute("mensajeError", "Error al actualizar el equipo");
        }
        return "redirect:/tienda/actualizar/" + equipo.getId();
    }

    @GetMapping("/listar")
    public String listarEquipos(Model model) {
        List<Equipo> equipos = equipoService.getTodosLosEquipos();
        model.addAttribute("equipos", equipos);
        return "listaEquipos";
    }

}