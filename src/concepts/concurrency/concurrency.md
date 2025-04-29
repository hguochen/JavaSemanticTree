# Concurrency

- in early computing, one CPU switches between programmes to achieve pseudo task parallelism.
- multithreading, multiple threads execute within a program, which is in one CPU.
- in conjunction, multiple CPUs can also execute multiple programs, in which each executes multiple threads

Why multithreading?
- better CPU utilization, especially when IO tasks are running
- higher application responsiveness

Multithreading Issues
- race conditions
- invisible writes
- congestion
- deadlock
- nested monitor lockout
- starvation
- slipped conditions
- missed signals