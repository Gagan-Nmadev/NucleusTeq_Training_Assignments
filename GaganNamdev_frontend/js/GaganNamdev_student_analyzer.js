// Student Performance Analyzer
//Creating student data structure

const students = [
    {
        name: "Lalit",
        marks: [
            { subject: "Math", score: 78 },
            { subject: "English", score: 82 },
            { subject: "Science", score: 74 },
            { subject: "History", score: 69 },
            { subject: "Computer", score: 88 }
        ],
        attendance: 82
    },
    {
        name: "Rahul",
        marks: [
            { subject: "Math", score: 90 },
            { subject: "English", score: 85 },
            { subject: "Science", score: 80 },
            { subject: "History", score: 76 },
            { subject: "Computer", score: 92 }
        ],
        attendance: 91
    },
    {
        name: "Aman",
        marks: [
            { subject: "Math", score: 60 },
            { subject: "English", score: 55 },
            { subject: "Science", score: 48 },
            { subject: "History", score: 52 },
            { subject: "Computer", score: 65 }
        ],
        attendance: 70
    },
    {
        name: "Riya",
        marks: [
            { subject: "Math", score: 88 },
            { subject: "English", score: 92 },
            { subject: "Science", score: 85 },
            { subject: "History", score: 80 },
            { subject: "Computer", score: 90 }
        ],
        attendance: 95
    },
    {
        name: "Karan",
        marks: [
            { subject: "Math", score: 35 },
            { subject: "English", score: 60 },
            { subject: "Science", score: 55 },
            { subject: "History", score: 58 },
            { subject: "Computer", score: 62 }
        ],
        attendance: 85
    }
];

// Step 2: Calculate total marks

function calculateTotalMarks(student) {
    let total = 0;

    for (let i = 0; i < student.marks.length; i++) {
        total = total + student.marks[i].score;
    }

    return total;
}
students.forEach(function(student) {
    let total = calculateTotalMarks(student);
    console.log(student.name + " Total Marks: " + total);
});

// Step 3: Calculate average marks

function calculateAverage(student) {
    let total = calculateTotalMarks(student); 
    let average = total / student.marks.length; 

    return average;
}

students.forEach(function(student) {
    let avg = calculateAverage(student);
    console.log(student.name + " Average: " + avg.toFixed(1));
});

// Step 4: Subject-wise highest score

let subjects = ["Math", "English", "Science", "History", "Computer"];

subjects.forEach(function(subject) {

    let highest = 0;
    let topperName = "";

    // one by one evry student check 
    students.forEach(function(student) {

        student.marks.forEach(function(m) {

            if (m.subject === subject && m.score > highest) {
                highest = m.score;
                topperName = student.name;
            }

        });

    });

    console.log("Highest in " + subject + ": " + topperName + " (" + highest + ")");
});

// Step 5: Subject-wise average score

subjects.forEach(function(subject) {

    let total = 0;

    // adding evry students marks
    students.forEach(function(student) {

        student.marks.forEach(function(m) {

            if (m.subject === subject) {
                total += m.score;
            }

        });

    });

    let avg = total / students.length;

    console.log("Average " + subject + " Score: " + avg);
});

// Step 6: Find class topper

let highestMarks = 0;
let topperName = "";

students.forEach(function(student) {

    let total = calculateTotalMarks(student);

    if (total > highestMarks) {
        highestMarks = total;
        topperName = student.name;
    }

});

console.log("Class Topper: " + topperName + " with " + highestMarks + " marks");