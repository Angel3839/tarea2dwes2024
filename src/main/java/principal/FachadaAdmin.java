package principal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import control.Controlador;
import modelo.Credenciales;
import modelo.Ejemplar;
import modelo.Mensaje;
import modelo.Persona;
import modelo.Planta;

	/**
	 * Clase que representa la fachada de administración de la aplicación,
	 * proporcionando opciones de gestión para plantas, ejemplares, mensajes
	 * y personas a través de un menú de administrador.
	 */
	public class FachadaAdmin {
	    private static FachadaAdmin portalAdmin;
	    
	    /**
	     * Constructor privado para implementar el patrón singleton y
	     * evitar la creación directa de instancias fuera de la clase.
	     */
	    private FachadaAdmin() {
	    
	    }
	    
	    /**
	     * Devuelve la única instancia de la fachada de administración,
	     * creándola si no existe.
	     *
	     * @return Instancia única de FachadaAdmin.
	     */
	    public static FachadaAdmin getPortalAdmin() {
	        if (portalAdmin == null) {
	            portalAdmin = new FachadaAdmin();
	        }
	        return portalAdmin;
	    }
	    
	    private Controlador controlador = Controlador.getServicios();
	    Scanner in = new Scanner(System.in);
	    
	    /**
	     * Muestra el menú principal del administrador y permite la
	     * navegación entre las opciones de gestión de plantas, ejemplares,
	     * mensajes, personas y la opción de cerrar sesión.
	     */
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
	            try {
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
	                case 5:
	                	Controlador.getServicios().cerrarSesion();
	                	return;
	            }
	            
	        }catch(InputMismatchException e){
	        	System.out.println("Debes ingresar un número.");
	        	in.nextLine();
	        	opcion = 0;
	        }
	            
	    	} while (opcion != 5);
	        
	    }
	    
	    /**
	     * Muestra el menú de administración de plantas y permite
	     * gestionar la visualización, creación y modificación de plantas.
	     */
	    public void menuAdminPlantas() {
	        int opcion = 0;
	        do {
	            System.out.println("Selecciona una opción:");
	            System.out.println("1. Ver plantas.");
	            System.out.println("2. Crear nueva planta.");
	            System.out.println("3. Modificar datos de una planta.");
	            System.out.println("4. Volver al menú principal.");
	            try {
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
	            
	            }catch(InputMismatchException e) {
	            	System.out.println("Debes ingresar un número. Inténtelo de nuevo.");
	            	in.nextLine();
	            	opcion = 0;
	            }
	            
	        } while (opcion != 4);
	        
	    }
	    
	    /**
	     * Muestra el menú de opciones para modificar los datos de una planta
	     * específica, permitiendo cambiar el nombre común o el nombre científico.
	     */
	    public void menuAdminModificarPlantas() {
	    	int opcion = 0;
	        do {
	        System.out.println("Selecciona una opción:");
	        System.out.println("1. Modificar nombre común.");
	        System.out.println("2. Modificar nombre científico.");
	        System.out.println("3. Volver al menú de plantas.");
	        try {
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
	        
	        }catch(InputMismatchException e) {
	        	System.out.println("Debes ingresar un número. Inténtelo de nuevo.");
	        	in.nextLine();
	        	opcion = 0;
	        }
	        
	        } while (opcion != 3);
	    
	    }
	
	    /**
	     * Muestra el menú de administración de ejemplares, con opciones
	     * para registrar nuevos ejemplares, filtrar ejemplares y ver mensajes.
	     */
	    public void menuAdminEjemplares() {
	    	int opcion = 0;
	    	do {
	        System.out.println("Selecciona una opción:");
	        System.out.println("1. Registrar nuevo ejemplar.");
	        System.out.println("2. Filtrar ejemplares por tipo de planta.");
	        System.out.println("3. Ver mensajes de un ejemplar.");
	        System.out.println("4. Volver al menú principal.");
	        try {
	        opcion = in.nextInt();
	        if (opcion < 1 || opcion > 4) {
	            System.out.println("Opción incorrecta.");
	            continue;
	        }
	        switch (opcion) {
	            case 1:
	                nuevoEjemplar();
	                break;
	            case 2:
	                FachadaPersonal.getPortalPersonal().filtrarEjemplaresPorCodigoPlanta();
	                break;
	            case 3:
	            	verMensajesEjemplar();
	            
	        }
	        
	        }catch(InputMismatchException e) {
	        	System.out.println("Debes ingresar un número. Inténtelo de nuevo.");
	        	in.nextLine();
	        	opcion = 0;
	        }
	        
	    	} while (opcion != 4);
	    
	    }
	
	    /**
	     * Muestra el menú de administración de personas, con opciones
	     * para registrar y visualizar personas.
	     */
	    public void menuAdminPersonas() {
	    	int opcion = 0;
	    	do {
	        System.out.println("Selecciona una opción:");
	        System.out.println("1. Registrar nueva persona.");
	        System.out.println("2. Ver todas las personas.");
	        System.out.println("3. Volver al menú principal.");
	        try {
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
	        
	        }catch(InputMismatchException e) {
	        	System.out.println("Debes ingresar un número. Inténtelo de nuevo.");
	        	in.nextLine();
	        	opcion = 0;
	        }
	        
	    	} while (opcion != 3);
	    
	    }
	
	    /**
	     * Muestra el menú de administración de mensajes, con opciones para
	     * crear un nuevo mensaje o ver los mensajes existentes.
	     */
	    public void menuAdminMensajes() {
	    	int opcion = 0;
	    	do {
	        System.out.println("Selecciona una opción:");
	        System.out.println("1. Nuevo mensaje.");
	        System.out.println("2. Ver mensajes.");
	        System.out.println("3. Volver al menú principal.");
	        try {
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
	        
	        }catch(InputMismatchException e) {
	        	System.out.println("Debes ingresar un número. Inténtelo de nuevo.");
	        	in.nextLine();
	        	opcion = 0;
	        }
	        
	        } while (opcion != 3);
	    
	    }
	    
	    /**
	     * Muestra un submenú para ver mensajes filtrados por criterios como
	     * persona, rango de fechas o tipo de planta.
	     */
	    public void menuAdminVerMensajes() {
	    	int opcion = 0;
	    	do {
	        System.out.println("Selecciona una opción:");
	        System.out.println("1. Ver todos los mensajes.");
	        System.out.println("2. Ver mensajes por persona.");
	        System.out.println("3. Ver mensajes por rango de fechas.");
	        System.out.println("4. Ver mensajes por tipo de planta.");
	        System.out.println("5. Volver al menú de mensajes.");
	        try {
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
	                FachadaPersonal.getPortalPersonal().verMensajeFechas();
	                break;
	            case 4:
	                FachadaPersonal.getPortalPersonal().verMensajeTipoPlanta();
	                break;
	            
	        }
	        
	        }catch(InputMismatchException e) {
	        	System.out.println("Debes ingresar un número. Inténtelo de nuevo.");
	        	in.nextLine();
	        	opcion = 0;
	        }
	        
	    	} while (opcion != 5);
	    
	    }
	    
	    /**
	     * Crea y registra una nueva planta en el sistema, solicitando la
	     * entrada de datos como código, nombre común y nombre científico.
	     *
	     * @return La planta creada y registrada.
	     */
	    public Planta nuevaPlanta() {
	        in.nextLine();
	        Planta p;
	        boolean datosPlantaCorrectos = false;
	        do {
	            p = new Planta();
	            System.out.print("Código: ");
	            try {
	                String codigo = in.nextLine().trim().toUpperCase();
	                boolean correcto = controlador.getServiciosPlanta().validarCodigo(codigo);
	                boolean existe = controlador.getServiciosPlanta().codigoExistente(codigo);
	                if (!correcto) {
	                    System.out.println("El formato del código no es correcto.");
	                    continue;
	                }
	                if (existe) {
	                    System.out.println("El código ya existe para una planta.");
	                    continue;
	                }
	                
	                p.setCodigo(codigo);
	                System.out.print("Nombre común: ");
	                String nombreComun = in.nextLine().trim();
	                p.setNombrecomun(nombreComun);
	                System.out.print("Nombre científico: ");
	                String nombreCientifico = in.nextLine().trim();
	                p.setNombrecientifico(nombreCientifico);
	                datosPlantaCorrectos = controlador.getServiciosPlanta().validarPlanta(p);
	                if (!datosPlantaCorrectos) {
	                    System.out.println("Los datos que has introducido no son correctos.");
	                }
	                
	            } catch (Exception ex) {
	                System.out.println("Error durante la entrada de datos: " + ex.getMessage());
	                datosPlantaCorrectos = false;
	            }
	        } while (!datosPlantaCorrectos);
	        
	        try {
	            long plant = controlador.getServiciosPlanta().insertar(p);
	            if (plant > 0) {
	                System.out.println("Planta insertada correctamente");
	            } else {
	                System.out.println("Error al insertar la planta.");
	            }
	            
	        } catch (Exception ex) {
	            System.out.println("Error al insertar la planta: " + ex.getMessage());
	        }
	
	        return p;
	    }
	
	    /**
	     * Crea y registra un nuevo ejemplar en el sistema solicitando
	     * el código de la planta asociada y almacena un mensaje de creación.
	     *
	     * @return El ejemplar creado y registrado.
	     */
	    public Ejemplar nuevoEjemplar() {
	        in.nextLine(); 
	        Ejemplar e;
	        Mensaje m;
	        boolean correcto = false;
	        do {
	            e = new Ejemplar();
	            System.out.print("Código de la planta del ejemplar: ");
	            String codigo = in.nextLine().trim().toUpperCase();
	            boolean valido = controlador.getServiciosPlanta().validarCodigo(codigo);
	            if (!valido) {
	                System.out.println("El formato del código no es correcto.");
	                continue; 
	            }
	            
	            e.setCodigoPlanta(codigo);
	            e.setNombre(codigo);
	            correcto = true;
	        } while (!correcto);
	        try {
	            long idEjemplar = controlador.getServiciosEjemplar().insertar(e);
	            if (idEjemplar > 0) {
	                e.setId(idEjemplar);
	                e.setNombre(e.getCodigoPlanta() + "_" + idEjemplar);
	                System.out.println("Ejemplar insertado con ID: " + idEjemplar);
	                controlador.getServiciosEjemplar().cambiarNombre(e.getId(), e.getNombre());
	                
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
	    
	    /**
	     * Registra una nueva persona en el sistema. Solicita datos personales
	     * como el nombre, el correo electrónico, el nombre de usuario y la contraseña.
	     * Comprueba que el correo electrónico y el usuario no existan previamente en
	     * la base de datos y valida que la contraseña cumpla con los requisitos mínimos.
	     *
	     * @return La persona creada y registrada.
	     */
	    public Persona nuevaPersona() {
	        in.nextLine();
	        Persona pers;
	        Credenciales c;
	        boolean correcto = false;
	        boolean emailValido = false;
	        boolean usuarioValido = false;
	        boolean contraseñaValida = false;
	        String usuario = "";
	        String contraseña = "";
	        do {
	            emailValido = false;
	            usuarioValido = false;
	            contraseñaValida = false;
	            pers = new Persona(); 
	            c = new Credenciales();
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
	                    System.out.println("El usuario 'admin' ya está ocupado.");
	                } else if (controlador.getServiciosCredenciales().usuarioExistente(usuario) || usuario.length() < 3) {
	                    System.out.println("El usuario que has introducido ya está registrado o no cumple con los requisitos.");
	                } else {
	                    usuarioValido = true;
	                    c.setUsuario(usuario);
	                }
	                
	            } while (!usuarioValido);
	            do {
	                System.out.print("Contraseña: ");
	                contraseña = in.nextLine().trim();
	                if (controlador.getServiciosCredenciales().validarContraseña(contraseña) == false) {
	                    System.out.println("La contraseña debe tener al menos 8 caracteres e incluir al menos un carácter especial (Ej: . ; ,) .");
	                } else {
	                    contraseñaValida = true;
	                    c.setPassword(contraseña);
	                }
	                
	            } while (!contraseñaValida);
	            correcto = controlador.getServiciosPersona().validarPersona(pers);
	            if (!correcto) {
	                System.out.println("Los datos que has introducido no son correctos.");
	            }
	            
	        } while (!correcto);
	        
	        try {
	        	
	            long idPersona = controlador.getServiciosPersona().insertar(pers);
	            if (idPersona > 0) {
	                c.setIdPersona(idPersona);
	                int insertarCredenciales = controlador.getServiciosCredenciales().insertar(usuario, contraseña, idPersona);
	                if (insertarCredenciales > 0) {
	                    System.out.println("Persona y credenciales insertadas correctamente.");
	                } else {
	                    System.out.println("Error al insertar las credenciales en la base de datos.");
	                }
	                
	            } else {
	                System.out.println("Error al insertar la persona.");
	            }
	            
	        } catch (Exception ex) {
	            System.out.println("Error al insertar la persona nueva: " + ex.getMessage());
	        }
	
	        return pers;
	    }
	    
	    /**
	     * Muestra todas las personas registradas en la base de datos. 
	     * Imprime los detalles de cada persona o muestra un mensaje si no 
	     * hay personas en la base de datos.
	     */
	    public void verTodasPersonas() {
	        ArrayList<Persona> personas = (ArrayList<Persona>) controlador.getServiciosPersona().verTodos();
	        if (personas == null || personas.isEmpty()) {
	            System.out.println("No hay personas para mostrar en la base de datos.");
	            return;
	        }
	        
	        System.out.println("Todas las personas: ");
	        System.out.println();
	        for (Persona pers : personas) {
	            System.out.println(pers);
	            System.out.println();
	        }
	    }
	    
	    /**
	     * Muestra todos los mensajes registrados en el sistema. 
	     * Imprime el contenido de cada mensaje o muestra un mensaje si 
	     * no hay mensajes en la base de datos.
	     */
	    public void verTodosMensajes() {
	        ArrayList<Mensaje> mensajes = (ArrayList<Mensaje>) controlador.getServiciosMensaje().verTodos();
	        if (mensajes == null || mensajes.isEmpty()) {
	            System.out.println("No hay mensajes para mostrar en la base de datos.");
	            return;
	        }
	        
	        System.out.println("Todos los mensajes: ");
	        for (Mensaje m: mensajes) {
	            System.out.println(m);
	        }
	    }
	    
	    /**
	     * Modifica el nombre común de una planta en la base de datos.
	     * Solicita el código de la planta, valida su existencia y luego
	     * permite al usuario ingresar un nuevo nombre común para actualizarlo.
	     */
	    public void modificarNombreComun() {
	    	in.nextLine();
	    	    boolean valido = false;
	    	    String codigo = "";
	    	    boolean existe = false;
	    	    do {
	    	        System.out.print("Introduce el código de la planta de la que quieres modificar el nombre común: ");
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
	    	    
	    	    System.out.print("Introduce el nuevo nombre común de la planta: ");
	    	    String nuevoNombreComun = in.nextLine().trim().toUpperCase();
	    	    
	    	    try {
	    	        boolean nuevo = controlador.getServiciosPlanta().actualizarNombreComun(codigo, nuevoNombreComun);
	    	        if (nuevo==true) {
	    	            System.out.println("El nombre común de la planta con código " + codigo + " ha sido actualizado, ahora el nombre es: " + nuevoNombreComun);
	    	        } else {
	    	            System.out.println("Error al intentar actualizar el nombre común");
	    	        }
	    	        
	    	    } catch (Exception ex) {
	    	        System.out.println("Error al actualizar el nombre común: " + ex.getMessage());
	    	    }
	    }
	    
	    /**
	     * Modifica el nombre científico de una planta en la base de datos.
	     * Solicita el código de la planta, verifica que el código sea válido
	     * y existente, y permite al usuario actualizar el nombre científico.
	     */
	    public void modificarNombreCientifico() {
	    	in.nextLine();
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
		            System.out.println("El nombre cientifico de la planta con código " + codigo + " ha sido actualizado, ahora el nombre es: " + nuevoNombreCientifico);
		        } else {
		            System.out.println("Error al intentar actualizar el nombre cientifico");
		        }
		        
		    } catch (Exception ex) {
		        System.out.println("Error al actualizar el nombre cientifico: " + ex.getMessage());
		    }
	
	    }
	
	    /**
	     * Muestra los mensajes asociados a un ejemplar específico. 
	     * Solicita el ID del ejemplar y obtiene los mensajes relacionados 
	     * si el ejemplar existe y tiene mensajes registrados.
	     */
	    public void verMensajesEjemplar() {
	        System.out.print("Introduce el id de un ejemplar para ver sus mensajes: ");
	        try {
	            long idEjemplar = in.nextLong();
	            if (idEjemplar < 1 || idEjemplar > controlador.getServiciosEjemplar().contarEjemplares()) {
	                System.out.println("Debes introducir un número entre el 1 y " + controlador.getServiciosEjemplar().contarEjemplares());
	                return; 
	            }
	            
	            ArrayList<Mensaje> mensajes = controlador.getServiciosMensaje().verMensajesPorEjemplar(idEjemplar);
	            if (mensajes.isEmpty()) {
	                System.out.println("No se encontraron mensajes para el ejemplar");
	            } else {
	                System.out.println("Mensajes del ejemplar con ID: " + idEjemplar + ":");
	                for (Mensaje m : mensajes) {
	                    System.out.println(m);
	                }
	            }
	            
	        } catch (Exception e) {
	            System.out.println("Error al intentar obtener los mensajes del ejemplar: " + e.getMessage());
	        }
	    }
	}
	