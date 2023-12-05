const fs = require("fs")

class Object {
    constructor(destinationRange, sourceRange, length) {
        this.destinationRange = destinationRange
        this.sourceRange = sourceRange
        this.length = length
    }
}

const readSplitAndSolve = () => {
    const data = fs.readFileSync("data.txt", "utf8")
        .split("\r\n").filter(entry => entry.trim() !== "")
    const seedsText = data[0].replace("seeds: ", "").split(" ")

    let min = Infinity
    const fullArray = []
    fullArray[0] = min
    let tempArray = []

    data.slice(2).forEach((line, index) => {
        const [destinationRange, sourceRange, length] = line.split(" ").map(Number)
        if (isNaN(destinationRange) || index === data.length - 3) {
            fullArray.push([...tempArray])
            tempArray = []
        }
        else {
            tempArray.push(new Object(destinationRange, sourceRange, length))
        }
    })

    for (let i = 0; i < seedsText.length; i+=2) {
        console.log(i)
        const seedStart = parseInt(seedsText[i])
        const seedEnd = seedStart + parseInt(seedsText[i+1])
        for (let j = seedStart; j < seedEnd; j++){
            fullArray[0] = j
            min = Math.min(min, solvePuzzle(fullArray))
        }
    }
    return min
}

function solvePuzzle(array) {
    for (let i = 1; i < array.length; i++) {
        for (let j = 0; j < array[i].length; j++){
            if (array[i][j].sourceRange <= array[0]
                && array[0] <= (array[i][j].sourceRange + (array[i][j].length - 1))){
                array[0] = array[i][j].destinationRange + (array[0] - array[i][j].sourceRange)
                break
            }
        }
    }
    return array[0]
}

console.log(readSplitAndSolve())
