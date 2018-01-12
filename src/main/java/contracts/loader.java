package contracts;

import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import contracts.wrapper.NepCurrency;
import contracts.wrapper.SimpleStorage;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import services.SignatureService;

public class loader {

    public static void main(String[] args){
//        loadSimpleStorage();
        loadNepCurrency();
    }

    /**
     * loader for nepCurrency contract
     * */
    public  static void loadNepCurrency(){

        ECKeyPair kp = ECKeyPair.create(new BigInteger(NodeConstants.PRIVATE_KEY_NOTARY, 16));
        String _minter = new String("0x583031d1113ad414f02576bd6afabfb302140225");
        BigInteger _balance = BigInteger.valueOf(100000); //1 thousand
        String _message = "some random shit";
        SignatureService.SignatureData signature = SignatureService.signMessage(_message.getBytes(), kp);

        System.out.println("Loading contract: NepCurrency");
        // Get or create Web3j instance
        Web3j web3j = Web3j.build(new HttpService(NodeConstants.WEB3_URL));

        // Get the node credentials
        Credentials NODE = Credentials.create(NodeConstants.PRIVATE_KEY);

        try {
            // load contract
            NepCurrency contract = NepCurrency.load(NodeConstants.contractAddress_nepCurrency, web3j, NODE,
                    NodeConstants.GAS_PRICE_nepCurrency, NodeConstants.GAS_LIMIT_nepCurrency);
            // check if contract is valid
            assert contract.isValid();
            System.out.println("Contract Address:" + contract.getContractAddress());

            BigInteger value = contract.balanceOf(NodeConstants.PUBLIC_KEY).send();
            System.out.println("Value:"+value);

            //mint some coins
//            contract.mint(_minter,_balance,_message.getBytes(),
//                    BigInteger.valueOf(signature.getV()), signature.getR(), signature.getS() )
//                    .observable()
//                    .subscribe(x -> {
//                                System.out.println("Tx Hash:" + x.getTransactionHash());
//                                RemoteCall<BigInteger> value = contract.balanceOf(NodeConstants.PUBLIC_KEY);
//                                System.out.println("Value:"+value);
//                            });

        } catch (IOException ex) {
            Logger.getLogger(loader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(loader.class.getName()).log(Level.SEVERE, null, ex);
        }
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