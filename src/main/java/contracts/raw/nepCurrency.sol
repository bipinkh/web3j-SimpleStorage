pragma solidity ^0.4.17;

/**
 * abstract class for erc 20 token
 *
*/

contract ERC20Token {

    string public name;
    string public symbol;
    uint public decimals  = 2;           // 2 decimal is enough because minimum value we consider is 0.01 NRS
    uint256 public totalSupply;
    address public creator;
    address public notary;

    uint public transactionFeeRate = 1;        // 1 percent transaction fee
    uint public minTransactionFee = 1000;       //10 rupees
    uint public maxTransactionFee = 100000;     // 1 thousand

    function transfer(address _to, uint _tokens) public returns (bool success);
    function transferFrom(address _from, address _to, uint _tokens) public returns (bool success);
    function approve(address _spender, uint _tokens) public returns (bool success);

    event Transfer(address indexed from, address indexed to, uint tokens);
    event Approval(address indexed tokenOwner, address indexed spender, uint tokens);
    event Burn(address indexed from, uint256 value);
    event Mint(address minter, uint256 balance);

    mapping (address => uint256 ) public balanceOf;                     // uint256 is the balance in paisaa
    mapping (address => mapping (address => uint256) ) public allowance;


}


/**
 * contract class for nepCurrency token
 *
*/

contract nepCurrency is ERC20Token{


    /** modifiers  **/

    modifier isCreator(){
        require (msg.sender == creator);
        _;
    }


    /**
     * constructor of contract
    */

    function nepCurrency(uint256 _initialSupply, string _name, string _symbol, address _notary) public{

        totalSupply = _initialSupply * 10 ** uint256(decimals);
        creator= msg.sender;
        balanceOf[msg.sender] = totalSupply;
        name = _name;
        symbol = _symbol;
        notary = _notary;

    }


    /**
     * minting coins
     *
     * here, the logic is that only after the creator receives cash, he can mint coins and send to the minter
     *
     * @param _balance is the amount in paisaa
    */

    function mint(address _minter, uint256 _balance, bytes32 _signedMessage,  uint8 _v, bytes32 _r, bytes32 _s)
        public isCreator() returns(bool success){

        require( verifySignature ( _signedMessage, _v, _r, _s ) );     // check if signature is matched

        uint _txnFee = calculateTxnFee(_balance);                   //calcuate transaction fee

        require(balanceOf[_minter] + _balance - _txnFee > balanceOf[_minter]);      // Check for overflows

        balanceOf[_minter] += _balance - _txnFee;

        totalSupply += _balance ;

        balanceOf[creator] += _txnFee;                      //creator collects transaction fee

        Mint(_minter, _balance);                            //set out event

        return true;
    }


    /**
     * burning coin
     *
     * any user can burn his/her coin, but this function can only be called by creator
    */

    function burn(uint256 _value) public returns (bool success) {
        require(balanceOf[msg.sender] >= _value);   // Check if the sender has enough
        balanceOf[msg.sender] -= _value;            // Subtract from the sender
        totalSupply -= _value;                      // Updates totalSupply
        Burn(msg.sender, _value);                   //set out event
        return true;
    }


    /**
     * set approval of allowance
    */

    function approve(address _spender, uint256 _value) public returns (bool success) {
        allowance[msg.sender][_spender] = _value;
        return true;
    }


    /**
     * internnal function to transfer balance from an address to other
    */

    function _transfer(address _from, address _to, uint _value) internal {

        uint _txnFee = calculateTxnFee(_value);

        require(_to != 0x0);                                    // Prevent transfer to 0x0 address

        require(balanceOf[_from] >= _value);                    // Check if the sender has enough

        require(balanceOf[_to] + _value - _txnFee > balanceOf[_to]);      // Check for overflows

        // uint previousBalances = balanceOf[_from] + balanceOf[_to] ; // Save this for an assertion in the future

        balanceOf[_from] -= _value;                             // Subtract from the sender

        balanceOf[_to] += _value - _txnFee;                               // Add the same to the recipient

        balanceOf[creator] += _txnFee;

        Transfer(_from, _to, _value - _txnFee);

        // Asserts are used to use static analysis to find bugs in your code. They should never fail
        // assert(balanceOf[_from] + balanceOf[_to] == previousBalances);
    }


    /**
     * transfer own coin to some other address
    */

    function transfer(address _to, uint _tokens) public returns (bool success){
        _transfer(msg.sender, _to, _tokens);
        return true;
    }


    /**
     * transfer tokens from parents address to some other address (provided the allowance is set)
    */

    function transferFrom(address _from, address _to, uint256 _tokens) public returns (bool success) {
        require(balanceOf[_from] >= _tokens);                // Check if the balance is enough
        require(_tokens <= allowance[_from][msg.sender]);     // Check allowance
        allowance[_from][msg.sender] -= _tokens;
        _transfer(_from, _to, _tokens);
        return true;
    }


    /**
     * just burn some tokens in exchange of cash in real life
    */
    function burnFrom(address _from, uint256 _value) public returns (bool success) {
        require(balanceOf[_from] >= _value);                // Check if the targeted balance is enough
        require(_value <= allowance[_from][msg.sender]);    // Check allowance
        balanceOf[_from] -= _value;                         // Subtract from the targeted balance
        allowance[_from][msg.sender] -= _value;             // Subtract from the sender's allowance
        totalSupply -= _value;                              // Update totalSupply
        Burn(_from, _value);
        return true;
    }



    /**
     * calculate the transactionFee
    */
    function calculateTxnFee(uint256 _amount) public view returns(uint fee){

        fee = (transactionFeeRate  * _amount) / 100;

        if (fee < minTransactionFee){
            fee = minTransactionFee;
        }
        if (fee > maxTransactionFee){
            fee = maxTransactionFee;
        }

    }

    /**
     * verify if the signature matches to notary
     *
    */
    function verifySignature (bytes32 _sign, uint8 v, bytes32 r, bytes32 s)public view returns (bool verified){
        return ecrecover(_sign, v, r, s) == notary;
    }

}
