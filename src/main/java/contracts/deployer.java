package contracts;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import contracts.wrapper.SimpleStorage;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

public class deployer {

    public static final String WEB3_URL = "https://rinkeby.infura.io/";
    public static final String ADDRESS = "0xd302e9d4e41251327396ca3dac183b4c9227f32f";
    public static final String PRIVATE_KEY = "da932039e7457ebecb270c41d4770923c3bb44749b95b30a30508e1c78e427a1";
    public static final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);
    public static final BigInteger GAS_LIMIT = BigInteger.valueOf(100_000);
    public static final BigInteger INITIAL_WEI_VALUE = BigInteger.valueOf(9_300_000);

    public static void main(String[] args) {
        System.out.println("Deploying contract: SimpleStorage");
        // Get or create Web3j instance
        Web3j web3j = Web3j.build(new HttpService(WEB3_URL));

        // Get the node credentials
        Credentials NODE = Credentials.create(PRIVATE_KEY);

        try {
            // deploy contract
            SimpleStorage contract = SimpleStorage.deploy(web3j, NODE, GAS_PRICE, GAS_LIMIT).send();

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