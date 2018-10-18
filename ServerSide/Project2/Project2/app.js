const readline = require("readline-sync");
let warehouse = require("./lib/warehouse")();
let Lot = require("./lib/lot");

let config = require("./config");

let action = getActionChoice();
warehouse.getWarehouseInventory();
// Keep looping as long as they don't want to exit the program
while (action !== 3) {
  if (action == 1) {

    var amount = 0;

    while(amount < 1 || isNaN(amount)) {
      amount = readline.questionInt("Please enter the amount for this lot: ");
    }

    var notes = readline.question("Please enter any notes for this lot: ");

    //creates lot
    const thisLot = buildLot(amount, notes);
    const location = getLocation();

    //adds lot to location if valid
    if (warehouse.addLot(thisLot, location.row, location.square)) {
      console.log(`Lot ${thisLot.id} has been added with ${thisLot.amount} inventory at location ${location.row}, ${location.square}`);
    } else {
      console.log(`Sorry, I am unable to add the lot to location ${location.row}, ${location.square}.`);
    }

  } if (action === 2) {
    // Ask the user where the current lot is, then look up a reference to that lot
    const location = getLocation();
    let sourceLot = warehouse.getLot(location.row, location.square);
    if (sourceLot && sourceLot.amount > 0) {

      // Ask the user how much they want to ship, then generate a new shipping lot and ship it
      const shippingAmount = getShipAmount(sourceLot.amount);
      let shippingLot = sourceLot.shipInventory(shippingAmount);

      //ships lot (adds to outbound inventory)
      warehouse.shipLot(shippingLot);
      warehouse.updateLotAmount(shippingLot.amount, location.row, location.square);

      console.log(`Lot ${shippingLot.id} has been shipped with ${shippingAmount} inventory.`);
    } else {
      console.log(`Sorry, a lot with inventory can not be found at location ${location.row}, ${location.square}.`);
    }

  } if (action === 3) {
    break;
  }
  action = getActionChoice();
}
process.exit(0);

// Gives the user a menu and gets their chosen action
function getActionChoice() {

  console.log("Please select an action.");
  console.log("1. Add Inventory");
  console.log("2. Ship Inventory");
  console.log("3. Quit Application");
  selection = readline.questionInt("Selection: ");
  return selection;

}

// Interacts with the user to build a new lot object
function buildLot(newAmount, newNotes) {
  // Return a new lot object based on this input
  var lot = new Lot(newAmount, newNotes);
  return lot;
  // make sure they enter a valid amount
  // Get the notes as well
  // Return a new lot object based on this input
}

// Interacts with the user to ask how much they want to ship
function getShipAmount(currentAmount) {
  // Make sure the user enters a valid number that isn't greater than the amount that can be shipped
  var amountToShip = readline.questionInt("How much would you like to ship from the lot: ");

  if(amountToShip > currentAmount) {
    amountToShip = getShipAmount(currentAmount);
  }
  return amountToShip;
}

// Interacts with the user to ask where a lot should be, returns an object with row and location numbers
function getLocation(row, square) {

  var row = readline.questionInt("What row for this lot: ");
  var square = readline.questionInt("What square for this lot: ");
  
  location = {
    row: row,
    square: square
  };
  
  return location;
}