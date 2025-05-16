/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package codigo_completo;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorChat {
    private static List<PrintWriter> clientes = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Servidor de Chat iniciado...");

        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            while (true) {
                Socket socket = serverSocket.accept();
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                synchronized (clientes) {
                    clientes.add(out);
                }
                new Thread(new ManejadorCliente(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ManejadorCliente implements Runnable {
        private Socket socket;
        private BufferedReader in;

        public ManejadorCliente(Socket socket) {
            this.socket = socket;
            try {
                this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                String mensaje;
                while ((mensaje = in.readLine()) != null) {
                    guardarMensaje(mensaje);
                    synchronized (clientes) {
                        for (PrintWriter cliente : clientes) {
                            cliente.println(mensaje);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                    synchronized (clientes) {
                        clientes.removeIf(out -> out.checkError());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void guardarMensaje(String mensaje) {
            try (FileWriter fw = new FileWriter("conversacion_chat.txt", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(mensaje);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
