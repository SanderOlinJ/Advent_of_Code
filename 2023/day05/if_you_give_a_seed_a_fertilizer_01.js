const fs = require("fs")

class Object {
    constructor(destinationRange, sourceRange, length) {
        this.destinationRange = destinationRange
        this.sourceRange = sourceRange
        this.length = length
    }
}

const readAndSplitArray = () => {
    const data = fs.readFileSync("data.txt", "utf8")
        .split("\r\n").filter(entry => entry.trim() !== "")
    const seedsText = data[0].replace("seeds: ", "").split(" ")
    const fullArray = [seedsText]
    let tempArray = []

    data.forEach((line, index) => {
        const [destinationRange, sourceRange, length] = line.split(" ").map(Number)
        if (isNaN(destinationRange) || index === data.length - 3) {
            fullArray.push([...tempArray])
            tempArray = []
        }
        else {
            tempArray.push(new Object(destinationRange, sourceRange, length))
        }
    })
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


