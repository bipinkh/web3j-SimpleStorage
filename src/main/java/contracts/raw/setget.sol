pragma solidity ^0.4.0;

contract SetGetBC {
    uint storedData;
    address notary;
    uint result = 0;
    
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

    function getResult() public constant returns(uint not){
           return result;
        }

    function checkHash(string msgString, uint256 msgUint, address msgAddrs, uint8 v, bytes32 r, bytes32 s) public
         returns (bool success){

        bytes32 msgHash = keccak256(msgString, msgUint, msgAddrs);

        address signerAddrs = ecrecover(msgHash, v, r, s);

        if (notary != signerAddrs){
        result = 2;
        return false;
        }

        result = 1;
        return true;

    }
}

