package principal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import control.Controlador;
import modelo.Credenciales;
import modelo.Ejemplar;
import modelo.Mensaje;
import modelo.Persona;
import modelo.Planta;

public class FachadaAdmin {
    private static FachadaAdmin portalAdmin;
    private FachadaAdmin() {
    }
    public static FachadaAdmin getPortalAdmin() {
        if (portalAdmin == null) {
            portalAdmin = new FachadaAdmin();
        }
        return portalAdmin;
    }
    private Controlador controlador = Controlador.getServicios();
    Scanner in = new Scanner(System.in);
    
    public void menuAdmin() {
        int opcion = 0;
        do {
        	System.out.println("------MENÚ DE ADMINISTRADOR------");
            System.out.println("Selecciona una opción:");
            System.out.println("1. Gestión de plantas.");
            System.out.println("2. Gestión de ejemplares.");
            System.out.println("3. Gestión de mensajes.");
            System.out.println("4. Gestión de personas.");
            System.out.println("5. CERRAR SESIÓN.");
            opcion = in.nextInt();
            if (opcion < 1 || opcion > 5) {
                System.out.println("Opción incorrecta.");
                continue;
            }
            switch (opcion) {
                case 1:
                    menuAdminPlantas();
                    break;
                case 2:
                    menuAdminEjemplares();
                    break;
                case 3:
                    menuAdminMensajes();
                    break;
                case 4:
                    menuAdminPersonas();
                    break;
            }
        } while (opcion != 5);
        Controlador.getServicios().cerrarSesion();
        FachadaInvitado.getPortalInvitado().menuInvitado();
    }
    public void menuAdminPlantas() {
        int opcion = 0;
        do {
            System.out.println("Selecciona una opción:");
            System.out.println("1. Ver plantas.");
            System.out.println("2. Crear nueva planta.");
            System.out.println("3. Modificar datos de una planta.");
            System.out.println("4. Volver al menú principal.");

            opcion = in.nextInt();
            if (opcion < 1 || opcion > 4) {
                System.out.println("Opción incorrecta.");
                continue;
            }
            switch (opcion) {
                case 1:
                    FachadaInvitado.getPortalInvitado().verTodasPlantas();
                    break;
                case 2:
                    nuevaPlanta();
                    break;
                case 3:
                    menuAdminModificarPlantas();
                    break;
            }
        } while (opcion != 4);
    }
    public void menuAdminModificarPlantas() {
    	int opcion = 0;
        do {
        System.out.println("Selecciona una opción:");
        System.out.println("1. Modificar nombre común.");
        System.out.println("2. Modificar nombre científico.");
        System.out.println("3. Volver al menú de plantas.");
        opcion = in.nextInt();
        if (opcion < 1 || opcion > 3) {
            System.out.println("Opción incorrecta.");
            continue;
        }
        switch (opcion) {
            case 1:
                modificarNombreComun();
                break;
            case 2:
                modificarNombreCientifico();
                break;
            
        }
    } while (opcion != 3);
    }

    public void menuAdminEjemplares() {
    	int opcion = 0;
    	do {
        System.out.println("Selecciona una opción:");
        System.out.println("1. Registrar nuevo ejemplar.");
        System.out.println("2. Filtrar ejemplares por tipo de planta.");
        System.out.println("3. Volver al menú principal.");
        opcion = in.nextInt();
        if (opcion < 1 || opcion > 3) {
            System.out.println("Opción incorrecta.");
            continue;
        }
        switch (opcion) {
            case 1:
                nuevoEjemplar();
                break;
            case 2:
                break;
            
        }
    } while (opcion != 3);
    }

    public void menuAdminPersonas() {
    	int opcion = 0;
    	do {
        System.out.println("Selecciona una opción:");
        System.out.println("1. Registrar nueva persona.");
        System.out.println("2. Ver todas las personas.");
        System.out.println("3. Volver al menú principal.");
        opcion = in.nextInt();
        if (opcion < 1 || opcion > 3) {
            System.out.println("Opción incorrecta.");
            continue;
        }
        switch (opcion) {
            case 1:
            	nuevaPersona();
                break;
            case 2:
                verTodasPersonas();
                break;
            
        }
    } while (opcion != 3);
    }

