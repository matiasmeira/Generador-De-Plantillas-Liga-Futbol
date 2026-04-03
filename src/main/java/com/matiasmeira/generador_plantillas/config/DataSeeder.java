package com.matiasmeira.generador_plantillas.config;

import java.time.LocalDate;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.matiasmeira.generador_plantillas.dto.EquipoDTO;
import com.matiasmeira.generador_plantillas.dto.JugadorDTO;
import com.matiasmeira.generador_plantillas.dto.UsuarioDTO;
import com.matiasmeira.generador_plantillas.service.EquipoService;
import com.matiasmeira.generador_plantillas.service.JugadorService;
import com.matiasmeira.generador_plantillas.service.UsuarioService;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UsuarioService usuarioService;
    private final EquipoService equipoService;
    private final JugadorService jugadorService;
    private final Random random = new Random();

    // Listas para armar nombres realistas de forma aleatoria
    private final String[] nombres = {"Lionel", "Emiliano", "Ángel", "Julián", "Franco", "Enzo", "Lautaro", "Nicolás", "Rodrigo", "Cristian", "Leandro", "Alexis", "Paulo", "Lisandro", "Alejandro", "Walter", "Nahuel", "Guido", "Exequiel", "Juan", "Marcos", "Joaquín", "Facundo", "Thiago", "Matías", "Tomás", "Lucas", "Martín", "Agustín", "Federico", "Gonzalo", "Diego"};
    private final String[] apellidos = {"Martínez", "Messi", "Di María", "Álvarez", "Armani", "Fernández", "Otamendi", "De Paul", "Romero", "Paredes", "Mac Allister", "Dybala", "Lo Celso", "Garnacho", "González", "Molina", "Rodríguez", "Palacios", "Correa", "Acuña", "Pezzella", "Rulli", "Foyth", "Montiel", "Almada", "Gómez", "López", "Pérez", "Sánchez", "García"};

    public DataSeeder(UsuarioService usuarioService, EquipoService equipoService, JugadorService jugadorService) {
        this.usuarioService = usuarioService;
        this.equipoService = equipoService;
        this.jugadorService = jugadorService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Verificamos si ya existen usuarios para no duplicar datos cada vez que arranca la app
        if (!usuarioService.obtenerTodos().isEmpty()) {
            return;
        }

        System.out.println("Cargando base de datos con 5 equipos y 15 jugadores por equipo...");

        // 1. Crear el administrador
        UsuarioDTO.Entrada adminEntrada = new UsuarioDTO.Entrada("test", "test123", "ADMIN");
        UsuarioDTO.Salida admin = usuarioService.guardar(adminEntrada);

        // 2. Nombres de nuestros 5 equipos
        String[] nombresEquipos = {
            "Los Leones FC", 
            "Real Bañil", 
            "Atlético del Sur", 
            "Deportivo Norte", 
            "Estrella Roja"
        };

        // 3. Crear cada equipo con 15 jugadores (El límite de tu API es 22)
        for (String nombreEquipo : nombresEquipos) {
            crearEquipoConJugadoresAleatorios(nombreEquipo, admin.id(), 15);
        }

        System.out.println("¡Datos de prueba cargados exitosamente! (Total: 5 Equipos, 75 Jugadores)");
    }

    private void crearEquipoConJugadoresAleatorios(String nombreEquipo, Long usuarioId, int cantidadJugadores) {
        // Creamos el equipo
        EquipoDTO.Entrada equipoEntrada = new EquipoDTO.Entrada(nombreEquipo);
        EquipoDTO.Salida equipo = equipoService.guardar(equipoEntrada, usuarioId);

        // Generamos los jugadores solicitados para este equipo
        for (int i = 0; i < cantidadJugadores; i++) {
            String nombre = nombres[random.nextInt(nombres.length)];
            String apellido = apellidos[random.nextInt(apellidos.length)];
            
            // Generar un DNI aleatorio entre 30.000.000 y 45.000.000
            String dni = String.valueOf(30000000 + random.nextInt(15000000)); 
            
            // Generar fecha de nacimiento aleatoria entre 1985 y 2004
            int year = 1985 + random.nextInt(20); 
            int month = 1 + random.nextInt(12);
            int day = 1 + random.nextInt(28); // Hasta el día 28 para evitar problemas de meses cortos o bisiestos
            LocalDate fechaNac = LocalDate.of(year, month, day);

            // Guardar jugador
            JugadorDTO.Entrada jugadorFinal = new JugadorDTO.Entrada(
                nombre, 
                apellido, 
                fechaNac, 
                dni, 
                equipo.id()
            );
            jugadorService.guardar(jugadorFinal, equipo.id());
        }
    }
}