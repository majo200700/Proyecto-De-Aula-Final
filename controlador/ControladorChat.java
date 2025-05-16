/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo_completo.controlador;

import codigo_completo.modelo.ChatService;
import codigo_completo.vista.VistaChat;
import java.util.Set;

public class ControladorChat {
    private ChatService chatService;
    private VistaChat vistaChat;

    public ControladorChat(ChatService chatService, VistaChat vistaChat) {
        this.chatService = chatService;
        this.vistaChat = vistaChat;
    }

    public void chatCliente(String nombreCliente, Set<String> especialistas) {
        vistaChat.mostrarChatCliente(nombreCliente, especialistas, chatService);
    }

    public void chatEspecialista(String nombreEspecialista) {
        vistaChat.mostrarChatEspecialista(nombreEspecialista, chatService);
    }
}
