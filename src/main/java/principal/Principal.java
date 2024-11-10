package principal;

import java.util.Scanner;

	/**
	 * Clase principal
	 */
	public class Principal {
	    public static void main(String[] args) {
	        
	    	/**
	    	 * Acceso al portal de invitado
	    	 */
	    	FachadaInvitado portalInvitado = FachadaInvitado.getPortalInvitado();
	        portalInvitado.menuInvitado();
	    	
	    }
	}
