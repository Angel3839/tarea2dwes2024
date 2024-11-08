package principal;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione un rol para acceder al sistema:");
        System.out.println("1. Invitado");
        System.out.println("2. Administrador");
        System.out.println("3. Personal");
        System.out.print("Seleccione una opción (1-3): ");
        int opcion = scanner.nextInt();
        scanner.nextLine();  

        switch (opcion) {
            case 1:
                FachadaInvitado portalInvitado = FachadaInvitado.getPortalInvitado();
                portalInvitado.menuInvitado();
                break;

            case 2:
                FachadaAdmin portalAdmin = FachadaAdmin.getPortalAdmin();
                portalAdmin.menuAdmin();
                break;

            case 3:
                FachadaPersonal portalPersonal = FachadaPersonal.getPortalPersonal();
                portalPersonal.menuPersonal();
                break;

            default:
                System.out.println("Opción no válida.");
        }

        scanner.close();
    }
}