    public void menuAdminMensajes() {
    	int opcion = 0;
    	do {
        System.out.println("Selecciona una opción:");
        System.out.println("1. Nuevo mensaje.");
        System.out.println("2. Ver mensajes.");
        System.out.println("3. Volver al menú principal.");
        opcion = in.nextInt();
        if (opcion < 1 || opcion > 3) {
            System.out.println("Opción incorrecta.");
            continue;
        }
        switch (opcion) {
            case 1:
            	FachadaPersonal.getPortalPersonal().nuevoMensaje();
                break;
            case 2:
                menuAdminVerMensajes();
                break;
            
        }
    } while (opcion != 3);
    }
    public void menuAdminVerMensajes() {
    	int opcion = 0;
    	do {
        System.out.println("Selecciona una opción:");
        System.out.println("1. Ver todos los mensajes.");
        System.out.println("2. Ver mensajes por persona.");
        System.out.println("3. Ver mensajes por rango de fechas.");
        System.out.println("4. Ver mensajes por tipo de planta.");
        System.out.println("5. Volver al menú principal.");
        opcion = in.nextInt();
        if (opcion < 1 || opcion > 5) {
            System.out.println("Opción incorrecta.");
            continue;
        }
        switch (opcion) {
            case 1:
            	verTodosMensajes();
                break;
            case 2:
                FachadaPersonal.getPortalPersonal().verMensajesPersona();
                break;
            case 3:
                break;
            case 4:
                break;
            
        }
    } while (opcion != 5);
    }
    

    public Planta nuevaPlanta() {
        Planta p;
        boolean correcto = false;
        do {
            p = new Planta();
            System.out.println("Introduce los datos para una nueva planta:");
            System.out.print("Código: ");
            try {
                String codigo = in.nextLine().trim().toUpperCase();
                correcto = controlador.getServiciosPlanta().validarCodigo(codigo);
                if (!correcto) {
                    System.out.println("El formato del código no es correcto");
                } else {
                    p.setCodigo(codigo);
                    System.out.print("Nombre común: ");
                    String nombrecomun = in.nextLine();
                    p.setNombrecomun(nombrecomun);
                    System.out.print("Nombre científico: ");
                    String nombrecientifico = in.nextLine();
                    p.setNombrecientifico(nombrecientifico);
                    correcto = controlador.getServiciosPlanta().validarPlanta(p);
                    if (!correcto) {
                        System.out.println("Los datos que has introducido no son correctos.");
                    }
                }
            } catch (Exception ex) {
                System.out.println("Error durante la entrada de datos: " + ex.getMessage());
                correcto = false;
            }
        } while (!correcto);
        try {
            controlador.getServiciosPlanta().insertar(p);
            System.out.println("Planta insertada");
        } catch (Exception ex) {
            System.out.println("Error al insertar la planta: " + ex.getMessage());
        }
        return p;
    }

    public Ejemplar nuevoEjemplar() {
        Ejemplar e;
        Mensaje m;
        boolean correcto = false;
        do {
            e = new Ejemplar();
            System.out.println("Introduce los datos para crear un nuevo ejemplar:");
            System.out.print("Código de la planta del ejemplar: ");
            String codigoPlanta = in.nextLine();
            e.setCodigoPlanta(codigoPlanta);
            correcto = controlador.getServiciosEjemplar().validarEjemplar(e);
            if (!correcto) {
                System.out.println("Los datos que has introducido no son correctos.");
            }
        } while (!correcto);
        try {
            long idEjemplar = controlador.getServiciosEjemplar().insertar(e);
            if (idEjemplar > 0) {
                e.setId(idEjemplar);
                e.setNombre(e.getCodigoPlanta() + "_" + idEjemplar);
                System.out.println("Ejemplar insertado con ID: " + idEjemplar);
                String mensaje = "Añadido el ejemplar " + e.getNombre();
                LocalDateTime fechaHora = LocalDateTime.now();
                String usuarioAutenticado = controlador.getUsuarioAutenticado();
                long idUsuario = controlador.getServiciosPersona().IdUsuarioAutenticado(usuarioAutenticado);
                m = new Mensaje(fechaHora, mensaje, idEjemplar, idUsuario);
                if (controlador.getServiciosMensaje().insertar(m) > 0) {
                    System.out.println("Mensaje añadido correctamente.");
                } else {
                    System.out.println("No se pudo añadir el mensaje asociado al ejemplar.");
                }
            } else {
                System.out.println("Error al insertar el ejemplar en la base de datos.");
            }
        } catch (Exception ex) {
            System.out.println("Error al insertar el ejemplar o el mensaje: " + ex.getMessage());
        }
        return e;
    }
    public Persona nuevaPersona() {
        Persona pers;
        Credenciales c;
        boolean correcto = false;
        boolean emailValido = false;
        boolean usuarioValido = false;
        String usuario = "";
        String contrasena = "";
        do {
            emailValido = false;
            usuarioValido = false;
            pers= new Persona();
            c= new Credenciales();
            System.out.println("Introduce los datos para registrar una nueva persona:");
            System.out.print("Nombre: ");
            String nombre = in.nextLine().trim();
            pers.setNombre(nombre);
            String email = "";
            do {
                System.out.print("Email: ");
                email = in.nextLine().trim();
                pers.setEmail(email);
                if (controlador.getServiciosPersona().emailExistente(email)) {
                    System.out.println("El email que has introducido ya está registrado.");
                } else {
                    emailValido = true;
                }
            } while (!emailValido);
            do {
                System.out.print("Usuario: ");
                usuario = in.nextLine().trim();
                if (usuario.equalsIgnoreCase("ADMIN")) {
                    System.out.println("El usuario de admin ya está ocupado.");
                } else if (controlador.getServiciosCredenciales().usuarioExistente(usuario)) {
                    System.out.println("El usuario que has introducido ya está registrado.");
                } else {
                    usuarioValido = true;
                    c.setUsuario(usuario);
                }
            } while (!usuarioValido);
            System.out.print("Contraseña: ");
            contrasena = in.nextLine().trim();
            c.setPassword(contrasena); 
            correcto = controlador.getServiciosPersona().validarPersona(pers);
            if (!correcto) {
                System.out.println("Los datos que has introducido no son correctos.");
            }
        } while (!correcto);
        try {
            long idPersona = controlador.getServiciosPersona().insertar(pers);
            if (idPersona > 0) {
                c.setIdPersona(idPersona);
                int insertarCredenciales = controlador.getServiciosCredenciales().insertar(usuario, contrasena, idPersona);
                if (insertarCredenciales > 0) {
                    System.out.println("Persona y sus credenciales insertadas correctamente.");
                } else {
                    System.out.println("Error al insertar las credenciales en la base de datos.");
                }
            } else {
                System.out.println("Error al insertar la persona en la base de datos.");
            }
        } catch (Exception ex) {
            System.out.println("Error al insertar la persona nueva: " + ex.getMessage());
        }

        return pers;
    }
    public void verTodasPersonas() {
        ArrayList<Persona> personas = (ArrayList<Persona>) controlador.getServiciosPersona().verTodos();
        if (personas == null || personas.isEmpty()) {
            System.out.println("Lo siento, no hay personas para mostrar en la base de datos.");
            return;
        }
        System.out.println("Todas las personas: ");
        for (Persona pers : personas) {
            System.out.println(pers);
        }
    }
    public void verTodosMensajes() {
        ArrayList<Mensaje> mensajes = (ArrayList<Mensaje>) controlador.getServiciosMensaje().verTodos();
        if (mensajes == null || mensajes.isEmpty()) {
            System.out.println("Lo siento, no hay mensajes para mostrar en la base de datos.");
            return;
        }
        System.out.println("Todos los mensajes: ");
        for (Mensaje m: mensajes) {
            System.out.println(m);
        }
    }
    
