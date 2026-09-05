# 🛗 Elevator Navigation

A Java algorithmic exercise that models a simple **elevator navigation problem** and attempts to find the minimum number of jumps required to reach a target floor.

The program works with a set of floors and recursively explores movement in two directions:

* Moving **up** by a fixed number of floors
* Moving **down** by a fixed number of floors

In addition to calculating the minimum number of jumps, the program records and prints the route used to reach the destination.

---

# 📌 Overview

The project represents an elevator moving between numbered floors.

The available floors are:

```java
static int[] Floors = {
    0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
};
```

The current example starts at:

```text
Start Floor = 2
```

and attempts to reach:

```text
Target Floor = 10
```

The elevator can move:

```text
Up   = 2 floors
Down = 2 floors
```

The main call is:

```java
ReachB(Floors, r, 10, 2, 2, 2);
```

which can be interpreted as:

```text
Target = 10
Start  = 2
Up     = 2
Down   = 2
```

---

# 🎯 Problem Definition

The basic problem can be described as:

> Given a starting floor, a target floor, and fixed upward/downward movement sizes, find a route to the target using the minimum number of moves.

For the current input:

```text
Start  = 2
Target = 10
Up     = 2
Down   = 2
```

a direct valid route is:

```text
2 → 4 → 6 → 8 → 10
```

which requires:

```text
4 jumps
```

---

# 🧠 Algorithm

The main algorithm is implemented in:

```java
ReachB()
```

with the following signature:

```java
private static int ReachB(
    int[] Floors,
    int[] r,
    int B,
    int A,
    int u,
    int d
)
```

Where:

| Parameter | Meaning                                         |
| --------- | ----------------------------------------------- |
| `Floors`  | Available floor numbers                         |
| `r`       | Array used to store navigation/cost information |
| `B`       | Target floor                                    |
| `A`       | Current floor                                   |
| `u`       | Upward movement                                 |
| `d`       | Downward movement                               |

The algorithm recursively explores:

```text
A + u
```

and:

```text
A - d
```

until it either reaches the target or determines that a route is not available.

---

# 🔄 Navigation Strategy

At each floor, the algorithm considers two possible movements.

```text
                Current Floor
                     │
          ┌──────────┴──────────┐
          ▼                     ▼
       Move Up               Move Down
       A + u                  A - d
          │                     │
          ▼                     ▼
     Recursive Search      Recursive Search
          │                     │
          └──────────┬──────────┘
                     ▼
               Compare Results
                     │
                     ▼
                Choose Smaller
```

This is the central idea behind the implementation.

---

# 🏁 Reaching the Destination

The most important base case is:

```java
if (A == B)
```

When the current floor equals the target floor, the destination has been reached.

The program then:

* Marks the destination in `r`
* Stores the target in the route array `s`
* Increments the navigation index
* Returns `0`

Returning `0` indicates that no additional jumps are required once the destination has been reached.

---

# ⬆️ Moving Up

The upward recursive branch is:

```java
int p = ReachB(
    Floors,
    r,
    B,
    A + u,
    u,
    d
);
```

The next floor is therefore:

```text
A + u
```

For example, with:

```text
A = 4
u = 2
```

the next floor becomes:

```text
6
```

---

# ⬇️ Moving Down

The downward recursive branch is:

```java
int q = ReachB(
    Floors,
    r,
    B,
    A - d,
    u,
    d
);
```

The next floor is therefore:

```text
A - d
```

For example:

```text
A = 6
d = 2
```

results in:

```text
4
```

---

# 🔎 Choosing the Better Route

After recursively evaluating both directions:

```java
if(p < q)
```

the algorithm selects the smaller result.

If the upward route is considered better:

```java
r[A] = 1 + p;
```

Otherwise:

```java
r[A] = 1 + q;
```

The `+1` represents the current movement.

Conceptually:

```text
Cost of current move
        +
Cost of remaining route
        =
Total cost
```

---

# 🗺️ Route Tracking

The project uses:

```java
static int[] s = new int[40];
```

to store the floors visited during the recursive navigation.

During the recursive process, the current floor is stored:

```java
s[navigate] = A;
navigate++;
```

Once the search is finished, the program reverses the array:

```java
s = Reverse(s);
```

This is necessary because recursive calls reach deeper states before returning to earlier states.

The route is then printed in the intended order.

---

# 🔁 Reversing the Route

The helper method:

```java
Reverse()
```

creates another array and copies the elements in reverse order.

Conceptually:

```text
Recursive storage:

10
8
6
4
2

        ↓ Reverse

2
4
6
8
10
```

This makes the final output easier to interpret as a route from the starting floor to the destination.

---

# 📊 Cost Tracking

The array:

```java
static int[] r = new int[11];
```

is used to store navigation-related values for the floors.

The main method iterates through this array:

