package contracts;

import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

public class NodeConstants {


        public static final String WEB3_URL = "http://127.0.0.1:7545/";
        public static final String PUBLIC_KEY = "0x627306090abaB3A6e1400e9345bC60c78a8BEf57";
        public static final String PRIVATE_KEY = "c87509a1c067bbde78beb793e6fa76530b6382a4c0241e5e4a9ec0a0f44dc0d3";

        public static final BigInteger GAS_PRICE_nepCurrency = BigInteger.valueOf(20000000000L);
        public static final BigInteger GAS_LIMIT_nepCurrency = BigInteger.valueOf(6721975);
        public static final BigInteger INITIAL_SUPPLY_nepCurrency = BigInteger.valueOf(10_000_000);
        public static final String TOKEN_NAME_nepCurrency = new String("bipin");
        public static final String TOKEN_SYMBOL_nepCurrency = new String("bpn");
        public static final String PUBLIC_KEY_NOTARY = new String("0xC14d31ef3167e9b8A972A654D7BE441e594235F6");
        public static final String PRIVATE_KEY_NOTARY = new String("618ebf876881be0ee5a80c383062981dac0c598b13520a9689c5c722cd2dc788");
        public static final String contractAddress_nepCurrency = new String("0x345ca3e014aaf5dca488057592ee47305d9b3e10");


        public static String contractAddress_SimpleStorage = "0x937f9ca7c556fec7de2e71ea18daf89d4cce1004";
        public static final BigInteger GAS_PRICE_SimpleStorage = BigInteger.valueOf(20_000_000_000L);
        public static final BigInteger GAS_LIMIT_SimpleStorage = BigInteger.valueOf(100_000);
        public static final BigInteger INITIAL_WEI_VALUE_SimpleStorage = BigInteger.valueOf(9_300_000);


        public static String contractAddress_SetGet = "0x8cdaf0cd259887258bc13a92c0a6da92698644c0";
        public static final BigInteger GAS_PRICE_setget = BigInteger.valueOf(20_000_000_000L);
        public static final BigInteger GAS_LIMIT_setget = BigInteger.valueOf(100_000);
}
