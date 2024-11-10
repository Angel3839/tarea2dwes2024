package principal;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import control.Controlador;
import modelo.Planta;

	/**
	 * Clase FachadaInvitado.
	 * Gestiona el menú de opciones disponible para los usuarios invitados en la aplicación.
	 * Permite ver la lista de plantas, iniciar sesión y salir del programa.
	 */
	public class FachadaInvitado {
	    private static FachadaInvitado portalInvitado;
	    
	    /**
	     * Constructor privado para implementar el patrón Singleton.
	     */
	    private FachadaInvitado() {
	    	
	    }
	    
	    /**
	     * Devuelve la instancia única de la clase FachadaInvitado.
	     * @return Instancia única de FachadaInvitado.
	     */
	    public static FachadaInvitado getPortalInvitado() {
	        if (portalInvitado == null) {
	            portalInvitado = new FachadaInvitado();
	        }
	        return portalInvitado;
	    }
	    
	    private Controlador controlador = Controlador.getServicios();
	    private Scanner in = new Scanner(System.in);
	    
	    /**
	     * Muestra el menú de opciones para el usuario invitado, incluyendo:
	     * ver todas las plantas, iniciar sesión y salir del programa.
	     * Controla la entrada de datos para evitar errores de formato.
	     */
	    public void menuInvitado() {
	        int opcion = 0;
	        do {
	        	System.out.println("------GESTIÓN DEL VIVERO------");
	        	System.out.println("Selecciona una opción: ");
	            System.out.println("1. VER TODAS LAS PLANTAS");
	            System.out.println("2. LOGIN");
	            System.out.println("3. SALIR DEL PROGRAMA");
	            try {
	            opcion = in.nextInt();
	            switch (opcion) {
	                case 1:
	                    verTodasPlantas();
	                    break;
	                case 2:
	                    login();
	                    break;
	                case 3:
	                    System.out.println("Has salido del programa.");
	                    break;
	                default:
	                    System.out.println("Opción incorrecta.");
	            }
	            }catch(InputMismatchException e){
	            	System.out.println("Debes ingresar un número.");
	            	in.nextLine();
	            	opcion = 0;
	            }
	        } while (opcion != 3);
	    }
	    
	    /**
	     * Permite al usuario iniciar sesión proporcionando su usuario y contraseña.
	     * Si la autenticación es exitosa, redirige al menú correspondiente según el rol:
	     * menú de administrador o menú del personal del vivero.
	     */
	    public void login() {
	        in.nextLine();
	        System.out.print("Introduce usuario: ");
	        String usuario = in.nextLine();
	        System.out.print("Introduce contraseña: ");
	        String contraseña = in.nextLine();
	        try {
	            boolean autenticar = controlador.getServiciosCredenciales().autenticar(usuario, contraseña);
	            if (autenticar) {
	                System.out.println("Has iniciado sesión como " + usuario);
	                controlador.setUsuarioAutenticado(usuario);
	                if (usuario.equalsIgnoreCase("admin") && contraseña.equalsIgnoreCase("admin")) {
	                    System.out.println("Eres el usuario administrador");
	                    FachadaAdmin.getPortalAdmin().menuAdmin();
	                } else {
	                    System.out.println("Eres un usuario del personal del vivero");
	                    FachadaPersonal.getPortalPersonal().menuPersonal();
	                }
	            } else {
	                System.out.println("Usuario o contraseña incorrectos.");
	                return;
	            }
	        } catch (Exception e) {
	            System.out.println("No se ha podido iniciar sesión: " + e.getMessage());
	            return;
	        }
	    }
	    
	    /**
	     * Muestra una lista de todas las plantas disponibles en la base de datos.
	     * Si no hay plantas disponibles, informa al usuario.
	     */
	    public void verTodasPlantas() {
	        ArrayList<Planta> plantas = (ArrayList<Planta>) controlador.getServiciosPlanta().verTodos();
	        if (plantas == null || plantas.isEmpty()) {
	            System.out.println("No hay plantas para mostrar en la base de datos.");
	            return;
	        }
	        System.out.println("Todas las plantas: ");
	        System.out.println();
	        for (Planta p : plantas) {
	            System.out.println(p);
	            System.out.println();
	        }
	    }
	    
	}
	
	