```java
for (int i = 0; i < r.length; i++) {
    if(r[i] > 0 &&
       r[i] != 0 &&
       r[i] != Integer.MAX_VALUE) {

        x = x + r[i];
    }
}
```

The resulting value is printed as:

```text
min jumps : ...
```

The implementation therefore uses `r` as part of its mechanism for tracking calculated navigation costs and unreachable states.

---

# 🚫 Handling Unreachable States

The implementation uses:

```java
Integer.MAX_VALUE
```

to represent an invalid or unreachable state.

For example, when the current position passes beyond the target:

```java
if(A > B)
```

the corresponding state is marked as:

```java
r[A-u] = Integer.MAX_VALUE;
```

Similarly, negative positions are handled through:

```java
if(A < 0)
```

and marked as unreachable.

This provides a simple sentinel value for states that should not contribute to the minimum route.

---

# 🧩 Special Case: Equal Movement

The implementation contains a separate method:

```java
ReachEqual()
```

which is called when:

```java
u == d
```

The current program uses:

```text
u = 2
d = 2
```

so this branch is used by the provided example.

The method handles the equal upward/downward movement case and recursively continues the navigation.

---

# 🔄 Complete Algorithm Flow

```text
                    Start
                      │
                      ▼
                Current Floor
                      │
                      ▼
              Is A equal to B?
                 /          \
               Yes           No
                │             │
                ▼             ▼
             Return 0     Check Bounds
                              │
                    ┌─────────┴─────────┐
                    │                   │
                 Invalid             Valid
                    │                   │
                    ▼                   ▼
              Mark Unreachable    Explore Two Moves
                                        │
                             ┌──────────┴──────────┐
                             ▼                     ▼
                          A + u                 A - d
                             │                     │
                             ▼                     ▼
                        Recursive             Recursive
                         Search                Search
                             │                     │
                             └──────────┬──────────┘
                                        ▼
                                  Compare Costs
                                        │
                                        ▼
                                  Choose Minimum
                                        │
                                        ▼
                                  Store Route
                                        │
                                        ▼
                                     Return
```

---

# 🧮 Example

The current program starts at:

```text
2
```

and wants to reach:

```text
10
```

with:

```text
Up   = 2
Down = 2
```

One valid route is:

```text
2 → 4 → 6 → 8 → 10
```

The number of jumps is:

```text
2 → 4     = 1
4 → 6     = 2
6 → 8     = 3
8 → 10    = 4
```

Therefore:

```text
Minimum jumps = 4
```

The corresponding route is:

```text
2 4 6 8 10
```

---

# 💻 Main Program

The current `main()` method initializes the navigation problem:

```java
static int[] Floors = {
    0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
};

static int[] r = new int[11];

static int[] s = new int[40];
```

and executes:

```java
int q = ReachB(
    Floors,
    r,
    10,
    2,
    2,
    2
);
```

After the calculation, it:

1. Prints the contents of `r`.
2. Calculates the reported jump count.
3. Checks whether a route was found.
4. Reverses the stored route.
5. Prints the route.

---

# 🛠️ Technology Stack

| Technology            | Usage                                 |
| --------------------- | ------------------------------------- |
| **Java**              | Programming language                  |
| **1D Arrays**         | Floors, route and navigation state    |
| **Recursion**         | Exploring possible elevator movements |
| **Conditional Logic** | Boundary and state handling           |
| **Integer.MAX_VALUE** | Representing unreachable states       |
| **Console Output**    | Displaying results                    |

---

# 📚 Concepts Demonstrated

This project is mainly an algorithmic exercise.

### Java Concepts

* Arrays
* Static methods
* Recursion
* Conditional statements
* `for` loops
* `Integer.MAX_VALUE`
* Console output

### Algorithmic Concepts

* Recursive search
* Minimum-cost route selection
* State tracking
* Boundary checking
* Route reconstruction
* Handling unreachable states

---

# ⏱️ Complexity

Let:

```text
n = number of reachable floor states
```

and assume that every state can recursively explore both:

```text
A + u
```

and:

```text
A - d
```

The current implementation does **not use a conventional memoization table for the recursive return values**.

Therefore, in the general case, the recursive search can revisit states and may have **exponential behavior**.

A simplified worst-case characterization is:

```text
O(2^n)
```

depending on the reachable state space and movement parameters.

The implementation also uses fixed-size arrays:

```java
r = new int[11];
s = new int[40];
```

so the current example has bounded storage, but these fixed sizes limit the scalability of the implementation.

---

# ⚠️ Implementation Notes

This repository is an educational exercise rather than a production elevator-control system.

Despite the repository name `elevator`, the implementation does **not** simulate a real elevator with:

* Multiple elevators
* Passengers
* Doors
* Floor buttons
* Sensors
* Scheduling
* GUI
* Real-time movement

Instead, it models a simplified mathematical navigation problem where an elevator can move a fixed number of floors upward or downward.

