package contracts;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import contracts.wrapper.NepCurrency;
import contracts.wrapper.SimpleStorage;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import static contracts.NodeConstants.WEB3_URL;

public class deployer {

    public static void main(String[] args){
//        deploySimpleStorage();
        deployNepCurrency();
    }

    /**
     * @params for contracts:
     *      @param initialSupply BigInteger
     *      @param name  String
     *      @param symbol    String
     *      @param notary    String
     */

    public static void deployNepCurrency() {
        System.out.println("Deploying contract: NepCurrency");

        // Get or create Web3j instance
        Web3j web3j = Web3j.build(new HttpService(WEB3_URL));

        // Get the node credentials
        Credentials NODE = Credentials.create(NodeConstants.PRIVATE_KEY);

        try {
            // deploy contract
            NepCurrency contract = NepCurrency.deploy(web3j, NODE,
                    NodeConstants.GAS_PRICE_nepCurrency, NodeConstants.GAS_LIMIT_nepCurrency,
                    NodeConstants.INITIAL_SUPPLY_nepCurrency, NodeConstants.TOKEN_NAME_nepCurrency,
                    NodeConstants.TOKEN_SYMBOL_nepCurrency, NodeConstants.PUBLIC_KEY_NOTARY).send();

            // check if contract is valid
            assert contract.isValid();

            // print the contract address
            System.out.println("Contract Address:" + contract.getContractAddress());
        } catch (InterruptedException ex) {
            Logger.getLogger(deployer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(deployer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(deployer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(deployer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deploySimpleStorage() {
        System.out.println("Deploying contract: SimpleStorage");
        // Get or create Web3j instance
        Web3j web3j = Web3j.build(new HttpService(WEB3_URL));

        // Get the node credentials
        Credentials NODE = Credentials.create(NodeConstants.PRIVATE_KEY);

        try {
            // deploy contract
            SimpleStorage contract = SimpleStorage.deploy(web3j, NODE,
                    NodeConstants.GAS_PRICE_SimpleStorage, NodeConstants.GAS_LIMIT_SimpleStorage).send();

            // check if contract is valid
            assert contract.isValid();

            // print the contract address
            System.out.println("Contract Address:" + contract.getContractAddress());
        } catch (InterruptedException ex) {
            Logger.getLogger(deployer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(deployer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(deployer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(deployer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}