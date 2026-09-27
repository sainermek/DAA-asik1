Assignment 1: Divide-and-Conquer Algorithm Analysis



Student: Sain Yermek  

Group: SE-2503  

Course: Design and Analysis of Algorithms  



Overview



In this assignment, we implement and analyze 4 classic divide-and-conquer algorithms:

1\. MergeSort - stable sorting using an auxiliary buffer and an insertion sort cutoff for small arrays.

2\. QuickSort - randomized pivot selection with tail-call optimization to keep recursion depth optimal.

3\. Deterministic Select (Median-of-Medians) - worst-case linear selection using groups of 5.

4\. Closest Pair of Points - geometric algorithm using strip optimization.



Project Structure



assignment1-divide-and-conquer/

├── src/                 # Algorithm implementations \& main runner

├── tests/               # Correctness unit tests

├── docs/

│   ├── screenshots/     # Test results and console output screenshots

│   └── plots/           # Generated performance charts \& script

├── results/

│   └── results.csv      # Raw benchmark data

├── README.md

└── pom.xml



How to Run



Compile and run the demo/experiments from the project root using standard JDK:



mkdir out

javac -d out src/\*.java

java -cp out Main



Run correctness tests:



javac -d out src/\*.java tests/\*.java

java -cp out TestRunner



Generate performance plots:



cd docs/plots

pip install matplotlib

python3 generate\_plots.py



Experimental Results \& Plots



The experiments measure running time, recursion depth, and operation counts across different input sizes (n).



Screenshots



Check the docs/screenshots/ folder for:

\- Program execution output (Main)

\- Test runner results (TestRunner)

