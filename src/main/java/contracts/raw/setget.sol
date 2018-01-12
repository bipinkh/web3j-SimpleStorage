pragma solidity ^0.4.0;

contract SetGetBC {
    uint storedData;
    address notary;
    
    function set(uint a)public{
        storedData = a;
    }
    
    function get()public constant returns (uint){
        return storedData;
    }
    
    function setNotaryAddress(address not) public{
        notary = not;   
    }
    
     function getNotaryAddress() public constant returns(address not){
       return notary;  
    }
    
    function checkHash(string msgString, uint256 msgUint, address msgAddrs, uint8 v, bytes32 r, bytes32 s) public
        constant returns (bool success){

        bytes32 msgHash = keccak256(msgString, msgUint, msgAddrs);

        address signerAddrs = ecrecover(msgHash, v, r, s);

        if (notary != signerAddrs) return false;

        return true;

    }
}

