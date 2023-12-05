const fs = require("fs")

class Object {
    constructor(destinationRange, sourceRange, length) {
        this.destinationRange = destinationRange
        this.sourceRange = sourceRange
        this.length = length
    }
}

function readAndSplitArray() {
    let data = fs.readFileSync("data.txt", "utf8").split("\r\n")
    const array = data.filter(function(entry) { return entry.trim() !== ""; });
    const seedsText = array.at(0).replace("seeds: ", "").split(" ")
    let fullArray = [];
    fullArray.push(seedsText);
    let tempArray = []
    for (let i = 2; i < array.length; i++){
        const line = array.at(i).split(" ");
        if (isNaN(parseInt(line[0])) || i === array.length - 1) {
            if (i === array.length - 1) {
                tempArray.push(new Object(parseInt(line[0]), parseInt(line[1]), parseInt(line[2])))
            }
            fullArray.push(tempArray)
            tempArray = []
            continue
        }
        tempArray.push(new Object(parseInt(line[0]), parseInt(line[1]), parseInt(line[2])))
    }
    return fullArray
}

function solvePuzzle(array) {
    array = readAndSplitArray()
    for (let i = 1; i < array.length; i++) {
        for (let j = 0; j < array[0].length; j++){
            for (let k = 0; k < array[i].length; k++){
                if (array[i][k].sourceRange <= array[0][j]
                    && array[0][j] <= (array[i][k].sourceRange + (array[i][k].length - 1))){
                    array[0][j] = array[i][k].destinationRange + (array[0][j] - array[i][k].sourceRange)
                    break
                }
            }
        }
    }
    return Math.min(...array[0])
}

console.log(solvePuzzle())


