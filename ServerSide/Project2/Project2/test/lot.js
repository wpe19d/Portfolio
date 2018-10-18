let expect = require('chai').expect;

let Lot = require("../lib/lot");

// Tests for the lot object
describe('Lot', function () {

  it("Has a self-generated id", function () {
    var lot = new Lot(5, 'stuff');
    expect(lot).to.have.property('id').to.not.be.undefined;
  });

  it("Only allows a positive number for amount", function () {
    var lot = new Lot(-5, 'stuff');
    expect(lot).to.have.property('amount').to.be.equal(0);
  });

  it("Will have an empty string if no notes are passed in", function () {
    var lot = new Lot(5);
    expect(lot).to.have.property('notes').to.be.equal('');
  });

  it("Allows inventory to be shipped out", function () {
    var lot = new Lot(5, 'stuff');
    expect(lot.shipInventory(4)).to.be.an('object');
  });

  it("Requires a positive inventory number to be shipped out", function () {
    var lot = new Lot(5, 'stuff');
    expect(lot.shipInventory(-4)).to.be.false;
  });

  it("Will not allow excess inventory to be shipped out", function () {
    var lot = new Lot(5, 'stuff');
    expect(lot.shipInventory(6)).to.be.false;
  });

  it("Returns a new Lot object when inventory is shipped", function () {
    var lot = new Lot(5, 'stuff');
    expect(lot.shipInventory(4)).to.be.an('object');
  });

});