import contracts.NodeConstants;
import contracts.wrapper.SetGetBC;
import contracts.wrapper.SimpleStorage;
import org.web3j.crypto.ECKeyPair;
import services.SignatureService;
import contracts.loader;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignatureChecking {
    static SetGetBC contract;

    public static void main(String[] args){
        contract  = loader.getSetGetContract();
        setNotary();

    }

    private static void setNotary() {
        //set notary address
        contract.setNotaryAddress(NodeConstants.PUBLIC_KEY_NOTARY)
                .observable()
                .subscribe(x -> {
                    System.out.println("Tx hash :  " + x.getTransactionHash());
                    try{
                        String value = contract.getNotaryAddress().send();
                        System.out.println("Notary Address stored :: "+ value);
                    }catch (Exception e){
                        System.out.println("could not read notary address");
                    }
                });
    }

    public static void checkSign(){

        //prepare signature
        ECKeyPair kp = ECKeyPair.create(new BigInteger(NodeConstants.PRIVATE_KEY_NOTARY, 16));
        String msgString = new String("hello world");
        BigInteger _balance = BigInteger.valueOf(100000); //1 thousand
        SignatureService.SignatureData signature = SignatureService.signMessage(msgString.getBytes(), kp);


    }
}
