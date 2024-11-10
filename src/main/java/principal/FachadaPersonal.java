package principal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import control.Controlador;
import modelo.Ejemplar;
import modelo.Mensaje;

	/**
	 * Clase FachadaPersonal.
	 * Gestiona las opciones del menú de personal en una aplicación de administración de ejemplares y mensajes.
	 * Proporciona acceso a métodos para gestionar ejemplares y mensajes, y controla la navegación entre menús.
	 */
	public class FachadaPersonal {
		private static FachadaPersonal portalPersonal;
	
		/**
	     * Constructor privado para implementar el patrón Singleton.
	     */
		private FachadaPersonal() {
	
		}
	
		/**
	     * Devuelve la instancia única de la clase FachadaPersonal.
	     * @return Instancia única de FachadaPersonal.
	     */
		public static FachadaPersonal getPortalPersonal() {
			if (portalPersonal == null) {
				portalPersonal = new FachadaPersonal();
			}
			return portalPersonal;
		}
	
		private Controlador controlador = Controlador.getServicios();
		Scanner in = new Scanner(System.in);
	
		/**
	     * Muestra el menú principal del personal y permite seleccionar diversas opciones,
	     * como ver todas las plantas, gestionar ejemplares o mensajes, y cerrar sesión.
	     */
		public void menuPersonal() {
			int opcion = 0;
			do {
				System.out.println("------MENÚ DEL PERSONAL------");
				System.out.println("Selecciona una opción:");
				System.out.println("1. Ver todas las plantas.");
				System.out.println("2. Gestión de ejemplares.");
				System.out.println("3. Gestión de mensajes.");
				System.out.println("4. CERRAR SESIÓN.");
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
					menuPersonalEjemplares();
					break;
				case 3:
					menuPersonalMensajes();
					break;
				case 4:
					Controlador.getServicios().cerrarSesion();
					return;
	
				}
				}catch(InputMismatchException e){
	            	System.out.println("Debes ingresar un número. Inténtelo de nuevo.");
	            	in.nextLine();
	            	opcion = 0;
	            }
			} while (opcion != 4);
		}
	
		/**
	     * Muestra el menú de gestión de ejemplares, permitiendo registrar nuevos ejemplares,
	     * filtrar ejemplares por tipo de planta, o ver mensajes de un ejemplar.
	     */
		public void menuPersonalEjemplares() {
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
					FachadaAdmin.getPortalAdmin().nuevoEjemplar();
					break;
				case 2:
					filtrarEjemplaresPorCodigoPlanta();
					break;
				case 3:
					FachadaAdmin.getPortalAdmin().verMensajesEjemplar();
				}
				}catch(InputMismatchException e){
	            	System.out.println("Debes ingresar un número. Inténtelo de nuevo.");
	            	in.nextLine();
	            	opcion = 0;
	            }
			} while (opcion != 4);
		}
	
		/**
	     * Muestra el menú de gestión de mensajes, permitiendo crear un nuevo mensaje,
	     * ver todos los mensajes, o filtrarlos por persona, rango de fechas o tipo de planta.
	     */
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
				try {
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
					verMensajeFechas();
					break;
				case 5:
					 verMensajeTipoPlanta();
					break;
	
				}
				}catch(InputMismatchException e){
	            	System.out.println("Debes ingresar un número.");
	            	in.nextLine();
	            	opcion = 0;
	            }
			} while (opcion != 6);
		}
	
		/**
	     * Muestra todos los ejemplares disponibles en el sistema.
	     * Si no hay ejemplares, muestra un mensaje indicándolo.
	     */
		public void verTodosEjemplares() {
			ArrayList<Ejemplar> ejemplares = (ArrayList<Ejemplar>) controlador.getServiciosEjemplar().verTodos();
			if (ejemplares == null || ejemplares.isEmpty()) {
				System.out.println("No hay ejemplares para mostrar en la base de datos.");
				return;
			}
			System.out.println("Todos los ejemplares: ");
			for (Ejemplar e : ejemplares) {
				System.out.println(e);
			}
		}
		
		/**
	     * Crea un nuevo mensaje para un ejemplar, solicitando al usuario el ID del ejemplar,
	     * el contenido del mensaje y validando el formato.
	     */
		public void nuevoMensaje() {
			Mensaje nuevoMensaje = null;
			int idEjemplar = 0;
			boolean correcto = false;
			do {
				try {
					verTodosEjemplares();
					System.out.println();
					System.out.println("Introduce el id del ejemplar para ponerle un mensaje: ");
					idEjemplar = in.nextInt();
					in.nextLine();
					if (idEjemplar < 1 || idEjemplar > controlador.getServiciosEjemplar().contarEjemplares()) {
						System.out.println("Debes introducir un número entre el 1 y el" + controlador.getServiciosEjemplar().contarEjemplares());
					} else {
						String mensaje = "";
						boolean mensajeValido = false;
						do {
							System.out.println("Introduce el mensaje: ");
							mensaje = in.nextLine();
							if (!controlador.getServiciosMensaje().validarMensaje(mensaje)) {
								System.out.println("Formato no válido.");
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
		
		/**
	     * Filtra y muestra los ejemplares según el código de planta proporcionado.
	     * Si no se encuentra el código de planta, muestra un mensaje indicándolo.
	     */
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
					System.out.println("No se encontró ninguna planta con el código: " + codigo);
				}
			} catch (Exception e) {
				System.out.println("Error al intentar filtrar los ejemplares: " + e.getMessage());
			}
		}
	
		/**
	     * Muestra todos los mensajes asociados a una persona, solicitando su ID.
	     * Si no se encuentran mensajes, muestra un mensaje indicándolo.
	     */
		public void verMensajesPersona() {
			System.out.print("Introduce el id de una persona para ver sus mensajes: ");
			try {
				long idPersona = in.nextLong();
				ArrayList<Mensaje> mensajes = controlador.getServiciosMensaje().verMensajesPorPersona(idPersona);
				if (mensajes.isEmpty()) {
					System.out.println("No se encontraron mensajes para la persona: " + idPersona);
				} else {
					System.out.println("Mensajes:");
					System.out.println();
					for (Mensaje m : mensajes) {
						System.out.println(m);
						System.out.println();
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("Debes introducir un número válido.");
				in.nextLine();
			} catch (Exception e) {
				System.out.println("Error al intentar obtener los mensajes: " + e.getMessage());
			}
		}
	
		/**
	     * Muestra los mensajes de una planta específica basada en el código de planta proporcionado.
	     * Si no se encuentran mensajes, muestra un mensaje indicándolo.
	     */
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
		                System.out.println("No se encontraron mensajes de la planta");
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
		
		 /**
	     * Filtra y muestra mensajes en un rango de fechas específico, solicitando la fecha de inicio
	     * y la fecha de fin al usuario, y validando el formato de las fechas.
	     */
		public void verMensajeFechas() {
			in.nextLine();
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
			    LocalDateTime fechaInicio = null;
			    LocalDateTime fechaFin = null;
			    do{
			        try {
			            System.out.print("Introduce la primera fecha y la hora con el formato: dd-MM-yyyy HH:mm ");
			            String fechaInicioIntro = in.nextLine();
			            fechaInicio = LocalDateTime.parse(fechaInicioIntro, formatter);
			        } catch (DateTimeParseException e) {
			            System.out.println("Formato de fecha no válido.");
			        }
			    }while (fechaInicio == null);
			    do{
			        try {
			            System.out.print("Introduce la segunda fecha y la hora con el formato: dd-MM-yyyy HH:mm ");
			            String fechaFinIntro = in.nextLine();
			            fechaFin = LocalDateTime.parse(fechaFinIntro, formatter);
			            if (fechaFin.isBefore(fechaInicio)) {
			                System.out.println("La fecha de fin no puede ser anterior a la fecha de inicio.");
			                fechaFin = null;
			            }
			        } catch (DateTimeParseException e) {
			            System.out.println("Formato de fecha no válido.");
			        }
			    }while (fechaFin == null) ;
			    ArrayList<Mensaje> mensajes = controlador.getServiciosMensaje().verMensajesFecha(fechaInicio, fechaFin);
			    if (mensajes.isEmpty()) {
			        System.out.println("No se encontraron mensajes en el rango de fechas proporcionado.");
			    } else {
			        System.out.println("Mensajes encontrados:");
			        for (Mensaje m : mensajes) {
			            System.out.println("ID: " + m.getId());
			            System.out.println("Fecha: " + m.getFechaHora());
			            System.out.println("Mensaje: " + m.getMensaje());
			            System.out.println("ID Ejemplar: " + m.getIdEjemplar());
			            System.out.println("ID Persona: " + m.getIdPersona());
			            System.out.println("-------------");
			        }
			    }
		}
	}
		
	