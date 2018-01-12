import contracts.NodeConstants;
import contracts.wrapper.SetGetBC;
import contracts.wrapper.SimpleStorage;
import org.bouncycastle.crypto.digests.KeccakDigest;
import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.bouncycastle.jcajce.provider.digest.SHA3;
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
//        setNotary();
        checkSign();
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


    //todo this
    public static void checkSign(){

        //prepare signature
        ECKeyPair kp = ECKeyPair.create(new BigInteger(NodeConstants.PRIVATE_KEY_NOTARY, 16));

        String msgString = new String("bipinkhatiwada");
        BigInteger msgInt = BigInteger.valueOf(3);
        String msgAddress = "0xf17f52151EbEF6C7334FAD080c5704D77216b732";

        String input = msgString + String.valueOf(msgInt) + msgAddress;
        SHA3.Digest256 digestSHA3 = new SHA3.Digest256();
        byte[] hashbytes = digestSHA3.digest(input.getBytes());

        SignatureService.SignatureData signature = SignatureService.signMessage(hashbytes, kp);

        contract.checkHash(msgString, msgInt, msgAddress,
                BigInteger.valueOf(signature.getV()), signature.getR(), signature.getS())
                .observable()
                .subscribe(x -> {
                    System.out.println("Tx hash :  " + x.getTransactionHash());
                    try{
                        BigInteger value = contract.getResult().send();
                        System.out.println("Result value stored :: "+ value);
                    }catch (Exception e){
                        System.out.println("could not read result from contract");
                    }
                });


    }
}
