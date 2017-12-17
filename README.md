# Equijoin-Map-Reduce

**A map-reduce program to perform equijoin.**

1. The code is in Java (use Java 1.8.x) using HadoopFramework (use Hadoop 2.7.x).

2. The code would take two inputs, one would be the HDFS location of the file on which the equijoin is performed and other would be the HDFS location of the file, where the output is stored.

**Format of the Input File: -**
  Table1Name, Table1JoinColumn, Table1Other Column1, Table1OtherColumn2, ……..
  Table2Name, Table2JoinColumn, Table2Other Column1, Table2OtherColumn2, ……...

**Format of the Output File: -**

  If Table1JoinColumn value is equal to Table2JoinColumn value, simply append both line side by side for Output. If 
  Table1JoinColumn value does not match any value of Table2JoinColumn, simply remove them for the output file. No duplicate     joins should be present in output file.

**Note: -**
  Table1JoinColumn and Table2JoinColumn would both be Integer or Real or Double or Float, basically Numeric.

**Example Input : -**
  
  R, 2, Don, Larson, Newark, 555-3221
  S, 1, 33000, 10000, part1
  S, 2, 18000, 2000, part1
  R, 3, Sal, Maglite, Nutley, 555-6905
  S, 3, 24000, 5000, part1
  S, 4, 22000, 7000, part1
  R, 4, Bob, Turley, Passaic, 555-8908

**Example Output: -**

  R, 2, Don, Larson, Newark, 555-3221, S, 2, 18000, 2000, part1
  R, 3, Sal, Maglite, Nutley, 555-6905, S, 3, 24000, 5000, part1
  S, 4, 22000, 7000, part1, R, 4, Bob, Turley, Passaic, 555-8908

**Another correct answer is:**
  
  R, 2, Don, Larson, Newark, 555-3221, S, 2, 18000, 2000, part1
  R, 3, Sal, Maglite, Nutley, 555-6905, S, 3, 24000, 5000, part1
  R, 4, Bob, Turley, Passaic, 555-8908, S, 4, 22000, 7000, part1

Ordering of S and R don't matter in the ouput.

You cannot assume that the table are R and S all the time. They can be other two tables. Number of tables
in the input are exactly 2.
