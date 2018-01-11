# << web3j-sample >>

## --> some essentials tools <--

 To compile Contract file and generate abi and bin files

>> solc < contract >.sol --bin --abi --optimize -o <output-dir>


 To generate  wrapper class of the compiled contract files

>> web3j solidity generate <smart-contract>.bin <smart-contract>.abi -o /path/to/src/main/java -p packagename


To create a rinkeby test wallet

>> web3j wallet create

To run geth client on your machine

>> geth --rpcapi personal,db,eth,net,web3 --rpc --rinkeby


 Get your private key from here
>> https://www.myetherwallet.com/#view-wallet-info


 Add some test ether to your wallet
>> https://faucet.rinkeby.io/


 Check your test ether balance
>> https://rinkeby.etherscan.io/error








