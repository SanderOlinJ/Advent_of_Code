var fs = require("fs")
var data = fs.readFileSync("data.txt", "utf-8").split("\r\n")

const testData = [
    "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
    "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
    "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
    "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
    "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
]

function solvePuzzle(dataSet){
    return dataSet.reduce((sum, line) => {
        line = line.substring(line.indexOf(":")+1)
        const entries = line.split(/[:,;]/)
        const minCubes = { red: 0, green: 0, blue: 0 }

        for (let entry of entries) {
            const [amount, color] = entry.trim().split(" ")
            minCubes[color] = Math.max(minCubes[color], parseInt(amount))
        }

        let product = 1
        for (let i in minCubes) {
            product *= minCubes[i]
        }

        return sum + product
    }, 0)
}

console.log(solvePuzzle(data))