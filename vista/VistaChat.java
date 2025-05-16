/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo_completo.vista;

import codigo_completo.modelo.ChatService;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class VistaChat {
    private Scanner scanner = new Scanner(System.in);

    public void mostrarChatCliente(String cliente, Set<String> especialistas, ChatService chatService) {
        if (especialistas.isEmpty()) {
            System.out.println("No hay especialistas disponibles en este momento.");
            return;
        }

        System.out.println("Especialistas disponibles:");
        for (String esp : especialistas) {
            System.out.println("- " + esp);
        }

        System.out.print("Elige el especialista con quien deseas hablar: ");
        String especialista = scanner.nextLine();

        if (!especialistas.contains(especialista)) {
            System.out.println("Especialista no encontrado.");
            return;
        }

        System.out.println("Escribe tu mensaje (escribe 'salir' para terminar):");
        while (true) {
            String mensaje = scanner.nextLine();
            if (mensaje.equalsIgnoreCase("salir")) break;
            chatService.enviarMensaje(cliente, especialista, mensaje);
            System.out.println("Mensaje enviado.");
        }
    }

    public void mostrarChatEspecialista(String especialista, ChatService chatService) {
        System.out.println("Clientes que te han escrito:");
        Set<String> clientes = chatService.obtenerClientesQueEscribieron(especialista);
        if (clientes.isEmpty()) {
            System.out.println("No tienes mensajes nuevos.");
            return;
        }

        for (String cliente : clientes) {
            System.out.println("- " + cliente);
        }

        System.out.print("Elige un cliente para ver su chat: ");
        String cliente = scanner.nextLine();

        List<String> mensajes = chatService.obtenerMensajes(cliente, especialista);
        System.out.println("Chat con " + cliente + ":");
        for (String mensaje : mensajes) {
            System.out.println(mensaje);
        }

        System.out.println("¿Deseas responder? Escribe tu mensaje o 'salir' para terminar:");
        while (true) {
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("salir")) break;
            chatService.responderMensaje(cliente, especialista, respuesta);
            System.out.println("Respuesta enviada.");
        }
    }
}
