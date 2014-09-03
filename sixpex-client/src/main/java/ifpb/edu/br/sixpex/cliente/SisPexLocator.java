/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.edu.br.sixpex.cliente;

import br.edu.ifpb.emailsharedpod.Fachada;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;

/**
 *
 * @author Elisiany
 */
public class SisPexLocator {

    public static Fachada lookup() {
        Fachada fachada = null;
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 10999);
            fachada = (Fachada) registry.lookup("fachada");
        } catch (RemoteException | NotBoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "SixPex", JOptionPane.ERROR_MESSAGE);
        }
        return fachada;
    }

}