    public void modificarNombreComun() {
    	    boolean valido = false;
    	    String codigo = "";
    	    
    	    do {
    	        System.out.print("Introduce el código de la planta de la que quieres modificar el nombre común: ");
    	        codigo = in.nextLine().trim().toUpperCase();
    	        valido = controlador.getServiciosPlanta().validarCodigo(codigo);
    	        if (valido==false) {
    	            System.out.println("El código de la planta que has introducido no es válido");
    	        }
    	    } while (valido==false);
    	    System.out.print("Introduce el nuevo nombre común de la planta: ");
    	    String nuevoNombreComun = in.nextLine().trim().toUpperCase();
    	    try {
    	        boolean nuevo = controlador.getServiciosPlanta().actualizarNombreComun(codigo, nuevoNombreComun);
    	        if (nuevo==true) {
    	            System.out.println("El nombre común de la planta con código " + codigo + "ha sido actualizado, ahora el nombre es:" + nuevoNombreComun);
    	        } else {
    	            System.out.println("Error al intentar actualizar el nombre común");
    	        }
    	    } catch (Exception ex) {
    	        System.out.println("Error al actualizar el nombre común: " + ex.getMessage());
    	    }
    }
    
    public void modificarNombreCientifico() {
    	boolean valido = false;
    	boolean existe = false;
	    String codigo = "";
	    do {
	        System.out.print("Introduce el código de la planta de la que quieres modificar el nombre científico: ");
	        codigo = in.nextLine().trim().toUpperCase();
	        valido = controlador.getServiciosPlanta().validarCodigo(codigo);
	        if (valido==false) {
	            System.out.println("El código de la planta que has introducido no es válido");
	        }
	    } while (valido==false);
	    existe = controlador.getServiciosPlanta().codigoExistente(codigo);
	    if (existe==false) {
            System.out.println("El código de la planta que has introducido no existe en la base de datos");
        }
     while (valido==false);
	    System.out.print("Introduce el nuevo nombre cientifico de la planta: ");
	    String nuevoNombreCientifico = in.nextLine().trim();
	    try {
	        boolean nuevo = controlador.getServiciosPlanta().actualizarNombreCientifico(codigo, nuevoNombreCientifico);
	        if (nuevo == true) {
	            System.out.println("El nombre cientifico de la planta con código " + codigo + "ha sido actualizado, ahora el nombre es" + nuevoNombreCientifico);
	        } else {
	            System.out.println("Error al intentar actualizar el nombre cientifico");
	        }
	    } catch (Exception ex) {
	        System.out.println("Error al actualizar el nombre cientifico: " + ex.getMessage());
	    }

    }
}