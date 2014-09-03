/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ifpb.edu.br.sixpex.servidor.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Elisiany
 */
/*
    Essa é a interface conhecida tanto pelo cliente quanto pelo servidor.
    È como se fosse o idioma conhecido por eles para conversarem entre si.
    È no mesmo esquema das interfaces dos EJBs.
*/
public interface EmailRemote extends Remote {
    
    public String enviaEmail(List<String> emails, String mensagem) throws RemoteException;
    public List<String> getEmails() throws RemoteException;
    
}
