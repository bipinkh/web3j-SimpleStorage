pragma solidity ^0.4.0;

contract SetGetBC {
    uint storedData;
    
    function set(uint a){
        storedData = a;
    }
    
    function get() constant returns (uint){
        return storedData;
    }
}
