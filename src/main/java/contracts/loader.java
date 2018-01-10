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

    public static String contractAddress = "0x937f9ca7c556fec7de2e71ea18daf89d4cce1004";

    public static final String WEB3_URL = "https://rinkeby.infura.io/";
    public static final String ADDRESS = "0x6440a960a36d9e30ce21c5c7f082873272399f70";
    public static final String PRIVATE_KEY = "da932039e7457ebecb270c41d4770923c3bb44749b95b30a30508e1c78e427a1";
    public static final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);
    public static final BigInteger GAS_LIMIT = BigInteger.valueOf(100_000);
    public static final BigInteger INITIAL_WEI_VALUE = BigInteger.valueOf(9_300_000);

    public static void main(String[] args) {
        System.out.println("Loading contract: SimpleStorage");
        // Get or create Web3j instance
        Web3j web3j = Web3j.build(new HttpService(WEB3_URL));

        // Get the node credentials
        Credentials NODE = Credentials.create(PRIVATE_KEY);

        try {
            // deploy contract
            SimpleStorage contract = SimpleStorage.load(contractAddress, web3j, NODE, GAS_PRICE, GAS_LIMIT);
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