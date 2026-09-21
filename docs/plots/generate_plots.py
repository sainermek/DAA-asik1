import csv
import matplotlib.pyplot as plt
from collections import defaultdict

rows = []
with open("../../results/results.csv") as f:
    reader = csv.DictReader(f)
    for row in reader:
        rows.append(row)

def group(algorithm, inputType):
    data = defaultdict(list)
    for r in rows:
        if r["algorithm"] == algorithm and r["inputType"] == inputType:
            data[int(r["n"])].append(r)
    sizes = sorted(data.keys())
    times = [int(data[n][0]["timeNanos"]) / 1_000_000 for n in sizes]
    depths = [int(data[n][0]["maxDepth"]) for n in sizes]
    return sizes, times, depths

plt.figure()
for algo in ["MergeSort", "QuickSort"]:
    sizes, times, depths = group(algo, "random")
    plt.plot(sizes, times, marker="o", label=algo)
plt.xlabel("n")
plt.ylabel("time, ms")
plt.title("Time vs n (random input)")
plt.legend()
plt.grid(True)
plt.savefig("time_vs_n.png")

plt.figure()
for algo in ["MergeSort", "QuickSort"]:
    sizes, times, depths = group(algo, "random")
    plt.plot(sizes, depths, marker="o", label=algo)
plt.xlabel("n")
plt.ylabel("max recursion depth")
plt.title("Recursion depth vs n (random input)")
plt.legend()
plt.grid(True)
plt.savefig("depth_vs_n.png")

print("Plots saved: time_vs_n.png, depth_vs_n.png")
