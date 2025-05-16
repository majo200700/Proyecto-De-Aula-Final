/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo_completo.modelo;

import java.util.*;

public class ChatService {
    private Map<String, Map<String, List<String>>> chats;

    public ChatService() {
        chats = new HashMap<>();
    }

    public void enviarMensaje(String cliente, String especialista, String mensaje) {
        chats.putIfAbsent(cliente, new HashMap<>());
        chats.get(cliente).putIfAbsent(especialista, new ArrayList<>());
        chats.get(cliente).get(especialista).add("Cliente: " + mensaje);
    }

    public void responderMensaje(String cliente, String especialista, String respuesta) {
        if (chats.containsKey(cliente) && chats.get(cliente).containsKey(especialista)) {
            chats.get(cliente).get(especialista).add("Especialista: " + respuesta);
        }
    }

    public List<String> obtenerMensajes(String cliente, String especialista) {
        if (chats.containsKey(cliente) && chats.get(cliente).containsKey(especialista)) {
            return chats.get(cliente).get(especialista);
        } else {
            return new ArrayList<>();
        }
    }

    public Set<String> obtenerClientesQueEscribieron(String especialista) {
        Set<String> clientes = new HashSet<>();
        for (String cliente : chats.keySet()) {
            if (chats.get(cliente).containsKey(especialista)) {
                clientes.add(cliente);
            }
        }
        return clientes;
    }
}
