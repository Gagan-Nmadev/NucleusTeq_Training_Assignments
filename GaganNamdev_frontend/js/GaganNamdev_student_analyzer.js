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
console.log(JSON.stringify(students, null, 2));

