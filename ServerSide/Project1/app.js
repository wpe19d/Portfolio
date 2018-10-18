const STUD_SPACING = 16/12;
const STUD_LENGTH = 8;
const PLYWOOD_COST = 19.85;
const STUD_COST = 3.27;
const DOOR_COST = 631.28;
const WINDOW_COST = 121.17;

var readLine = require('readline-sync');
var area = 0;

let shoppingList = {
    plyWoodCost: 0,
    plyWoodAmt: 0,
    studCost: 0,
    studAmt: 0,
    windowCost: 0,
    windowAmt: 0,
    doorCost: DOOR_COST,
    totalCost: 0
};

//Asks user for the length of the wall.
while(!length || isNaN(length) || length < 6 || length > 20) {

    var length = readLine.question("\nWhat is the length of the shed? (enter a number between 6 and 20): ");    
}

//Asks user for the width of the wall.
while(!width || isNaN(width) || width < 6 || width > 20 ) {

    var width = readLine.question("What is the width of the shed? (enter a number between 6 and 20): ");
}

//Asks user for the number of windows to be installed.
while(!numberOfWindows || isNaN(numberOfWindows) || numberOfWindows < 0 || numberOfWindows > 3) {

    var numberOfWindows = readLine.question("How many windows will be installed? (enter a number between 0 and 3): ");
    shoppingList.windowAmt = parseInt(numberOfWindows);
}

getNetArea(length, width, numberOfWindows, area);
getPlyWoodAmount();
getNumberOfStuds(length, width);

//calculates and returns the net area of all the walls.
function getNetArea(length, width, numberOfWindows) {
    this.area = (getWallArea(length, width) - (numberOfWindows * getWindowArea()) - (getDoorArea()));
    return this.area;
}

//returns the gross area of all the walls.
function getWallArea(length, width) {
    
    this.area = (((length * 8) * 2) + ((width * 8) * 2));
    return this.area;
}

//returns the area of the window in feet.
function getWindowArea() {
    var windowArea = ((18/12) * (27/12));
    return Math.ceil(windowArea);
}

//returns the area of the door in feet.
function getDoorArea() {
    var doorArea =  ((72/12) * (80/12)); 
    return Math.ceil(doorArea);
}

//calculates and returns the number of plywood sheets needed for the shed.
function getPlyWoodAmount() {
    shoppingList.plyWoodAmt = Math.ceil(this.area / (4 * 8));
}

//calculates and returns the number of studs needed for the shed.
function getNumberOfStuds(length, width) {
    var studTotal = (Math.ceil(length / STUD_SPACING) + 1) * 2;
    studTotal += (Math.ceil(width / STUD_SPACING) + 1) * 2;
    studTotal += (Math.ceil(length / STUD_LENGTH)) * 4;
    studTotal += (Math.ceil(width / STUD_LENGTH)) * 4;
    shoppingList.studAmt = studTotal;
}

shoppingList.plyWoodCost = parseFloat(parseFloat(shoppingList.plyWoodAmt * PLYWOOD_COST).toFixed(2));
shoppingList.studCost = parseFloat(parseFloat(shoppingList.studAmt * STUD_COST).toFixed(2));
shoppingList.windowCost = parseFloat(parseFloat(shoppingList.windowAmt * WINDOW_COST).toFixed(2));

//prints out list shopping for each item including the price
console.log("\nHere's the shopping list for this shed: \n--------------------------------------\n");
console.log("Windows:        " + numberOfWindows + " - $" + `${shoppingList.windowCost}`);
console.log("Door:           " + "1" + " - $" + `${DOOR_COST}`);
console.log(`Plywood Sheets: ${shoppingList.plyWoodAmt} - ${shoppingList.plyWoodCost}`);
console.log(`Studs:          ${shoppingList.studAmt} - ${shoppingList.studCost}`);

//calculates the total cost
shoppingList.totalCost = shoppingList.windowCost + DOOR_COST + shoppingList.plyWoodCost + shoppingList.studCost;

//prints out the total cost
console.log(`Total Cost:          $${shoppingList.totalCost}\n\n`);