package contracts.wrapper;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
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
public class Deliverables extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b60008054600160a060020a033316600160a060020a031990911617905561021a8061003b6000396000f3006060604052600436106100615763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416638da5cb5b81146100665780639e39db7314610095578063d6594816146100b6578063dea9446c146100d5575b600080fd5b341561007157600080fd5b610079610106565b604051600160a060020a03909116815260200160405180910390f35b34156100a057600080fd5b6100b4600160a060020a0360043516610115565b005b34156100c157600080fd5b6100b4600160a060020a0360043516610195565b34156100e057600080fd5b6100f4600160a060020a03600435166101cf565b60405190815260200160405180910390f35b600054600160a060020a031681565b60005433600160a060020a0390811691161461013057600080fd5b604080519081016040908152600160a060020a038316808352600060208085018290529181526001909152208151815473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a039190911617815560208201516001909101555050565b60005433600160a060020a039081169116146101b057600080fd5b600160a060020a03166000908152600160208190526040909120810155565b600160a060020a031660009081526001602081905260409091200154905600a165627a7a723058205cf23efcc496903f06b4f008a480ce0b3e3a8f4b010966f1b3b1c526ef5d42aa0029";

    protected Deliverables(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Deliverables(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> store(String id) {
        Function function = new Function(
                "store", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> delivered(String id) {
        Function function = new Function(
                "delivered", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(id)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> statusFor(String id) {
        Function function = new Function("statusFor", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public static RemoteCall<Deliverables> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Deliverables.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Deliverables> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Deliverables.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static Deliverables load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Deliverables(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Deliverables load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Deliverables(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
