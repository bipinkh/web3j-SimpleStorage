package contracts;

import contracts.wrapper.Deliverables;
import contracts.wrapper.SetGetBC;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;

public class deploy {

    public static final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);
    public static final BigInteger GAS_LIMIT = BigInteger.valueOf(4_300_000);

    public static void main(String[] args){
        Web3j web3 = Web3j.build(new HttpService());                            // defaults to http://localhost:8545/
        Credentials credentials = Credentials.create(NodeConstants.PRIVATE_KEY);

        try {
            System.out.println(" testpoint 1");

            SetGetBC contract = SetGetBC.deploy(
                    web3, credentials, GAS_PRICE, GAS_LIMIT).send();

            System.out.println(" testpoint 2");

            assert contract.isValid();

            System.out.println("Contract Address is ::" + contract.getContractAddress());

            System.out.println(" testpoint 3");

        } catch (Exception e) {
            System.out.println("Exception occured while deploying contract !!" + e.getMessage());
        }



    }


}
