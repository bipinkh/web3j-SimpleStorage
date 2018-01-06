contract Deliverables {
  address public owner;
  mapping (address => Deliverable) deliverables;

  struct Deliverable {
    address id;
    uint status;
  }

  modifier onlyByOwner() {
    if (msg.sender != owner) throw;
    _;
  }

  function Deliverables() {
    owner = msg.sender;
  }

  function store(address id) onlyByOwner {
    deliverables[id] = Deliverable(id, 0);
  }

  function statusFor(address id) constant returns(uint status){
    return deliverables[id].status;
  }

  function delivered(address id) onlyByOwner {
    deliverables[id].status = 1;
  }
}