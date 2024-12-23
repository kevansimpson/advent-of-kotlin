# Advent of Code (Kotlin)
Solutions for various [Advent of Code](https://adventofcode.com/) in Java.<br/>
The tables below contain links for solutions in other languages to demonstrate proficiency,
but mostly shows my learning curve and that, even in my spare time, there will be tests and
measurements to ensure performant code. ;-)

## Run Solutions
To run expedited solutions:
```
mvn verify
```

To run full solutions:
```
mvn verify -Dfull=true
```

## Comparisons
Solution comparisons by year and language:
[2015](#2015) - [2016](#2016) - [2019](#2019) - [2022](#2022) - [2023](#2023) - [2024](#2024)

### Sans Comparison
| Year | Language | Notes                                                           |
|------|----------|-----------------------------------------------------------------|
| 2017 | Ruby     | Pushed to repository at work like a numbskull, then lost access |
| 2018 | Node     | https://github.com/kevansimpson/advent-of-node                  |
| 2020 | Scala    | https://github.com/kevansimpson/advent-of-scala                 |
| 2021 | Kotlin   | https://github.com/kevansimpson/advent-of-kotlin                |

### 2015

| Description                                   | Java                                                                                                                | Kotlin                                                                                                             | Ruby                                                                          | NodeJS                                                                                | Golang                                                                           |
|-----------------------------------------------|---------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------|---------------------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| [Day01](https://adventofcode.com/2015/day/1)  | [Day01](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day01.java) | [Day01](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day01.kt) | [Day01](https://github.com/kevansimpson/aoc2015ruby/blob/master/lib/day01.rb) | [Day01](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day01.ts) | [Day01](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day01.go) |
| [Day02](https://adventofcode.com/2015/day/2)  | [Day02](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day02.java) | [Day02](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day02.kt) | [Day02](https://github.com/kevansimpson/aoc2015ruby/blob/master/lib/day02.rb) | [Day02](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day02.ts) | [Day02](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day02.go) |
| [Day03](https://adventofcode.com/2015/day/3)  | [Day03](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day03.java) | [Day03](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day03.kt) | [Day03](https://github.com/kevansimpson/aoc2015ruby/blob/master/lib/day03.rb) | [Day03](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day03.ts) | [Day03](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day03.go) |
| [Day04](https://adventofcode.com/2015/day/4)  | [Day04](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day04.java) | [Day04](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day04.kt) | [Day04](https://github.com/kevansimpson/aoc2015ruby/blob/master/lib/day04.rb) | [Day04](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day04.ts) | [Day04](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day04.go) |
| [Day05](https://adventofcode.com/2015/day/5)  | [Day05](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day05.java) | [Day05](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day05.kt) | [Day05](https://github.com/kevansimpson/aoc2015ruby/blob/master/lib/day05.rb) | [Day05](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day05.ts) | [Day05](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day05.go) |
| [Day06](https://adventofcode.com/2015/day/6)  | [Day06](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day06.java) | [Day06](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day06.kt) | [Day06](https://github.com/kevansimpson/aoc2015ruby/blob/master/lib/day06.rb) | [Day06](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day06.ts) | [Day06](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day06.go) |
| [Day07](https://adventofcode.com/2015/day/7)  | [Day07](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day07.java) | [Day07](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day07.kt) | [Day07](https://github.com/kevansimpson/aoc2015ruby/blob/master/lib/day07.rb) | [Day07](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day07.ts) | [Day07](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day07.go) |
| [Day08](https://adventofcode.com/2015/day/8)  | [Day08](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day08.java) | [Day08](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day08.kt) | [Day08](https://github.com/kevansimpson/aoc2015ruby/blob/master/lib/day08.rb) | [Day08](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day08.ts) | [Day08](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day08.go) |
| [Day09](https://adventofcode.com/2015/day/9)  | [Day09](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day09.java) | [Day09](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day09.kt) | [Day09](https://github.com/kevansimpson/aoc2015ruby/blob/master/lib/day09.rb) | [Day09](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day09.ts) | [Day09](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day09.go) |
| [Day10](https://adventofcode.com/2015/day/10) | [Day10](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day10.java) | [Day10](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day10.kt) | [Day10](https://github.com/kevansimpson/aoc2015ruby/blob/master/lib/day10.rb) | [Day10](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day10.ts) | [Day10](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day10.go) |
| [Day11](https://adventofcode.com/2015/day/11) | [Day11](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day11.java) | [Day11](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day11.kt) | [Day11](https://github.com/kevansimpson/aoc2015ruby/blob/master/lib/day11.rb) | [Day11](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day11.ts) | [Day11](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day11.go) |
| [Day12](https://adventofcode.com/2015/day/12) | [Day12](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day12.java) | [Day12](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day12.kt) | [Day12](https://github.com/kevansimpson/aoc2015ruby/blob/master/lib/day12.rb) | [Day12](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day12.ts) | [Day12](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day12.go) |
| [Day13](https://adventofcode.com/2015/day/13) | [Day13](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day13.java) | [Day13](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day13.kt) | [Day13](https://github.com/kevansimpson/aoc2015ruby/blob/master/lib/day13.rb) | [Day13](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day13.ts) | [Day13](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day13.go) |
| [Day14](https://adventofcode.com/2015/day/14) | [Day14](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day14.java) | [Day14](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day14.kt) | [Day14](https://github.com/kevansimpson/aoc2015ruby/blob/master/lib/day14.rb) | [Day14](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day14.ts) | [Day14](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day14.go) |
| [Day15](https://adventofcode.com/2015/day/15) | [Day15](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day15.java) | [Day15](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day15.kt) | [Day15](https://github.com/kevansimpson/aoc2015ruby/blob/master/lib/day15.rb) | [Day15](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day15.ts) | [Day15](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day15.go) |
| [Day16](https://adventofcode.com/2015/day/16) | [Day16](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day16.java) | [Day16](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day16.kt) | [Day06](https://github.com/kevansimpson/aoc2015ruby/blob/master/lib/day16.rb) | [Day16](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day16.ts) | [Day16](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day16.go) |
| [Day17](https://adventofcode.com/2015/day/17) | [Day17](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day17.java) | [Day17](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day17.kt) | [Day17](https://github.com/kevansimpson/aoc2015ruby/blob/master/lib/day17.rb) | [Day17](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day17.ts) | [Day17](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day17.go) |
| [Day18](https://adventofcode.com/2015/day/18) | [Day18](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day18.java) | [Day18](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day18.kt) | [Day18](https://github.com/kevansimpson/aoc2015ruby/blob/master/lib/day18.rb) | [Day18](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day18.ts) | [Day18](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day18.go) |
| [Day19](https://adventofcode.com/2015/day/19) | [Day19](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day19.java) | [Day19](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day19.kt) | n/a                                                                           | [Day19](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day19.ts) | [Day19](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day19.go) |
| [Day20](https://adventofcode.com/2015/day/20) | [Day20](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day20.java) | [Day20](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day20.kt) | n/a                                                                           | [Day20](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day20.ts) | [Day20](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day20.go) |
| [Day21](https://adventofcode.com/2015/day/21) | [Day21](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day21.java) | [Day21](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day21.kt) | n/a                                                                           | [Day21](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day21.ts) | [Day21](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day21.go) |
| [Day22](https://adventofcode.com/2015/day/22) | [Day22](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day22.java) | [Day22](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day22.kt) | n/a                                                                           | [Day23](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day22.ts) | [Day22](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day22.go) |
| [Day23](https://adventofcode.com/2015/day/23) | [Day23](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day23.java) | [Day23](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day23.kt) | n/a                                                                           | [Day23](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day23.ts) | [Day23](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day23.go) |
| [Day24](https://adventofcode.com/2015/day/24) | [Day24](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day24.java) | [Day24](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day24.kt) | n/a                                                                           | [Day24](https://github.com/kevansimpson/advent-of-node/blob/master/src/2015/day24.ts) | [Day24](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day24.go) |
| [Day25](https://adventofcode.com/2015/day/25) | [Day25](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2015/Day25.java) | [Day24](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2015/Day24.kt) | n/a                                                                           | n/a                                                                                   | [Day25](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2015/day25.go) |

[Back to top](#comparisons)

### 2016

| Description                                   | Java                                                                                                                | Kotlin                                                                                                             | Golang                                                                           |
|-----------------------------------------------|---------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| [Day01](https://adventofcode.com/2016/day/1)  | [Day01](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2016/Day01.java) | [Day01](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2016/Day01.kt) | [Day01](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2016/day01.go) |
| [Day02](https://adventofcode.com/2016/day/2)  | [Day02](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2016/Day02.java) | [Day02](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2016/Day02.kt) | [Day02](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2016/day02.go) |
| [Day03](https://adventofcode.com/2016/day/3)  | [Day03](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2016/Day03.java) | [Day03](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2016/Day03.kt) | [Day03](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2016/day03.go) |
| [Day04](https://adventofcode.com/2016/day/4)  | [Day04](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2016/Day04.java) | [Day04](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2016/Day04.kt) | [Day04](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2016/day04.go) |
| [Day05](https://adventofcode.com/2016/day/5)  | [Day05](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2016/Day05.java) | [Day05](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2016/Day05.kt) | [Day05](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2016/day05.go) |
| [Day06](https://adventofcode.com/2016/day/6)  | [Day06](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2016/Day06.java) | [Day06](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2016/Day06.kt) | [Day06](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2016/day06.go) |
| [Day07](https://adventofcode.com/2016/day/7)  | [Day07](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2016/Day07.java) | [Day07](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2016/Day07.kt) | [Day07](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2016/day07.go) |
| [Day08](https://adventofcode.com/2016/day/8)  | [Day08](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2016/Day08.java) | [Day08](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2016/Day08.kt) | [Day08](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2016/day08.go) |
| [Day09](https://adventofcode.com/2016/day/9)  | [Day09](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2016/Day09.java) | [Day09](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2016/Day09.kt) | [Day09](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2016/day09.go) |
| [Day10](https://adventofcode.com/2016/day/10) | [Day10](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2016/Day10.java) | [Day10](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2016/Day10.kt) | [Day10](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2016/day10.go) |

[Back to top](#comparisons)

### 2019

| Description                                   | Java                                                                                                                | Kotlin                                                                                                             | Golang                                                                           |
|-----------------------------------------------|---------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| [Day01](https://adventofcode.com/2019/day/1)  | [Day01](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2019/Day01.java) | [Day01](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2019/Day01.kt) | [Day01](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2016/day01.go) |
| [Day02](https://adventofcode.com/2019/day/2)  | [Day02](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2019/Day02.java) | [Day02](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2019/Day02.kt) | [Day02](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2016/day02.go) |
| [Day03](https://adventofcode.com/2019/day/3)  | [Day03](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2019/Day03.java) | [Day03](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2019/Day03.kt) | [Day03](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2016/day03.go) |
| [Day04](https://adventofcode.com/2019/day/4)  | [Day04](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2019/Day04.java) | [Day04](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2019/Day04.kt) | [Day04](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2016/day04.go) |
| [Day05](https://adventofcode.com/2019/day/5)  | [Day05](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2019/Day05.java) | [Day05](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2019/Day05.kt) | [Day05](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2016/day05.go) |
| [Day06](https://adventofcode.com/2019/day/6)  | [Day06](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2019/Day06.java) | [Day06](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2019/Day06.kt) | [Day06](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2016/day06.go) |
| [Day07](https://adventofcode.com/2019/day/7)  | [Day07](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2019/Day07.java) | [Day07](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2019/Day07.kt) | [Day07](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2016/day07.go) |
| [Day08](https://adventofcode.com/2019/day/8)  | [Day08](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2019/Day08.java) | [Day08](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2019/Day08.kt) | [Day08](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2016/day08.go) |
| [Day09](https://adventofcode.com/2019/day/9)  | [Day09](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2019/Day09.java) | [Day09](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2019/Day09.kt) | [Day09](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2016/day09.go) |
| [Day10](https://adventofcode.com/2019/day/10) | [Day10](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2019/Day10.java) | [Day10](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2019/Day10.kt) | [Day10](https://github.com/kevansimpson/adventgo/blob/main/pkg/aoc2016/day10.go) |

[Back to top](#comparisons)

### 2022

| Description                                   | Java                                                                                                                | Kotlin                                                                                                             |
|-----------------------------------------------|---------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------|
| [Day01](https://adventofcode.com/2022/day/1)  | [Day01](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2022/Day01.java) | [Day01](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2022/Day01.kt) |
| [Day02](https://adventofcode.com/2022/day/2)  | [Day02](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2022/Day02.java) | [Day02](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2022/Day02.kt) |
| [Day03](https://adventofcode.com/2022/day/3)  | [Day03](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2022/Day03.java) | [Day03](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2022/Day03.kt) |
| [Day04](https://adventofcode.com/2022/day/4)  | [Day04](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2022/Day04.java) | [Day04](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2022/Day04.kt) |
| [Day05](https://adventofcode.com/2022/day/5)  | [Day05](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2022/Day05.java) | [Day05](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2022/Day05.kt) |
| [Day06](https://adventofcode.com/2022/day/6)  | [Day06](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2022/Day06.java) | [Day06](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2022/Day06.kt) |
| [Day07](https://adventofcode.com/2022/day/7)  | [Day07](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2022/Day07.java) | [Day07](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2022/Day07.kt) |
| [Day08](https://adventofcode.com/2022/day/8)  | [Day08](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2022/Day08.java) | [Day08](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2022/Day08.kt) |
| [Day09](https://adventofcode.com/2022/day/9)  | [Day09](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2022/Day09.java) | [Day09](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2022/Day09.kt) |
| [Day10](https://adventofcode.com/2022/day/10) | [Day10](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2022/Day10.java) | [Day10](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2022/Day10.kt) |
| [Day11](https://adventofcode.com/2022/day/11) | [Day11](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2022/Day11.java) | [Day11](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2022/Day11.kt) |
| [Day12](https://adventofcode.com/2022/day/12) | [Day12](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2022/Day12.java) | [Day12](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2022/Day12.kt) |
| [Day13](https://adventofcode.com/2022/day/13) | [Day13](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2022/Day13.java) | [Day13](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2022/Day13.kt) |
| [Day14](https://adventofcode.com/2022/day/14) | [Day14](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2022/Day14.java) | [Day14](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2022/Day14.kt) |
| [Day15](https://adventofcode.com/2022/day/15) | [Day15](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2022/Day15.java) | [Day15](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2022/Day15.kt) |
| [Day16](https://adventofcode.com/2022/day/16) | [Day16](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2022/Day16.java) | [Day16](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2022/Day16.kt) |
| [Day17](https://adventofcode.com/2022/day/17) | [Day17](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2022/Day17.java) | [Day17](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2022/Day17.kt) |

[Back to top](#comparisons)

### 2023

| Description                                  | Java                                                                                                                | Kotlin                                                                                                                     |
|----------------------------------------------|---------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------|
| [Day01](https://adventofcode.com/2023/day/1) | [Day01](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2023/Day01.java) | [Day01](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2023/Day01.kt)         |
| [Day02](https://adventofcode.com/2023/day/2) | [Day02](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2023/Day02.java) | [Day02](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2023/Day02.kt)         |
| [Day03](https://adventofcode.com/2023/day/3) | [Day03](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2023/Day03.java) | [Day03, Part 1](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2023/Day03.kt) |
| [Day04](https://adventofcode.com/2023/day/4) | [Day04](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2023/Day04.java) | [Day04](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2023/Day04.kt)         |
| [Day05](https://adventofcode.com/2023/day/5) | [Day05](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2023/Day05.java) | [Day05](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2023/Day05.kt)         |
| [Day06](https://adventofcode.com/2023/day/6) | [Day06](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2023/Day06.java) | [Day06](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2023/Day06.kt)         |
| [Day07](https://adventofcode.com/2023/day/7) | [Day07](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2023/Day07.java) | [Day07](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2023/Day07.kt)         |

[Back to top](#comparisons)

### 2024

| Description                                   | Java                                                                                                                | Kotlin                                                                                                             | Typescript/NodeJS                                                                     |
|-----------------------------------------------|---------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------|
| [Day01](https://adventofcode.com/2024/day/1)  | [Day01](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2024/Day01.java) | [Day01](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2024/Day01.kt) | [Day01](https://github.com/kevansimpson/advent-of-node/blob/master/src/2024/day01.ts) |
| [Day02](https://adventofcode.com/2024/day/2)  | [Day02](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2024/Day02.java) | [Day02](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2024/Day02.kt) | [Day02](https://github.com/kevansimpson/advent-of-node/blob/master/src/2024/day02.ts) |
| [Day03](https://adventofcode.com/2024/day/3)  | [Day03](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2024/Day03.java) | [Day03](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2024/Day03.kt) | [Day03](https://github.com/kevansimpson/advent-of-node/blob/master/src/2024/day03.ts) |
| [Day04](https://adventofcode.com/2024/day/4)  | [Day04](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2024/Day04.java) | [Day04](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2024/Day04.kt) | [Day04](https://github.com/kevansimpson/advent-of-node/blob/master/src/2024/day04.ts) |
| [Day05](https://adventofcode.com/2024/day/5)  | [Day05](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2024/Day05.java) | [Day05](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2024/Day05.kt) | [Day05](https://github.com/kevansimpson/advent-of-node/blob/master/src/2024/day05.ts) |
| [Day06](https://adventofcode.com/2024/day/6)  | [Day06](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2024/Day06.java) | [Day06](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2024/Day06.kt) | [Day06](https://github.com/kevansimpson/advent-of-node/blob/master/src/2024/day06.ts) |
| [Day07](https://adventofcode.com/2024/day/7)  | [Day07](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2024/Day07.java) | [Day07](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2024/Day07.kt) | [Day07](https://github.com/kevansimpson/advent-of-node/blob/master/src/2024/day07.ts) |
| [Day08](https://adventofcode.com/2024/day/8)  | [Day08](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2024/Day08.java) | [Day08](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2024/Day08.kt) | [Day08](https://github.com/kevansimpson/advent-of-node/blob/master/src/2024/day08.ts) |
| [Day09](https://adventofcode.com/2024/day/9)  | [Day09](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2024/Day09.java) | [Day09](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2024/Day09.kt) | [Day09](https://github.com/kevansimpson/advent-of-node/blob/master/src/2024/day09.ts) |
| [Day10](https://adventofcode.com/2024/day/10) | [Day10](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2024/Day10.java) | [Day10](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2024/Day10.kt) | [Day10](https://github.com/kevansimpson/advent-of-node/blob/master/src/2024/day10.ts) |
| [Day11](https://adventofcode.com/2024/day/11) | [Day11](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2024/Day11.java) | [Day11](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2024/Day11.kt) | [Day11](https://github.com/kevansimpson/advent-of-node/blob/master/src/2024/day11.ts) |
| [Day12](https://adventofcode.com/2024/day/12) | [Day12](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2024/Day12.java) | [Day12](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2024/Day12.kt) | [Day12](https://github.com/kevansimpson/advent-of-node/blob/master/src/2024/day12.ts) |
| [Day13](https://adventofcode.com/2024/day/13) | [Day13](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2024/Day13.java) | [Day13](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2024/Day13.kt) | [Day13](https://github.com/kevansimpson/advent-of-node/blob/master/src/2024/day13.ts) |
| [Day14](https://adventofcode.com/2024/day/14) | [Day14](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2024/Day14.java) | [Day14](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2024/Day14.kt) | [Day14](https://github.com/kevansimpson/advent-of-node/blob/master/src/2024/day14.ts) |
| [Day15](https://adventofcode.com/2024/day/15) | [Day15](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2024/Day15.java) | [Day15](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2024/Day15.kt) | [Day15](https://github.com/kevansimpson/advent-of-node/blob/master/src/2024/day15.ts) |
| [Day16](https://adventofcode.com/2024/day/16) | [Day16](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2024/Day16.java) | [Day16](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2024/Day16.kt) | [Day16](https://github.com/kevansimpson/advent-of-node/blob/master/src/2024/day16.ts) |
| [Day17](https://adventofcode.com/2024/day/17) | [Day17](https://github.com/kevansimpson/advent-of-code/blob/main/src/main/java/org/base/advent/code2024/Day17.java) | [Day17](https://github.com/kevansimpson/advent-of-kotlin/blob/main/src/main/kotlin/org/base/advent/k2024/Day17.kt) | [Day17](https://github.com/kevansimpson/advent-of-node/blob/master/src/2024/day17.ts) |

[Back to top](#comparisons)
