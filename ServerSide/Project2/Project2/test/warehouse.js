let expect = require('chai').expect;



// Need a lot object to add to the warehouse
let Lot = require("../lib/lot");
let warehouse = require("../lib/warehouse")();

// Tests for the warehouse object
describe('Warehouse', function () {

  it("Contains warehouse inventory", function () {
     expect(warehouse.getWarehouseInventory()).to.be.an.instanceof(Array);
  });

  it("Contains outbound inventory", function () {
    expect(warehouse.getOutboundInventory()).to.be.an.instanceof(Array);
  });

  it("Lets you add and retrieve a lot in inventory", function () {
    var lot = new Lot(5, 'stuff');
    warehouse.addLot(lot, 5, 5);
    expect(warehouse.getLot(5, 5)).to.be.an.instanceof(Lot);
  });

  it("Will return false if you try to add to an invalid inventory location", function () {
    var lot = new Lot(5, 'stuff');
    expect(warehouse.addLot(lot, 35, 35)).to.be.false;
    warehouse.addLot(lot, 5, 5);
    expect(warehouse.addLot(lot, 5, 5)).to.be.false;
  });

  it("Will return undefined if you retrieve a non-existent lot from inventory", function () {
    expect(warehouse.getLot(10, 5)).to.be.undefined;
  });

  it("Lets you ship a lot", function () {
    warehouse.shipLot(warehouse.getLot(5, 5));
    expect(warehouse.getOutboundInventory()).to.be.lengthOf(1);
  });
});