package contracts;

import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

public class NodeConstants {


        public static final String WEB3_URL = "https://rinkeby.infura.io/";
        public static final String PUBLIC_KEY = "0x6440a960a36d9e30ce21c5c7f082873272399f70";
        public static final String PRIVATE_KEY = "da932039e7457ebecb270c41d4770923c3bb44749b95b30a30508e1c78e427a1";

        public static final BigInteger GAS_PRICE_nepCurrency = BigInteger.valueOf(20_000_000_000_000L);
        public static final BigInteger GAS_LIMIT_nepCurrency = BigInteger.valueOf(500_000);
        public static final BigInteger INITIAL_SUPPLY_nepCurrency = BigInteger.valueOf(10_000_000);
        public static final String TOKEN_NAME_nepCurrency = new String("bipin");
        public static final String TOKEN_SYMBOL_nepCurrency = new String("bpn");
        public static final String PUBLIC_KEY_NOTARY = new String("0xC14d31ef3167e9b8A972A654D7BE441e594235F6");
        public static final String PRIVATE_KEY_NOTARY = new String("618ebf876881be0ee5a80c383062981dac0c598b13520a9689c5c722cd2dc788");
        public static final String contractAddress_nepCurrency = new String("0xb38eafa257cc9bd70b4a4d9c70fd3124d29e58c0");


        public static String contractAddress_SimpleStorage = "0x937f9ca7c556fec7de2e71ea18daf89d4cce1004";
        public static final BigInteger GAS_PRICE_SimpleStorage = BigInteger.valueOf(20_000_000_000L);
        public static final BigInteger GAS_LIMIT_SimpleStorage = BigInteger.valueOf(100_000);
        public static final BigInteger INITIAL_WEI_VALUE_SimpleStorage = BigInteger.valueOf(9_300_000);
}
