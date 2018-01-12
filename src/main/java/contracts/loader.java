package contracts;

import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import contracts.wrapper.NepCurrency;
import contracts.wrapper.SetGetBC;
import contracts.wrapper.SimpleStorage;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import services.SignatureService;

public class loader {
    static NepCurrency contract_nepcurrency = null;
    static SimpleStorage contract_simplestorage = null;
    static SetGetBC contract_setget = null;


    public static NepCurrency getNepCurrencyContract(){
        if (contract_nepcurrency == null)
            loadNepCurrency();
        return contract_nepcurrency;
    }
    public static SimpleStorage getSimpleStorageContract(){
        if (contract_simplestorage == null)
            loadSimpleStorage();
        return contract_simplestorage;
    }
    public static SetGetBC getSetGetContract(){
        if (contract_setget == null)
            loadSetGetBC();
        return contract_setget;
    }

    /**
     * loader for nepCurrency contract
     * */
    public  static void loadNepCurrency(){

        System.out.println("Loading contract: NepCurrency");
        // Get or create Web3j instance
        Web3j web3j = Web3j.build(new HttpService(NodeConstants.WEB3_URL));

        // Get the node credentials
        Credentials NODE = Credentials.create(NodeConstants.PRIVATE_KEY);

        try {
            // load contract
            contract_nepcurrency = NepCurrency.load(NodeConstants.contractAddress_nepCurrency, web3j, NODE,
                    NodeConstants.GAS_PRICE_nepCurrency, NodeConstants.GAS_LIMIT_nepCurrency);
            // check if contract is valid
            assert contract_nepcurrency.isValid();
            System.out.println("Contract Address:" + contract_nepcurrency.getContractAddress());

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
            contract_simplestorage = SimpleStorage.load(NodeConstants.contractAddress_SimpleStorage, web3j, NODE,
                    NodeConstants.GAS_PRICE_SimpleStorage, NodeConstants.GAS_LIMIT_SimpleStorage);
            // check if contract is valid
            assert contract_simplestorage.isValid();
            System.out.println("Contract Address:" + contract_simplestorage.getContractAddress());

        } catch (IOException ex) {
            Logger.getLogger(loader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(loader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * loader for SetGetBC contract
     * */

    public static void loadSetGetBC() {
        System.out.println("Loading contract: SetGetBC");
        // Get or create Web3j instance
        Web3j web3j = Web3j.build(new HttpService(NodeConstants.WEB3_URL));

        // Get the node credentials
        Credentials NODE = Credentials.create(NodeConstants.PRIVATE_KEY);

        try {
            // deploy contract
            contract_setget = SetGetBC.load(NodeConstants.contractAddress_SetGet, web3j, NODE,
                    NodeConstants.GAS_PRICE_setget, NodeConstants.GAS_LIMIT_setget);
            // check if contract is valid
            assert contract_setget.isValid();
            System.out.println("Contract Address:" + contract_setget.getContractAddress());

        } catch (IOException ex) {
            Logger.getLogger(loader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(loader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}