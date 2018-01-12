package contracts.wrapper;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.2.0.
 */
public class SetGetBC extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b6103218061001e6000396000f30060606040526004361061006c5763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416633fcbec15811461007157806360fe47b1146100a05780636d4ce63c146100b85780638bc05609146100dd578063f0a4e1a2146100fc575b600080fd5b341561007c57600080fd5b610084610185565b604051600160a060020a03909116815260200160405180910390f35b34156100ab57600080fd5b6100b6600435610194565b005b34156100c357600080fd5b6100cb610199565b60405190815260200160405180910390f35b34156100e857600080fd5b6100b6600160a060020a036004351661019f565b341561010757600080fd5b61017160046024813581810190830135806020601f8201819004810201604051908101604052818152929190602084018383808284375094965050843594600160a060020a03602082013516945060ff6040820135169350606081013592506080013590506101ce565b604051901515815260200160405180910390f35b600154600160a060020a031690565b600055565b60005490565b6001805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a0392909216919091179055565b60008060008888886040518084805190602001908083835b602083106102055780518252601f1990920191602091820191016101e6565b6001836020036101000a0380198251168184511617909252505050919091019384525050600160a060020a03166c010000000000000000000000000260208201526034019050604051809103902091506001828787876040516000815260200160405260006040516020015260405193845260ff90921660208085019190915260408085019290925260608401929092526080909201915160208103908084039060008661646e5a03f115156102ba57600080fd5b505060206040510351600154909150600160a060020a038083169116146102e457600092506102e9565b600192505b505096955050505050505600a165627a7a7230582032044b0a4095c71687027adbd4563cd848f16e21d75c60c2ab02f69f12838b140029";

    protected SetGetBC(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected SetGetBC(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> getNotaryAddress() {
        Function function = new Function("getNotaryAddress", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> set(BigInteger a) {
        Function function = new Function(
                "set", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(a)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> get() {
        Function function = new Function("get", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setNotaryAddress(String not) {
        Function function = new Function(
                "setNotaryAddress", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(not)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Boolean> checkHash(String msgString, BigInteger msgUint, String msgAddrs, BigInteger v, byte[] r, byte[] s) {
        Function function = new Function("checkHash", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(msgString), 
                new org.web3j.abi.datatypes.generated.Uint256(msgUint), 
                new org.web3j.abi.datatypes.Address(msgAddrs), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public static RemoteCall<SetGetBC> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SetGetBC.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<SetGetBC> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(SetGetBC.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static SetGetBC load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new SetGetBC(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static SetGetBC load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new SetGetBC(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
