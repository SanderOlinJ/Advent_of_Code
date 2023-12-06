const fs = require("fs")

function solvePuzzle() {
    const data = fs.readFileSync("data.txt", "utf8")
        .split("\n")
        .map(line => line.replace(/[^\d+]/g, ""))
        .filter(entry => entry.trim() !== "")

    const time = parseInt(data[0])
    const distance = parseInt(data[1])
    let currentWaysToWin = 0
    for (let i = 1; i < time; i++){
        const newDistance = i * (time-i)
        if (newDistance > distance) {
            currentWaysToWin++
        }
    }
    return currentWaysToWin
}
console.log(solvePuzzle())