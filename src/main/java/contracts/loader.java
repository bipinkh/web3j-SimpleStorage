package contracts;

import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import contracts.wrapper.SimpleStorage;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class loader {

    public static void main(String[] args){
        loadSimpleStorage();
    }

    /**
     * loader for nepCurrency contract
     * */
    public  static void nepCurrency(){

    }

    /**
     * loader for SimpleStorage contract
     * */

    public static void loadSimpleStorage() {
        System.out.println("Loading contract: SimpleStorage");
        // Get or create Web3j instance
        Web3j web3j = Web3j.build(new HttpService(NodeConstants.WEB3_URL));

        // Get the node credentials
        Credentials NODE = Credentials.create(NodeConstants.PRIVATE_KEY);

        try {
            // deploy contract
            SimpleStorage contract = SimpleStorage.load(NodeConstants.contractAddress_SimpleStorage, web3j, NODE,
                    NodeConstants.GAS_PRICE_SimpleStorage, NodeConstants.GAS_LIMIT_SimpleStorage);
            // check if contract is valid
            assert contract.isValid();
            System.out.println("Contract Address:" + contract.getContractAddress());

            contract.set(new BigInteger("12345678"))
                    .observable()
                    .subscribe(x -> {
                        System.out.println("Tx Hash:" + x.getTransactionHash());
                        try {
                            // Read set value
                            BigInteger value = contract.get().send();
                            System.out.println("Value:"+value);
                        } catch (Exception ex) {
                            Logger.getLogger(loader.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

        } catch (IOException ex) {
            Logger.getLogger(loader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(loader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}