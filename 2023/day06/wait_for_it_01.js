const fs = require("fs")

function solvePuzzle() {
    const data = fs.readFileSync("data.txt", "utf8")
        .match(/\d+/g)
    let sum = 1
    for (let i = 0; i < data.length/2; i++) {
        let currentWaysToWin = 0
        const time = data[i]
        const distanceRecord = data[i + data.length/2]
        for (let j = 1; j < time; j++){
            const newDistance = j * (time-j)
            if (newDistance > distanceRecord) currentWaysToWin++
        }
        sum *= currentWaysToWin
    }
    return sum
}

console.log(solvePuzzle())