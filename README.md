
## Group Members -
Ratna Prakirana - 3663-9969 </br>
Bharat Bansal -  2591-1118</br>

# Sequence Alignments

## Introduction

In this project the following are implemented <br />
1.  Simulator for sequence generator<br />
2.  Simulator for sequence partitioning<br />
3.  Sequence assembler<br />

## Inputs for Program 1 
This program will get the following as input:<br />

1.  integer: n = sequence length <br />
2.  four integers: a c g t = fraction of letters A, C, G, and T<br />
3.  integer: k = number of sequences<br />
4.  real number: p = mutation probability in [0:1] interval<br />
5.  string: output file name<br />

Sample input: 10000 25 25 25 25 10 0.005 outputfile <br />

## Output for Program 1
The  program  will  output k nucleotide  sequence  in Fasta format.The  first  sequence will have n letters.
The next k−1 sequences will be a copy of the first sequence, but each letter will be mutated with replace or delete with probability
p.  The output file will contain all k sequences in Fasta format.
<br />
Example - <br />
>seq0 <br />
CTATAGCACCTTAGACGGCGGGGCGATAAATTTAGATAAGTGTGTGGAAGAAACCGCGAGGACAGA <br />
>seq1 <br />
CTATAGCCCTTAGACGGCGGGCCGATAATTTAGATAAGTGTATTGAAGAAACCGCGA
</br>

## Inputs for Program 2
 
This program will get the following as input:<br />
1.  string: input file name (the input filename for this program is the output file of the 1st program)<br />
2.  integer: x = minimum fragment length<br />
3.  integer: y = maximum fragment length ( y ≥ x )<br />
4.  string:  output file name<br />

Sample input: inputfile 100 150 outputfile <br />  

## Output for Program 2
The program will read the sequences in the input file, which is in Fasta format.
This input file is the output of the first program above.  It will then partition each sequence
into smaller fragments. The length of each fragment is a random number in the range [x:y].
The output file will contain all sequences in Fasta format.
<br />
Example - <br />
>seq0 <br />
CTATAGCACCTTAGACGGCGGGGCGATAAA <br />
>seq1 <br />
TTTAGATAAGTGTGTGGAAGAAACCGCGAGGA <br />
>seq2 <br />
CAGATTAATCCGATCAAGACTGTAGTCGTT <br />
>seq3 <br />
CTATAGCCCTTAGACGGCGGGCCGATAATTTAGA <br />
</br>

## Inputs for Program 3
 
This program will get the following as input:<br />
1.  string:  input file name <br />
2.  integer: s = score for match (positive integer) <br />
3.  integer: r = penalty for replace (negative integer) <br />
4.  integer: d = penalty for delete/insert (negative integer) <br />
5.  string:  output file name <br />

Sample input: inputfile 1 -1 -3 outputfile <br />  

## Output for Program 3

The program will read the sequences in the input file, which is in Fasta format.
This input file is the output of the second program above.  It will then combine fragments
using a greedy strategy - dovetail alignment.
The output file will contain all sequences in Fasta format.
<br />
Example - <br />
>seq0 <br />
CTATAGCACCTTAGACGGCGGGGCGATAAATGTGGAAGAAACCGCGAGGACTGTAGTCGTT <br />

</br>

## Execution

> javac *.java <br />
>java Hw1_1 10000 25 25 25 25 10 0.005 output1 <br />
>java Hw1_2 output1 100 150 output2 <br />
>java Hw1_3 output2 100 150 output3 <br />
#NOTE - The program at large numbers, runs for approx. 3 to 5 mins.