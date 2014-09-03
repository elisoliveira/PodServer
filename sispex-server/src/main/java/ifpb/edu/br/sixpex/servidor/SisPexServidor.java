/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.edu.br.sixpex.servidor;

import ifpb.edu.br.sixpex.servidor.interfaces.EmailRemote;
import ifpb.edu.br.sixpex.servidor.servicos.FachadaService;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Elisiany
 */
public class SisPexServidor {

    public static void main(String[] args) {
       
        try {

            EmailRemote emailRemote = new FachadaService();
            /**
             * O método estático UnicastRemoteObject.exportObject exporta o
             * objeto remoto fornecido para que possa receber chamadas de seus
             * métodos remotas de clientes remotos(isto é, cria um stub para o
             * objeto remoto). O segundo argumento, um int, especifica qual
             * porta TCP a ser usada para escutar as solicitações de invocação
             * remota de entrada para o objeto. Uma vez que a invocação
             * ExportObject retornou com êxito, o objeto remoto EmailService
             * está pronto para processar chamadas remotas de entrada. Note-se
             * que o tipo da variavel de retorno deve ser EmailRemote, não
             * EmailService, porque o stub para um objeto remoto apenas
             * implementa as interfaces remotas que implementa o objeto remoto
             * exportados.
             */
            EmailRemote emailStub = (EmailRemote) UnicastRemoteObject.exportObject(emailRemote, 1099);
            /**
             * A interface remota Registry é a API para a ligação (ou registro)
             * e pesquisa de objetos remotos no registro. A classe
             * LocateRegistry fornece métodos estáticos para a criação de uma
             * referência remota para um registro em um endereço de rede
             * específico (host e porta). Uma vez que um objeto remoto é
             * registrado com um registro RMI no host local, os clientes em
             * qualquer máquina podem procurar o objeto remoto pelo nome, obter
             * a sua referência, e depois invocar métodos remotos do objeto. O
             * registro pode ser compartilhado por todos os servidores em
             * execução em uma máquina ou um processo servidor individual pode
             * criar e usar seu próprio registro.
             */
            Registry registry = LocateRegistry.createRegistry(1099);
            /**
             * Religa o nome especificado para um novo objeto remoto ( istos é,
             * caso ja exista um objeto referenciado por esse nome, o objeto será atualizado). 
             * Qualquer ligação para o nome existente é substituído. A implantação de
             * objetos remotos, como EmailService, nunca deixam a máquina
             * virtual Java em que eles foram criados. Assim, quando um cliente
             * realiza uma pesquisa no registro de objeto remoto de um servidor,
             * uma cópia do stub é retornado. Objetos remotos, nesses casos,
             * são, portanto, efetivamente aprovada pelo (remota) de referência
             * e não por valor.
             */
            registry.rebind("emailRemote", emailStub);
            System.out.println("Servidor Iniciado...");

        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
