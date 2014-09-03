package br.edu.ifpb.emailsharedpod;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author FILIPE
 */
public interface Fachada extends Remote{
    public String enviaEmail(Email email) throws RemoteException;
    public void salvar(Pessoa pessoa) throws RemoteException;
    public List<Pessoa> listaPessoas() throws RemoteException;
}