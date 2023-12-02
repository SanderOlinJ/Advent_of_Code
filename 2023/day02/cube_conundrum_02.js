var fs = require("fs")
var dataSet = fs.readFileSync("data.txt").toString().split("\r\n")

const testData = [
    "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
    "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
    "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
    "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
    "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
]


let minimumAmountOfCubes = [
    {
        "color": "red",
        "amount": 0
    },
    {
        "color": "green",
        "amount": 0
    },
    {
        "color": "blue",
        "amount": 0
    }
]

function refreshArray() {
    for (let i in minimumAmountOfCubes) {
        minimumAmountOfCubes[i].amount = 0
    }
}


function solvePuzzle(array){
    let totalSum = 0;
    for (let i in array) {
        refreshArray()
        let stringToParse = array[i]
        stringToParse = stringToParse.substring(stringToParse.indexOf(":") + 2)
        while (stringToParse.length > 0){

            let temp = stringToParse
            if (stringToParse.includes(";")){
                temp = stringToParse.substring(0, stringToParse.indexOf(";"))
            }
            while (temp.length > 0){
                let temp2 = temp
                if (temp.includes(",")){
                    temp2 = temp.substring(0, temp.indexOf(","))
                }
                let amountOfCubes = temp2.replace(/\D/g, "")
                let color = temp2.replace(/^[\s\d]+/, "")
                for (let i in minimumAmountOfCubes) {
                    if (color === minimumAmountOfCubes[i].color){
                        if (parseInt(amountOfCubes) <= minimumAmountOfCubes[i].amount){
                            continue
                        }
                        minimumAmountOfCubes[i].amount = amountOfCubes
                    }
                }
                if (temp.includes(",")){
                    temp = temp.substring(temp.indexOf(",") + 2)
                } else {
                    temp = ""
                }
            }
            if (stringToParse.includes(";")) {
                stringToParse = stringToParse.substring(stringToParse.indexOf(";") + 2)
            } else {
                stringToParse = ""
            }
        }
        let tempSum = 1
        for (let i in minimumAmountOfCubes) {
            tempSum *= minimumAmountOfCubes[i].amount
        }
        totalSum += tempSum
    }
    return totalSum
}

console.log(solvePuzzle(dataSet))