The current implementation also contains:

```java
import java.util.Arrays;
import java.util.Collections;
```

which are not required by the current algorithm.

---

# 🔧 Possible Improvements

The project could be extended considerably.

### Algorithm

* [ ] Implement proper memoization
* [ ] Use a dedicated DP array for minimum jumps
* [ ] Avoid revisiting the same floor state
* [ ] Implement Breadth-First Search (BFS)
* [ ] Compare recursive search with BFS
* [ ] Improve unreachable-state handling
* [ ] Separate cost calculation from route reconstruction

### Input

* [ ] Accept the starting floor from the user
* [ ] Accept the destination floor
* [ ] Allow configurable upward movement
* [ ] Allow configurable downward movement
* [ ] Support arbitrary building sizes

### Code Quality

* [ ] Replace magic array sizes with dynamic allocation
* [ ] Use descriptive variable names instead of `A`, `B`, `u`, `d`, `p`, and `q`
* [ ] Remove unused imports
* [ ] Encapsulate the navigation logic inside a dedicated class
* [ ] Add unit tests
* [ ] Handle invalid input explicitly

### Simulation

A future version could evolve this exercise into a more realistic elevator simulator with:

```text
Floor Requests
      ↓
Elevator Scheduler
      ↓
Best Elevator
      ↓
Movement
      ↓
Destination
```

---

# 🧪 Suggested Test Cases

The algorithm can be tested with different starting and destination floors.

### Case 1 — Current Example

```text
Start: 2
Target: 10
Up: 2
Down: 2
```

Possible route:

```text
2 → 4 → 6 → 8 → 10
```

---

### Case 2 — Already at Destination

```text
Start: 10
Target: 10
```

Expected number of additional jumps:

```text
0
```

---

### Case 3 — Moving Down

For example:

```text
Start: 8
Target: 2
Up: 2
Down: 2
```

A valid route is:

```text
8 → 6 → 4 → 2
```

---

### Case 4 — Different Movement Sizes

For example:

```text
Start: 2
Target: 10
Up: 3
Down: 1
```

This introduces a different reachable-state structure and can be used to test the recursive search.

---

### Case 5 — Unreachable Destination

Choose movement values that make the target unreachable from the starting floor.

The algorithm should identify the absence of a valid route rather than treating an invalid state as a normal path.

---

# 📁 Project Structure

The repository has a compact Java/IntelliJ structure:

```text
elevator/
│
├── .idea/
│
├── src/
│   └── Main.java
│
├── Elevator.iml
│
└── README.md
```

The complete implementation is currently contained in:

```text
src/Main.java
```

The repository currently contains **7 commits**.

---

# 🚀 Getting Started

## Prerequisites

You need:

* Java JDK
* IntelliJ IDEA or another Java IDE

---

## Clone the Repository

```bash
git clone https://github.com/Sobhankhedry/elevator.git
```

Navigate into the project:

```bash
cd elevator
```

Open the project in IntelliJ IDEA.

Run:

```text
src/Main.java
```

The program will calculate the navigation result and print the route information to the console.

---

# 🎯 Learning Objectives

The project is useful for practicing:

* Recursive algorithms
* Search problems
* Minimum-cost decisions
* Array-based state tracking
* Route reconstruction
* Boundary checking
* Handling unreachable states
* Translating a real-world concept into an algorithmic problem

---

# 📌 Project Status

**Status:** Educational / Algorithmic Exercise

This project represents an elevator as a simplified floor-navigation problem.

Its primary purpose is to practice **recursive search, minimum-jump calculation, state tracking, and route reconstruction in Java**.

It should not be considered a complete elevator-control or elevator-simulation system.

---

# 👨‍💻 Author

**Sobhan Khedry**

Computer Engineering Graduate Student
Backend Development Enthusiast

GitHub: [@Sobhankhedry](https://github.com/Sobhankhedry)

---

# ⭐ Key Takeaways

The project reduces elevator navigation to a recursive minimum-jump problem:

```text
              Start Floor
                   │
                   ▼
             Current Floor
                   │
          ┌────────┴────────┐
          ▼                 ▼
       Move Up           Move Down
        A + u              A - d
          │                 │
          ▼                 ▼
       Recursive         Recursive
        Search             Search
          │                 │
          └────────┬────────┘
                   ▼
             Compare Costs
                   │
                   ▼
             Choose Minimum
                   │
                   ▼
              Store Route
                   │
                   ▼
            Reverse Route
                   │
                   ▼
              Final Path
```

For the current example:

```text
Start  = 2
Target = 10
Up     = 2
Down   = 2
```

the navigation can be represented as:

```text
2 → 4 → 6 → 8 → 10
```

requiring:

```text
4 jumps
```

The main educational value of the repository is understanding how **recursive search can be used to explore possible movements and select a minimum-cost route**.
