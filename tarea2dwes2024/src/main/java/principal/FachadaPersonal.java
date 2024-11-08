package principal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import control.Controlador;
import modelo.Ejemplar;
import modelo.Mensaje;
import modelo.Planta;

public class FachadaPersonal {
	private static FachadaPersonal portalPersonal;

	private FachadaPersonal() {

	}

	public static FachadaPersonal getPortalPersonal() {
		if (portalPersonal == null) {
			portalPersonal = new FachadaPersonal();
		}
		return portalPersonal;
	}

	private Controlador controlador = Controlador.getServicios();
	Scanner in = new Scanner(System.in);

	public void menuPersonal() {
		int opcion = 0;
		do {
			System.out.println("------MENÚ DEL PERSONAL------");
			System.out.println("Selecciona una opción:");
			System.out.println("1. VER TODAS LAS PLANTAS.");
			System.out.println("2. Gestión de ejemplares.");
			System.out.println("3. Gestión de mensajes.");
			System.out.println("4. CERRAR SESIÓN.");

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
				menuPersonalEjemplares();
				break;
			case 3:
				menuPersonalMensajes();
				break;

			}
		} while (opcion != 4);
		Controlador.getServicios().cerrarSesion();
		FachadaInvitado.getPortalInvitado().menuInvitado();
	}

	public void menuPersonalEjemplares() {
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
				FachadaAdmin.getPortalAdmin().nuevoEjemplar();
				break;
			case 2:
				filtrarEjemplaresPorCodigoPlanta();
				break;

			}
		} while (opcion != 3);
	}

	public void menuPersonalMensajes() {
		int opcion = 0;
		do {
			System.out.println("Selecciona una opción:");
			System.out.println("1. Nuevo mensaje.");
			System.out.println("2. Ver todos los mensajes.");
			System.out.println("3. Ver mensajes por persona.");
			System.out.println("4. Ver mensajes por rango de fechas.");
			System.out.println("5. Ver mensajes por tipo de planta.");
			System.out.println("6. Volver al menú principal.");
			opcion = in.nextInt();
			if (opcion < 1 || opcion > 6) {
				System.out.println("Opción incorrecta.");
				continue;
			}
			switch (opcion) {
			case 1:
				nuevoMensaje();
				break;
			case 2:
				FachadaAdmin.getPortalAdmin().verTodosMensajes();
				break;
			case 3:
				verMensajesPersona();
				break;
			case 4:
				break;
			case 5:
				break;

			}
		} while (opcion != 6);
	}

	public void verTodosEjemplares() {
		ArrayList<Ejemplar> ejemplares = (ArrayList<Ejemplar>) controlador.getServiciosEjemplar().verTodos();
		if (ejemplares == null || ejemplares.isEmpty()) {
			System.out.println("Lo siento, no hay ejemplsres para mostrar en la base de datos.");
			return;
		}
		System.out.println("Todos los ejemplares: ");
		for (Ejemplar e : ejemplares) {
			System.out.println(e);
		}
	}

	public void nuevoMensaje() {
		Scanner in = new Scanner(System.in);
		Mensaje nuevoMensaje = null;
		int idEjemplar = 0;
		boolean correcto = false;
		do {
			try {
				verTodosEjemplares();
				System.out.println("Introduce el id del ejemplar: ");
				idEjemplar = in.nextInt();
				in.nextLine();
				if (idEjemplar < 1 || idEjemplar > controlador.getServiciosEjemplar().contarEjemplares()) {
					System.out.println("Debes introducir un número entre el 1 y "
							+ controlador.getServiciosEjemplar().contarEjemplares());
				} else {
					String mensaje = "";
					boolean mensajeValido = false;
					do {
						System.out.println("Introduce el mensaje: ");
						mensaje = in.nextLine();
						if (!controlador.getServiciosMensaje().validarMensaje(mensaje)) {
							System.out.println("Formato de mensaje no válido.");
						} else {
							mensajeValido = true;
							String usuarioAutenticado = controlador.getUsuarioAutenticado();
							long idUsuario = controlador.getServiciosPersona().IdUsuarioAutenticado(usuarioAutenticado);
							nuevoMensaje = new Mensaje(LocalDateTime.now(), mensaje, idEjemplar, idUsuario);
							if (controlador.getServiciosMensaje().insertar(nuevoMensaje) > 0) {
								System.out.println("Mensaje añadido.");
								correcto = true;
							} else {
								System.out.println("No se ha podido añadir el mensaje.");
							}
						}
					} while (!mensajeValido);
				}
			} catch (InputMismatchException e) {
				System.out.println("Debes introducir un número válido.");
				in.nextLine();
			}
		} while (!correcto);
	}

	public void filtrarEjemplaresPorCodigoPlanta() {
		try {
			System.out.print("Introduce el código de la planta para ver los ejemplares: ");
			String codigo = in.nextLine().trim().toUpperCase();
			boolean valido = controlador.getServiciosPlanta().codigoExistente(codigo);
			if (valido) {
				ArrayList<Ejemplar> ejemplares = controlador.getServiciosEjemplar().ejemplaresPorTipoPlanta(codigo);
				if (ejemplares.isEmpty()) {
					System.out.println("No hay ejemplares para la planta con código: " + codigo);
				} else {
					System.out.println("Ejemplares con el código " + codigo + ":");
					for (Ejemplar e : ejemplares) {
						System.out.println("ID: " + e.getId() + ", Nombre: " + e.getNombre());
					}
				}
			} else {
				System.out.println("No se encontró ninguna planta con el código especificado: " + codigo);
			}
		} catch (Exception e) {
			System.out.println("Error al intentar filtrar los ejemplares: " + e.getMessage());
		}
	}

	public void verMensajesPersona() {
		System.out.print("Introduce el id de una persona para ver sus mensajes: ");
		try {
			long idPersona = in.nextLong();
			ArrayList<Mensaje> mensajes = controlador.getServiciosMensaje().verMensajesPorPersona(idPersona);
			if (mensajes.isEmpty()) {
				System.out.println("No se encontraron mensajes para la persona: " + idPersona);
			} else {
				System.out.println("Mensajes:");
				for (Mensaje m : mensajes) {
					System.out.println(m);
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("Debes introducir un número válido.");
		} catch (Exception e) {
			System.out.println("Se produjo un error al intentar obtener los mensajes: " + e.getMessage());
		}
	}

	public void verMensajeTipoPlanta() {
	    System.out.print("Introduce el código de una planta: ");
	    String codigo = in.nextLine().trim().toUpperCase();
	    try {
	        boolean valido = controlador.getServiciosPlanta().validarCodigo(codigo);
	        if (!valido) {
	            System.out.println("El código de la planta no es válido.");
	            return;
	        }
	        boolean existe = controlador.getServiciosPlanta().codigoExistente(codigo);
	        if (existe) {
	            ArrayList<Mensaje> mensajes = controlador.getServiciosMensaje().verMensajesPorCodigoPlanta(codigo);
	            if (mensajes.isEmpty()) {
	                System.out.println("No se encontraron mensajes de la planta esa");
	            } else {
	                System.out.println("Mensajes para la planta con el código " + codigo);
	                for (Mensaje m : mensajes) {
	                    System.out.println(m); 
	                }
	            }
	        } else {
	            System.out.println("No se encontró ninguna planta con ese código");
	        }
	    } catch (Exception e) {
	        System.out.println("Se produjo un error al intentar obtener los mensajes: " + e.getMessage());
	    }
	}

}