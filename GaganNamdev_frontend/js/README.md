# Hello.....
# Student Performance Analyzer

## About
This is a simple JavaScript console-based project.  
In this project, I worked with student data and performed different calculations like total marks, average marks, subject-wise analysis, and grade calculation.

The program runs completely in the console using JavaScript.
# Student Data Structure

This block defines an array of student objects. Each student contains:

name
marks (array of subjects with scores)
attendance

This structured data is used for all further calculations.
### code
![Data Structure](Screenshots/datastructure1.png)
![Data Structure](Screenshots/datastructure2.png)


### Output Screenshots
![Data Structure](Screenshots/outputqus1.png)

# Calculate Total Marks

This function calculates the total marks of a student by looping through all subjects and summing their scores.
### code
![Total Marks](Screenshots/2Calculate_total_marks2.png)
### output
![Total Marks](Screenshots/output2.png)

# Calculate Average Marks

This function calculates the average marks of a student.

It uses the total marks function
Divides total marks by number of subjects
Returns the average score of the student.
### code
![Average](Screenshots/avg3.png)
### output
![Average](Screenshots/avgoutput3.png)

# Subject-wise Highest Score

This block finds the highest scorer in each subject.

Loops through each subject
Compares scores of all students
Stores the highest score and student name
Outputs topper for each subject.
### Topper Output
![H Score](Screenshots/highest_score4.png)

### output
![H Score](Screenshots/highest_scoreoutput4.png)

# Subject-wise Average Score

This block calculates the average score for each subject.

Adds marks of all students for a subject
Divides by total number of students
Gives overall class performance per subject.
### code 
![Average](Screenshots/Subject-wise_average_score5.png)
### output
![Average](Screenshots/Find_class_topper6(2).png)

# Class Topper

This block identifies the overall class topper.
Calculates total marks of each student
Compares and finds highest total
Outputs student with maximum marks.
### code
![Topper](Screenshots/Find_class_topper6.png)
### output
![Topper](Screenshots/Find_class_topper6(3).png)

# Grade Calculation

This function assigns grades based on performance.

Conditions:

Fail if any subject ≤ 40
Fail if attendance < 75
Otherwise:
A (≥ 85)
B (≥ 70)
C (≥ 50)
Fail (< 50)

Returns final grade of each student.
### Grade code
![Grade](Screenshots/Grade_calculation7.png)
### Grade Output
![Grade](Screenshots/Grade_calculation7(2).png)


## My Learning
While building this project, I understood how to work with arrays, objects, loops, and conditions.  
I also learned how to structure logic step by step.