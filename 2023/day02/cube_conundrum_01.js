const fs = require("fs")

const data = fs.readFileSync("data.txt", "utf8").split("\r\n")
const maxCubes = { red: 12, green: 13, blue: 14 }

const testData = [
  "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
  "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
  "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
  "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
  "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
]

function solvePuzzle(dataSet) {
  return dataSet.reduce((sum, line) => {
    const game = line.match(/Game (\d+):/)
    const gameId = parseInt(game[1])
    const entries = line.split(/[:,;]/).slice(1)

    for (let entry of entries) {
      const [amount, color] = entry.trim().split(" ")
      if (parseInt(amount) > maxCubes[color]) return sum
    }

    return sum + gameId
  }, 0)
}

console.log(solvePuzzle(data))